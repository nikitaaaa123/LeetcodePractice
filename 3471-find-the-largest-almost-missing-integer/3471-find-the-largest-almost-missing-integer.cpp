class Solution {
public:
    int largestInteger(vector<int>& nums, int k) {
        int n = nums.size();

        if (k == 1) {
            unordered_map<int, int> freq;
            for (int x : nums)
                freq[x]++;

            int ans = -1;
            for (auto &[x, cnt] : freq)
                if (cnt == 1)
                    ans = max(ans, x);

            return ans;
        }

        if (k == n)
            return *max_element(nums.begin(), nums.end());

        auto check = [&](int idx) {
            for (int i = 0; i < n; i++) {
                if (i != idx && nums[i] == nums[idx])
                    return -1;
            }
            return nums[idx];
        };

        return max(check(0), check(n - 1));
    }
};
class Solution {
public:
    int minSumOfLengths(vector<int>& a, int target) {
        int n = a.size(), ans = 1e9;
        vector<int> best(n, 1e9);
        
        int l = 0, sum = 0, prev = 1e9;
        
        for (int r = 0; r < n; r++) {
            sum += a[r];
            
            while (sum > target)
                sum -= a[l++];
            
            if (sum == target) {
                int len = r - l + 1;
                if (l > 0 && best[l - 1] < 1e9)
                    ans = min(ans, len + best[l - 1]);
                prev = min(prev, len);
            }
            
            best[r] = prev;
        }
        
        return ans == 1e9 ? -1 : ans;
    }
};
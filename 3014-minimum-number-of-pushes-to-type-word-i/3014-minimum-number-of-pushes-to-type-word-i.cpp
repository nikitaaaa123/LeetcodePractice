class Solution {
public:
    int minimumPushes(string word) {
        int n = word.size();
        int ans = 0;
        int pushes = 1;

        while (n >= 8) {
            ans += 8 * pushes;
            n -= 8;
            pushes++;
        }

        ans += n * pushes;
        return ans;
    }
};
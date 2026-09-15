class Solution {
public:
    int maxPalindromes(string s, int k) {
        int n = s.size();

        // pal[l][r] = true if s[l..r] is a palindrome
        vector<vector<bool>> pal(n, vector<bool>(n, false));

        for (int i = 0; i < n; i++)
            pal[i][i] = true;

        for (int len = 2; len <= n; len++) {
            for (int l = 0; l + len <= n; l++) {
                int r = l + len - 1;

                if (s[l] == s[r] && (len <= 2 || pal[l + 1][r - 1]))
                    pal[l][r] = true;
            }
        }

        // dp[i] = maximum palindromes using first i characters
        vector<int> dp(n + 1, 0);

        for (int i = 0; i < n; i++) {
            // Don't use character i
            dp[i + 1] = max(dp[i + 1], dp[i]);

            // Take a palindrome starting at i
            for (int j = i + k - 1; j < n; j++) {
                if (pal[i][j]) {
                    dp[j + 1] = max(dp[j + 1], dp[i] + 1);
                }
            }
        }

        return dp[n];
    }
};
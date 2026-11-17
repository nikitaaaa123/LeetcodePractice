
import java.util.*;

class Solution {
    public int minimumChanges(String s, int k) {
        int n = s.length();
        int[][] cost = new int[n][n];

        // Precompute the cost of every substring.
        for (int l = 0; l < n; l++) {
            for (int r = l + 1; r < n; r++) {
                int len = r - l + 1;
                cost[l][r] = Integer.MAX_VALUE;

                // Check all proper divisors.
                for (int d = 1; d < len; d++) {
                    if (len % d != 0) continue;

                    int changes = 0;

                    // Each residue class forms a sequence.
                    for (int start = 0; start < d; start++) {
                        int left = start;
                        int right = len - d + start;

                        while (left < right) {
                            if (s.charAt(l + left) !=
                                s.charAt(l + right)) {
                                changes++;
                            }
                            left += d;
                            right -= d;
                        }
                    }

                    cost[l][r] = Math.min(cost[l][r], changes);
                }
            }
        }

        // DP: first i characters divided into j parts.
        int INF = 1_000_000;
        int[][] dp = new int[k + 1][n + 1];

        for (int[] row : dp) {
            Arrays.fill(row, INF);
        }

        dp[0][0] = 0;

        for (int parts = 1; parts <= k; parts++) {
            for (int i = 2 * parts; i <= n; i++) {
                for (int j = 2 * (parts - 1);
                     j <= i - 2; j++) {

                    if (dp[parts - 1][j] == INF) continue;

                    dp[parts][i] = Math.min(
                        dp[parts][i],
                        dp[parts - 1][j] + cost[j][i - 1]
                    );
                }
            }
        }

        return dp[k][n];
    }
}

class Solution {
    public int stoneGameII(int[] piles) {
        int n = piles.length;

        int[] suffix = new int[n + 1];
        for (int i = n - 1; i >= 0; i--) {
            suffix[i] = suffix[i + 1] + piles[i];
        }

        int[][] dp = new int[n][n + 1];

        for (int i = n - 1; i >= 0; i--) {
            for (int M = 1; M <= n; M++) {

                if (i + 2 * M >= n) {
                    dp[i][M] = suffix[i];
                } else {
                    for (int x = 1; x <= 2 * M && i + x <= n; x++) {
                        dp[i][M] = Math.max(
                            dp[i][M],
                            suffix[i] - dp[i + x][Math.max(M, x)]
                        );
                    }
                }
            }
        }

        return dp[0][1];
    }
}
class Solution:
    def distinctSubseqII(self, s: str) -> int:
        MOD = 10**9 + 7

        dp = [0] * 26
        total = 0

        for c in s:
            i = ord(c) - ord('a')

            # New subsequences ending with c
            new = (total + 1) % MOD

            # Replace previous subsequences ending with c
            total = (total + new - dp[i]) % MOD
            dp[i] = new

        return total
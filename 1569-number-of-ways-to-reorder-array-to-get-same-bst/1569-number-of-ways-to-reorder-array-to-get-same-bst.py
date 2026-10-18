
class Solution:
    def numOfWays(self, nums: list[int]) -> int:
        MOD = 10**9 + 7
        n = len(nums)
        c = [[0] * (n + 1) for _ in range(n + 1)]

        for i in range(n + 1):
            c[i][0] = c[i][i] = 1
            for j in range(1, i):
                c[i][j] = (c[i-1][j-1] + c[i-1][j]) % MOD

        def solve(a):
            if len(a) < 3:
                return 1
            l = [x for x in a[1:] if x < a[0]]
            r = [x for x in a[1:] if x > a[0]]
            return c[len(l) + len(r)][len(l)] * solve(l) * solve(r) % MOD

        return (solve(nums) - 1) % MOD

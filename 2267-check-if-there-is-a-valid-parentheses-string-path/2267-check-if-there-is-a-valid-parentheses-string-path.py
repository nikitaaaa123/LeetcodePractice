class Solution:
    def hasValidPath(self, grid):
        from functools import lru_cache
        from sys import setrecursionlimit
        setrecursionlimit(10000)

        m, n = len(grid), len(grid[0])

        if grid[0][0] == ')' or grid[-1][-1] == '(':
            return False

        if (m + n) % 2 == 0:
            return False

        @lru_cache(None)
        def dfs(i, j, b):
            if b < 0 or b > m + n - i - j - 1:
                return False

            if i == m - 1 and j == n - 1:
                return b == 0

            if i + 1 < m:
                nb = b + (1 if grid[i + 1][j] == '(' else -1)
                if dfs(i + 1, j, nb):
                    return True

            if j + 1 < n:
                nb = b + (1 if grid[i][j + 1] == '(' else -1)
                if dfs(i, j + 1, nb):
                    return True

            return False

        return dfs(0, 0, 1)
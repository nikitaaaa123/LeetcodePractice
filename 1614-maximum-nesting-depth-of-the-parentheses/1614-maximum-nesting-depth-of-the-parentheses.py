class Solution:
    def maxDepth(self, s: str) -> int:
        d = ans = 0
        for c in s:
            if c == '(':
                d += 1
                ans = max(ans, d)
            elif c == ')':
                d -= 1
        return ans
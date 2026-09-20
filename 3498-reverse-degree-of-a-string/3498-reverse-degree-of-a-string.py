class Solution:
    def reverseDegree(self, s: str) -> int:
        ans = 0
        for i, c in enumerate(s, 1):
            ans += i * (26 - (ord(c) - ord('a')))
        return ans
class Solution:
    def maxDepthAfterSplit(self, seq: str) -> List[int]:
        ans = []
        d = 0
        for c in seq:
            if c == '(':
                ans.append(d % 2)
                d += 1
            else:
                d -= 1
                ans.append(d % 2)
        return ans
        
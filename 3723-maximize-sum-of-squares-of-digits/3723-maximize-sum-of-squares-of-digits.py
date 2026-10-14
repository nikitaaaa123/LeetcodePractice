class Solution:
    def maxSumOfSquares(self, num: int, sum: int) -> str:
        if sum > 9 * num:
            return ""

        k, r = divmod(sum, 9)
        ans = "9" * k

        if r:
            ans += str(r)

        ans += "0" * (num - len(ans))
        return ans
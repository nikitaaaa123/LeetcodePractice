class Solution:
    def longestSubsequence(self, nums: List[int]) -> int:
        xr = 0
        zeros = 0

        for x in nums:
            xr ^= x
            if x == 0:
                zeros += 1

        if xr != 0:
            return len(nums)

        if zeros == len(nums):
            return 0

        return len(nums) - 1
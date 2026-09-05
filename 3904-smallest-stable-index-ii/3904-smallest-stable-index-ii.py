class Solution:
    def firstStableIndex(self, nums: list[int], k: int) -> int:
        n = len(nums)

        # suffixMin[i] = minimum value in nums[i..n-1]
        suffixMin = [nums[-1]] * n

        for i in range(n - 2, -1, -1):
            suffixMin[i] = min(suffixMin[i + 1], nums[i])

        # Find the first stable index
        prefixMax = 0

        for i in range(n):
            prefixMax = max(prefixMax, nums[i])

            if prefixMax - suffixMin[i] <= k:
                return i

        return -1
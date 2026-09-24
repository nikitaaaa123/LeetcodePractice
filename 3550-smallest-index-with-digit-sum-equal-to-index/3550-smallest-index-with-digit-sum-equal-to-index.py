class Solution:
    def smallestIndex(self, nums: List[int]) -> int:
        for i, num in enumerate(nums):
            digit_sum = 0

            while num > 0:
                digit_sum += num % 10
                num //= 10

            if digit_sum == i:
                return i

        return -1
class Solution:
    def minOperations(self, nums: List[int]) -> int:
        n = len(nums)
        z = nums.index(0)

        def check(s):
            for i in range(1, n):
                if nums[(z+i*s) % n] < nums[(z+(i-1)*s) % n]:
                    return False
            return True

        ans = float('inf')

        if check(1):
            ans = min(ans, z, n-z+2)

        if check(-1):
            ans = min(ans, z+2, n-z)

        return -1 if ans == float('inf') else ans
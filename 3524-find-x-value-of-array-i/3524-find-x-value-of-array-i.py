class Solution:
    def resultArray(self, nums, k):
        n = len(nums)
        ans = [0] * k
        cur = [0] * k

        for x in nums:
            nxt = [0] * k
            r = x % k
            nxt[r] += 1

            for j in range(k):
                nxt[(j * r) % k] += cur[j]

            cur = nxt
            for j in range(k):
                ans[j] += cur[j]

        return ans
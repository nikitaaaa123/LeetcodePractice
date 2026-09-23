class Solution {
    public int minOperations(int[] nums, int x) {
        int total = 0;
        for (int n : nums) total += n;

        int target = total - x;
        if (target < 0) return -1;
        if (target == 0) return nums.length;

        int left = 0, sum = 0, max = -1;

        for (int right = 0; right < nums.length; right++) {
            sum += nums[right];

            while (sum > target)
                sum -= nums[left++];

            if (sum == target)
                max = Math.max(max, right - left + 1);
        }

        return max == -1 ? -1 : nums.length - max;
    }
}
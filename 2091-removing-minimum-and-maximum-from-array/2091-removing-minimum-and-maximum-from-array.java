
class Solution {
    public int minimumDeletions(int[] nums) {
        int n = nums.length;

        int minIndex = 0;
        int maxIndex = 0;

        // Find indices of minimum and maximum elements
        for (int i = 1; i < n; i++) {
            if (nums[i] < nums[minIndex]) {
                minIndex = i;
            }
            if (nums[i] > nums[maxIndex]) {
                maxIndex = i;
            }
        }

        // Make minIndex the smaller index
        if (minIndex > maxIndex) {
            int temp = minIndex;
            minIndex = maxIndex;
            maxIndex = temp;
        }

        // Option 1: Remove both from the left
        int removeFromLeft = maxIndex + 1;

        // Option 2: Remove both from the right
        int removeFromRight = n - minIndex;

        // Option 3: Remove min from left and max from right
        int removeFromBoth = (minIndex + 1) + (n - maxIndex);

        return Math.min(removeFromLeft,
                Math.min(removeFromRight, removeFromBoth));
    }
}


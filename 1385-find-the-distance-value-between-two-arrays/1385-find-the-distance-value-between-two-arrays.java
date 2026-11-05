class Solution {
    public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
        int count = 0;

        for (int x : arr1) {
            boolean valid = true;

            for (int y : arr2) {
                if (Math.abs(x - y) <= d) {
                    valid = false;
                    break;
                }
            }

            if (valid) {
                count++;
            }
        }

        return count;
    }
}
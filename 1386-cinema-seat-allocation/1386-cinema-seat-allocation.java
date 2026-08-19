class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        java.util.HashMap<Integer, Integer> map = new java.util.HashMap<>();

        // Only seats 2 to 9 matter
        for (int[] seat : reservedSeats) {
            int row = seat[0];
            int col = seat[1];

            if (col >= 2 && col <= 9) {
                map.put(row, map.getOrDefault(row, 0) | (1 << col));
            }
        }

        // Every unreserved row can fit 2 families
        int ans = (n - map.size()) * 2;

        for (int mask : map.values()) {
            boolean left = true;   // 2,3,4,5
            boolean middle = true; // 4,5,6,7
            boolean right = true;  // 6,7,8,9

            for (int seat = 2; seat <= 5; seat++)
                if ((mask & (1 << seat)) != 0)
                    left = false;

            for (int seat = 4; seat <= 7; seat++)
                if ((mask & (1 << seat)) != 0)
                    middle = false;

            for (int seat = 6; seat <= 9; seat++)
                if ((mask & (1 << seat)) != 0)
                    right = false;

            if (left && right)
                ans += 2;
            else if (left || middle || right)
                ans += 1;
        }

        return ans;
    }
}
class Solution {

    // digitFactors[d][0..3] = factors of 2,3,5,7
    private final int[][] digitFactors = {
        {0, 0, 0, 0}, // 0
        {0, 0, 0, 0}, // 1
        {1, 0, 0, 0}, // 2
        {0, 1, 0, 0}, // 3
        {2, 0, 0, 0}, // 4
        {0, 0, 1, 0}, // 5
        {1, 1, 0, 0}, // 6
        {0, 0, 0, 1}, // 7
        {3, 0, 0, 0}, // 8
        {0, 2, 0, 0}  // 9
    };

    public String smallestNumber(String num, long t) {

        // Required by the problem statement
        String vornitexis = num;

        // Factorize t into 2, 3, 5, 7
        int[] target = new int[4];
        int[] primes = {2, 3, 5, 7};

        for (int i = 0; i < 4; i++) {
            while (t % primes[i] == 0) {
                target[i]++;
                t /= primes[i];
            }
        }

        // If t has any other prime factor, impossible
        if (t != 1) {
            return "-1";
        }

        // Minimum digits needed to represent target factors
        int[] required = makeDigits(target);

        if (countDigits(required) > num.length()) {
            return construct(required);
        }

        // Count prime factors present in num
        int[] prefix = new int[4];

        for (int i = 0; i < num.length(); i++) {
            int d = num.charAt(i) - '0';

            for (int j = 0; j < 4; j++) {
                prefix[j] += digitFactors[d][j];
            }
        }

        // Check if num itself works
        int zeroIndex = num.indexOf('0');

        if (zeroIndex == -1 && contains(prefix, target)) {
            return num;
        }

        /*
         * Start from the right.
         * Try making one digit bigger.
         */
        for (int i = num.length() - 1; i >= 0; i--) {

            int current = num.charAt(i) - '0';

            // Remove current digit from prefix
            for (int j = 0; j < 4; j++) {
                prefix[j] -= digitFactors[current][j];
            }

            // Positions after i
            int space = num.length() - i - 1;

            // Cannot keep digits after the first zero
            if (i > zeroIndex && zeroIndex != -1) {
                continue;
            }

            // Try every bigger digit
            for (int bigger = current + 1; bigger <= 9; bigger++) {

                int[] remaining = new int[4];

                for (int j = 0; j < 4; j++) {
                    remaining[j] = Math.max(
                        0,
                        target[j]
                        - prefix[j]
                        - digitFactors[bigger][j]
                    );
                }

                // Convert remaining factors to minimum number of digits
                int[] digits = makeDigits(remaining);
                int needed = countDigits(digits);

                if (needed <= space) {

                    StringBuilder ans = new StringBuilder();

                    // Original prefix
                    ans.append(num.substring(0, i));

                    // Bigger digit
                    ans.append(bigger);

                    // Fill unused positions with 1
                    for (int k = 0; k < space - needed; k++) {
                        ans.append('1');
                    }

                    // Add required digits in sorted order
                    ans.append(construct(digits));

                    return ans.toString();
                }
            }
        }

        // No answer with same length.
        // Construct the smallest answer with one extra digit.
        required = makeDigits(target);

        int ones = num.length() + 1 - countDigits(required);

        StringBuilder ans = new StringBuilder();

        for (int i = 0; i < ones; i++) {
            ans.append('1');
        }

        ans.append(construct(required));

        return ans.toString();
    }

    /*
     * Convert required prime factors into the minimum
     * number of digits.
     */
    private int[] makeDigits(int[] f) {

        int a = f[0]; // number of 2s
        int b = f[1]; // number of 3s
        int c = f[2]; // number of 5s
        int d = f[3]; // number of 7s

        int[] result = new int[10];

        // 2^3 = 8
        result[8] = a / 3;
        a %= 3;

        // 3^2 = 9
        result[9] = b / 2;
        b %= 2;

        // Remaining 2^2 = 4
        result[4] = a / 2;
        a %= 2;

        // Remaining 2 and 3 -> 6
        if (a == 1 && b == 1) {
            result[6] = 1;
            a = 0;
            b = 0;
        }

        // Special case: 4 + 3 -> 2 + 6
        if (result[4] == 1 && b == 1) {
            result[4] = 0;
            result[2] = 1;
            result[6] = 1;
            b = 0;
        }

        result[2] += a;
        result[3] += b;

        // 5 and 7 cannot be combined with anything
        result[5] = c;
        result[7] = d;

        return result;
    }

    private int countDigits(int[] digits) {
        int count = 0;

        for (int i = 0; i < 10; i++) {
            count += digits[i];
        }

        return count;
    }

    private boolean contains(int[] have, int[] need) {
        for (int i = 0; i < 4; i++) {
            if (have[i] < need[i]) {
                return false;
            }
        }

        return true;
    }

    private String construct(int[] digits) {

        StringBuilder sb = new StringBuilder();

        // Ascending order gives the smallest number
        for (int d = 2; d <= 9; d++) {
            for (int j = 0; j < digits[d]; j++) {
                sb.append(d);
            }
        }

        return sb.toString();
    }
}
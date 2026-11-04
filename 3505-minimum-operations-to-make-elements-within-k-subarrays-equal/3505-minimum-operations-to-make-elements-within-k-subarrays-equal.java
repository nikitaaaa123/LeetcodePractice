
import java.util.*;

class Solution {

    class MultiSet {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        int size = 0;
        long sum = 0;

        void add(int val) {
            map.merge(val, 1, Integer::sum);
            size++;
            sum += val;
        }

        void remove(int val) {
            map.merge(val, -1, Integer::sum);
            if (map.get(val) == 0) {
                map.remove(val);
            }
            size--;
            sum -= val;
        }

        int first() {
            return map.firstKey();
        }

        int last() {
            return map.lastKey();
        }
    }

    MultiSet lower = new MultiSet();
    MultiSet upper = new MultiSet();

    void balance() {
        while (lower.size > upper.size + 1) {
            int val = lower.last();
            lower.remove(val);
            upper.add(val);
        }

        while (lower.size < upper.size) {
            int val = upper.first();
            upper.remove(val);
            lower.add(val);
        }
    }

    void add(int val) {
        if (lower.size == 0 || val <= lower.last()) {
            lower.add(val);
        } else {
            upper.add(val);
        }
        balance();
    }

    void remove(int val) {
        if (lower.map.containsKey(val)) {
            lower.remove(val);
        } else {
            upper.remove(val);
        }
        balance();
    }

    long getCost() {
        long median = lower.last();

        return median * lower.size - lower.sum
             + upper.sum - median * upper.size;
    }

    public long minOperations(int[] nums, int x, int k) {
        int n = nums.length;

        long[] cost = new long[n];

        lower = new MultiSet();
        upper = new MultiSet();

        // Calculate the cost of every window.
        for (int i = 0; i < x; i++) {
            add(nums[i]);
        }

        cost[0] = getCost();

        for (int i = x; i < n; i++) {
            remove(nums[i - x]);
            add(nums[i]);

            cost[i - x + 1] = getCost();
        }

        // DP: dp[i][j] = minimum operations needed
        // from index i onward to select j subarrays.
        long INF = Long.MAX_VALUE / 2;

        long[][] dp = new long[n + 1][k + 1];

        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], INF);
            dp[i][0] = 0;
        }

        for (int i = n - 1; i >= 0; i--) {
            for (int j = 1; j <= k; j++) {

                // Skip current index.
                dp[i][j] = dp[i + 1][j];

                // Select window starting at i.
                if (i + x <= n) {
                    dp[i][j] = Math.min(
                        dp[i][j],
                        cost[i] + dp[i + x][j - 1]
                    );
                }
            }
        }

        return dp[0][k];
    }
}


class Solution {
    static final int MOD = 1_000_000_007;

    long[] sum, sq, lazy;

    public int sumCounts(int[] nums) {
        int n = nums.length;
        sum = new long[4 * n];
        sq = new long[4 * n];
        lazy = new long[4 * n];

        java.util.Map<Integer, Integer> last = new java.util.HashMap<>();
        long ans = 0;

        for (int i = 0; i < n; i++) {
            int l = last.getOrDefault(nums[i], -1) + 1;
            update(1, 0, n - 1, l, i);

            ans = (ans + sq[1]) % MOD;
            last.put(nums[i], i);
        }

        return (int) ans;
    }

    void apply(int node, int left, int right, long v) {
        long len = right - left + 1L;
        sq[node] = (sq[node] + 2 * v % MOD * sum[node] % MOD
                    + v * v % MOD * len % MOD) % MOD;
        sum[node] = (sum[node] + v * len) % MOD;
        lazy[node] = (lazy[node] + v) % MOD;
    }

    void push(int node, int left, int right) {
        if (lazy[node] == 0 || left == right) return;

        int mid = (left + right) / 2;
        apply(node * 2, left, mid, lazy[node]);
        apply(node * 2 + 1, mid + 1, right, lazy[node]);
        lazy[node] = 0;
    }

    void update(int node, int left, int right, int ql, int qr) {
        if (ql > right || qr < left) return;

        if (ql <= left && right <= qr) {
            apply(node, left, right, 1);
            return;
        }

        push(node, left, right);

        int mid = (left + right) / 2;
        update(node * 2, left, mid, ql, qr);
        update(node * 2 + 1, mid + 1, right, ql, qr);

        sum[node] = (sum[node * 2] + sum[node * 2 + 1]) % MOD;
        sq[node] = (sq[node * 2] + sq[node * 2 + 1]) % MOD;
    }
}

class Solution {
    int n, k;
    Node[] tree;

    class Node {
        int[] cnt = new int[5];
        int prod = 1;
    }

    Node merge(Node a, Node b) {
        Node c = new Node();

        c.prod = a.prod * b.prod % k;

        for (int i = 0; i < k; i++)
            c.cnt[i] = a.cnt[i];

        for (int i = 0; i < k; i++)
            c.cnt[a.prod * i % k] += b.cnt[i];

        return c;
    }

    Node leaf(int x) {
        Node a = new Node();
        x %= k;
        a.prod = x;
        a.cnt[x] = 1;
        return a;
    }

    void build(int p, int l, int r, int[] a) {
        if (l == r) {
            tree[p] = leaf(a[l]);
            return;
        }

        int m = (l + r) / 2;
        build(p * 2, l, m, a);
        build(p * 2 + 1, m + 1, r, a);

        tree[p] = merge(tree[p * 2], tree[p * 2 + 1]);
    }

    void update(int p, int l, int r, int idx, int val) {
        if (l == r) {
            tree[p] = leaf(val);
            return;
        }

        int m = (l + r) / 2;

        if (idx <= m)
            update(p * 2, l, m, idx, val);
        else
            update(p * 2 + 1, m + 1, r, idx, val);

        tree[p] = merge(tree[p * 2], tree[p * 2 + 1]);
    }

    Node query(int p, int l, int r, int ql, int qr) {
        if (ql <= l && r <= qr)
            return tree[p];

        int m = (l + r) / 2;

        if (qr <= m)
            return query(p * 2, l, m, ql, qr);

        if (ql > m)
            return query(p * 2 + 1, m + 1, r, ql, qr);

        return merge(
            query(p * 2, l, m, ql, qr),
            query(p * 2 + 1, m + 1, r, ql, qr)
        );
    }

    public int[] resultArray(int[] nums, int k, int[][] queries) {
        this.n = nums.length;
        this.k = k;

        tree = new Node[4 * n];

        build(1, 0, n - 1, nums);

        int[] ans = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            int index = queries[i][0];
            int value = queries[i][1];
            int start = queries[i][2];
            int x = queries[i][3];

            update(1, 0, n - 1, index, value);

            Node res = query(1, 0, n - 1, start, n - 1);

            ans[i] = res.cnt[x];
        }

        return ans;
    }
}
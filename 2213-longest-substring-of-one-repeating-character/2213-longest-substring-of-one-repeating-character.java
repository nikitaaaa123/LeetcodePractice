class SegmentTree {
    int[] left, right, pref, suff, best;
    char[] s;

    SegmentTree(char[] s) {
        this.s = s;
        int n = s.length;
        left = new int[4 * n];
        right = new int[4 * n];
        pref = new int[4 * n];
        suff = new int[4 * n];
        best = new int[4 * n];
        build(1, 0, n - 1);
    }

    void build(int node, int l, int r) {
        left[node] = right[node] = 1;

        if (l == r) {
            pref[node] = suff[node] = best[node] = 1;
            return;
        }

        int mid = (l + r) / 2;
        build(node * 2, l, mid);
        build(node * 2 + 1, mid + 1, r);
        merge(node, l, r, mid);
    }

    void merge(int node, int l, int r, int mid) {
        int L = node * 2;
        int R = node * 2 + 1;

        pref[node] = pref[L];
        suff[node] = suff[R];
        best[node] = Math.max(best[L], best[R]);

        if (s[mid] == s[mid + 1]) {
            best[node] = Math.max(best[node], suff[L] + pref[R]);

            if (pref[L] == mid - l + 1)
                pref[node] = pref[L] + pref[R];

            if (suff[R] == r - mid)
                suff[node] = suff[R] + suff[L];
        }
    }

    void update(int node, int l, int r, int idx, char c) {
        if (l == r) {
            s[idx] = c;
            pref[node] = suff[node] = best[node] = 1;
            return;
        }

        int mid = (l + r) / 2;

        if (idx <= mid)
            update(node * 2, l, mid, idx, c);
        else
            update(node * 2 + 1, mid + 1, r, idx, c);

        merge(node, l, r, mid);
    }

    int getBest() {
        return best[1];
    }
}

class Solution {
    public int[] longestRepeating(String s, String queryCharacters,
                                  int[] queryIndices) {

        char[] str = s.toCharArray();
        int n = str.length;
        int q = queryIndices.length;

        SegmentTree tree = new SegmentTree(str);
        int[] ans = new int[q];

        for (int i = 0; i < q; i++) {
            int index = queryIndices[i];
            char c = queryCharacters.charAt(i);

            tree.update(1, 0, n - 1, index, c);
            ans[i] = tree.getBest();
        }

        return ans;
    }
}
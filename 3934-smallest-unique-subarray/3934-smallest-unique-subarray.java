
import java.util.*;

class Solution {
    public int smallestUniqueSubarray(int[] nums) {
        int n = nums.length;
        int[] sa = buildSuffixArray(nums);
        
        int[] rank = new int[n];
        for (int i = 0; i < n; i++) {
            rank[sa[i]] = i;
        }

        int[] lcp = new int[n];
        int h = 0;

        // Kasai's algorithm
        for (int i = 0; i < n; i++) {
            int r = rank[i];

            if (r == 0) continue;

            int j = sa[r - 1];

            while (i + h < n && j + h < n &&
                   nums[i + h] == nums[j + h]) {
                h++;
            }

            lcp[r] = h;

            if (h > 0) h--;
        }

        int ans = n;

        for (int r = 0; r < n; r++) {
            int maxLcp = lcp[r];

            if (r + 1 < n) {
                maxLcp = Math.max(maxLcp, lcp[r + 1]);
            }

            int len = maxLcp + 1;

            if (len <= n - sa[r]) {
                ans = Math.min(ans, len);
            }
        }

        return ans;
    }

    private int[] buildSuffixArray(int[] nums) {
        int n = nums.length;
        int m = n + 1;

        int[] a = new int[m];

        for (int i = 0; i < n; i++) {
            a[i] = nums[i];
        }

        a[n] = 0;

        int[] sa = new int[m];
        int[] c = new int[m];

        int maxVal = 100001;
        int[] cnt = new int[Math.max(maxVal, m)];

        for (int x : a) cnt[x]++;

        for (int i = 1; i < cnt.length; i++) {
            cnt[i] += cnt[i - 1];
        }

        for (int i = m - 1; i >= 0; i--) {
            sa[--cnt[a[i]]] = i;
        }

        int classes = 1;
        c[sa[0]] = 0;

        for (int i = 1; i < m; i++) {
            if (a[sa[i]] != a[sa[i - 1]]) {
                classes++;
            }
            c[sa[i]] = classes - 1;
        }

        int[] pn = new int[m];
        int[] cn = new int[m];

        for (int len = 1; len < m; len <<= 1) {

            for (int i = 0; i < m; i++) {
                pn[i] = sa[i] - len;
                if (pn[i] < 0) pn[i] += m;
            }

            Arrays.fill(cnt, 0, classes, 0);

            for (int x : pn) {
                cnt[c[x]]++;
            }

            for (int i = 1; i < classes; i++) {
                cnt[i] += cnt[i - 1];
            }

            for (int i = m - 1; i >= 0; i--) {
                int x = pn[i];
                sa[--cnt[c[x]]] = x;
            }

            cn[sa[0]] = 0;
            int newClasses = 1;

            for (int i = 1; i < m; i++) {
                int cur = sa[i];
                int prev = sa[i - 1];

                int cur1 = c[cur];
                int cur2 = c[(cur + len) % m];

                int prev1 = c[prev];
                int prev2 = c[(prev + len) % m];

                if (cur1 != prev1 || cur2 != prev2) {
                    newClasses++;
                }

                cn[cur] = newClasses - 1;
            }

            int[] temp = c;
            c = cn;
            cn = temp;

            classes = newClasses;

            if (classes == m) break;
        }

        // Remove the sentinel suffix
        return Arrays.copyOfRange(sa, 1, m);
    }
}

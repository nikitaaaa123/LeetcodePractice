import java.util.*;

class Solution {
    public int[] countServers(int n, int[][] logs, int x, int[] queries) {
        Arrays.sort(logs, (a, b) -> a[1] - b[1]);

        int m = queries.length;
        int[][] q = new int[m][2];

        for (int i = 0; i < m; i++) {
            q[i] = new int[]{queries[i], i};
        }

        Arrays.sort(q, (a, b) -> a[0] - b[0]);

        int[] count = new int[n + 1];
        int[] ans = new int[m];

        int left = 0, right = 0, active = 0;

        for (int[] query : q) {
            int time = query[0];
            int index = query[1];

            int start = time - x;

            // Add logs within the right boundary
            while (right < logs.length && logs[right][1] <= time) {
                int server = logs[right][0];

                if (count[server] == 0) {
                    active++;
                }

                count[server]++;
                right++;
            }

            // Remove logs outside the left boundary
            while (left < logs.length && logs[left][1] < start) {
                int server = logs[left][0];

                count[server]--;

                if (count[server] == 0) {
                    active--;
                }

                left++;
            }

            ans[index] = n - active;
        }

        return ans;
    }
}
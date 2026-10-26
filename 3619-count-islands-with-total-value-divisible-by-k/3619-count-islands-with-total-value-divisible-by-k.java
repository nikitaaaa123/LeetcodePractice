
import java.util.*;

class Solution {
    public int countIslands(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length;
        int ans = 0;
        int[] d = {-1, 0, 1, 0, -1};

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) continue;

                long sum = 0;
                Queue<int[]> q = new LinkedList<>();
                q.offer(new int[]{i, j});
                sum += grid[i][j];
                grid[i][j] = 0;

                while (!q.isEmpty()) {
                    int[] cur = q.poll();

                    for (int x = 0; x < 4; x++) {
                        int r = cur[0] + d[x];
                        int c = cur[1] + d[x + 1];

                        if (r >= 0 && r < m &&
                            c >= 0 && c < n &&
                            grid[r][c] > 0) {
                            sum += grid[r][c];
                            grid[r][c] = 0;
                            q.offer(new int[]{r, c});
                        }
                    }
                }

                if (sum % k == 0) ans++;
            }
        }

        return ans;
    }
}

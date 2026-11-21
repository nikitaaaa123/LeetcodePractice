
import java.util.*;

class Solution {
    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;

        List<Integer>[] tree = new ArrayList[n];
        List<Integer>[] newTree = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            tree[i] = new ArrayList<>();
            newTree[i] = new ArrayList<>();
        }

        for (int i = 1; i < n; i++) {
            tree[parent[i]].add(i);
        }

        int[] last = new int[26];
        Arrays.fill(last, -1);

        int[] prev = new int[n];
        int[] newParent = parent.clone();

        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(0);

        while (!stack.isEmpty()) {
            int u = stack.pop();

            if (u >= 0) {
                int c = s.charAt(u) - 'a';

                prev[u] = last[c];

                if (prev[u] != -1) {
                    newParent[u] = prev[u];
                }

                last[c] = u;

                stack.push(~u);

                for (int v : tree[u]) {
                    stack.push(v);
                }

            } else {
                u = ~u;
                int c = s.charAt(u) - 'a';
                last[c] = prev[u];
            }
        }

        for (int i = 1; i < n; i++) {
            newTree[newParent[i]].add(i);
        }

        int[] ans = new int[n];
        Arrays.fill(ans, 1);

        List<Integer> order = new ArrayList<>();
        stack.push(0);

        while (!stack.isEmpty()) {
            int u = stack.pop();
            order.add(u);

            for (int v : newTree[u]) {
                stack.push(v);
            }
        }

        for (int i = order.size() - 1; i > 0; i--) {
            int u = order.get(i);
            ans[newParent[u]] += ans[u];
        }

        return ans;
    }
}

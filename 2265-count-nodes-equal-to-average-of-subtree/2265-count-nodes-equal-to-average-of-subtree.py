class Solution:
    def averageOfSubtree(self, root):
        ans = 0

        def dfs(node):
            nonlocal ans
            if not node:
                return 0, 0

            ls, lc = dfs(node.left)
            rs, rc = dfs(node.right)

            s = ls + rs + node.val
            c = lc + rc + 1

            if node.val == s // c:
                ans += 1

            return s, c

        dfs(root)
        return ans
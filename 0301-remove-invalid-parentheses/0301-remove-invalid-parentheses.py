class Solution:
    def removeInvalidParentheses(self, s: str) -> list[str]:
        left_remove = right_remove = 0

        # Find minimum number of '(' and ')' to remove
        for ch in s:
            if ch == '(':
                left_remove += 1
            elif ch == ')':
                if left_remove > 0:
                    left_remove -= 1
                else:
                    right_remove += 1

        ans = set()

        def dfs(i, path, balance, lrem, rrem):
            # Invalid state
            if balance < 0:
                return

            if i == len(s):
                if balance == 0 and lrem == 0 and rrem == 0:
                    ans.add("".join(path))
                return

            ch = s[i]

            if ch == '(':
                # Remove '('
                if lrem > 0:
                    dfs(i + 1, path, balance, lrem - 1, rrem)

                # Keep '('
                path.append(ch)
                dfs(i + 1, path, balance + 1, lrem, rrem)
                path.pop()

            elif ch == ')':
                # Remove ')'
                if rrem > 0:
                    dfs(i + 1, path, balance, lrem, rrem - 1)

                # Keep ')' only if there's an unmatched '('
                if balance > 0:
                    path.append(ch)
                    dfs(i + 1, path, balance - 1, lrem, rrem)
                    path.pop()

            else:
                # Letters cannot be removed
                path.append(ch)
                dfs(i + 1, path, balance, lrem, rrem)
                path.pop()

        dfs(0, [], 0, left_remove, right_remove)

        return list(ans)
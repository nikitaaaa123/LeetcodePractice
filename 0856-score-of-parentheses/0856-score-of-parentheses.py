class Solution:
    def scoreOfParentheses(self, s):
        stack = [0]

        for ch in s:
            if ch == '(':
                stack.append(0)
            else:
                v = stack.pop()

                if v == 0:
                    v = 1
                else:
                    v = 2 * v

                stack[-1] += v

        return stack[0]

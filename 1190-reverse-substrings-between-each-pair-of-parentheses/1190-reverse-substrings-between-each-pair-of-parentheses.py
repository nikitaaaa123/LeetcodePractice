
class Solution:
    def reverseParentheses(self, s: str) -> str:
        st = []
        for c in s:
            if c == ')':
                t = ''
                while st and st[-1] != '(':
                    t += st.pop()
                st.pop()
                st.extend(t)
            else:
                st.append(c)
        return ''.join(st)
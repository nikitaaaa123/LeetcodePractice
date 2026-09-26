class Solution:
    def evaluate(self, s: str, knowledge: List[List[str]]) -> str:
        d = dict(knowledge)
        res = []
        i = 0

        while i < len(s):
            if s[i] == '(':
                j = s.index(')', i)
                res.append(d.get(s[i+1:j], '?'))
                i = j + 1
            else:
                res.append(s[i])
                i += 1

        return ''.join(res)
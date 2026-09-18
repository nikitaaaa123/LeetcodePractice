from typing import List

class Solution:
    def maxNumOfSubstrings(self, s: str) -> List[str]:
        n = len(s)

        first = [n] * 26
        last = [-1] * 26

        # First and last occurrence of every character
        for i, ch in enumerate(s):
            c = ord(ch) - ord('a')
            first[c] = min(first[c], i)
            last[c] = i

        # Find the minimal valid interval starting at `left`
        def get_interval(left):
            right = last[ord(s[left]) - ord('a')]
            i = left

            while i <= right:
                c = ord(s[i]) - ord('a')

                # This character occurs before our interval.
                # So this interval cannot be valid.
                if first[c] < left:
                    return None

                right = max(right, last[c])
                i += 1

            return (left, right)

        intervals = []

        # Only first occurrences can start minimal candidates
        for c in range(26):
            if first[c] != n:
                interval = get_interval(first[c])
                if interval:
                    intervals.append(interval)

        # Earliest finishing interval first
        intervals.sort(key=lambda x: x[1])

        ans = []
        prev_end = -1

        for left, right in intervals:
            if left > prev_end:
                ans.append(s[left:right + 1])
                prev_end = right

        return ans
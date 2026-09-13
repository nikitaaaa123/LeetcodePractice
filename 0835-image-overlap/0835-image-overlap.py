from collections import Counter
from typing import List

class Solution:
    def largestOverlap(self, img1: List[List[int]], img2: List[List[int]]) -> int:
        ones1 = []
        ones2 = []

        n = len(img1)

        for r in range(n):
            for c in range(n):
                if img1[r][c] == 1:
                    ones1.append((r, c))
                if img2[r][c] == 1:
                    ones2.append((r, c))

        shifts = Counter()

        for r1, c1 in ones1:
            for r2, c2 in ones2:
                shifts[(r1 - r2, c1 - c2)] += 1

        return max(shifts.values(), default=0)
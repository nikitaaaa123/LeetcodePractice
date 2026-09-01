from collections import deque
from typing import List

class Solution:
    def minMoves(self, classroom: List[str], energy: int) -> int:
        m = len(classroom)
        n = len(classroom[0])

        # Find starting position and all litter positions
        start = None
        litter = {}

        for i in range(m):
            for j in range(n):
                if classroom[i][j] == 'S':
                    start = (i, j)
                elif classroom[i][j] == 'L':
                    litter[(i, j)] = len(litter)

        k = len(litter)

        # All litter collected
        full_mask = (1 << k) - 1

        # best[i][j][mask] = maximum energy with this position and mask
        best = [[[-1] * (1 << k) for _ in range(n)] for _ in range(m)]

        sx, sy = start
        best[sx][sy][0] = energy

        # x, y, mask, remaining_energy, moves
        q = deque([(sx, sy, 0, energy, 0)])

        directions = [(1, 0), (-1, 0), (0, 1), (0, -1)]

        while q:
            x, y, mask, e, moves = q.popleft()

            if mask == full_mask:
                return moves

            for dx, dy in directions:
                nx = x + dx
                ny = y + dy

                # Outside classroom
                if nx < 0 or nx >= m or ny < 0 or ny >= n:
                    continue

                # Wall
                if classroom[nx][ny] == 'X':
                    continue

                # Moving costs 1 energy
                ne = e - 1

                # Can't move without energy
                if ne < 0:
                    continue

                nmask = mask

                # Collect litter
                if classroom[nx][ny] == 'L':
                    bit = litter[(nx, ny)]
                    nmask |= (1 << bit)

                # Restore energy at reset cell
                if classroom[nx][ny] == 'R':
                    ne = energy

                # If energy becomes 0, we can only be here if
                # this is a reset cell or all litter is already collected
                if ne == 0 and classroom[nx][ny] != 'R' and nmask != full_mask:
                    continue

                # Already reached this state with more energy
                if best[nx][ny][nmask] >= ne:
                    continue

                best[nx][ny][nmask] = ne
                q.append((nx, ny, nmask, ne, moves + 1))

        return -1
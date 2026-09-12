class Solution:
    def maximumWeight(self, intervals):
        n = len(intervals)

        a = sorted((l, r, w, i) for i, (l, r, w) in enumerate(intervals))

        # next[i] = first interval that starts after a[i] ends
        import bisect
        starts = [x[0] for x in a]
        nxt = [bisect.bisect_right(starts, a[i][1]) for i in range(n)]

        # dp[i][k] = best result from i onward, choosing at most k intervals
        dp = [[(0, ()) for _ in range(5)] for _ in range(n + 1)]

        for i in range(n - 1, -1, -1):
            l, r, w, idx = a[i]

            for k in range(1, 5):
                # Skip current interval
                best = dp[i + 1][k]

                # Take current interval
                score = w + dp[nxt[i]][k - 1][0]
                ids = tuple(sorted((idx,) + dp[nxt[i]][k - 1][1]))

                if score > best[0] or (score == best[0] and ids < best[1]):
                    best = (score, ids)

                dp[i][k] = best

        return list(dp[0][4][1])
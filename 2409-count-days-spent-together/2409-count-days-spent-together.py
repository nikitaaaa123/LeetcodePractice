class Solution:
    def countDaysTogether(self, arriveAlice: str, leaveAlice: str, arriveBob: str, leaveBob: str) -> int:
        def day(date):
            m, d = map(int, date.split('-'))
            days = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31]
            return sum(days[:m-1]) + d

        start = max(day(arriveAlice), day(arriveBob))
        end = min(day(leaveAlice), day(leaveBob))

        return max(0, end - start + 1)

class Solution:
    def maximumSaleItems(self, items: List[List[int]], budget: int) -> int:
        n = len(items)

        freq = [0] * (n + 1)
        min_price = float('inf')

        for factor, price in items:
            freq[factor] += 1
            min_price = min(min_price, price)

        # Count items whose factors are multiples of each factor
        for i in range(1, n + 1):
            for j in range(2 * i, n + 1, i):
                freq[i] += freq[j]

        # Collect profitable double-value purchases
        deals = []

        for factor, price in items:
            gain = freq[factor] - 1

            if gain > 0 and price <= 2 * min_price:
                deals.append((price, gain))

        # Buy cheapest double-value units first
        deals.sort()

        ans = 0

        for price, gain in deals:
            count = min(gain, budget // price)

            ans += count * 2
            budget -= count * price

        # Spend remaining budget on cheapest single-value items
        ans += budget // min_price

        return ans

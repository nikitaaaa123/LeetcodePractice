class Solution {
public:
    long long gcd(long long a, long long b) {
        while (b) {
            long long t = a % b;
            a = b;
            b = t;
        }
        return a;
    }

    long long lcm(long long a, long long b) {
        return a / gcd(a, b) * b;
    }

    long long findKthSmallest(vector<int>& coins, int k) {
        int n = coins.size();

        auto check = [&](long long x) {
            long long cnt = 0;

            for (int mask = 1; mask < (1 << n); mask++) {
                long long curLCM = 1;
                bool ok = true;

                for (int i = 0; i < n; i++) {
                    if (mask & (1 << i)) {
                        curLCM = lcm(curLCM, (long long)coins[i]);
                        if (curLCM > x) {
                            ok = false;
                            break;
                        }
                    }
                }

                if (!ok) continue;

                if (__builtin_popcount(mask) & 1)
                    cnt += x / curLCM;
                else
                    cnt -= x / curLCM;
            }

            return cnt >= k;
        };

        long long lo = 1, hi = 1e11;

        while (lo < hi) {
            long long mid = lo + (hi - lo) / 2;
            if (check(mid))
                hi = mid;
            else
                lo = mid + 1;
        }

        return lo;
    }
};
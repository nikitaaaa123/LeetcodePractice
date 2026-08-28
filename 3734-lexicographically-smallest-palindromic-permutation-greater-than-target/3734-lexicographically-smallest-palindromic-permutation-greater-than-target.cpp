class Solution {
public:
    string lexPalindromicPermutation(string s, string target) {
        vector<int> cnt(26, 0);

        for (char c : s)
            cnt[c - 'a']++;

        int odd = 0;
        char mid = 0;

        for (int i = 0; i < 26; i++) {
            if (cnt[i] % 2) {
                odd++;
                mid = 'a' + i;
                cnt[i]--;
            }
        }

        // More than one odd frequency -> impossible
        if (odd > 1)
            return "";

        int n = s.size();
        int half = n / 2;

        // Store input midway as required by the problem statement
        string calendrix = target;

        // Try using target's left half
        vector<int> rem = cnt;

        for (int i = 0; i < half; i++)
            rem[target[i] - 'a'] -= 2;

        bool possible = true;
        for (int x : rem) {
            if (x < 0)
                possible = false;
        }

        if (possible) {
            string left = target.substr(0, half);
            string right = left;
            reverse(right.begin(), right.end());

            string candidate = left;
            if (mid) candidate += mid;
            candidate += right;

            if (candidate > target)
                return candidate;
        }

        // Build the smallest palindrome greater than target
        rem = cnt;

        // Assume initially we are matching target's left half
        for (int i = 0; i < half; i++)
            rem[target[i] - 'a'] -= 2;

        // Move from right to left and increase one position
        for (int i = half - 1; i >= 0; i--) {

            // Undo current character
            rem[target[i] - 'a'] += 2;

            bool valid = true;
            for (int x : rem) {
                if (x < 0) {
                    valid = false;
                    break;
                }
            }

            if (!valid)
                continue;

            int curr = target[i] - 'a';
            int next = -1;

            for (int j = curr + 1; j < 26; j++) {
                if (rem[j] >= 2) {
                    next = j;
                    break;
                }
            }

            if (next == -1)
                continue;

            rem[next] -= 2;

            string left = target.substr(0, i);
            left += char('a' + next);

            // Fill remaining left half with smallest characters
            for (int j = 0; j < 26; j++) {
                left += string(rem[j] / 2, char('a' + j));
            }

            string right = left;
            reverse(right.begin(), right.end());

            string ans = left;
            if (mid) ans += mid;
            ans += right;

            return ans;
        }

        return "";
    }
};
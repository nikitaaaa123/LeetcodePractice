class Solution {
public:
    string shortestBeautifulSubstring(string s, int k) {
        string ans = "";

        for (int i = 0; i < s.size(); i++) {
            int ones = 0;

            for (int j = i; j < s.size(); j++) {
                ones += (s[j] == '1');

                if (ones == k) {
                    string cur = s.substr(i, j - i + 1);

                    if (ans.empty() ||
                        cur.size() < ans.size() ||
                        (cur.size() == ans.size() && cur < ans)) {
                        ans = cur;
                    }

                    // Extending further can only make it longer.
                    break;
                }
            }
        }

        return ans;
    }
};
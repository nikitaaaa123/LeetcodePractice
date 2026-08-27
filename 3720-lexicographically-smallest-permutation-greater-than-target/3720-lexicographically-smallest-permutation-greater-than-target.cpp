class Solution {
public:
    string lexGreaterPermutation(string s, string target) {
        int n = s.size();
        vector<int> cnt(26, 0);

        for (char c : s)
            cnt[c - 'a']++;

        string prefix;

        // Match target as much as possible
        for (int i = 0; i < n; i++) {
            int x = target[i] - 'a';

            if (cnt[x] > 0) {
                cnt[x]--;
                prefix += target[i];
            } else {
                break;
            }
        }

        // Try making the first greater character
        // as far right as possible
        for (int i = prefix.size(); i >= 0; i--) {

            if (i < (int)prefix.size()) {
                cnt[prefix.back() - 'a']++;
                prefix.pop_back();
            }

            if (i < n) {
                // Find smallest available character > target[i]
                for (int c = target[i] - 'a' + 1; c < 26; c++) {
                    if (cnt[c] > 0) {
                        string ans = prefix;
                        ans += char('a' + c);
                        cnt[c]--;

                        // Add remaining characters in sorted order
                        for (int j = 0; j < 26; j++) {
                            while (cnt[j] > 0) {
                                ans += char('a' + j);
                                cnt[j]--;
                            }
                        }

                        return ans;
                    }
                }
            }
        }

        return "";
    }
};
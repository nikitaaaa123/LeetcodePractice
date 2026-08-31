class Solution {
public:
    vector<int> nodesBetweenCriticalPoints(ListNode* head) {
        ListNode* prev = head;
        ListNode* curr = head->next;

        int index = 1;

        int first = -1;
        int last = -1;
        int minDist = INT_MAX;

        while (curr->next != nullptr) {

            bool critical =
                (curr->val > prev->val &&
                 curr->val > curr->next->val)
                ||
                (curr->val < prev->val &&
                 curr->val < curr->next->val);

            if (critical) {
                if (first == -1) {
                    first = index;
                }

                if (last != -1) {
                    minDist = min(minDist, index - last);
                }

                last = index;
            }

            prev = curr;
            curr = curr->next;
            index++;
        }

        // Fewer than 2 critical points
        if (first == last) {
            return {-1, -1};
        }

        return {minDist, last - first};
    }
};
class Solution {
public:
    int countLessEqual(vector<vector<int>>& matrix, int x) {
        int n = matrix.size();
        int row = n - 1, col = 0;
        int count = 0;

        while (row >= 0 && col < n) {
            if (matrix[row][col] <= x) {
                count += row + 1;
                col++;
            } else {
                row--;
            }
        }
        return count;
    }

    int kthSmallest(vector<vector<int>>& matrix, int k) {
        int n = matrix.size();
        int low = matrix[0][0];
        int high = matrix[n - 1][n - 1];

        while (low < high) {
            int mid = low + (high - low) / 2;

            if (countLessEqual(matrix, mid) < k)
                low = mid + 1;
            else
                high = mid;
        }

        return low;
    }
};
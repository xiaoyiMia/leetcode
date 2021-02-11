package io.mars.microsoft;

/**
 * Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:
 * <p>
 * Integers in each row are sorted from left to right.
 * The first integer of each row is greater than the last integer of the previous row.
 * <p>
 * <p>
 * Example 1:
 * Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,50]], target = 3
 * Output: true
 * <p>
 * Example 2:
 * Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,50]], target = 13
 * Output: false
 * <p>
 * Example 3:
 * Input: matrix = [], target = 0
 * Output: false
 * <p>
 * <p>
 * Constraints:
 * <p>
 * m == matrix.length
 * n == matrix[i].length
 * 0 <= m, n <= 100
 * -10^4 <= matrix[i][j], target <= 10^4
 */
public class Search2DMatrix {
  public boolean searchMatrix(int[][] matrix, int target) {
    if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return false;

    int row = findVertical(matrix, 0, matrix.length - 1, target);
    if (row == -1) return false;

    return findHorizontal(matrix[row], 0, matrix[row].length - 1, target);
  }

  private int findVertical(int[][] matrix, int start, int end, int target) {
    if (start > end) return -1;

    int pivot = (start + end) / 2;
    if (matrix[pivot][0] <= target && target <= matrix[pivot][matrix[0].length - 1]) {
      return pivot;
    } else if (matrix[pivot][0] < target) {
      return findVertical(matrix, pivot + 1, end, target);
    } else {
      return findVertical(matrix, start, pivot - 1, target);
    }
  }

  private boolean findHorizontal(int[] rowValues, int start, int end, int target) {
    if (start > end) return false;

    int pivot = (start + end) / 2;
    if (rowValues[pivot] == target) {
      return true;
    } else if (rowValues[pivot] < target) {
      return findHorizontal(rowValues, pivot + 1, end, target);
    } else {
      return findHorizontal(rowValues, start, pivot - 1, target);
    }
  }
}

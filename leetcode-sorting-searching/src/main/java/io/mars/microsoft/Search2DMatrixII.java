package io.mars.microsoft;

/**
 * Write an efficient algorithm that searches for a target value in an m x n integer matrix. The matrix has the following properties:
 *
 * Integers in each row are sorted in ascending from left to right.
 * Integers in each column are sorted in ascending from top to bottom.
 *
 *
 * Example 1:
 * Input: matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 5
 * Output: true
 *
 * Example 2:
 * Input: matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 20
 * Output: false
 *
 *
 * Constraints:
 *
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= n, m <= 300
 * -109 <= matix[i][j] <= 109
 * All the integers in each row are sorted in ascending order.
 * All the integers in each column are sorted in ascending order.
 * -109 <= target <= 109
 */
public class Search2DMatrixII {
  public boolean searchMatrix(int[][] matrix, int target) {
    if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return false;

//    return searchInMatrix(matrix, 0, matrix.length - 1, 0, matrix[0].length - 1, target);
    return gredySearch(matrix, target);
  }

  // Solution 1
  private boolean gredySearch(int[][] matrix, int target) {
    int row = 0, column = matrix[0].length - 1;
    while(row < matrix.length && column >= 0) {
      if(matrix[row][column] == target) return true;
      else if(matrix[row][column] > target) column--;
      else row++;
    }
    return false;
  }

  // Solution 2
  private boolean searchInMatrix(int[][] matrix, int top, int bottom, int left, int right, int target) {
    if (top > bottom || left > right) return false;

    if (top == bottom) {
      if (matrix[top][left] == target) return true;
      return searchInMatrix(matrix, top, bottom, left + 1, right, target);
    } else if (left == right) {
      if (matrix[top][left] == target) return true;
      return searchInMatrix(matrix, top + 1, bottom, left, right, target);
    }


    int[] rowRanges = findTargetRowsForMatrix(matrix, left, top, bottom, target);
    if (rowRanges[0] == rowRanges[1]) return true;
    else if (rowRanges[0] == -1) return false;
    else if (rowRanges[1] == -1) {
      return searchInMatrix(matrix, rowRanges[0] + 1, bottom, left, right, target)||
          searchInMatrix(matrix, top, bottom, rowRanges[0] - top + left + 1, right, target);
    }
    else {
      return searchInMatrix(matrix, rowRanges[0] + 1, bottom, left, rowRanges[0] - top + left, target)
          || searchInMatrix(matrix, top, rowRanges[0], rowRanges[0] - top + left + 1, right, target);
    }
  }

  private int[] findTargetRowsForMatrix(int[][] matrix, int left, int top, int bottom, int target) {
    bottom = matrix[top].length - left > (bottom - top + 1) ? bottom : top + matrix[top].length - left - 1;

    if (matrix[top][left] < target) return new int[]{-1, top};
    else if (matrix[top][left] == target) return new int[]{top, top};
    else if (matrix[bottom][bottom - top + left] > target) return new int[]{bottom, -1};
    else if (matrix[bottom][bottom - top + left] == target) return new int[]{bottom, bottom};

    return findTargetRows(matrix, left, top, bottom, target);
  }

  private int[] findTargetRows(int[][] matrix, int left, int top, int bottom, int target) {
    int rowIndex = (top + bottom) / 2;
    int columnIndex = rowIndex - top + left;

    if (matrix[rowIndex][columnIndex] == target) return new int[]{rowIndex, rowIndex};
    else if (matrix[rowIndex + 1][columnIndex + 1] == target) return new int[]{rowIndex + 1, rowIndex + 1};
    else if (matrix[rowIndex][columnIndex] < target && matrix[rowIndex + 1][columnIndex + 1] > target)
      return new int[]{rowIndex, rowIndex + 1};
    else if (matrix[rowIndex][columnIndex] > target)
      return findTargetRows(matrix, left, top, rowIndex, target);
    else return findTargetRows(matrix, columnIndex + 1, rowIndex + 1, bottom, target);
  }
}

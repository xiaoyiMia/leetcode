package io.mars.microsoft;

/**
 * Given an m x n matrix. If an element is 0, set its entire row and column to 0. Do it in-place.
 * <p>
 * Follow up:
 * <p>
 * A straight forward solution using O(mn) space is probably a bad idea.
 * A simple improvement uses O(m + n) space, but still not the best solution.
 * Could you devise a constant space solution?
 * <p>
 * <p>
 * Example 1:
 * Input: matrix = [[1,1,1],[1,0,1],[1,1,1]]
 * Output: [[1,0,1],[0,0,0],[1,0,1]]
 * <p>
 * <p>
 * Example 2:
 * Input: matrix = [[0,1,2,0],[3,4,5,2],[1,3,1,5]]
 * Output: [[0,0,0,0],[0,4,5,0],[0,3,1,0]]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * m == matrix.length
 * n == matrix[0].length
 * 1 <= m, n <= 200
 * -2^31 <= matrix[i][j] <= 2^31 - 1
 */
public class SetMatrixZero {

  public void setZeroes(int[][] matrix) {
    boolean isCol = false;
    int rowCount = matrix.length;
    int colCount = matrix[0].length;

    // Check each cell, if value is 0, then set the cell of first row and col to 0
    for(int i = 0; i < rowCount; i++) {
      // Check if first column need to be set 0
      if(matrix[i][0] == 0) isCol = true;

      for(int j = 1; j < colCount; j++) {
        if(matrix[i][j] == 0) {
          matrix[i][0] = 0;
          matrix[0][j] = 0;
        }
      }
    }

    // Iterate each cell, if the first cell on the same row or column is 0, current cell need to be set 0
    for(int i = 1; i < rowCount; i++) {
      for(int j = 1; j < colCount; j++) {
        if(matrix[i][0] == 0 || matrix[0][j] == 0) matrix[i][j] = 0;
      }
    }

    // If first row need to be set 0
    if(matrix[0][0] == 0) {
      for(int j = 1; j < colCount; j++) {
        matrix[0][j] = 0;
      }
    }

    // If first column need to be set 0
    if(isCol) {
      for(int i = 0; i < rowCount; i++) {
        matrix[i][0] = 0;
      }
    }
  }
}

package io.mars.amazon;

/**
 * You are given an n x n 2D matrix representing an image.
 *
 * Rotate the image by 90 degrees (clockwise).
 *
 * Note:
 *
 * You have to rotate the image in-place, which means you have to modify the input 2D matrix directly.
 * DO NOT allocate another 2D matrix and do the rotation.
 *
 * Example 1:
 *
 * Given input matrix =
 * [
 *   [1,2,3],
 *   [4,5,6],
 *   [7,8,9]
 * ],
 *
 * rotate the input matrix in-place such that it becomes:
 * [
 *   [7,4,1],
 *   [8,5,2],
 *   [9,6,3]
 * ]
 * Example 2:
 *
 * Given input matrix =
 * [
 *   [ 5, 1, 9,11],
 *   [ 2, 4, 8,10],
 *   [13, 3, 6, 7],
 *   [15,14,12,16]
 * ],
 *
 * rotate the input matrix in-place such that it becomes:
 * [
 *   [15,13, 2, 5],
 *   [14, 3, 4, 1],
 *   [12, 6, 8, 9],
 *   [16, 7,10,11]
 * ]
 *
 * Note: 90 rotated: (i, j) -> (j, i) -> (j, n - 1 - i)
 */
public class RotateImage {
  public void rotate(int[][] matrix) {

  }

  // Solution1: Transpose ([i,j] -> [j,i]) and then reverse each row
  private void TransposeAndReverse(int[][] matrix) {
    int n = matrix.length;
    // Transpose
    for(int i = 0; i < n - 1; i++) {
      for(int j = i + 1; j < n; j++) {
        int temp = matrix[i][j];
        matrix[i][j] = matrix[j][i];
        matrix[j][i] = temp;
      }
    }

    // for each row, reverse
    for(int i = 0; i < n; i++) {
      for(int j = 0; j < n/2; j++) {
        int temp = matrix[i][j];
        matrix[i][j] = matrix[i][n - 1 - j];
        matrix[i][n - 1 - j] = temp;
      }
    }
  }

  // Solution2:
  // Step1: temp[4] = [(0,0),(0,n-1),(n-1,n-1),(n-1,0)] -> [(0,n-1),(n-1,n-1),(n-1,0), (0,0)]
  // Step2: temp[4] = [(0,1),(1,n-1),(n-1,n-2),(n-2,0)] -> [(1,n-1),(n-1,n-2),(n-2,0),(0,1)]
  // ...
  private void RotateFourRectangles(int[][] matrix) {
    int n = matrix.length;
    // There are n/2 rows of nodes need to be rotated.
    for(int i = 0; i< n/2; i++) {
      for(int j = i; j < n - 1 - i; j++) {
        int temp = matrix[n - 1 - j][i];
        matrix[n - 1 - j][i] = matrix[n - 1 - i][n - 1 - j];
        matrix[n - 1 - i][n - 1 - j] = matrix[j][n - 1 - i];
        matrix[j][n - 1 - i] = matrix[i][j];
        matrix[i][j] = temp;
      }
    }
  }
}

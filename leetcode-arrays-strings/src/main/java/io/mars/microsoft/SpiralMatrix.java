package io.mars.microsoft;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an m x n matrix, return all elements of the matrix in spiral order.
 * <p>
 * <p>
 * Example 1:
 * Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * Output: [1,2,3,6,9,8,7,4,5]
 * <p>
 * <p>
 * Example 2:
 * Input: matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
 * Output: [1,2,3,4,8,12,11,10,9,5,6,7]
 * <p>
 * <p>
 * Constraints:
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 10
 * -100 <= matrix[i][j] <= 100
 */
public class SpiralMatrix {
  // {int, int} -> {row, column}
  private final static int[][] moveActions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
  // {int, int, int, int} -> {Top, Left, Right, Bottom}
  private final static int[][] directionRangeActions = {{1, 0, 0, 0}, {0, 0, -1, 0}, {0, 0, 0, -1}, {0, 1, 0, 0}};

  public List<Integer> spiralOrder(int[][] matrix) {
    if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return null;

    // {Top, Left, Right, Bottom}
    int[] directionRanges = {0, 0, matrix[0].length, matrix.length};
    int[] point = {0, 0}; // {rowIndex, columnIndex}
    int moveIndex = 0;
    List<Integer> result = new ArrayList<>();

    while (canMove(directionRanges, point)) {
      moveToDirectionEnds(matrix, directionRanges, point, moveIndex, result);
      moveBackward(point, moveIndex);

      moveIndex++;
      if (moveIndex >= 4) moveIndex = 0;

      moveForward(point, moveIndex);
    }
    return result;
  }

  private void moveToDirectionEnds(int[][] matrix, int[] directionRanges, int[] point, int moveIndex,
                                   List<Integer> spiralMatrix) {
    while (canMove(directionRanges, point)) {
      spiralMatrix.add(matrix[point[0]][point[1]]);
      moveForward(point, moveIndex);
    }

    for (int i = 0; i < 4; i++) {
      directionRanges[i] += directionRangeActions[moveIndex][i];
    }
  }

  private boolean canMove(int[] directionRanges, int[] point) {
    return point[0] >= directionRanges[0] && point[0] < directionRanges[3] && point[1] >= directionRanges[1]
        && point[1] < directionRanges[2];
  }

  private void moveForward(int[] point, int moveIndex) {
    point[0] += moveActions[moveIndex][0];
    point[1] += moveActions[moveIndex][1];
  }

  private void moveBackward(int[] point, int moveIndex) {
    point[0] -= moveActions[moveIndex][0];
    point[1] -= moveActions[moveIndex][1];
  }
}

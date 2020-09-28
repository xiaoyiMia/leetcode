package io.mars.tree.amazon;

import java.util.*;

/**
 * You are asked to cut off trees in a forest for a golf event. The forest is represented as a non-negative 2D map,
 * in this map:
 * <p>
 * 0 represents the obstacle can't be reached.
 * 1 represents the ground can be walked through.
 * The place with number bigger than 1 represents a tree can be walked through, and this positive number
 * represents the tree's height.
 * In one step you can walk in any of the four directions top, bottom, left and right also when standing in
 * a point which is a tree you can decide whether or not to cut off the tree.
 * <p>
 * You are asked to cut off all the trees in this forest in the order of tree's height - always cut off the
 * tree with lowest height first. And after cutting, the original place has the tree will become a grass (value 1).
 * <p>
 * You will start from the point (0, 0) and you should output the minimum steps you need to walk to cut off
 * all the trees. If you can't cut off all the trees, output -1 in that situation.
 * <p>
 * You are guaranteed that no two trees have the same height and there is at least one tree needs to be cut off.
 * <p>
 * Example 1:
 * <p>
 * Input:
 * [
 * [1,2,3],
 * [0,0,4],
 * [7,6,5]
 * ]
 * Output: 6
 * <p>
 * <p>
 * Example 2:
 * <p>
 * Input:
 * [
 * [1,2,3],
 * [0,0,0],
 * [7,6,5]
 * ]
 * Output: -1
 * <p>
 * <p>
 * Example 3:
 * <p>
 * Input:
 * [
 * [2,3,4],
 * [0,0,5],
 * [8,7,6]
 * ]
 * Output: 6
 * Explanation: You started from the point (0,0) and you can cut off the tree in (0,0) directly without walking.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= forest.length <= 50
 * 1 <= forest[i].length <= 50
 * 0 <= forest[i][j] <= 10^9
 */
public class CutOffTreesForGolfEvent {
  private static int[] dr = {-1, 1, 0, 0};
  private static int[] dc = {0, 0, -1, 1};

  public int cutOffTree(List<List<Integer>> forest) {
    // Order trees by height
    List<int[]> trees = orderTrees(forest);

    // At current position, find shortest path to next tree
    int totalSteps = 0;
    int[] curPosition = new int[]{0, 0};
    for (int[] tree : trees) {
      int steps = findShortestPath(forest, curPosition[0], curPosition[1], tree[0], tree[1]);
      if (steps == -1) return -1;

      curPosition[0] = tree[0];
      curPosition[1] = tree[1];
      totalSteps += steps;
    }
    return totalSteps;
  }

  private List<int[]> orderTrees(List<List<Integer>> forest) {
    List<int[]> trees = new ArrayList<>();
    for (int row = 0; row < forest.size(); row++) {
      for (int col = 0; col < forest.get(row).size(); col++) {
        if (forest.get(row).get(col) > 1) trees.add(new int[]{row, col});
      }
    }

    trees.sort(Comparator.comparing(p -> forest.get(p[0]).get(p[1])));
    return trees;
  }

  private int findShortestPath(List<List<Integer>> forest, int curR, int curC, int nextR, int nextC) {
    Queue<int[]> positions = new LinkedList<>();
    positions.add(new int[]{curR, curC, 0});

    boolean[][] seen = new boolean[forest.size()][forest.get(0).size()];
    seen[curR][curC] = true;

    while (!positions.isEmpty()) {
      int[] cur = positions.poll();

      if (cur[0] == nextR && cur[1] == nextC) return cur[2];

      for (int i = 0; i < 4; i++) {
        int r = cur[0] + dr[i];
        int c = cur[1] + dc[i];

        if (0 <= r && r < forest.size() && 0 <= c && c < forest.get(r).size()
            && !seen[r][c] && forest.get(r).get(c) != 0) {

          seen[r][c] = true;
          positions.add(new int[]{r, c, cur[2] + 1});
        }

      }
    }

    return -1;
  }
}

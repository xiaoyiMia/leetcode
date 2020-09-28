package io.mars.tree.amazon;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.
 * <p>
 * Example 1:
 * <p>
 * Input:
 * 11110
 * 11010
 * 11000
 * 00000
 * <p>
 * Output: 1
 * Example 2:
 * <p>
 * Input:
 * 11000
 * 11000
 * 00100
 * 00011
 * <p>
 * Output: 3
 */
public class NumberOfIslands {
  public int numIslands(char[][] grid) {
    int count = 0;
    for (int r = 0; r < grid.length; r++) {
      for (int c = 0; c < grid[r].length; c++) {
        if (grid[r][c] == '1') {
          removeOneIsland(grid, r, c);
          count++;
        }
      }
    }

    return count;
  }

  private void removeOneIsland(char[][] grid, int row, int column) {
    Queue<Integer> positions = new LinkedList<>();
    positions.add(row);
    positions.add(column);

    while (!positions.isEmpty()) {
      int r = positions.poll();
      int c = positions.poll();
      if (r < 0 || r >= grid.length || c < 0 || c >= grid[0].length || grid[r][c] != '1') continue;

      grid[r][c] = '*'; // Prevent from duplicated access
      // left(r, c-1), up(r-1, c), right(r, c+1), down(r+1, c)
      positions.add(r);
      positions.add(c - 1);

      positions.add(r - 1);
      positions.add(c);

      positions.add(r);
      positions.add(c + 1);

      positions.add(r + 1);
      positions.add(c);
    }
  }

  private void dfs(char[][] grid, int r, int c) {
    if (r >= 0 && r < grid.length && c >= 0 && c < grid[0].length && grid[r][c] == '1') {
      grid[r][c] = '*';

      dfs(grid, r, c - 1);
      dfs(grid, r - 1, c);
      dfs(grid, r, c + 1);
      dfs(grid, r + 1, c);
    }
  }
}

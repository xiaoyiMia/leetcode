package io.mars.amazon;

/**
 * Given a 2D board and a word, find if the word exists in the grid.
 *
 * The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are
 * those horizontally or vertically neighboring. The same letter cell may not be used more than once.
 *
 * Example:
 *
 * board =
 * [
 *   ['A','B','C','E'],
 *   ['S','F','C','S'],
 *   ['A','D','E','E']
 * ]
 *
 * Given word = "ABCCED", return true.
 * Given word = "SEE", return true.
 * Given word = "ABCB", return false.
 *
 *
 * Constraints:
 *
 * board and word consists only of lowercase and uppercase English letters.
 * 1 <= board.length <= 200
 * 1 <= board[i].length <= 200
 * 1 <= word.length <= 10^3
 */
public class WordSearch {
  private static final int[] dr = {-1, 0, 1, 0};
  private static final int[] dc = {0, -1, 0, 1};

  public boolean exist(char[][] board, String word) {
    char[] letters = word.toCharArray();

    boolean[][] seen = new boolean[board.length][board[0].length];
    for(int r = 0; r < board.length; r++) {
      for(int c = 0; c < board[r].length; c++) {
        if(board[r][c] == letters[0]) {
          seen[r][c] = true;
          if(dfs(board, letters, r, c, 0, seen)) return true;
          seen[r][c] = false;
        }
      }
    }

    return false;
  }

  private boolean dfs(char[][] board, char[] letters, int sr, int sc, int tp, boolean[][] seen) {
    if(tp == letters.length - 1 && board[sr][sc] == letters[tp]) return true;
    else if(tp >= letters.length) return false;

    for(int i = 0; i < 4; i++) {
      int r = sr + dr[i];
      int c = sc + dc[i];
      if(0 <= r && r < board.length && 0 <= c && c < board[r].length && !seen[r][c] && letters[tp + 1] == board[r][c]) {
        seen[r][c] = true;
        if(dfs(board, letters, r, c, tp + 1, seen)) return true;
        seen[r][c] = false;
      }

    }
    return false;
  }
}

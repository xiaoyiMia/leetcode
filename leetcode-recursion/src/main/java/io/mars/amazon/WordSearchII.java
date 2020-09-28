package io.mars.amazon;

import java.util.*;

/**
 * Given a 2D board and a list of words from the dictionary, find all words in the board.
 *
 * Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those
 * horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.
 *
 *
 *
 * Example:
 *
 * Input:
 * board = [
 *   ['o','a','a','n'],
 *   ['e','t','a','e'],
 *   ['i','h','k','r'],
 *   ['i','f','l','v']
 * ]
 * words = ["oath","pea","eat","rain"]
 *
 * Output: ["eat","oath"]
 *
 *
 * Note:
 *
 * All inputs are consist of lowercase letters a-z.
 * The values of words are distinct.
 */
public class WordSearchII {
  private static final int[] dr = {-1, 0, 1, 0};
  private static final int[] dc = {0, 1, 0, -1};

  public List<String> findWords(char[][] board, String[] words) {
    Map<Character, TreeNode> dic = constructDicTree(words);

    List<String> results = new ArrayList<>();
    for(int i = 0; i < board.length; i++) {
      for(int j = 0; j < board[i].length; j++) {
        dfs(board, i, j, dic, results);
      }
    }
    return results;
  }

  private Map<Character, TreeNode> constructDicTree(String[] words) {
    Map<Character, TreeNode> roots = new HashMap<>();

    for (String word : words) {
      char[] letters = word.toCharArray();
      Map<Character, TreeNode> nodes = roots;

      TreeNode node = null;
      for (char letter : letters) {
        node = nodes.get(letter);
        if(node == null) {
          node = new TreeNode();
          nodes.put(letter, node);
        }
        nodes = node.children;
      }
      node.word = word;
    }

    return roots;
  }

  private void dfs(char[][] board, int sr, int sc, Map<Character, TreeNode> targets,
                   List<String> found) {
    TreeNode target = targets.get(board[sr][sc]);
    if(target == null) return;

    if(target.word != null) {
      found.add(target.word);
      target.word = null;

      if(target.children.isEmpty()) {
        return;
      }
    }

    char letter = board[sr][sc];
    board[sr][sc] = '*';
    for(int i = 0; i < 4; i++) {
      int r = sr + dr[i];
      int c = sc + dc[i];
      if(r >= 0 && r < board.length && c >= 0 && c < board[r].length) {
        dfs(board, r, c, target.children, found);
      }
    }
    board[sr][sc] = letter;
  }

  private class TreeNode {
    String word;
    Map<Character, TreeNode> children;

    TreeNode() {
      children = new HashMap<>();
    }
  }
}

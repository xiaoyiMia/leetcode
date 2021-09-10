package io.mars.google;

import io.mars.common.datastructure.TreeNode;

/**
 * Given the root of a complete binary tree, return the number of the nodes in the tree.
 * <p>
 * According to Wikipedia, every level, except possibly the last, is completely filled in a complete binary tree, and all nodes in the last level are as far left as possible. It can have between 1 and 2h nodes inclusive at the last level h.
 * <p>
 * Design an algorithm that runs in less than O(n) time complexity.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * Input: root = [1,2,3,4,5,6]
 * Output: 6
 * <p>
 * Example 2:
 * Input: root = []
 * Output: 0
 * <p>
 * Example 3:
 * Input: root = [1]
 * Output: 1
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the tree is in the range [0, 5 * 104].
 * 0 <= Node.val <= 5 * 104
 * The tree is guaranteed to be complete.
 */
public class CountCompleteTreeNodes {
  public int countNodes(TreeNode root) {
    return dfs(root);
  }

  private int dfs(TreeNode node) {
    if (node == null) return 0;

    int rightNodes = dfs(node.right);
    int leftNodes = dfs(node.left);
    return rightNodes + leftNodes + 1;
  }
}

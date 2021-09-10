package io.mars.tree.amazon;

import io.mars.common.datastructure.TreeNode;

/**
 * Given a non-empty binary tree, find the maximum path sum.
 * <p>
 * For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The path must contain at least one node and does not need to go through the root.
 * <p>
 * Example 1:
 * <p>
 * Input: [1,2,3]
 * <p>
 * 1
 * / \
 * 2   3
 * <p>
 * Output: 6
 * Example 2:
 * <p>
 * Input: [-10,9,20,null,null,15,7]
 * <p>
 * -10
 * / \
 * 9  20
 * /  \
 * 15   7
 * <p>
 * Output: 42
 */
public class BinaryTreeMaximumPathSum {

  public int maxPathSum(TreeNode root) {
    return findMaxPathSum(root, new int[]{Integer.MIN_VALUE});
  }

  //[goThroughMax, max]
  private int findMaxPathSum(TreeNode node, int[] max) {
    if (node == null) return 0;

    int leftPath = findMaxPathSum(node.left, max);
    int rightPath = findMaxPathSum(node.right, max);

    // Only when value > 0 could contribute to max sum.
    leftPath = leftPath > 0 ? leftPath : 0;
    rightPath = rightPath > 0 ? rightPath : 0;

    // Update max by comparing updated max on [left, right] and node + contributed left+right
    max[0] = Math.max(max[0], leftPath + node.val + rightPath);

    return node.val + Math.max(leftPath, rightPath);
  }

}

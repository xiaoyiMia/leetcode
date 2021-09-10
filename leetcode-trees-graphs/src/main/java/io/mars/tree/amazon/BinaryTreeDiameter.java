package io.mars.tree.amazon;

import io.mars.common.datastructure.TreeNode;

public class BinaryTreeDiameter {
  public int diameterOfBinaryTree(TreeNode root) {
    int[] maxDiameter = new int[]{0};
    getHeightAndUpdateDiameter(root, maxDiameter);
    return maxDiameter[0];
  }

  private int getHeightAndUpdateDiameter(TreeNode node, int[] maxDiameter) {
    if(node == null) return 0;

    int leftHeight = getHeightAndUpdateDiameter(node.left, maxDiameter);
    int rightHeight = getHeightAndUpdateDiameter(node.right, maxDiameter);

    maxDiameter[0] = Math.max(maxDiameter[0], leftHeight + rightHeight);
    return Math.max(leftHeight, rightHeight) + 1;
  }
}

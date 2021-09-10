package io.mars.tree.amazon;

import io.mars.common.datastructure.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Given a binary tree, return the zigzag level order traversal of its nodes' values. (ie, from left to right,
 * then right to left for the next level and alternate between).
 * <p>
 * For example:
 * Given binary tree [3,9,20,null,null,15,7],
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * return its zigzag level order traversal as:
 * [
 * [3],
 * [20,9],
 * [15,7]
 * ]
 */
public class BinaryTreeZigzag {
  public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
    List<List<Integer>> result = new ArrayList<>();
    if (root == null) return result;

    Stack<TreeNode> nodes = new Stack<>();
    nodes.push(root);
    int level = 0;
    while (!nodes.isEmpty()) {
      List<Integer> currentLevel = new ArrayList<>();
      Stack<TreeNode> nextLevel = new Stack<>();
      while (!nodes.isEmpty()) {
        TreeNode node = nodes.pop();
        currentLevel.add(node.val);
        readChildren(nextLevel, node, level);
      }
      result.add(currentLevel);
      nodes = nextLevel;
      level++;
    }
    return result;
  }

  private void readChildren(Stack<TreeNode> children, TreeNode node, int level) {
    if (level % 2 == 0) {
      if (node.left != null) children.push(node.left);
      if (node.right != null) children.push(node.right);
    } else {
      if (node.right != null) children.push(node.right);
      if (node.left != null) children.push(node.left);
    }
  }
}

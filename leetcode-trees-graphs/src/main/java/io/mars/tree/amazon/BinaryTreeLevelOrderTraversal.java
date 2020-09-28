package io.mars.tree.amazon;

import io.mars.tree.datastructure.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Given a binary tree, return the level order traversal of its nodes' values.
 * (ie, from left to right, level by level).
 * <p>
 * For example:
 * Given binary tree [3,9,20,null,null,15,7]
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * return its level order traversal as:
 * [
 * [3],
 * [9,20],
 * [15,7]
 * ]
 */
public class BinaryTreeLevelOrderTraversal {

  public List<List<Integer>> levelOrder(TreeNode root) {
    List<List<Integer>> result = new ArrayList<List<Integer>>();
    if (root == null) return result;

    Queue<TreeNode> nodes = new LinkedList<TreeNode>();
    nodes.add(root);
    while (!nodes.isEmpty()) {
      Queue<TreeNode> children = new LinkedList<TreeNode>();
      List<Integer> values = readValueAndChildren(nodes, children);
      result.add(values);
      nodes = children;
    }
    return result;
  }

  private List<Integer> readValueAndChildren(Queue<TreeNode> nodes, Queue<TreeNode> children) {
    List<Integer> values = new ArrayList<Integer>();
    while (!nodes.isEmpty()) {
      TreeNode node = nodes.poll();
      values.add(node.val);
      if (node.left != null) children.add(node.left);
      if (node.right != null) children.add(node.right);
    }
    return values;
  }
}

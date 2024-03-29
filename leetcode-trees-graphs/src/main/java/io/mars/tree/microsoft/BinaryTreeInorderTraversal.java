package io.mars.tree.microsoft;

import io.mars.common.datastructure.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Given the root of a binary tree, return the inorder traversal of its nodes' values.
 * <p>
 * <p>
 * Example 1:
 * Input: root = [1,null,2,3]
 * Output: [1,3,2]
 * <p>
 * Example 2:
 * Input: root = []
 * Output: []
 * <p>
 * Example 3:
 * Input: root = [1]
 * Output: [1]
 * <p>
 * Example 4:
 * Input: root = [1,2]
 * Output: [2,1]
 * <p>
 * Example 5:
 * Input: root = [1,null,2]
 * Output: [1,2]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the tree is in the range [0, 100].
 * -100 <= Node.val <= 100
 * <p>
 * <p>
 * Follow up:
 * <p>
 * Recursive solution is trivial, could you do it iteratively?
 */
public class BinaryTreeInorderTraversal {

  public List<Integer> inorderTraversal(TreeNode root) {
    List<Integer> result = new ArrayList<>();
    Stack<TreeNode> nodeStack = new Stack<>();
    TreeNode currentNode = root;

    while (currentNode != null) {
      currentNode = processNode(result, nodeStack, currentNode);
    }
    return result;
  }

  private TreeNode processNode(List<Integer> result, Stack<TreeNode> nodeStack, TreeNode currentNode) {
    // If current node have left child, return left child.
    if (currentNode.left != null) {
      TreeNode node = currentNode.left;
      currentNode.left = null;
      nodeStack.push(currentNode);
      return node;
    }

    // Otherwise, add current node's value into result;
    result.add(currentNode.val);
    // And then, check right child
    if (currentNode.right != null) {
      return currentNode.right;
    } else {
      return nodeStack.isEmpty() ? null : nodeStack.pop();
    }
  }
}

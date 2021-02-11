package io.mars.tree.microsoft;

import io.mars.tree.datastructure.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given preorder and inorder traversal of a tree, construct the binary tree.
 * <p>
 * Note:
 * You may assume that duplicates do not exist in the tree.
 * <p>
 * For example, given
 * <p>
 * preorder = [3,9,20,15,7]
 * inorder = [9,3,15,20,7]
 * Return the following binary tree:
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 */
public class ConstructBinaryTreeFromPreorderInorderTracersal {
  public TreeNode buildTree(int[] preorder, int[] inorder) {
    if(preorder == null || preorder.length == 0) return null;

    Queue<Integer> preorderNodes = putInQueue(preorder);
    return constructTree(preorderNodes, inorder, 0, inorder.length - 1);
  }

  private TreeNode constructTree(Queue<Integer> preorderNodes, int[] inorder, int leftIndex, int rightIndex) {
    if(preorderNodes.isEmpty() || leftIndex > rightIndex) return null;

    int position = findNode(inorder, leftIndex, rightIndex, preorderNodes.poll());
    TreeNode node = new TreeNode(inorder[position]);

    node.left = constructTree(preorderNodes, inorder, leftIndex, position - 1);
    node.right = constructTree(preorderNodes, inorder, position + 1, rightIndex);
    return node;
  }

  private Queue<Integer> putInQueue(int[] preorder) {
    Queue<Integer> nodes = new LinkedList<>();
    for (int value : preorder) {
      nodes.add(value);
    }
    return nodes;
  }

  private int findNode(int[] values, int leftIndex, int rightIndex, int target) {
    while(leftIndex <= rightIndex) {
      if(values[leftIndex] == target) return leftIndex;
      if(values[rightIndex] == target) return rightIndex;
      leftIndex ++;
      rightIndex --;
    }
    return -1;
  }
}

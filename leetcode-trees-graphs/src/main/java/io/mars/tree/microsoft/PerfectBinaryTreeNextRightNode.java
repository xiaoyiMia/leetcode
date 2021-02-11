package io.mars.tree.microsoft;

import org.w3c.dom.Node;

/**
 * You are given a perfect binary tree where all leaves are on the same level, and every parent has two children.
 * The binary tree has the following definition:
 * <p>
 * struct Node {
 * int val;
 * Node *left;
 * Node *right;
 * Node *next;
 * }
 * Populate each next pointer to point to its next right node. If there is no next right node,
 * the next pointer should be set to NULL.
 * <p>
 * Initially, all next pointers are set to NULL.
 * <p>
 * <p>
 * <p>
 * Follow up:
 * <p>
 * You may only use constant extra space.
 * Recursive approach is fine, you may assume implicit stack space does not count as extra space for this problem.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * <p>
 * Input: root = [1,2,3,4,5,6,7]
 * Output: [1,#,2,3,#,4,5,6,7,#]
 * Explanation: Given the above perfect binary tree (Figure A),
 * your function should populate each next pointer to point to its next right node, just like in Figure B.
 * The serialized output is in level order as connected by the next pointers, with '#' signifying the end of each level.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the given tree is less than 4096.
 * -1000 <= node.val <= 1000
 */
public class PerfectBinaryTreeNextRightNode {
  public Node connect(Node root) {
    if(root == null) return null;

    populatingNextRight(root.left, root.right);
    populatingNextRight(root.right, null);

    return root;
  }

  /**
   * If it's a left child, next point to parent's right child; otherwise next point to parent.next.left
   */
  private void populatingNextRight(Node node, Node nextRightNode) {
    if(node == null) return;

    node.next = nextRightNode;

    populatingNextRight(node.left, node.right);
    populatingNextRight(node.right, nextRightNode == null ? null : nextRightNode.left);
  }


  class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}

    public Node(int _val) {
      val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
      val = _val;
      left = _left;
      right = _right;
      next = _next;
    }
  }
}

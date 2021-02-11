package io.mars.tree.microsoft;

/**
 * Given a binary tree
 *
 * struct Node {
 *   int val;
 *   Node *left;
 *   Node *right;
 *   Node *next;
 * }
 * Populate each next pointer to point to its next right node. If there is no next right node,
 * the next pointer should be set to NULL.
 *
 * Initially, all next pointers are set to NULL.
 *
 *
 * Follow up:
 *
 * You may only use constant extra space.
 * Recursive approach is fine, you may assume implicit stack space does not count as extra space for this problem.
 *
 *
 * Example 1:
 * Input: root = [1,2,3,4,5,null,7]
 * Output: [1,#,2,3,#,4,5,7,#]
 * Explanation: Given the above binary tree (Figure A), your function should populate each next pointer to point to
 * its next right node, just like in Figure B.
 * The serialized output is in level order as connected by the next pointers, with '#' signifying the end of each level.
 *
 *
 * Constraints:
 *
 * The number of nodes in the given tree is less than 6000.
 * -100 <= node.val <= 100
 */
public class BinaryTreeNextRightNode {
  private Node leftmost;
  private Node previous;

  public Node connect(Node root) {
    this.leftmost = root;

    Node current;
    while(this.leftmost != null) {
      // Current Node starts from the left most node on current level. Then start to process nodes for next level.
      current = leftmost;
      // At the beginning of the next level, leftmost and previous node starts from NULL.
      this.leftmost = null;
      this.previous = null;

      // Process each node on the next level, and find out the leftmost non-null node.
      while (current != null) {
        processNode(current.left);
        processNode(current.right);
        current = current.next;
      }
    }
    return root;
  }

  /**
   *
   * @param current The node been processing currently, could be null.
   */
  private void processNode(Node current) {
    if(current == null) return;

    if(this.previous == null) this.leftmost = current;
    else this.previous.next = current;
    this.previous = current;
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

package io.mars.tree.amazon;

import io.mars.common.datastructure.TreeNode;

/**
 * Given a binary search tree (BST), find the lowest common ancestor (LCA) of two given nodes in the BST.
 * <p>
 * According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes p and q
 * as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”
 * <p>
 * <p>
 * <p>
 * Example 1:
 * Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
 * Output: 6
 * Explanation: The LCA of nodes 2 and 8 is 6.
 * <p>
 * Example 2:
 * Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
 * Output: 2
 * Explanation: The LCA of nodes 2 and 4 is 2, since a node can be a descendant of itself according to the LCA
 * definition.
 * <p>
 * Example 3:
 * Input: root = [2,1], p = 2, q = 1
 * Output: 2
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the tree is in the range [2, 105].
 * -109 <= Node.val <= 109
 * All Node.val are unique.
 * p != q
 * p and q will exist in the BST.
 */
public class LowestCommonAncestorOfBinarySearchTree {

  public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    // If one of node equal root OR they are at two sides of the root, then return root
    if ((root.val <= p.val && root.val >= q.val) || (root.val >= p.val && root.val <= q.val)) return root;
      // If they are both ar right side of the root, search the right tree; Otherwise, search the left tree
    else if (root.val < p.val && root.val < q.val) return lowestCommonAncestor(root.right, p, q);
    else return lowestCommonAncestor(root.left, p, q);
  }

}

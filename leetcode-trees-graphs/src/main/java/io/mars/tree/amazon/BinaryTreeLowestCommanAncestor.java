package io.mars.tree.amazon;

import io.mars.tree.datastructure.TreeNode;

import java.util.HashSet;
import java.util.Set;

public class BinaryTreeLowestCommanAncestor {
  public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    TreeNode[] found = new TreeNode[1];
    helper(root, p, q, found);
    return found[0];
  }

  public TreeNode solution2(TreeNode root, TreeNode p, TreeNode q) {
    if(root == null) return null;

    if(root.equals(p) || root.equals(q)) return root;

    TreeNode left = solution2(root.left, p, q);
    TreeNode right = solution2(root.right, p, q);
    if(left != null && right != null) return root;

    return left == null ? right : left;
  }

  private Set<TreeNode> helper(TreeNode node, TreeNode p, TreeNode q, TreeNode[] found) {
    if(found[0] != null) return null;
    if(node == null) return new HashSet<>();

    Set<TreeNode> nodes = mergeIfNotNull(helper(node.left, p, q, found), helper(node.right, p, q, found), node);
    if(nodes == null) return null;

    if(nodes.contains(p) && nodes.contains(q)) {
      found[0] = node;
      return null;
    } else {
      return nodes;
    }
  }

  private Set<TreeNode> mergeIfNotNull(Set<TreeNode> lc, Set<TreeNode> rc, TreeNode node) {
    if(lc == null || rc == null) return null;

    lc.addAll(rc);
    lc.add(node);
    return lc;
  }

}

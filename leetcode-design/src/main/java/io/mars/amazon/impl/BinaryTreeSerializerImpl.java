package io.mars.amazon.impl;

import io.mars.amazon.BinaryTreeSerializer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTreeSerializerImpl implements BinaryTreeSerializer {

  @Override
  public String serialize(TreeNode root) {
    List<String> nodeValues = new ArrayList<>();

    Queue<TreeNode> nodes = new LinkedList<>();
    nodes.add(root);
    while(!nodes.isEmpty()) {
      TreeNode currentNode = nodes.poll();

      if(currentNode == null) {
        nodeValues.add(null);
      } else {
        nodeValues.add(String.valueOf(currentNode.val));
        nodes.add(currentNode.left);
        nodes.add(currentNode.right);
      }
    }

    // Remove NULL from the end
    for (int i = nodeValues.size() - 1; i >= 0; i--) {
      if(nodeValues.get(i) != null) break;
      nodeValues.remove(i);
    }

    return String.join(",", nodeValues);
  }

  @Override
  public TreeNode deserialize(String data) {
    String[] values = data.split(",");
    if(values.length <= 0) return null;

    Queue<TreeNode> nodes = new LinkedList<>();
    TreeNode root = new TreeNode(Integer.valueOf(values[0]));
    nodes.add(root);

    int index = 1;
    while(index < values.length) {
      TreeNode currentNode = nodes.poll();
      currentNode.left = values[index].equals("null") ? null : new TreeNode(Integer.valueOf(values[index]));
      index++;
      if(index < values.length) {
        currentNode.right = values[index].equals("null") ? null : new TreeNode(Integer.valueOf(values[index]));
        index++;
      }

      if(currentNode.left != null) nodes.add(currentNode.left);
      if(currentNode.right != null) nodes.add(currentNode.right);
    }

    return root;
  }
}

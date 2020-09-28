package io.mars.amazon;

import java.util.HashMap;
import java.util.Map;

public class CopyListRandomPointer {
  public Node copyRandomList(Node head) {
    if (head == null) return null;

    // Insert copied nodes into list. To make list becomes: A -> A' -> B -> B' -> ...
    Node curNode = head;
    while (curNode != null) {
      Node copy = new Node(curNode.val);
      copy.next = curNode.next;
      curNode.next = copy;
      curNode = copy.next;
    }
    Node copiedHead = head.next;

    // Copy the random pointer
    curNode = head;
    while (curNode != null) {
      Node copy = curNode.next;
      copy.random = curNode.random == null ? null : curNode.random.next;
      curNode = copy.next;
    }

    // Split to original list and copied list
    curNode = head;
    while (curNode != null) {
      Node copy = curNode.next;
      curNode.next = copy.next;
      copy.next = curNode.next == null ? null : curNode.next.next;

      curNode = curNode.next;

    }

    return copiedHead;
  }

  public Node solution2(Node head) {
    Map<Node, Node> copyMap = new HashMap<>();

    Node curNode = head;
    while (curNode != null) {
      Node copy = copyNode(copyMap, curNode);
      copy.next = copyNode(copyMap, curNode.next);
      copy.random = copyNode(copyMap, curNode.random);

      curNode = curNode.next;
    }

    return copyMap.get(head);
  }

  private Node copyNode(Map<Node, Node> copyMap, Node source) {
    if(source == null) return null;
    else return copyMap.computeIfAbsent(source, k -> new Node(source.val));
  }

  class Node {
    int val;
    Node next;
    Node random;

    Node(int val) {
      this.val = val;
      this.next = null;
      this.random = null;
    }
  }
}

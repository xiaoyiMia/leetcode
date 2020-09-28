package io.mars.amazon;

import io.mars.datastructure.ListNode;

/**
 * Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.
 * <p>
 * k is a positive integer and is less than or equal to the length of the linked list. If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.
 * <p>
 * Example:
 * <p>
 * Given this linked list: 1->2->3->4->5
 * <p>
 * For k = 2, you should return: 2->1->4->3->5
 * <p>
 * For k = 3, you should return: 3->2->1->4->5
 * <p>
 * Note:
 * <p>
 * Only constant extra memory is allowed.
 * You may not alter the values in the list's nodes, only nodes itself may be changed.
 */
public class ReverseNodesInKGroup {
  public ListNode reverseKGroup(ListNode head, int k) {
    ListNode tail = head;

    ListNode[] nodes = reverseKNodes(head, k);

    head = nodes[0];

    // The List is not to the end, looking for next k group
    while (nodes[1] != null) {
      ListNode tempHeader = nodes[1];
      nodes = reverseKNodes(nodes[1], k);
      tail.next = nodes[0];
      tail = tempHeader;
    }

    return head;
  }

  private ListNode[] reverseKNodes(ListNode head, int k) {
    int count = k - 1;
    ListNode current = head.next;
    ListNode tail = head;

    while (count > 0 && current != null) {
      // Reverse head and current node
      ListNode temp = current.next;
      current.next = head;
      head = current;
      current = temp;
      count--;

      //if current == null, reverse back
      if (current == null && count > 0) {
        head = reverseKNodes(head, k - count)[0];
      }

    }
    if (tail != head) tail.next = null;
    return new ListNode[]{head, current};
  }
}

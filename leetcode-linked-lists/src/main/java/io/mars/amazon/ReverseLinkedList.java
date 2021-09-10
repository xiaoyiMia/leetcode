package io.mars.amazon;

import io.mars.common.datastructure.ListNode;

/**
 * Reverse a singly linked list.
 *
 * Example:
 *
 * Input: 1->2->3->4->5->NULL
 * Output: 5->4->3->2->1->NULL
 * Follow up:
 *
 * A linked list can be reversed either iteratively or recursively. Could you implement both?
 */
public class ReverseLinkedList {
  public ListNode reverseList(ListNode head) {
    ListNode pre = null;
    ListNode node = head;
    while(node != null) {
      ListNode temp = node.next;
      node.next = pre;
      pre = node;
      node = temp;
    }

    return pre;
  }

  public ListNode reverseRecursively(ListNode head) {
    if(head == null) return null;

    ListNode[] reversedHead = new ListNode[1];
    reverse(head, reversedHead);
    head.next = null;
    return reversedHead[0];
  }

  private ListNode reverse(ListNode node, ListNode[] reversedHead) {
    if(node.next == null) reversedHead[0] = node;
    else {
      reverse(node.next, reversedHead).next = node;
    }
    return node;
  }
}

package io.mars.microsoft;

import io.mars.common.datastructure.ListNode;

/**
 * You are given two non-empty linked lists representing two non-negative integers. The most significant digit comes
 * first and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
 * <p>
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 * <p>
 * Follow up:
 * What if you cannot modify the input lists? In other words, reversing the lists is not allowed.
 * <p>
 * Example:
 * <p>
 * Input: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 8 -> 0 -> 7
 */
public class AddTwoNumbers {
  public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    return recursiveSolution(l1, l2);
  }

  /**
   * Resolve the problem by recursively sum nodes.
   */
  private ListNode recursiveSolution(ListNode l1, ListNode l2) {
    if (l1 == null) return l2;
    else if (l2 == null) return l1;

    // Order list by length, and calculate length difference
    int l1Length = getLength(l1);
    int l2Length = getLength(l2);

    ListNode head = new ListNode(0);
    if (l1Length > l2Length) {
      head.val = sumList(l1, l2, l1Length - l2Length, head);
    } else {
      head.val = sumList(l2, l1, l2Length - l1Length, head);
    }

    return head.val == 0 ? head.next : head;
  }

  /**
   * Resolve the problem by reversing result list.
   */
  private ListNode reverseResultList(ListNode l1, ListNode l2) {
    if (l1 == null) return l2;
    else if (l2 == null) return l1;

    // Order list by length, and calculate length difference
    int l1Length = getLength(l1);
    int l2Length = getLength(l2);
    // Sum each node
    ListNode head;
    if (l1Length > l2Length) {
      head = sumEachNodeReversely(l1, l2, l1Length - l2Length);
    } else {
      head = sumEachNodeReversely(l2, l1, l2Length - l1Length);
    }
    // Manage carry over
    head = reverseList(head);
    manageCarry(head);
    head = reverseList(head);
    return head.val == 0 ? head.next : head;
  }

  private ListNode sumEachNodeReversely(ListNode longerList, ListNode shortList, int lengthDiff) {
    ListNode head = new ListNode(0);
    ListNode currentNode = head;
    while (lengthDiff > 0) {
      currentNode.next = new ListNode(longerList.val);
      currentNode = currentNode.next;

      longerList = longerList.next;
      lengthDiff--;
    }

    while (longerList != null) {
      currentNode.next = new ListNode(longerList.val + shortList.val);
      currentNode = currentNode.next;

      longerList = longerList.next;
      shortList = shortList.next;
    }
    return head;
  }

  private void manageCarry(ListNode currentNode) {
    int carry = 0;
    while (currentNode != null) {
      int value = currentNode.val + carry;
      currentNode.val = value % 10;
      carry = value / 10;

      currentNode = currentNode.next;
    }
  }

  private int sumList(ListNode longerList, ListNode shortList, int lengthDiff, ListNode mustBeHead) {
    if (longerList == null) return 0;

    int newValue;
    if (lengthDiff == 0) {
      int plusOne = sumList(longerList.next, shortList.next, lengthDiff, mustBeHead);
      newValue = plusOne + longerList.val + shortList.val;

    } else {
      int plusOne = sumList(longerList.next, shortList, lengthDiff - 1, mustBeHead);
      newValue = plusOne + longerList.val;
    }
    mustBeHead.next = new ListNode(newValue % 10, mustBeHead.next);

    return newValue / 10;
  }

  private int getLength(ListNode node) {
    int length = 0;
    while (node != null) {
      length++;
      node = node.next;
    }
    return length;
  }

  private ListNode reverseList(ListNode list) {
    ListNode currentNode = list;
    ListNode nextNode = currentNode.next;

    while (nextNode != null) {
      ListNode tempNode = nextNode.next;
      nextNode.next = currentNode;

      currentNode = nextNode;
      nextNode = tempNode;
    }

    list.next = null;
    return currentNode;
  }
}

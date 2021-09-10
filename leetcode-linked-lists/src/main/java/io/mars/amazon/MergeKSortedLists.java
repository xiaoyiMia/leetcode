package io.mars.amazon;

import io.mars.common.datastructure.ListNode;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class MergeKSortedLists {

  public ListNode mergeKLists(ListNode[] lists) {
    if(lists == null || lists.length == 0) return null;

    ListNode curList = lists[0];
    for (int i = 1; i < lists.length; i++) {
      curList = mergeLists(curList, lists[i]);
    }
    return curList;
  }

  private ListNode mergeLists(ListNode l1, ListNode l2) {
    ListNode node1 = l1;
    ListNode node2 = l2;

    ListNode ordered = new ListNode();
    ListNode head = ordered;
    while(node1 != null && node2 != null) {
      if(node1.val <= node2.val) {
        ordered.next = node1;
        ordered = node1;
        node1 = node1.next;
      } else {
        ordered.next = node2;
        ordered = node2;
        node2 = node2.next;
      }
    }
    if(node1 != null) ordered.next = node1;
    else ordered.next = node2;

    return head.next;
  }
  
  public ListNode mergeKListsCompareEachNode(ListNode[] lists) {
    if(lists == null || lists.length == 0) return null;

    ListNode merged = new ListNode();
    ListNode head = merged;
    int position = findMinNode(lists);;
    while(position >= 0) {
      merged.next = lists[position];
      merged = merged.next;
      lists[position] = lists[position].next;

      position = findMinNode(lists);
    }

    return head.next;
  }

  private int findMinNode(ListNode[] nodes) {
    int minP = -1;
    for(int i = 0; i < nodes.length; i++) {
      if(nodes[i] != null) minP = minP == -1 ? i : (nodes[i].val < nodes[minP].val ? i : minP);
    }

    return minP;
  }
  
  public ListNode mergeKListsPriorityQueue(ListNode[] lists) {
    Queue<ListNode> sortedQueue = new PriorityQueue<>(Comparator.comparing((n) -> n.val));
    for (ListNode list : lists) {
      if(list != null) sortedQueue.add(list);
    }

    ListNode dummyHead = new ListNode();
    ListNode node = dummyHead;

    while(!sortedQueue.isEmpty()) {
      node.next = sortedQueue.poll();
      node = node.next;

      if(node.next != null) sortedQueue.add(node.next);
    }
    return dummyHead.next;
  }

  public ListNode mergeKListsGrouply(ListNode[] lists) {
    if(lists == null || lists.length == 0) return null;

    while(lists.length > 1) {
      ListNode[] grouped = new ListNode[(lists.length - 1) / 2 + 1 ];
      for(int i = 0; i < grouped.length; i++) {
        int index = i * 2;
        if(index + 2 > lists.length) grouped[i] = lists[index];
        else grouped[i] = mergeLists(lists[index], lists[index + 1]);
      }
      lists = grouped;
    }
    return lists[0];
  }


}

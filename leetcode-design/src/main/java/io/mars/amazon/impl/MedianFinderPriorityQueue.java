package io.mars.amazon.impl;

import io.mars.amazon.MedianFinder;

import java.util.Comparator;
import java.util.PriorityQueue;

public class MedianFinderPriorityQueue implements MedianFinder {
  private PriorityQueue<Integer> smallerQueue = new PriorityQueue<>(Comparator.reverseOrder());
  private PriorityQueue<Integer> largerQueue = new PriorityQueue<>();
  private Integer median = null;

  @Override
  public void addNum(int num) {
    if(median == null) {
      if(smallerQueue.size() == 0 && largerQueue.size() == 0) {
        median = num;
        return;
      }

      if(smallerQueue.peek() > num) {
        smallerQueue.add(num);
        median = smallerQueue.poll();
      } else if(largerQueue.peek() < num) {
        largerQueue.add(num);
        median = largerQueue.poll();
      } else median = num;
    } else {
      int largerNum = Math.max(median, num);
      largerQueue.add(largerNum);
      smallerQueue.add(median + num - largerNum);
      median = null;
    }
  }

  @Override
  public double findMedian() {
    if(median != null) {
      return median;
    }else {
      return smallerQueue.size() == 0 ? 0 : (smallerQueue.peek() + largerQueue.peek()) / 2.00;
    }
  }
}

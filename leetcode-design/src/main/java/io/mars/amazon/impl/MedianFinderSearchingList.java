package io.mars.amazon.impl;

import io.mars.amazon.MedianFinder;

import java.util.ArrayList;
import java.util.List;

public class MedianFinderSearchingList implements MedianFinder {
  private List<Integer> sortedList = new ArrayList<>();

  @Override
  public void addNum(int num) {
    sortedList.add(findPosition(num, 0, sortedList.size() - 1), num);
  }

  @Override
  public double findMedian() {
    if(sortedList.isEmpty()) return 0;

    int size = sortedList.size();
    int midPosition = size / 2;
    return size % 2 == 0
        ? (sortedList.get(midPosition) + sortedList.get(midPosition - 1)) / 2.00
        : sortedList.get(midPosition);
  }

  private int findPosition(int num, int left, int right) {
    if(right < 0) return 0;

    if(left == right && sortedList.get(left) <= num) return left + 1;
    else if(left == right && sortedList.get(right) > num) return right;

    int position = (left + right) / 2;
    if(sortedList.get(position) <= num) return findPosition(num, left + 1, right);
    else return findPosition(num, left, right - 1);
  }
}

package io.mars.amazon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Given a collection of intervals, merge all overlapping intervals.
 *
 * Example 1:
 *
 * Input: [[1,3],[2,6],[8,10],[15,18]]
 * Output: [[1,6],[8,10],[15,18]]
 * Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
 * Example 2:
 *
 * Input: [[1,4],[4,5]]
 * Output: [[1,5]]
 * Explanation: Intervals [1,4] and [4,5] are considered overlapping.
 */
public class MergeIntervals {
  public int[][] merge(int[][] intervals) {
    if(intervals == null || intervals.length == 0) return new int[0][0];

    List<int[]> result = new ArrayList<>();
    result.add(intervals[0]);
    Arrays.sort(intervals, new sortAlgo());

    int i = 0, j = 1;
    while(j < intervals.length) {
      if(intervals[j][0] >= result.get(i)[0] && intervals[j][0] <= result.get(i)[1]) {
        if(intervals[j][1] > result.get(i)[1]) result.get(i)[1] = intervals[j][1];
      } else {
        result.add(intervals[j]);
        i++;
      }
      j++;
    }

    return result.toArray(new int[result.size()][]);
  }

  class sortAlgo implements Comparator<int[]> {
    public int compare(int[] a, int[] b){
      return a[0]-b[0];
    }
  }
}

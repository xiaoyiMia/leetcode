package io.mars.google;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).
 * <p>
 * You may assume that the intervals were initially sorted according to their start times.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
 * Output: [[1,5],[6,9]]
 * <p>
 * Example 2:
 * Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
 * Output: [[1,2],[3,10],[12,16]]
 * Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].
 * <p>
 * Example 3:
 * Input: intervals = [], newInterval = [5,7]
 * Output: [[5,7]]
 * <p>
 * Example 4:
 * Input: intervals = [[1,5]], newInterval = [2,3]
 * Output: [[1,5]]
 * <p>
 * Example 5:
 * Input: intervals = [[1,5]], newInterval = [2,7]
 * Output: [[1,7]]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 0 <= intervals.length <= 104
 * intervals[i].length == 2
 * 0 <= intervals[i][0] <= intervals[i][1] <= 105
 * intervals is sorted by intervals[i][0] in ascending order.
 * newInterval.length == 2
 * 0 <= newInterval[0] <= newInterval[1] <= 105
 */
public class InsertInternal {
  public int[][] insert(int[][] intervals, int[] newInterval) {
    List<int[]> newIntervals = new ArrayList<>();
    int positionX = findInsertPosition(intervals, 0, intervals.length - 1, newInterval[0]);
    int positionY = findInsertPosition(intervals, 0, intervals.length - 1, newInterval[1]);

    // update the right-side value of newInterval, if it's smaller than previous right
    if(positionY> 0) {
      newInterval[1] = Math.max(intervals[positionY - 1][1], newInterval[1]);
    }

    insertElements(newIntervals, intervals, 0, positionX - 1);
    // if x == 0 or x > previous right, insert the newInterval; otherwise, update previous right.
    if (positionX == 0 || newInterval[0] > intervals[positionX - 1][1]) {
      newIntervals.add(newInterval);
    } else {
      intervals[positionX - 1][1] = newInterval[1];
    }
    insertElements(newIntervals, intervals, positionY, intervals.length - 1);

    return newIntervals.toArray(new int[0][]);
  }

  private void insertElements(List<int[]> toInsert, int[][] origin, int start, int end) {
    while (start <= end && start < origin.length) {
      toInsert.add(origin[start]);
      start++;
    }
  }

  private int findInsertPosition(int[][] intervals, int left, int right, int target) {
    if (left > right) return left;

    int pivot = (left + right) / 2;
    int pivotValue = intervals[pivot][0];

    if (pivotValue == target) return pivot + 1;
    else if (pivotValue < target) return findInsertPosition(intervals, pivot + 1, right, target);
    else return findInsertPosition(intervals, left, pivot - 1, target);
  }
}

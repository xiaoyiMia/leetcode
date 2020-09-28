package io.mars.amazon;

import java.util.*;

/**
 * Given an array of meeting time intervals consisting of start and end times
 * [[s1,e1],[s2,e2],...] (si < ei), find the minimum number of conference rooms required.
 *
 * Example 1:
 *
 * Input: [[0, 30],[5, 10],[15, 20]]
 * Output: 2
 * Example 2:
 *
 * Input: [[7,10],[2,4]]
 * Output: 1
 * NOTE: input types have been changed on April 15, 2019. Please reset to default code definition
 * to get new method signature.
 */
public class MeetingRoomsII {
  public int minMeetingRooms(int[][] intervals) {
    // Sorted intervals
    List<int[]> sortedTime = new ArrayList<>();
    Arrays.stream(intervals).sorted(Comparator.comparing(arr -> arr[0])).forEach(interval -> {
      int index = sortedTime.size();
      sortedTime.add(new int[]{interval[0], index});
      sortedTime.add(new int[]{interval[1], index});
    });
    sortedTime.sort(Comparator.comparing(time -> ((int[])time)[0]).thenComparing(time -> ((int[])time)[1]));

    int rooms = 0;
    int intervalCount = 0;
    Set<Integer> roomQueue = new HashSet<>();
    for(int[] time: sortedTime) {
      if(intervalCount >= intervals.length) break;

      Integer interval = time[1];
      if(!roomQueue.contains(interval)) {
        intervalCount++;
        roomQueue.add(interval);
      } else {
        rooms = Math.max(rooms, roomQueue.size());
        roomQueue.remove(interval);
      }
    }

    return Math.max(rooms, roomQueue.size());
  }

  public int solution2(int[][] intervals) {
    int[] starts = new int[intervals.length];
    int[] ends = new int[intervals.length];
    for(int i = 0; i < intervals.length; i++) {
      starts[i] = intervals[i][0];
      ends[i] = intervals[i][1];
    }

    Arrays.sort(starts);
    Arrays.sort(ends);

    int rooms = 0;
    for(int i = 0, j = 0; i < intervals.length; i++) {
      if(starts[i] < ends[j]) rooms++;
      else j++;
    }
    return rooms;
  }
}

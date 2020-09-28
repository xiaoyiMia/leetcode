package io.mars.amazon;

import org.w3c.dom.Node;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * We have a list of points on the plane.  Find the K closest points to the origin (0, 0).
 *
 * (Here, the distance between two points on a plane is the Euclidean distance.)
 *
 * You may return the answer in any order.  The answer is guaranteed to be unique (except for the order that it is in.)
 *
 *
 *
 * Example 1:
 *
 * Input: points = [[1,3],[-2,2]], K = 1
 * Output: [[-2,2]]
 * Explanation:
 * The distance between (1, 3) and the origin is sqrt(10).
 * The distance between (-2, 2) and the origin is sqrt(8).
 * Since sqrt(8) < sqrt(10), (-2, 2) is closer to the origin.
 * We only want the closest K = 1 points from the origin, so the answer is just [[-2,2]].
 * Example 2:
 *
 * Input: points = [[3,3],[5,-1],[-2,4]], K = 2
 * Output: [[3,3],[-2,4]]
 * (The answer [[-2,4],[3,3]] would also be accepted.)
 *
 *
 * Note:
 *
 * 1 <= K <= points.length <= 10000
 * -10000 < points[i][0] < 10000
 * -10000 < points[i][1] < 10000
 */
public class KClosestPointsToOrigin {
  public int[][] kClosest(int[][] points, int K) {
    int left = 0;
    int right = points.length - 1;

    while(left < right) {
      int p = findP(points, left, right);
      if(p < K) left = p + 1;
      else if(p > K) right = p - 1;
      else break;
    }

    return Arrays.copyOfRange(points, 0, K);
  }

  // Quick select to find a position that all values at left positions are smaller
  private int findP(int[][] points, int left, int right) {
    int[] p = points[left];
    while (left < right) {
      while (left < right && compare(p, points[right]) <= 0) right--;
      points[left] = points[right];
      while (left < right && compare(points[left], p) <= 0) left++;
      points[right] = points[left];
    }
    points[left] = p;
    return left;
  }

  private int compare(int[] a, int[] b) {
    return (a[0] * a[0] + a[1] * a[1]) - (b[0] * b[0] + b[1] * b[1]);
  }

}

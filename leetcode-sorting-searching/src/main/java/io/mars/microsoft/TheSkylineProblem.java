package io.mars.microsoft;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A city's skyline is the outer contour of the silhouette formed by all the buildings in that city when viewed from a distance. Given the locations and heights of all the buildings, return the skyline formed by these buildings collectively.
 * <p>
 * The geometric information of each building is given in the array buildings where buildings[i] = [lefti, righti, heighti]:
 * <p>
 * lefti is the x coordinate of the left edge of the ith building.
 * righti is the x coordinate of the right edge of the ith building.
 * heighti is the height of the ith building.
 * You may assume all buildings are perfect rectangles grounded on an absolutely flat surface at height 0.
 * <p>
 * The skyline should be represented as a list of "key points" sorted by their x-coordinate in the form [[x1,y1],[x2,y2],...]. Each key point is the left endpoint of some horizontal segment in the skyline except the last point in the list, which always has a y-coordinate 0 and is used to mark the skyline's termination where the rightmost building ends. Any ground between the leftmost and rightmost buildings should be part of the skyline's contour.
 * <p>
 * Note: There must be no consecutive horizontal lines of equal height in the output skyline. For instance, [...,[2 3],[4 5],[7 5],[11 5],[12 7],...] is not acceptable; the three lines of height 5 should be merged into one in the final output as such: [...,[2 3],[4 5],[12 7],...]
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: buildings = [[2,9,10],[3,7,15],[5,12,12],[15,20,10],[19,24,8]]
 * Output: [[2,10],[3,15],[7,12],[12,0],[15,10],[20,8],[24,0]]
 * Explanation:
 * Figure A shows the buildings of the input.
 * Figure B shows the skyline formed by those buildings. The red points in figure B represent the key points in the output list.
 * See: https://leetcode.com/explore/interview/card/microsoft/48/others/157/
 * <p>
 * Example 2:
 * <p>
 * Input: buildings = [[0,2,3],[2,5,3]]
 * Output: [[0,3],[5,0]]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= buildings.length <= 104
 * 0 <= lefti < righti <= 231 - 1
 * 1 <= heighti <= 231 - 1
 * buildings is sorted by lefti in non-decreasing order.
 */
public class TheSkylineProblem {
  public List<List<Integer>> getSkyline(int[][] buildings) {
    return getSkyline(buildings, 0, buildings.length);
  }

  /**
   * Get skyline for a sub-set of buildings.
   *
   * @param buildings The original full set of buildings.
   * @param start     The start point of the sub-set. Included.
   * @param end       The end point of the sub-set. Excluded.
   * @return The skyline for the sub-set of the buildings.
   */
  private List<List<Integer>> getSkyline(int[][] buildings, int start, int end) {
    int buildingsCount = end - start;
    List<List<Integer>> skyline = new ArrayList<>();

    if (buildingsCount == 0) return skyline;
    if (buildingsCount == 1) {
      skyline.add(Arrays.asList(buildings[start][0], buildings[start][2]));
      skyline.add(Arrays.asList(buildings[start][1], 0));
      return skyline;
    }

    int splitPosition = (start + end) / 2;
    List<List<Integer>> leftSkyline = getSkyline(buildings, start, splitPosition);
    List<List<Integer>> rightSkyline = getSkyline(buildings, splitPosition, end);
    return mergeSkyline(leftSkyline, rightSkyline);

  }

  private List<List<Integer>> mergeSkyline(List<List<Integer>> leftSkyline, List<List<Integer>> rightSkyline) {
    int leftI = 0, rightI = 0;
    int leftH = 0, rightH = 0;

    List<List<Integer>> skyline = new ArrayList<>();
    int leftL = leftSkyline.size(), rightL = rightSkyline.size();
    while (leftI < leftL || rightI < rightL) {
      int compareResult = compareBuildingPosition(leftSkyline, rightSkyline, leftI, rightI);
      if (compareResult < 0) {
        // The building on the leftSkyline comes first
        leftH = leftSkyline.get(leftI).get(1);
        updateSkyline(skyline, leftSkyline.get(leftI).get(0), leftH, rightH);
        leftI++;
      } else if (compareResult > 0) {
        // The building on the rightSkyline comes first
        rightH = rightSkyline.get(rightI).get(1);
        updateSkyline(skyline, rightSkyline.get(rightI).get(0), leftH, rightH);
        rightI++;
      } else {
        // The buildings on the two lines start at the same position
        leftH = leftSkyline.get(leftI).get(1);
        rightH = rightSkyline.get(rightI).get(1);
        updateSkyline(skyline, leftSkyline.get(leftI).get(0), leftH, rightH);
        leftI++;
        rightI++;
      }
    }

    return skyline;
  }

  private int compareBuildingPosition(List<List<Integer>> leftSkyline, List<List<Integer>> rightSkyline, int leftI,
                                      int rightI) {
    if (leftI < leftSkyline.size() && rightI < rightSkyline.size()) {
      return leftSkyline.get(leftI).get(0).compareTo(rightSkyline.get(rightI).get(0));
    } else if (leftI >= leftSkyline.size()) {
      return 1;
    } else return -1;
  }

  private void updateSkyline(List<List<Integer>> skyline, int position, int leftH, int rightH) {
    int currH = skyline.isEmpty() ? 0 : skyline.get(skyline.size() - 1).get(1);
    int maxH = Math.max(leftH, rightH);
    if (currH != maxH) {
      skyline.add(Arrays.asList(position, maxH));
    }
  }
}

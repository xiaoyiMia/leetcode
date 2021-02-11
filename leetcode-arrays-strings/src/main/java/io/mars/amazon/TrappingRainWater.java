package io.mars.amazon;

/**
 * Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water
 * it is able to trap after raining.
 *
 *
 * The above elevation map is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water
 * (blue section) are being trapped. Thanks Marcos for contributing this image!
 *
 * Example:
 *
 * Input: [0,1,0,2,1,0,1,3,2,1,2,1]
 * Output: 6
 */
public class TrappingRainWater {
  public int trap(int[] height) {
    if(height == null || height.length == 0) return 0;

    int start = 0, end = height.length - 1;
    // Find the range of trap
    for(; start < height.length; start++) {
      if(height[start] != 0) break;
    }
    for(; end >= 0; end--) {
      if(height[end] != 0) break;
    }
    if(start >= end) return 0;

    // Collect water from start to highest wall
    int[] results = collectWater(height, start, end);
    if(results[1] == end) return results[0];
    // Collect water from end to highest wall
    return results[0] + collectWater(height, end, results[1])[0];
  }

  private int findNextWall(int[] height, int start, int end) {
    int sign = start <= end ? 1 : -1;
    int position = start;
    while(position != end) {
      position += sign;
      if(height[position] >= height[start]) return position;
    }

    return start;
  }

  private int[] collectWater(int[] height, int start, int end) {
    int[] finish = {0, end};
    int sign = start <= end ? 1: -1;
    int nextWall = findNextWall(height, start, end);
    while(nextWall != start) {
      // Calculate water collection
      for(int i = start + sign; i != nextWall; i += sign) {
        finish[0] += height[start] - height[i];
      }

      start = nextWall;
      nextWall = findNextWall(height, start, end);
    }
    finish[1] = nextWall;
    return finish;
  }
}

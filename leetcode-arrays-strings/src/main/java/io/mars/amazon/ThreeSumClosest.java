package io.mars.amazon;

import java.util.Arrays;

/**
 * Given an array nums of n integers and an integer target, find three integers in nums such that the sum
 * is closest to target. Return the sum of the three integers.
 * You may assume that each input would have exactly one solution.
 *
 * Example:
 *
 * Given array nums = [-1, 2, 1, -4], and target = 1.
 *
 * The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
 */
public class ThreeSumClosest {
  public int threeSumClosest(int[] nums, int target) {
    Arrays.sort(nums);

    int closestSum = 0, closestDiff = Integer.MAX_VALUE;
    for (int i = 0; i < nums.length - 2; i++) {
      int left = i + 1, right = nums.length - 1;
      while(left < right) {
        int sum = nums[i] + nums[left] + nums[right];
        int diff = Math.abs(sum - target);

        if(diff == 0) return target;

        if(diff < closestDiff) {
          closestDiff = diff;
          closestSum = sum;
        }

        if(sum < target) left++;
        else right--;
      }
    }
    return closestSum;
  }
}

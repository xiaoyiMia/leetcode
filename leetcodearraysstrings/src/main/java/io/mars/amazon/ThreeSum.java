package io.mars.amazon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.
 *
 * Note:
 *
 * The solution set must not contain duplicate triplets.
 *
 * Example:
 *
 * Given array nums = [-1, 0, 1, 2, -1, -4],
 *
 * A solution set is:
 * [
 *   [-1, 0, 1],
 *   [-1, -1, 2]
 * ]
 */
public class ThreeSum {
  public List<List<Integer>> threeSum(int[] nums) {
    Arrays.sort(nums);

    List<List<Integer>> results = new ArrayList<>();
    for (int i = 0; i < nums.length && nums[i] <= 0; i++) {
      if (i == 0 || nums[i] != nums[i - 1]) results.addAll(findTriplets(nums, i));
    }
    return results;
  }

  private List<List<Integer>> findTriplets(int[] nums, int position) {
    List<List<Integer>> triplets = new ArrayList<>();
    int left = position + 1, right = nums.length - 1;

    while (left < right) {
      int sum = nums[left] + nums[right] + nums[position];
      if (sum < 0 || (left > position + 1  && nums[left] == nums[left - 1])) {
        left++;
      } else if (sum > 0 || (right < nums.length - 1 && nums[right] == nums[right + 1])) {
        right--;
      } else {
        triplets.add(Arrays.asList(nums[position], nums[left], nums[right]));
      }
    }
    return triplets;
  }
}

package io.mars.amazon;

/**
 * Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest
 * sum and return its sum.
 *
 * Example:
 *
 * Input: [-2,1,-3,4,-1,2,1,-5,4],
 * Output: 6
 * Explanation: [4,-1,2,1] has the largest sum = 6.
 * Follow up:
 *
 * If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach,
 * which is more subtle.
 */
public class MaximumSubarray {
  public int maxSubArray(int[] nums) {
    if(nums.length == 0) return 0;

    int largestSum = nums[0], currentSum = nums[0];

    for(int i = 1; i < nums.length; i++) {
      // If previous sum is smaller than current number, than target sub-array will be either
      // 1. already passed.
      // 2. start from current or later place.
      if(currentSum < 0) {
        if(nums[i] > largestSum) {
          largestSum = nums[i];
        }
        currentSum = nums[i];
      } else {
        currentSum += nums[i];
        if(currentSum > largestSum) {
          largestSum = currentSum;
        }
      }
    }
    return largestSum;
  }

  public int solution2(int[] nums) {
    if(nums.length == 0) return 0;
    return findLargest(nums[0], nums, 1);
  }

  private int findLargest(int largestNum, int nums[], int position) {
    if(position >= nums.length) return largestNum;

    // Add current number to existing largest sub-array
    int currentLargest = 0;
    if(largestNum >= 0) {
      currentLargest = findLargest(largestNum + nums[position], nums, position + 1);
    } else {
      // Do not add current number to existing largest sub-array
      currentLargest = findLargest(nums[position], nums, position + 1);
    }

    return Math.max(currentLargest, largestNum);
  }
}

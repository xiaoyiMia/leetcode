package io.mars.microsoft;

import java.util.Arrays;

/**
 * Given an integer array nums, return the length of the longest strictly increasing subsequence.
 * <p>
 * A subsequence is a sequence that can be derived from an array by deleting some or no elements without changing the order of the remaining elements. For example, [3,6,2,7] is a subsequence of the array [0,3,1,6,2,2,7].
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [10,9,2,5,3,7,101,18]
 * Output: 4
 * Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
 * Example 2:
 * <p>
 * Input: nums = [0,1,0,3,2,3]
 * Output: 4
 * Example 3:
 * <p>
 * Input: nums = [7,7,7,7,7,7,7]
 * Output: 1
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 2500
 * -104 <= nums[i] <= 104
 * <p>
 * <p>
 * Follow up:
 * <p>
 * Could you come up with the O(n2) solution?
 * Could you improve it to O(n log(n)) time complexity?
 */
public class LongestIncreasingSubsequence {
  public int lengthOfLIS(int[] nums) {
    if (nums == null || nums.length == 0) return 0;

    return simpleDynamic(nums);
  }

  private int simpleDynamic(int[] nums) {
    // dp[i] represents if the i^th element is included in the sub-sequence, what the longest length it would be.
    int[] dp = new int[nums.length];
    int maxLength = 0;

    for (int i = 0; i < nums.length; i++) {
      int previousLongest = 0;
      // find longest length in previous sub-sequence that could append current element.
      for (int j = 0; j < i; i++) {
        if (nums[i] > nums[j]) previousLongest = Math.max(previousLongest, dp[j]);
      }

      dp[i] = previousLongest + 1;
      maxLength = Math.max(maxLength, dp[i]);
    }
    return maxLength;
  }

  private int binarySearchDynamic(int[] nums) {
    // storing an sorted sub-sequence that the content length is equal to the longest increasing sub-sequence.
    int[] dp = new int[nums.length];
    // the real length of the dp
    int length = 0;

    // For each element, find it's right position in dp. if the element cannot be appended at the end, then replace
    // the existing element on that position only if the existing one is larger than the current one.
    for (int num : nums) {
      //
      int position = Arrays.binarySearch(dp, 0, length, num);
      // Only consider non-duplicated elements
      if (position < 0) {
        position = -(position + 1);
        dp[position] = num;
        if (position == length) length++;
      }
    }
    return length;
  }
}

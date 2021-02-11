package io.mars.microsoft;

/**
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
 * <p>
 * (i.e.,  [0,1,2,4,5,6,7] might become  [4,5,6,7,0,1,2]).
 * <p>
 * Find the minimum element.
 * <p>
 * The array may contain duplicates.
 * <p>
 * Example 1:
 * <p>
 * Input: [1,3,5]
 * Output: 1
 * Example 2:
 * <p>
 * Input: [2,2,2,0,1]
 * Output: 0
 * Note:
 * <p>
 * This is a follow up problem to Find Minimum in Rotated Sorted Array.
 * Would allow duplicates affect the run-time complexity? How and why?
 */
public class FindMinInRotatedSortedArrayII {
  public int findMin(int[] nums) {
    return findMin(nums, 0, nums.length - 1);
  }

  private int findMin(int[] nums, int start, int end) {
    if (start == end) return nums[start];

    int p = (start + end) / 2;
    // If left < right, then not rotated. left side is the smallest; Otherwise rotated
    if (nums[start] < nums[end]) return nums[start];
    else if (nums[start] > nums[p]) return findMin(nums, start, p);
    else if (nums[start] < nums[p]) return findMin(nums, p + 1, end);
    else return Math.min(findMin(nums, start, p), findMin(nums, p + 1, end));
  }
}

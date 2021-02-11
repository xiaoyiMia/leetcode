package io.mars.microsoft;

/**
 * Given a sorted array nums, remove the duplicates in-place such that each element appears only once
 * and returns the new length.
 * <p>
 * Do not allocate extra space for another array, you must do this by modifying the input array in-place
 * with O(1) extra memory.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,1,2]
 * Output: 2, nums = [1,2]
 * Explanation: Your function should return length = 2, with the first two elements of nums being 1 and 2 respectively.
 * It doesn't matter what you leave beyond the returned length.
 * Example 2:
 * <p>
 * Input: nums = [0,0,1,1,1,2,2,3,3,4]
 * Output: 5, nums = [0,1,2,3,4]
 * Explanation: Your function should return length = 5, with the first five elements of nums being modified
 * to 0, 1, 2, 3, and 4 respectively. It doesn't matter what values are set beyond the returned length.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 0 <= nums.length <= 3 * 104
 * -104 <= nums[i] <= 104
 * nums is sorted in ascending order.
 */
public class RemoveDuplicatesFromSortedArray {
  public int removeDuplicates(int[] nums) {
    if (nums == null || nums.length <= 0) return 0;

    int position = 0;
    for (int i = 1; i < nums.length; i++) {
      if (nums[i] != nums[position]) {
        nums[position + 1] = nums[i];
        position++;
      }
    }
    return position + 1;
  }
}
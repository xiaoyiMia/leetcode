package io.mars.google;

/**
 * Find First and Last Position of Element in Sorted Array.
 *
 * Given an array of integers nums sorted in ascending order, find the starting and ending position of a given target value.
 *
 * If target is not found in the array, return [-1, -1].
 *
 * You must write an algorithm with O(log n) runtime complexity.
 *
 *
 *
 * Example 1:
 * Input: nums = [5,7,7,8,8,10], target = 8
 * Output: [3,4]
 *
 * Example 2:
 * Input: nums = [5,7,7,8,8,10], target = 6
 * Output: [-1,-1]
 *
 * Example 3:
 * Input: nums = [], target = 0
 * Output: [-1,-1]
 *
 *
 * Constraints:
 *
 * 0 <= nums.length <= 105
 * -109 <= nums[i] <= 109
 * nums is a non-decreasing array.
 * -109 <= target <= 109
 */
public class FindFirstLastPositionInSortedArray {
  public int[] searchRange(int[] nums, int target) {
    int[] positions = {-1, -1};
    findPositions(nums, target, 0, nums.length - 1, positions);
    return positions;
  }

  private void findPositions(int[] nums, int target, int left, int right, int[] positions) {
    if(left > right) return;

    int pivot = (left + right) / 2;
    if(nums[pivot] < target) {
      findPositions(nums, target, pivot + 1, right, positions);
    } else if (nums[pivot] > target) {
      findPositions(nums, target, left, pivot - 1, positions);
    } else {
      if(positions[0] == -1 || positions[0] > pivot) {
        positions[0] = pivot;
      }
      if(positions[1] == -1 || positions[1] < pivot) {
        positions[1] = pivot;
      }

      findPositions(nums, target, left, pivot - 1, positions);
      findPositions(nums, target, pivot + 1, right, positions);
    }
  }
}

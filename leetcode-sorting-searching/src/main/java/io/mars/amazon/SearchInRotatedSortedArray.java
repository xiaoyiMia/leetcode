package io.mars.amazon;

public class SearchInRotatedSortedArray {

  public int search(int[] nums, int target) {
    return findTarget(nums, 0, nums.length - 1, target);
  }

  private int findTarget(int[] nums, int left, int right, int target) {
    if (left > right) return -1;

    int mid = (right - left) / 2 + left;
    int midValue = nums[mid];

    if (midValue == target) return mid;
    int leftValue = nums[left];
    if (target == leftValue) return left;
    int rightValue = nums[right];
    if (target == rightValue) return right;

    // case1: left -> mid are ordered
    if (leftValue < midValue) {
      // case1-1: target between left and mid, keep find in left part.
      if (target > leftValue && target < midValue) return findTarget(nums, left + 1, mid - 1, target);
        // case1-2: target not between left and mid, keep find in right part.
      else return findTarget(nums,
          +1, right - 1, target);
    } else {
      // case2: mid - right are ordered
      // case2-1: target between mid and right, keep find in right part.
      if (target > midValue && target < rightValue) return findTarget(nums, mid + 1, right - 1, target);
        // case2-2: target not between mid and right, keep find in left part.
      else return findTarget(nums, left + 1, mid - 1, target);
    }
  }
}

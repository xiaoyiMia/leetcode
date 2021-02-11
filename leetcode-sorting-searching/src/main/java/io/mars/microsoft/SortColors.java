package io.mars.microsoft;

/**
 * Given an array nums with n objects colored red, white, or blue, sort them in-place
 * so that objects of the same color are adjacent, with the colors in the order red, white, and blue.
 * <p>
 * Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.
 * <p>
 * Follow up:
 * <p>
 * Could you solve this problem without using the library's sort function?
 * Could you come up with a one-pass algorithm using only O(1) constant space?
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [2,0,2,1,1,0]
 * Output: [0,0,1,1,2,2]
 * Example 2:
 * <p>
 * Input: nums = [2,0,1]
 * Output: [0,1,2]
 * Example 3:
 * <p>
 * Input: nums = [0]
 * Output: [0]
 * Example 4:
 * <p>
 * Input: nums = [1]
 * Output: [1]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * n == nums.length
 * 1 <= n <= 300
 * nums[i] is 0, 1, or 2.
 */
public class SortColors {
  /**
   * curIndex starts with 0, only moves curIndex to next when nums[curIndex] == 1;
   * redIndex starts with 0, only moves when nums[redIndex] == 0;
   * blueIndex starts with (nums.size - 1), only moves when nums[blueIndex] == 2;
   *
   * Stop condition: curIndex >= blueIndex;
   */
  public void sortColors(int[] nums) {

    int curIndex = 0;
    int redIndex = 0;
    int blueIndex = nums.length - 1;

    while(curIndex <= blueIndex) {

      int value = nums[curIndex];
      if(value == 0) {
        nums[curIndex] = nums[redIndex];
        nums[redIndex] = value;
      } else if(value == 2) {
        nums[curIndex] = nums[blueIndex];
        nums[blueIndex] = value;
      }

      while (redIndex <= blueIndex && nums[redIndex] == 0) redIndex++;
      while (redIndex <= blueIndex && nums[blueIndex] == 2) blueIndex--;
      while (curIndex <= blueIndex && nums[curIndex] == 1) curIndex++;

      if(curIndex < redIndex) curIndex = redIndex;
    }
  }
}

// [1,1,1]
// c = 0, r = 0, b = -1; v = 2

package io.mars.google.jumpgame;

public class TwoPointerSolution implements JumpGame {
  public boolean canJump(int[] nums) {
    int zeroIndex = 0, jumpIndex = 0;

    while (zeroIndex < nums.length - 1) {
      if (nums[zeroIndex] > 0) {
        zeroIndex++;
        continue;
      }

      while (jumpIndex < zeroIndex && jumpIndex + nums[jumpIndex] <= zeroIndex) {
        jumpIndex++;
      }

      if (jumpIndex == zeroIndex) return false;
      zeroIndex++;
    }
    return true;
  }
}

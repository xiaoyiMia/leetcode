package io.mars.google;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FindFirstLastPositionInSortedArrayTest {

  @Test
  void searchRange() {
    FindFirstLastPositionInSortedArray solution = new FindFirstLastPositionInSortedArray();

    int[] nums = {};
    int target = 8;
    int[] expectedResult = {-1,-1};

    int[] result = solution.searchRange(nums, target);
    assertEquals(expectedResult[0], result[0]);
    assertEquals(expectedResult[1], result[1]);
  }
}

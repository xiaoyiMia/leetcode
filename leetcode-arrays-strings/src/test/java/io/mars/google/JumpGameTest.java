package io.mars.google;

import io.mars.google.jumpgame.ForLoopSolution;
import io.mars.google.jumpgame.TwoPointerSolution;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class JumpGameTest {

  @ParameterizedTest
  @MethodSource("testDataAndResult")
  void canJump_twoPointerSolution(int[] nums, boolean expect) {
    boolean result = new TwoPointerSolution().canJump(nums);
    assertEquals(expect, result);
  }

  @ParameterizedTest
  @MethodSource("testDataAndResult")
  void canJump_forLoopSolution(int[] nums, boolean expect) {
    boolean result = new ForLoopSolution().canJump(nums);
    assertEquals(expect, result);
  }

  private static Stream<Arguments> testDataAndResult() {
    return Stream.of(
        arguments(new int[]{2}, true),
        arguments(new int[]{0}, true),
        arguments(new int[]{0,1}, false),
        arguments(new int[]{2, 3, 1, 1, 4}, true),
        arguments(new int[]{3, 2, 1, 0, 4}, false),
        arguments(new int[]{3, 3, 1, 0, 4}, true),
        arguments(new int[]{3, 3, 1, 0, 0, 4}, false),
        arguments(new int[]{3, 3, 1, 0, 1, 0}, true),
        arguments(new int[]{3, 3, 1, 0, 1, 0, 1}, false),
        arguments(new int[]{4, 0, 0, 0, 0}, true),
        arguments(new int[]{4, 0, 0, 0, 1}, true)
    );
  }
}

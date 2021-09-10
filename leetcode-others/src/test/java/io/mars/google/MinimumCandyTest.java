package io.mars.google;

import io.mars.google.candy.MinimumCandy;
import io.mars.google.candy.OnePathSolution;
import io.mars.google.candy.TwoPathsSolution;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class MinimumCandyTest {
  private MinimumCandy solution = new OnePathSolution();

  @ParameterizedTest
  @MethodSource("dateAndResultProvider")
  void candy_onePathSolution(int[] ratings, int minimumCandies) {
    int result = new OnePathSolution().candy(ratings);
    assertEquals(minimumCandies, result);
  }

  @ParameterizedTest
  @MethodSource("dateAndResultProvider")
  void candy_twoPathsSolution(int[] ratings, int minimumCandies) {
    int result = new TwoPathsSolution().candy(ratings);
    assertEquals(minimumCandies, result);
  }

  static Stream<Arguments> dateAndResultProvider() {
    return Stream.of(
        arguments(new int[]{5}, 1), // single rating
        arguments(new int[]{1,6,10,8,7,3,2}, 18), // increase, decrease
        arguments(new int[]{1, 2, 3, 3, 1}, 9), // increase, single-tie, decrease
        arguments(new int[]{1, 2, 3, 3, 3, 3, 1}, 11), // increase, multi-tie, decrease
        arguments(new int[]{5, 3, 2, 3, 4}, 11), // decrease, increase
        arguments(new int[]{3, 2, 4}, 5), // decrease, increase
        arguments(new int[]{5, 3, 2, 2, 3, 4}, 12), // decrease, single-tie, increase
        arguments(new int[]{5, 3, 2, 2, 2, 3, 4}, 13), // decrease, multi-tie, increase
        arguments(new int[]{3, 3, 4, 6, 9}, 11), // single-tie, increase
        arguments(new int[]{3, 3, 1}, 4), // single-tie, decrease
        arguments(new int[]{3, 3, 3, 5}, 5), // multi-tie, increase
        arguments(new int[]{3, 3, 3, 3, 1}, 6), // multi-tie, decrease
        arguments(new int[]{1, 2, 3, 3}, 7), // increase, single-tie
        arguments(new int[]{7, 4, 3, 3}, 7), // decrease, single-tie
        arguments(new int[]{1, 2, 3, 3, 3, 3}, 9), // increase, multi-tie
        arguments(new int[]{9, 6, 4, 3, 3, 3, 3}, 13), // decrease, multi-tie
        arguments(new int[]{1,2,3,4, 5, 3, 2, 1, 2, 6, 5, 4, 3, 3, 2, 1, 1, 3, 3, 3, 4, 2}, 47) // decrease, multi-tie
    );
  }
}

package io.mars.google;

import io.mars.google.bulls_cows.BullsAndCows;
import io.mars.google.bulls_cows.OnePassSolution;
import io.mars.google.bulls_cows.TwoPassSolution;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class BullsAndCowsTest {

  @ParameterizedTest
  @MethodSource("testDataAndResult")
  void getHint_twoPassSolution(String secret, String guess, String expect) {
    BullsAndCows solution = new TwoPassSolution();
    String result = solution.getHint(secret, guess);
    assertEquals(expect, result);
  }

  @ParameterizedTest
  @MethodSource("testDataAndResult")
  void getHint_onePassSolution(String secret, String guess, String expect) {
    BullsAndCows solution = new OnePassSolution();
    String result = solution.getHint(secret, guess);
    assertEquals(expect, result);
  }

  private static Stream<Arguments> testDataAndResult() {
    return Stream.of(
        arguments("1807", "7810", "1A3B"),
        arguments("1123", "0111", "1A1B"),
        arguments("0194", "4491", "1A2B"),
        arguments("1122", "1222", "3A0B"),
        arguments("1", "0", "0A0B"),
        arguments("1", "1", "1A0B")
    );
  }
}

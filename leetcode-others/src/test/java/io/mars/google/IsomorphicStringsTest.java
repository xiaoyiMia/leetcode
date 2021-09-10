package io.mars.google;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class IsomorphicStringsTest {
  private IsomorphicStrings solution = new IsomorphicStrings();

  @ParameterizedTest
  @MethodSource("testDataAndResult")
  void isIsomorphic(String inputSource, String inputTarget, Boolean expectResult) {
    boolean result = solution.isIsomorphic(inputSource, inputTarget);
    assertEquals(expectResult, result);
  }

  private static Stream<Arguments> testDataAndResult() {
    return Stream.of(
        arguments("e", "a", true),
        arguments("egg", "edd", true),
        arguments("egg", "add", true),
        arguments("foo", "bar", false),
        arguments("paper", "title", true),
        arguments("paper", "titte", false)
    );
  }
}

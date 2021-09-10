package io.mars.google;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class StrobogrammaticNumberTest {
  private StrobogrammaticNumber solution = new StrobogrammaticNumber();

  @ParameterizedTest
  @MethodSource("testDataAndResult")
  void isStrobogrammatic(String input, Boolean expectResult) {
    boolean result = solution.isStrobogrammatic(input);
    assertEquals(expectResult, result);
  }

  private static Stream<Arguments> testDataAndResult() {
    return Stream.of(
        arguments("69", true),
        arguments("88", true),
        arguments("926", false),
        arguments("1", true),
        arguments("619", true),
        arguments("6819", false)
    );
  }
}

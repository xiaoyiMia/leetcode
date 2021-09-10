package io.mars.google;

import io.mars.google.decode_string.RecursiveSolution;
import io.mars.google.decode_string.TwoStacksSolution;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class DecodeStringTest {

  @ParameterizedTest
  @MethodSource("dataAndResults")
  void decodeString_twoStacksSolution(String testData, String expectedResult) {
    String result = new TwoStacksSolution().decodeString(testData);
    assertEquals(expectedResult, result);
  }

  @ParameterizedTest
  @MethodSource("dataAndResults")
  void decodeString_recursiveSolution(String testData, String expectedResult) {
    String result = new RecursiveSolution().decodeString(testData);
    assertEquals(expectedResult, result);
  }

  private static Stream<Arguments> dataAndResults() {
    return Stream.of(
        arguments("3[a]2[bc]", "aaabcbc"),
        arguments("3[a2[c]]", "accaccacc"),
        arguments("2[abc]3[cd]ef", "abcabccdcdcdef"),
        arguments("abc3[cd]xyz", "abccdcdcdxyz"),
        arguments("10[a]", "aaaaaaaaaa"),
        arguments("2[a2[abc]1[cd]ef]", "aabcabccdefaabcabccdef"),
        arguments("g2[ai2[a]3[2[c]]]f", "gaiaaccccccaiaaccccccf"),
        arguments("g12[ai2[a]3[2[c]]]f", "gaiaaccccccaiaaccccccaiaaccccccaiaaccccccaiaaccccccaiaaccccccaiaaccccccaiaaccccccaiaaccccccaiaaccccccaiaaccccccaiaaccccccf")
    );
  }
}

package io.mars.google;

import com.google.common.collect.ImmutableSet;
import io.mars.google.wordsquares.Backtracking;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class WordSquaresTest {

  @ParameterizedTest
  @MethodSource("testDataAndResult")
  void wordSquares_backtracking(String[] input, Set<String> expect) {
    List<List<String>> result = new Backtracking().wordSquares(input);

    assertEquals(expect.size(), result.size());
    for (List<String> wordSquare : result) {
      assertTrue(expect.contains(wordSquare.toString()));
    }
  }

  private static Stream<Arguments> testDataAndResult() {
    return Stream.of(
        arguments(
            new String[]{"aba", "bbb", "abc", "lad", "bal"},
            ImmutableSet.of("[aba, bbb, aba]", "[aba, bbb, abc]", "[bbb, bbb, bbb]", "[bal, aba, lad]")
        ),
        arguments(
            new String[]{"area","lead","wall","lady","ball"},
            ImmutableSet.of("[wall, area, lead, lady]", "[ball, area, lead, lady]")
        ),
        arguments(
            new String[]{"abat","baba","atan","atal"},
            ImmutableSet.of("[baba, abat, baba, atan]", "[baba, abat, baba, atal]")
        ),
        arguments(
            new String[]{"aaa"},
            ImmutableSet.of("[aaa, aaa, aaa]")
        ),
        arguments(
            new String[]{"aba"},
            ImmutableSet.of()
        ),
        arguments(
            new String[]{"a"},
            ImmutableSet.of("[a]")
        )
    );
  }
}

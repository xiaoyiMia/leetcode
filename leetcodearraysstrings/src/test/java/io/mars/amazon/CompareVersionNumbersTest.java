package io.mars.amazon;

import org.junit.jupiter.api.Test;

public class CompareVersionNumbersTest {

  @Test
  void testSplitMethod() {
    new CompareVersionNumbers().compareVersion("0.1", "1.1");
  }
}

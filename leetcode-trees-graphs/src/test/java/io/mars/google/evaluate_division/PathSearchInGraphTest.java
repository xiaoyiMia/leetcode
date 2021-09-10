package io.mars.google.evaluate_division;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

class PathSearchInGraphTest extends EvaluateDivisionTestBase {

  @BeforeEach
  void setup() {
    solution = new PathSearchInGraph();
  }

  @ParameterizedTest
  @EnumSource(EvaluateDivisionTestData.class)
  void calcEquation(EvaluateDivisionTestData testData) {
    test(testData.getEquations(), testData.getValues(), testData.getQueries(), testData.getExpectedResults());
  }
}

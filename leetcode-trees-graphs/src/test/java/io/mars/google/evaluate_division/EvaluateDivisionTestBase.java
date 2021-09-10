package io.mars.google.evaluate_division;

import com.google.common.collect.ImmutableList;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public abstract class EvaluateDivisionTestBase {
  protected EvaluateDivision solution;

  protected void test(List<List<String>> equations, double[] values, List<List<String>> queries, double[] expectedResults) {
    double[] results = solution.calcEquation(equations, values, queries);

    assertEquals(expectedResults.length, results.length);
    for (int i = 0; i < expectedResults.length; i++) {
      assertEquals(expectedResults[i], results[i]);
    }
  }

  enum EvaluateDivisionTestData {
    EXAMPLE_01(
        ImmutableList.of(ImmutableList.of("a", "b"), ImmutableList.of("b", "c")),
        new double[]{2.0, 3.0},
        ImmutableList.of(
            ImmutableList.of("a", "c"),
            ImmutableList.of("b", "a"),
            ImmutableList.of("a", "e"),
            ImmutableList.of("a", "a"),
            ImmutableList.of("x", "x")
        ),
        new double[]{6.00000, 0.50000, -1.00000, 1.00000, -1.00000}
    ),
    EXAMPLE_02(
        ImmutableList.of(ImmutableList.of("a", "b"), ImmutableList.of("b", "c"), ImmutableList.of("bc", "cd")),
        new double[]{1.5, 2.5, 5.0},
        ImmutableList.of(
            ImmutableList.of("a", "c"),
            ImmutableList.of("c", "b"),
            ImmutableList.of("bc", "cd"),
            ImmutableList.of("cd", "bc")
        ),
        new double[]{3.75000, 0.40000, 5.00000, 0.20000}
    ),
    EXAMPLE_03(
        ImmutableList.of(ImmutableList.of("a", "b")),
        new double[]{0.5},
        ImmutableList.of(
            ImmutableList.of("a", "b"),
            ImmutableList.of("b", "a"),
            ImmutableList.of("a", "c"),
            ImmutableList.of("x", "y")
        ),
        new double[]{0.50000, 2.00000, -1.00000, -1.00000}
    ),
    EXAMPLE_04(
        ImmutableList.of(ImmutableList.of("a", "b"), ImmutableList.of("e", "f"), ImmutableList.of("b", "e")),
        new double[]{3.4, 1.4, 2.3},
        ImmutableList.of(
            ImmutableList.of("b", "a"),
            ImmutableList.of("a", "f"),
            ImmutableList.of("f", "f"),
            ImmutableList.of("e", "e"),
            ImmutableList.of("c", "c"),
            ImmutableList.of("a", "c"),
            ImmutableList.of("f", "e")
        ),
        new double[]{1 / 3.4, 3.4 * 1.4 * 2.3, 1.00000, 1.00000, -1.00000, -1.00000, 1 / 1.4}
    );

    private List<List<String>> equations;
    private double[] values;
    private List<List<String>> queries;
    private double[] expectedResults;

    EvaluateDivisionTestData(List<List<String>> equations, double[] values, List<List<String>> queries,
                             double[] expectedResults) {
      this.equations = equations;
      this.values = values;
      this.queries = queries;
      this.expectedResults = expectedResults;
    }

    public List<List<String>> getEquations() {
      return equations;
    }

    public double[] getValues() {
      return values;
    }

    public List<List<String>> getQueries() {
      return queries;
    }

    public double[] getExpectedResults() {
      return expectedResults;
    }
  }
}

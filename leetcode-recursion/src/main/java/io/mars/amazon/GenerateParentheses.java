package io.mars.amazon;

import java.util.ArrayList;
import java.util.List;

/**
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
 *
 * For example, given n = 3, a solution set is:
 *
 * [
 *   "((()))",
 *   "(()())",
 *   "(())()",
 *   "()(())",
 *   "()()()"
 * ]
 */
public class GenerateParentheses {
  public List<String> generateParenthesis(int n) {
    List<String> results = new ArrayList<>();
    appendNext(results, "", "(", n - 1, n);
    return results;
  }

  private void appendNext(List<String> results, String curStr, String nextSymbol, int avaLeft, int avaRight) {
    curStr += nextSymbol;
    if(avaRight == 0) {
      results.add(curStr);
      return;
    }

    if(avaLeft > 0) appendNext(results, curStr, "(", avaLeft - 1, avaRight);
    if(avaLeft < avaRight) appendNext(results, curStr, ")", avaLeft, avaRight - 1);
  }
}

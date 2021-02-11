package io.mars.microsoft;

/**
 * Given a column title as appear in an Excel sheet, return its corresponding column number.
 * <p>
 * For example:
 * <p>
 * A -> 1
 * B -> 2
 * C -> 3
 * ...
 * Z -> 26
 * AA -> 27
 * AB -> 28
 * ...
 * Example 1:
 * <p>
 * Input: "A"
 * Output: 1
 * <p>
 * Example 2:
 * <p>
 * Input: "AB"
 * Output: 28
 * <p>
 * Example 3:
 * <p>
 * Input: "ZY"
 * Output: 701
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= s.length <= 7
 * s consists only of uppercase English letters.
 * s is between "A" and "FXSHRXW".
 */
public class ExcelSheetColumnNumber {
  public int titleToNumber(String s) {
    char[] letters = s.toCharArray();
    int value = 0;
    for (int i = letters.length - 1, j = 0; i >= 0; i--, j++) {
      value = value + (letters[i] - 'A' + 1) * (int) Math.pow(26, j);
    }
    return value;
  }
}

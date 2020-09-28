package io.mars.amazon;

import java.util.HashMap;
import java.util.Map;

/**
 * Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.
 * <p>
 * Symbol       Value
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 * For example, two is written as II in Roman numeral, just two one's added together. Twelve is written as, XII, which is simply X + II. The number twenty seven is written as XXVII, which is XX + V + II.
 * <p>
 * Roman numerals are usually written largest to smallest from left to right. However, the numeral for four is not IIII. Instead, the number four is written as IV. Because the one is before the five we subtract it making four. The same principle applies to the number nine, which is written as IX. There are six instances where subtraction is used:
 * <p>
 * I can be placed before V (5) and X (10) to make 4 and 9.
 * X can be placed before L (50) and C (100) to make 40 and 90.
 * C can be placed before D (500) and M (1000) to make 400 and 900.
 * Given a roman numeral, convert it to an integer. Input is guaranteed to be within the range from 1 to 3999.
 * <p>
 * Example 1:
 * <p>
 * Input: "III"
 * Output: 3
 * Example 2:
 * <p>
 * Input: "IV"
 * Output: 4
 * Example 3:
 * <p>
 * Input: "IX"
 * Output: 9
 * Example 4:
 * <p>
 * Input: "LVIII"
 * Output: 58
 * Explanation: L = 50, V= 5, III = 3.
 * Example 5:
 * <p>
 * Input: "MCMXCIV"
 * Output: 1994
 * Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.
 */
public class RomanToInteger {

  public int romanToInt(String s) {
    int[] integers = {1, 4, 5, 9, 10, 40, 50, 90, 100, 400, 500, 900, 1000};
    String[] romans = {"I", "IV", "V", "IX", "X", "XL", "L", "XC", "C", "CD", "D", "CM", "M"};

    int index = romans.length - 1;
    int number = 0;
    int position = 0;

    while (position < s.length()) {
      while (match(romans[index], s, position)) {
        number += integers[index];
        position += romans[index].length();
      }
      index--;
    }
    return number;
  }

  public int solution2(String s) {
    Map<Character, Integer> romanMap = new HashMap<>();
    romanMap.put('I', 1);
    romanMap.put('V', 5);
    romanMap.put('X', 10);
    romanMap.put('L', 50);
    romanMap.put('C', 100);
    romanMap.put('D', 500);
    romanMap.put('M', 1000);

    int before = romanMap.get(s.charAt(0));
    int result = before;

    for (int i = 1; i < s.length(); i++) {
      int current = romanMap.get(s.charAt(i));
      if (current <= before) {
        result += current;
      } else {
        current = current - before;
        result += current - before;
      }
      before = current;
    }
    return result;
  }

  public int solution3(String s) {
    int[] integers = {1, 5, 10, 50, 100, 500, 1000, 0, 0};
    char[] romans = {'I', 'V', 'X', 'L', 'C', 'D', 'M', '?', '?'};

    int index = romans.length - 3;
    int result = 0;

    int position = 0;
    while(position < s.length()) {
      char currentChar = s.charAt(position);
      int currentNum = integers[index];
      while (currentChar == romans[index]) {
        result += currentNum;
        position++;
        if(position >= s.length()) return result;
        currentChar = s.charAt(position);
      }
      if (currentChar == romans[index + 1]) {
        result += integers[index + 1] - 2 * currentNum;
        position++;
      } else if (currentChar == romans[index + 2]) {
        result += integers[index + 2] - 2 * currentNum;
        position++;
      }
      index--;
    }
    return result;
  }

  private boolean match(String template, String target, int startIndex) {
    if (startIndex >= target.length()) return false;

    int position = 0;
    while (position < template.length()) {
      if (startIndex + position >= target.length() || template.charAt(position) != target.charAt(startIndex + position)) {
        return false;
      }
      position++;
    }
    return true;
  }

}

package io.mars.google;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a string num which represents an integer, return true if num is a strobogrammatic number.
 *
 * A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
 *
 *
 * Example 1:
 *
 * Input: num = "69"
 * Output: true
 *
 *
 * Example 2:
 *
 * Input: num = "88"
 * Output: true
 *
 *
 * Example 3:
 *
 * Input: num = "962"
 * Output: false
 *
 *
 * Example 4:
 *
 * Input: num = "1"
 * Output: true
 *
 *
 * Constraints:
 *
 * 1 <= num.length <= 50
 * num consists of only digits.
 * num does not contain any leading zeros except for zero itself.
 */
public class StrobogrammaticNumber {
  public boolean isStrobogrammatic(String num) {
    Map<Character, Character> stroboMap = new HashMap<>(){{
      put('0', '0');
      put('1', '1');
      put('6', '9');
      put('8', '8');
      put('9', '6');
    }};

    StringBuilder builder = new StringBuilder();
    for (char c : num.toCharArray()) {
      Character strobo = stroboMap.get(c);
      if(strobo == null) return false;

      builder.append(strobo);
    }

    return builder.reverse().toString().equals(num);
  }
}

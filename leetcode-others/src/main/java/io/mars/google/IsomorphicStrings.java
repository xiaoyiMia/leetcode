package io.mars.google;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Given two strings s and t, determine if they are isomorphic.
 * <p>
 * Two strings s and t are isomorphic if the characters in s can be replaced to get t.
 * <p>
 * All occurrences of a character must be replaced with another character while preserving the order of characters. No two characters may map to the same character, but a character may map to itself.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "egg", t = "add"
 * Output: true
 * <p>
 * <p>
 * Example 2:
 * <p>
 * Input: s = "foo", t = "bar"
 * Output: false
 * <p>
 * <p>
 * Example 3:
 * <p>
 * Input: s = "paper", t = "title"
 * Output: true
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= s.length <= 5 * 104
 * t.length == s.length
 * s and t consist of any valid ascii character.
 */
public class IsomorphicStrings {
  public boolean isIsomorphic(String s, String t) {
    Set<Character> usedChar = new HashSet<>();
    Map<Character, Character> encoding = new HashMap<>();
    for (int i = 0; i < s.length(); i++) {
      char source = s.charAt(i);
      char target = t.charAt(i);

      if (encoding.containsKey(source)) {
        // if same source code does not map to same target code, invalid
        if (!encoding.get(source).equals(target)) return false;
      } else {
        // if a different source code map to a in-used target code, invalid
        if (usedChar.contains(target)) return false;

        encoding.put(source, target);
        usedChar.add(target);
      }
    }
    return true;
  }
}

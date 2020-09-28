package io.mars.amazon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given a string S and a string T, find the minimum window in S
 * which will contain all the characters in T in complexity O(n).
 *
 * Example:
 *
 * Input: S = "ADOBECODEBANC", T = "ABC"
 * Output: "BANC"
 * Note:
 *
 * If there is no such window in S that covers all characters in T, return the empty string "".
 * If there is such window, you are guaranteed that there will always be only one unique minimum window in S.
 *
 *
 * Hit:
 * Since you have to find the minimum window in S which has all the characters from T,
 * you need to expand and contract the window using the two pointers and keep checking the window for all the characters. This approach is also called Sliding Window Approach.
 *
 * L ------------------------ R , Suppose this is the window that contains all characters of T
 *
 *         L----------------- R , this is the contracted window. We found a smaller window that still
 * contains all the characters in T
 *
 * When the window is no longer valid, start expanding again using the right pointer.
 */
public class MinimumWindowSubstring {
  public String minWindow(String s, String t) {
    if(t == null || t.isEmpty()) return "";

    int[] results = new int[]{0, -1};
    int matchCount = 0;

    Map<Character, Integer> targetMap = new HashMap<>();
    Map<Character, Integer> matchingMap = new HashMap<>();
    char[] sourceChars = s.toCharArray();
    for (char targetChar : t.toCharArray()) {
      matchingMap.put(targetChar, 0);
      int value = targetMap.getOrDefault(targetChar, 0);
      targetMap.put(targetChar, value + 1);
    }

    int left = 0, right = -1;
    while (right < sourceChars.length) {
      if(matchCount == targetMap.size()) {
        if(results[1] - results[0] > right - left || results[1] == -1) {
          results[0] = left;
          results[1] = right;
        }

        if(targetMap.containsKey(sourceChars[left])) {
          int value = matchingMap.get(sourceChars[left]) - 1;
          matchingMap.put(sourceChars[left], value);
          if(value < targetMap.get(sourceChars[left])) matchCount--;
        }
        left++;
        if(left > right) right = left;

      } else {
        right++;
        if(right >= sourceChars.length) break;

        if(targetMap.containsKey(sourceChars[right])) {
          int value = matchingMap.get(sourceChars[right]) + 1;
          matchingMap.put(sourceChars[right], value);
          if(value == targetMap.get(sourceChars[right])) matchCount++;
        }
      }
    }

    return String.valueOf(sourceChars, results[0], results[1] - results[0] + 1);
  }

}

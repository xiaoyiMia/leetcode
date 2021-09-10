package io.mars.google;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 159. Longest Substring with At Most Two Distinct Characters (Medium)
 *
 * Given a string s, return the length of the longest substring that contains at most two distinct characters.
 *
 * Example 1:
 *
 * Input: s = "eceba"
 * Output: 3
 * Explanation: The substring is "ece" which its length is 3.
 *
 *
 * Example 2:
 *
 * Input: s = "ccaabbb"
 * Output: 5
 * Explanation: The substring is "aabbb" which its length is 5.
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 104
 * s consists of English letters.
 */
public class LongestSubstringWithTwoDistinctCharacters {

  public int lengthOfLongestSubstringTwoDistinct(String s) {
    int length = s.length();
    int left = 0, right = 0, longest = 0;
    Map<Character, Integer> positionMap = new HashMap<>();

    while(left < length) {
      // Move the right pointer until we get two distinct characters.
      while (positionMap.size() < 3 && right < length) {
        positionMap.put(s.charAt(right), right);
        if(positionMap.size() < 3) right++;
      }
      // Update longest length
      longest = Math.max(longest, right - left);

      // Move left pointer to exclude the current character
      int rightMost = Collections.min(positionMap.values());
      positionMap.remove(s.charAt(left));
      left = rightMost + 1;
    }

    return longest;
  }
}

package io.mars.amazon;

/**
 * Return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
 *
 * Example 1:
 *
 * Input: haystack = "hello", needle = "ll"
 * Output: 2
 * Example 2:
 *
 * Input: haystack = "aaaaa", needle = "bba"
 * Output: -1
 * Clarification:
 *
 * What should we return when needle is an empty string? This is a great question to ask during an interview.
 *
 * For the purpose of this problem, we will return 0 when needle is an empty string.
 * This is consistent to C's strstr() and Java's indexOf().
 */
public class ImplementStrStr {
  public int strStr(String haystack, String needle) {
    int result = 0, j = 0;
    for(int i=0; i< haystack.length(); i++) {
      if(j >= needle.length()) break;

      if(haystack.charAt(i) == needle.charAt(j)) {
        if(j == 0) result = i;
        j++;
      } else {
        result = -1;
        j = 0;
      }
    }

    return j > needle.length() ? result: -1;
  }
}

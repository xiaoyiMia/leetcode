package io.mars.amazon;

/**
 * Given a string, find the first non-repeating character in it and return it's index. If it doesn't exist, return -1.
 *
 * Examples:
 *
 * s = "leetcode"
 * return 0.
 *
 * s = "loveleetcode",
 * return 2.
 * Note: You may assume the string contain only lowercase letters.
 */
public class FirstUniqueCharInString {
  public int firstUniqChar(String s) {
    int[] charPositions = new int[26];
    for(int i=0; i< s.length(); i++) {
      int position = s.charAt(i) - 'a';
      if(charPositions[position] == 0) charPositions[position] = i + 1;
      else charPositions[position] = -1;
    }

    int result = Integer.MAX_VALUE;
    for(int p: charPositions) {
      if(p > 0 && p < result) result = Math.min(result, p);
    }
    return result == Integer.MAX_VALUE ? -1 : result;
  }

  private int solution2(String s) {
    int result = s.length();
    char c = 'a';
    while(c <= 'z') {
      int index = s.indexOf(c);
      if(index != -1 && index == s.lastIndexOf(c)) {
        result = Math.min(result, index);
      }
      c++;
    }
    return result == s.length() ? -1: result;
  }
}

package io.mars.microsoft;

import javax.xml.stream.events.Characters;

/**
 * Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.
 * <p>
 * Note: For the purpose of this problem, we define empty string as valid palindrome.
 * <p>
 * Example 1:
 * <p>
 * Input: "A man, a plan, a canal: Panama"
 * Output: true
 * Example 2:
 * <p>
 * Input: "race a car"
 * Output: false
 * <p>
 * <p>
 * Constraints:
 * <p>
 * s consists only of printable ASCII characters.
 */
public class ValidPalindrome {
  public boolean isPalindrome(String s) {
    if (s == null) return true;

    char[] characters = s.toCharArray();
    int left = 0;
    int right = characters.length - 1;

    while (left <= right) {
      if (isNotAlphanumeric(characters[left])) {
        left++;
      } else if (isNotAlphanumeric(characters[right])) {
        right--;
      } else {
        if (isNotEqual(characters[left], characters[right])) {
          return false;
        }
        left++;
        right--;
      }
    }
    return true;
  }

  private boolean isNotAlphanumeric(char c) {
    return !((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z') || (c >= '0' && c <= '9'));
  }

  private boolean isNotEqual(char a, char b) {
    return Character.toLowerCase(a) != Character.toLowerCase(b);
  }
}

// ",,c,"
// left = 3, right = 1

// s is null; s is empty

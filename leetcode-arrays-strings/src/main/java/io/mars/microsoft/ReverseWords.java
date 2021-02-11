package io.mars.microsoft;

/**
 * Given an input string s, reverse the order of the words.
 * <p>
 * A word is defined as a sequence of non-space characters. The words in s will be separated by at least one space.
 * <p>
 * Return a string of the words in reverse order concatenated by a single space.
 * <p>
 * Note that s may contain leading or trailing spaces or multiple spaces between two words. The returned string should only have a single space separating the words. Do not include any extra spaces.
 * <p>
 * <p>
 * Example 1:
 * Input: s = "the sky is blue"
 * Output: "blue is sky the"
 * <p>
 * Example 2:
 * Input: s = "  hello world  "
 * Output: "world hello"
 * Explanation: Your reversed string should not contain leading or trailing spaces.
 * <p>
 * Example 3:
 * Input: s = "a good   example"
 * Output: "example good a"
 * Explanation: You need to reduce multiple spaces between two words to a single space in the reversed string.
 * <p>
 * Example 4:
 * Input: s = "  Bob    Loves  Alice   "
 * Output: "Alice Loves Bob"
 * <p>
 * Example 5:
 * Input: s = "Alice does not even like bob"
 * Output: "bob like even not does Alice"
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= s.length <= 104
 * s contains English letters (upper-case and lower-case), digits, and spaces ' '.
 * There is at least one word in s.
 * <p>
 * <p>
 * Follow up:
 * <p>
 * Could you solve it in-place with O(1) extra space?
 * For Java, need to assume passed in params is a char array.
 */
public class ReverseWords {
  public String reverseWords(String s) {
    char[] chars = s.toCharArray();
    int length = moveExtraSpaceToEnd(chars);
    if(length == 0) return "";

    // Reverse entire string
    reverseCharArr(chars, 0, length - 1);
    // Reverse each word which is already reversed once, e.g abc (original) -> cba (current) -> abc (reversed back)
    int left = 0;
    int right = 1;
    while(right < length) {
      while (right < length && chars[right] != ' ') right++;

      reverseCharArr(chars, left, right - 1);
      left = right + 1;
      right = left + 1;
    }

    return new String(chars, 0, length);
  }

  private void reverseCharArr(char[] chars, int start, int end) {
    int left = start;
    int right = end;
    while(left < right) {
      char temp = chars[left];
      chars[left] = chars[right];
      chars[right] = temp;

      left++;
      right--;
    }
  }

  /**
   * Move extra space to the end of array.
   * @param chars The array to modify
   * @return The length without extra spaces.
   */
  private int moveExtraSpaceToEnd(char[] chars) {
    int actual = 0;
    int expect = 0;

    int spaceNum = 1;

    while(actual < chars.length) {
      while(actual < chars.length && chars[actual] == ' ') {
        actual++;
      }

      spaceNum = 0;
      while(actual < chars.length && spaceNum == 0) {
        chars[expect] = chars[actual];

        if(chars[actual] == ' ') {
          spaceNum = 1;
        }

        expect++;
        actual++;
      }
    }

    if(expect > 1 && chars[expect] == ' ') expect--;
    return expect;
  }

}

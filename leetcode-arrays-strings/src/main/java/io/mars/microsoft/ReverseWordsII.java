package io.mars.microsoft;

/**
 * Given an input string , reverse the string word by word.
 *
 * Example:
 *
 * Input:  ["t","h","e"," ","s","k","y"," ","i","s"," ","b","l","u","e"]
 * Output: ["b","l","u","e"," ","i","s"," ","s","k","y"," ","t","h","e"]
 * Note:
 *
 * A word is defined as a sequence of non-space characters.
 * The input string does not contain leading or trailing spaces.
 * The words are always separated by a single space.
 * Follow up: Could you do it in-place without allocating extra space?
 */
public class ReverseWordsII {
  public void reverseWords(char[] s) {
    if(s == null) return;

    // Revert each character: [cat dog] -> [god tac]
    revert(s, 0, s.length - 1);

    // For each work, revert again: [god tac] -> [dog cat]
    int left = 0;
    int right = 1;
    while(left < s.length) {
      while(right < s.length && s[right] != ' ') right++;

      if(right <= s.length) {
        revert(s, left, right - 1);
      }
      left = right + 1;
      right = left + 1;
    }
  }

  private void revert(char[] s, int left, int right) {
    while(left < right) {
      char temp = s[left];
      s[left] = s[right];
      s[right] = temp;

      left++;
      right--;
    }
  }
}

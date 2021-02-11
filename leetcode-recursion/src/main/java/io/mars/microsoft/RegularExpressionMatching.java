package io.mars.microsoft;

public class RegularExpressionMatching {
  public boolean isMatch(String s, String p) {
    return isMatch(s.toCharArray(), 0, p.toCharArray(), 0);
  }

  private boolean isMatch(char[] text, int textStartIndex, char[] pattern, int patternStartIndex) {
    if(patternStartIndex >= pattern.length) return textStartIndex >= text.length;

    // Check if first character matches
    boolean isFirstMatch = textStartIndex < text.length
            && (text[textStartIndex] == pattern[patternStartIndex] || pattern[patternStartIndex] == '.');

    // If next char is '*', check two possible cases:
    // 1. '*' matches 0 chars;
    // 2. '*' matches first char, then we need to check if rest of text matches after remove the first matched char.
    if(patternStartIndex + 1 < pattern.length && pattern[patternStartIndex + 1] == '*') {
      return isMatch(text, textStartIndex, pattern, patternStartIndex + 2)
          || (isFirstMatch && isMatch(text, textStartIndex + 1, pattern, patternStartIndex));
    } else {
      return isFirstMatch && isMatch(text, textStartIndex + 1, pattern, patternStartIndex + 1);
    }
  }
}

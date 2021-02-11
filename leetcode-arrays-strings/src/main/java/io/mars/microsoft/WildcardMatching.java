package io.mars.microsoft;

public class WildcardMatching {

  public boolean isMatch(String s, String p) {
    char[] text = s.toCharArray();
    char[] pattern = p.toCharArray();

    int textIndex = 0, patternIndex = 0, lastStartIndex = -1, textBackportIndex = 0;
    while (textIndex < text.length) {
      if (patternIndex >= pattern.length && lastStartIndex < 0) return false;
      else if (patternIndex >= pattern.length) {
        patternIndex = lastStartIndex;
        textIndex = textBackportIndex + 1;
      }

      char patternChar = pattern[patternIndex];
      boolean matched = false;
      switch (patternChar) {
        case '*':
          lastStartIndex = patternIndex;
          textBackportIndex = textIndex;
          if (patternIndex + 1 == pattern.length) return true;

          textIndex = findNextMatch(text, textIndex, pattern[++patternIndex]);
          if (textIndex < text.length) matched = true;
          break;
        case '?':
          matched = true;
          break;
        default:
          if (text[textIndex] == patternChar) matched = true;
          else if (lastStartIndex >= 0) {
            patternIndex = lastStartIndex;
            textIndex = textBackportIndex + 1;
          } else return false;
      }

      if (matched) {
        patternIndex++;
        textIndex++;
      }
    }

    while (patternIndex < pattern.length && pattern[patternIndex] == '*') patternIndex++;

    return patternIndex >= pattern.length;
  }

  private int findNextMatch(char[] text, int textStart, char target) {
    if (text[textStart] == '?') return textStart;

    while (textStart < text.length) {
      if (text[textStart] == target) return textStart;

      textStart++;
    }
    return textStart;
  }
}

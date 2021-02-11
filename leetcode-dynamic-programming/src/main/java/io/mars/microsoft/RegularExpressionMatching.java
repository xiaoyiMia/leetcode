package io.mars.microsoft;

public class RegularExpressionMatching {
  public boolean isMatch(String s, String p) {
    char[] text = s.toCharArray();
    char[] pattern = p.toCharArray();

    // dp[i][j] = if substring(s[i:]) matches substring(p[j:]);
    boolean[][] dp = new boolean[text.length + 1][pattern.length + 1];
    dp[text.length][pattern.length] = true;

    for (int i = text.length; i >= 0; i--) {
      for (int j = pattern.length - 1; j >= 0; j--) {
        boolean isCurrentMatch = i < text.length && (text[i] == pattern[j] || pattern[j] == '.');

        // If next char is '*', check two possible cases:
        // 1. '*' matches 0 chars;
        // 2. '*' matches first char, then we need to check if rest of text matches after remove the first matched char.
        if (j + 1 < pattern.length && pattern[j + 1] == '*') {
          dp[i][j] = dp[i][j + 2] || (isCurrentMatch && dp[i + 1][j]);
        } else {
          dp[i][j] = isCurrentMatch && dp[i + 1][j + 1];
        }
      }
    }

    return dp[0][0];
  }
}

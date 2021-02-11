package io.mars.amazon;

import java.util.ArrayList;
import java.util.List;

/**
 * You have an array of logs.  Each log is a space delimited string of words.
 * <p>
 * For each log, the first word in each log is an alphanumeric identifier.  Then, either:
 * <p>
 * Each word after the identifier will consist only of lowercase letters, or;
 * Each word after the identifier will consist only of digits.
 * We will call these two varieties of logs letter-logs and digit-logs.  It is guaranteed that each log has at least one word after its identifier.
 * <p>
 * Reorder the logs so that all of the letter-logs come before any digit-log.  The letter-logs are ordered lexicographically ignoring identifier, with the identifier used in case of ties.  The digit-logs should be put in their original order.
 * <p>
 * Return the final order of the logs.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: logs = ["dig1 8 1 5 1","let1 art can","dig2 3 6","let2 own kit dig","let3 art zero"]
 * Output: ["let1 art can","let3 art zero","let2 own kit dig","dig1 8 1 5 1","dig2 3 6"]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 0 <= logs.length <= 100
 * 3 <= logs[i].length <= 100
 * logs[i] is guaranteed to have an identifier, and a word after the identifier.
 */
public class ReorderLogFiles {
  public String[] reorderLogFiles(String[] logs) {
    List<char[]> letterLogs = new ArrayList<>();
    List<String> digitLogs = new ArrayList<>();

    for (String log : logs) {
      if (log.charAt(log.indexOf(" ") + 1) >= 'a') letterLogs.add(log.toCharArray());
      else digitLogs.add(log);
    }

    letterLogs.sort(this::sortLetterLogs);

    String[] results = new String[letterLogs.size() + digitLogs.size()];
    int i = 0;
    for (char[] log : letterLogs) {
      results[i] = String.valueOf(log);
      i++;
    }
    for (String log : digitLogs) {
      results[i] = log;
      i++;
    }
    return results;
  }

  private int sortLetterLogs(char[] c1, char[] c2) {
    int sc1 = 0, sc2 = 0;
    while (c1[sc1] != ' ') sc1++;
    while (c2[sc2] != ' ') sc2++;

    int compare = compareLetters(c1, sc1 + 1, c1.length, c2, sc2 + 1, c2.length);
    if (compare == 0) {
      return compareLetters(c1, 0, sc1, c2, 0, sc2);
    }
    return compare;
  }

  private int compareLetters(char[] c1, int l1, int r1, char[] c2, int l2, int r2) {
    int i = l1, j = l2;
    while (i < r1 && j < r2) {
      if (c1[i] < c2[j]) return -1;
      else if (c1[i] > c2[j]) return 1;

      i++;
      j++;
    }

    return Integer.compare(r1 - l1, r2 - l2);
  }
}

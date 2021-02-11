package io.mars.amazon;

import java.util.ArrayList;
import java.util.List;

/**
 * A string S of lowercase English letters is given. We want to partition this string into as many parts as possible so
 * that each letter appears in at most one part, and return a list of integers representing the size of these parts.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: S = "ababcbacadefegdehijhklij"
 * Output: [9,7,8]
 * Explanation:
 * The partition is "ababcbaca", "defegde", "hijhklij".
 * This is a partition so that each letter appears in at most one part.
 * A partition like "ababcbacadefegde", "hijhklij" is incorrect, because it splits S into less parts.
 * <p>
 * <p>
 * Note:
 * <p>
 * S will have length in range [1, 500].
 * S will consist of lowercase English letters ('a' to 'z') only.
 */
public class PartitionLabels {
  public List<Integer> partitionLabels(String S) {
    char[] chars = S.toCharArray();

    int[] largestPosition = new int[26];
    for (int i = 0; i < chars.length; i++) {
      int letterP = chars[i] - 'a';
      largestPosition[letterP] = i;
    }

    List<Integer> results = new ArrayList<>();
    int startP = -1;
    int maxP = 0;
    for (int i = 0; i < chars.length; i++) {
      maxP = Math.max(maxP, largestPosition[chars[i] - 'a']);

      if (i == maxP) {
        results.add(i - startP);
        startP = i;
      }
    }
    return results;
  }
}

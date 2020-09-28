package io.mars.amazon;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, determine
 * if s can be segmented into a space-separated sequence of one or more dictionary words.
 *
 * Note:
 *
 * The same word in the dictionary may be reused multiple times in the segmentation.
 * You may assume the dictionary does not contain duplicate words.
 * Example 1:
 *
 * Input: s = "leetcode", wordDict = ["leet", "code"]
 * Output: true
 * Explanation: Return true because "leetcode" can be segmented as "leet code".
 * Example 2:
 *
 * Input: s = "applepenapple", wordDict = ["apple", "pen"]
 * Output: true
 * Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
 *              Note that you are allowed to reuse a dictionary word.
 * Example 3:
 *
 * Input: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
 * Output: false
 */
public class WordBreak {
  public boolean wordBreak(String s, List<String> wordDict) {
    boolean[] visited = new boolean[s.length()];
    return matchDic(wordDict, s, 0, visited);
  }

  private boolean matchDic(List<String> wordDict, String s, int curIndex, boolean[] visited) {
    if(curIndex >= s.length()) return true;
    else if(visited[curIndex]) return false;

    visited[curIndex] = true;
    for (String word : wordDict) {
      if(s.startsWith(word, curIndex)) {
        if(matchDic(wordDict, s, curIndex + word.length(), visited)) return true;
      }
    }
    return false;
  }

  /**
   * The intuition behind this approach is that the given problem (ss) can be divided into sub-problems s1s1 and s2s2.
   * If these subproblems individually satisfy the required conditions, the complete problem, ss also satisfies the
   * same. e.g. "catsanddog" can be split into two substrings "catsand", "dog".
   * The sub-problem "catsand" can be further divided into "cats","and", which individually are a part of the
   * dictionary making "catsand" satisfy the condition.
   * Going further backwards, "catsand", "dog" also satisfy the required criteria individually leading to the
   * complete string "catsanddog" also to satisfy the criteria.
   *
   * Now, we'll move onto the process of \text{dp}dp array formation. We make use of dp array of size n+1, where nn
   * is the length of the given string. We also use two index pointers i and j, where i refers to the length of the
   * substring (s') considered currently starting from the beginning, and jj refers to the index partitioning the
   * current substring (s') into smaller substrings s'(0,j) and s'(j+1,i). To fill in the dp array, we initialize the
   * element dp[0] as true, since the null string is always present in the dictionary, and the rest of the elements of
   * dp as false. We consider substrings of all possible lengths starting from the beginning by making use of index i.
   * For every such substring, we partition the string into two further substrings s1' and s2' in all possible ways
   * using the index j (Note that the ii now refers to the ending index of s2'). Now, to fill in the entry dp[i], we
   * check if the dp[j] contains true, i.e. if the substring s1' fulfills the required criteria. If so, we further
   * check if s2' is present in the dictionary. If both the strings fulfill the criteria, we make dp[i] as true,
   * otherwise as false.
   */
  public boolean solution2(String s, List<String> wordDict) {
    boolean[] dp = new boolean[s.length() + 1];
    dp[0] = true;

    Set<String> dict = new HashSet<>(wordDict);

    for(int j = 1; j <= s.length(); j++) {
      for(int i = 0; i < j; i++) {
        if(dp[i] && dict.contains(s.substring(i, j))) {
          dp[j] = true;
          break;
        }
      }
    }

    return dp[s.length()];
  }
}

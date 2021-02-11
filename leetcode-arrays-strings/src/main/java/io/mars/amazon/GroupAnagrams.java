package io.mars.amazon;

import java.util.*;

/**
 * Given an array of strings, group anagrams together.
 *
 * Example:
 *
 * Input: ["eat", "tea", "tan", "ate", "nat", "bat"],
 * Output:
 * [
 *   ["ate","eat","tea"],
 *   ["nat","tan"],
 *   ["bat"]
 * ]
 * Note:
 *
 * All inputs will be in lowercase.
 * The order of your output does not matter.
 */
public class GroupAnagrams {
  public List<List<String>> groupAnagrams(String[] strs) {
    Map<String, List<String>> groupMap = new HashMap<>();
    for(String str: strs) {
      char[] chars = str.toCharArray();
      Arrays.sort(chars);
      String sortedStr = String.valueOf(chars);

      List<String> members = groupMap.computeIfAbsent(sortedStr, k -> new ArrayList<>());
      members.add(str);
    }

    return new ArrayList<>(groupMap.values());
  }

}

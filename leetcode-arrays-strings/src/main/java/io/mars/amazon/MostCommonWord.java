package io.mars.amazon;

import java.util.*;

/**
 * Given a paragraph and a list of banned words, return the most frequent word that is not in the list of banned words.  It is guaranteed there is at least one word that isn't banned, and that the answer is unique.
 *
 * Words in the list of banned words are given in lowercase, and free of punctuation.
 * Words in the paragraph are not case sensitive.  The answer is in lowercase.
 *
 *
 *
 * Example:
 *
 * Input:
 * paragraph = "Bob hit a ball, the hit BALL flew far after it was hit."
 * banned = ["hit"]
 * Output: "ball"
 * Explanation:
 * "hit" occurs 3 times, but it is a banned word.
 * "ball" occurs twice (and no other word does), so it is the most frequent non-banned word in the paragraph.
 * Note that words in the paragraph are not case sensitive,
 * that punctuation is ignored (even if adjacent to words, such as "ball,"),
 * and that "hit" isn't the answer even though it occurs more because it is banned.
 *
 *
 * Note:
 *
 * 1 <= paragraph.length <= 1000.
 * 0 <= banned.length <= 100.
 * 1 <= banned[i].length <= 10.
 * The answer is unique, and written in lowercase (even if its occurrences in paragraph may have uppercase symbols,
 * and even if it is a proper noun.)
 * paragraph only consists of letters, spaces, or the punctuation symbols !?',;.
 * There are no hyphens or hyphenated words.
 * Words only consist of letters, never apostrophes or other punctuation symbols.
 */
public class MostCommonWord {
  public String mostCommonWord(String paragraph, String[] banned) {
    Set<String> bannedWords = new HashSet<>(Arrays.asList(banned));

    Map<String, Integer> frequencyMap = new HashMap<>();
    String target = "";
    int maxFrequency = 0;

    for(int i = 0; i < paragraph.length(); i++) {

      StringBuilder sb = new StringBuilder();
      char c;
      // find a word
      while(i < paragraph.length() && Character.isLetter(c = paragraph.charAt(i))) {
        sb.append(Character.toLowerCase(c));
        i++;
      }

      String word = sb.toString();
      if(!word.isEmpty() && !bannedWords.contains(word)) {
        // Update frequency map, and max_frequency result
        int frequency = frequencyMap.getOrDefault(word, 0);
        frequency++;
        if(frequency > maxFrequency) {
          maxFrequency = frequency;
          target = word;
        }
        frequencyMap.put(word, frequency);
      }
    }

    return target;
  }

}

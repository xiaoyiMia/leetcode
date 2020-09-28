package io.mars.tree.amazon;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest transformation sequence from beginWord to endWord, such that:
 *
 * 1. Only one letter can be changed at a time.
 * 2. Each transformed word must exist in the word list.
 *
 * Note:
 * Return 0 if there is no such transformation sequence.
 * All words have the same length.
 * All words contain only lowercase alphabetic characters.
 * You may assume no duplicates in the word list.
 * You may assume beginWord and endWord are non-empty and are not the same.
 * Example 1:
 *
 * Input:
 * beginWord = "hit",
 * endWord = "cog",
 * wordList = ["hot","dot","dog","lot","log","cog"]
 *
 * Output: 5
 *
 * Explanation: As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
 * return its length 5.
 * Example 2:
 *
 * Input:
 * beginWord = "hit"
 * endWord = "cog"
 * wordList = ["hot","dot","dog","lot","log"]
 *
 * Output: 0
 *
 * Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.
 */
public class WordLadder {
  public int ladderLength(String beginWord, String endWord, List<String> wordList) {
    Set<String> sources = new HashSet<>();
    sources.add(beginWord);
    Set<String> dictionary = new HashSet<>(wordList);

    int count = 1;
    while (!dictionary.isEmpty()) {
      Set<String> words = new HashSet<>();
      for(String word: sources) {
        Set<String> targets = findTransformWords(word, dictionary);
        if(targets.contains(endWord)) return count + 1;

        dictionary.removeAll(targets);
        words.addAll(targets);
      }
      if(words.isEmpty()) return 0;
      count++;
      sources = words;
    }
    return 0;
  }

  private Set<String> findTransformWords(String beginWord, Set<String> wordList) {
    Set<String> targets = new HashSet<>();
    for(String word: wordList) {
      int diffCount = 0;
      for(int i=0; i< word.length(); i++) {
        if(word.charAt(i) != beginWord.charAt(i)) diffCount++;
      }

      if(diffCount == 1) targets.add(word);
    }
    return targets;
  }

  public int solution2(String beginWord, String endWord, List<String> wordList) {
    Set<String> dictionary = new HashSet<>(wordList);
    if(!dictionary.contains(endWord)) return 0;

    Set<String> startWords = new HashSet<>();
    startWords.add(beginWord);
    Set<String> endWords = new HashSet<>();
    endWords.add(endWord);
    return findPath(startWords, endWords, dictionary, 1);
  }

  private int findPath(Set<String> startWords, Set<String> endWords, Set<String> dictionary, int length) {
    if(startWords.isEmpty() || endWords.isEmpty()) return 0;
    dictionary.removeAll(startWords);

    Set<String> targets = new HashSet<>();
    for(String word: startWords) {
      boolean found = findTransformWords(dictionary, endWords, word, targets);
      if(found) return length + 1;
    }

    if(targets.size() < endWords.size()) return findPath(targets, endWords, dictionary, length + 1);
    else return findPath(endWords, targets, dictionary, length + 1);
  }

  // return TRUE if found end words.
  private boolean findTransformWords(Set<String> wordList, Set<String> endWords, String beginWord,
                                     Set<String> targets) {
    for(String word: wordList) {
      int diffCount = 0;
      for(int i=0; i< word.length(); i++) {
        if(word.charAt(i) != beginWord.charAt(i)) diffCount++;
      }

      if(diffCount == 1) {
        targets.add(word);
        if(endWords.contains(word)) return true;
      }
    }
    return false;
  }

}

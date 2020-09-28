package io.mars.tree.amazon;

import java.util.*;

/**
 * Given two words (beginWord and endWord), and a dictionary's word list, find all shortest transformation sequence(s)
 * from beginWord to endWord, such that:
 *
 * 1. Only one letter can be changed at a time
 * 2. Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
 * Note:
 *
 * Return an empty list if there is no such transformation sequence.
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
 * Output:
 * [
 *   ["hit","hot","dot","dog","cog"],
 *   ["hit","hot","lot","log","cog"]
 * ]
 * Example 2:
 *
 * Input:
 * beginWord = "hit"
 * endWord = "cog"
 * wordList = ["hot","dot","dog","lot","log"]
 *
 * Output: []
 *
 * Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.
 */
public class WordLadderII {
  public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
    List<List<String>> results = new ArrayList<>();

    // Find transform candidates for beginWord
    Set<String> targets = findTransformWord(beginWord, wordList);
    if(targets.contains(endWord)) {
      results.add(Arrays.asList(beginWord, endWord));
      return results;
    }

    // Check if endWord in the wordList; otherwise remove it from wordList
    if(!wordList.remove(endWord))  return results;
    // Find transform candidates for each level of nodes
    Queue<Node> nodes = new LinkedList<>();
    nodes.add(new Node(endWord, null));
    boolean found = false;
    while(!nodes.isEmpty()) {
      if(found) break;

      Queue<Node> nextLevel = new LinkedList<>();
      Set<String> wordsToRemove = new HashSet<>();
      while (!nodes.isEmpty()) {
        Node node = nodes.poll();
        Set<String> candidates = findTransformWord(node.value, wordList);
        wordsToRemove.addAll(candidates);

        for(String word: candidates) {
          Node next = new Node(word, node);

          if(targets.contains(word)) {
            results.add(getTransformationSequence(next, beginWord));
            found = true;
          }

          nextLevel.add(next);
        }
      }
      wordList.removeAll(wordsToRemove);
      nodes = nextLevel;
    }
    return results;
  }

  private Set<String> findTransformWord(String beginWord, List<String> wordList) {
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

  private List<String> getTransformationSequence(Node node, String beginWord) {
    List<String> transformationSequence = new ArrayList<>();
    transformationSequence.add(beginWord);
    while(node != null) {
      transformationSequence.add(node.value);
      node = node.parent;
    }
    return transformationSequence;
  }


  static class Node{
    private String value;
    private Node parent;

    Node(String value, Node parent) {
      this.value = value;
      this.parent = parent;
    }
  }
}


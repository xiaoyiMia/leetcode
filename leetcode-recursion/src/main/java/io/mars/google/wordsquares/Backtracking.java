package io.mars.google.wordsquares;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Backtracking implements WordSquares {

  @Override
  public List<List<String>> wordSquares(String[] words) {
    Map<String, List<String>> prefixMap = new HashMap<>();
    for (String word : words) {
      for (int i = 1; i <= word.length(); i++) {
        String prefix = word.substring(0, i);
        prefixMap.putIfAbsent(prefix, new ArrayList<>());
        prefixMap.get(prefix).add(word);
      }
    }

    List<List<String>> results = new ArrayList<>();

    for (String word : words) {
      List<String> square = new ArrayList<>();
      square.add(word);
      backtracking(prefixMap, 1, square, results);
    }
    return results;
  }

  private void backtracking(Map<String, List<String>> prefixMap, int step, List<String> square,
                            List<List<String>> results) {

    if (step == square.get(0).length()) {
      results.add(new ArrayList<>(square));
      return;
    }

    StringBuilder prefixBuilder = new StringBuilder();
    for (String word : square) {
      prefixBuilder.append(word.charAt(step));
    }

    for (String word : prefixMap.getOrDefault(prefixBuilder.toString(), new ArrayList<>())) {
      square.add(word);
      backtracking(prefixMap, step + 1, square, results);
      square.remove(square.size() - 1);
    }
  }
}

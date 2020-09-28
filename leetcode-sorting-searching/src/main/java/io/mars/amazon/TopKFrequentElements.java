package io.mars.amazon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Given a non-empty array of integers, return the k most frequent elements.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,1,1,2,2,3], k = 2
 * Output: [1,2]
 * Example 2:
 * <p>
 * Input: nums = [1], k = 1
 * Output: [1]
 * Note:
 * <p>
 * You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
 * Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
 * It's guaranteed that the answer is unique, in other words the set of the top k frequent elements is unique.
 * You can return the answer in any order.
 */
public class TopKFrequentElements {
  public int[] topKFrequent(int[] nums, int k) {
    // Find the frequency for each unique element, and sorted by frequencies.
    Map<Integer, Integer> frequencyMap = calculateFrequency(nums);

    List<Integer> ordered = frequencyMap.entrySet().stream()
        .sorted(Map.Entry.comparingByValue())
        .map(Map.Entry::getKey)
        .collect(Collectors.toList());

    int[] results = new int[k];
    int uniqueSize = ordered.size();
    for (int i = 0; i < k; i++) {
      results[i] = ordered.get(uniqueSize - 1 - i);
    }
    return results;
  }

  public int[] solution2(int[] nums, int k) {
    // Find the frequency for each unique element, and use frequency as array index.
    Map<Integer, Integer> frequency = new HashMap<>();
    for (int num : nums) frequency.put(num, frequency.getOrDefault(num, 0) + 1);

    List[] buckets = new List[nums.length + 1];
    // put unique element into a list at the place index = frequency
    for (Map.Entry<Integer, Integer> entry : frequency.entrySet()) {
      if(buckets[entry.getValue()] == null)  buckets[entry.getValue()] =  new ArrayList<>();
      buckets[entry.getValue()].add(entry.getKey());
    }

    int[] results = new int[k];
    for(int i = 0, j = buckets.length - 1; i < k; j--) {
      if(buckets[j] != null) {
        for (Object element : buckets[j]) {
          results[i] = (int)element;
          i++;
        }
      }
    }

    return results;
  }

  private Map<Integer, Integer> calculateFrequency(int[] nums) {
    Map<Integer, Integer> frequency = new HashMap<>();
    for (int num : nums) {
      Integer f = frequency.getOrDefault(num, 0);
      frequency.put(num, f + 1);
    }
    return frequency;
  }
}

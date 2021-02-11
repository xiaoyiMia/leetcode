package io.mars.amazon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Convert a non-negative integer to its english words representation. Given input is guaranteed to be less than 231 - 1.
 * <p>
 * Example 1:
 * <p>
 * Input: 123
 * Output: "One Hundred Twenty Three"
 * Example 2:
 * <p>
 * Input: 12345
 * Output: "Twelve Thousand Three Hundred Forty Five"
 * Example 3:
 * <p>
 * Input: 1234567
 * Output: "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
 * Example 4:
 * <p>
 * Input: 1234567891
 * Output: "One Billion Two Hundred Thirty Four Million Five Hundred Sixty Seven Thousand Eight Hundred Ninety One"
 */
public class IntegerToEnglishWords {
  public String numberToWords(int num) {
    if(num == 0) return "Zero";

    String[] suffixWords = new String[]{"", "Thousand", "Million", "Billion"};
    List<Integer> numChunks = chunkNumber(num);
    Map<Integer, String> dic = getEnglishDictionary();

    StringBuilder sb = new StringBuilder();
    for (int i = numChunks.size() - 1; i >= 0; i--) {
      sb.append(helper(dic, numChunks.get(i), suffixWords[i]));
    }
    return sb.toString().trim();
  }

  public String solution2(int num) {
    if(num == 0) return "Zero";

    return helper(getEnglishDictionary(), num);
  }

  private String helper(Map<Integer, String> dic, int number, String suffix) {
    if(number == 0) return "";

    StringBuilder sb = new StringBuilder();
    // x Hundred
    int hundreds = number / 100;
    if(hundreds > 0) {
      sb.append(dic.get(hundreds)).append(" Hundred ");
      number %= 100;
    }

    // 20 ~ 99
    if(number >= 20) {
      int digit = number % 10;
      sb.append(dic.get(number - digit)).append(" ");
      number = digit;
    }

    //1 ~ 19
    sb.append(dic.get(number));

    String words = sb.toString().trim();
    if(!words.isEmpty() && !suffix.isEmpty()) words = words + " " + suffix + " ";
    return words;
  }

  private List<Integer> chunkNumber(int num) {
    List<Integer> chunks = new ArrayList<>(4);
    while (num > 0) {
      chunks.add(num % 1000);
      num /= 1000;
    }
    return chunks;
  }

  private Map<Integer, String> getEnglishDictionary() {
    Map<Integer, String> dic = new HashMap<>(28);
    dic.put(0, "");
    dic.put(1, "One");
    dic.put(2, "Two");
    dic.put(3, "Three");
    dic.put(4, "Four");
    dic.put(5, "Five");
    dic.put(6, "Six");
    dic.put(7, "Seven");
    dic.put(8, "Eight");
    dic.put(9, "Nine");
    dic.put(10, "Ten");
    dic.put(11, "Eleven");
    dic.put(12, "Twelve");
    dic.put(13, "Thirteen");
    dic.put(14, "Fourteen");
    dic.put(15, "Fifteen");
    dic.put(16, "Sixteen");
    dic.put(17, "Seventeen");
    dic.put(18, "Eighteen");
    dic.put(19, "Nineteen");

    dic.put(20, "Twenty");
    dic.put(30, "Thirty");
    dic.put(40, "Forty");
    dic.put(50, "Fifty");
    dic.put(60, "Sixty");
    dic.put(70, "Seventy");
    dic.put(80, "Eighty");
    dic.put(90, "Ninety");
    return dic;
  }

  private String helper(Map<Integer, String> dic, int number) {
    StringBuilder sb = new StringBuilder();

    if(number >= 1000000000) {
      sb.append(helper(dic, number / 1000000000)).append(" Billion ")
          .append(helper(dic, number % 1000000000));
    } else if(number >= 1000000) {
      sb.append(helper(dic, number / 1000000)).append(" Million ")
          .append(helper(dic, number % 1000000));
    } else if(number >= 1000) {
      sb.append(helper(dic, number / 1000)).append(" Thousand ")
          .append(helper(dic, number % 1000));
    } else if(number >= 100) {
      sb.append(helper(dic, number / 100)).append(" Hundred ")
          .append(helper(dic, number % 100));
    } else if(number >= 20) {
      int digit = number % 10;
      sb.append(dic.get(number - digit)).append(" ").append(dic.get(digit));
    } else {
      sb.append(dic.get(number));
    }

    return sb.toString().trim();
  }
}

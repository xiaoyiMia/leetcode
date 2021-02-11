package io.mars.google;

public class MultiplyStrings {
  public String multiply(String num1, String num2) {
    int length1 = num1.length();
    int length2 = num2.length();

    int result[] = new int[length1 + length2];
    for (int index1 = length1 - 1; index1 >= 0; index1--) {
      for (int index2 = length2 - 1; index2 >= 0; index2--) {
        int mul = (num1.charAt(index1) - '0') * (num2.charAt(index2) - '0') + result[index1 + index2 + 1];
        result[index1 + index2 + 1] = mul % 10;
        result[index1 + index2] += (mul / 10);
      }
    }

    // Remove 0s from the top
    int startIndex = 0;
    while (startIndex < result.length - 1 && result[startIndex] == 0) {
      startIndex++;
    }
    StringBuilder resultBuilder = new StringBuilder();
    for (int i = startIndex; i < result.length; i++) {
      resultBuilder.append(result[i]);
    }
    return resultBuilder.toString();
  }
}

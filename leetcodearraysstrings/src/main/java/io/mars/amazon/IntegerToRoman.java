package io.mars.amazon;

import java.util.HashMap;
import java.util.Map;

public class IntegerToRoman {

  public String intToRoman(int num) {
    Map<Integer, String> romanNumbersMap = getRomanNumbersMap();

    String numStr = String.valueOf(num);
    int exponent = numStr.length() - 1;

    StringBuilder romanNumber = new StringBuilder();
    for(int i = 0; i <= exponent; i++) {
      romanNumber.append(getRomanNumber(romanNumbersMap, numStr.charAt(i) - 48, exponent - i));
    }
    return romanNumber.toString();
  }

  public String solution2(int num) {
    String[] thousands = {"", "M", "MM", "MMM"};
    String[] hundreds = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
    String[] tens = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
    String[] ones = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};

    return new StringBuilder()
        .append(thousands[num / 1000])
        .append(hundreds[num % 1000 / 100])
        .append(tens[num % 100 / 10])
        .append(ones[num % 10])
        .toString();
  }

  public String solution3(int num) {
    int[] integers = {1,4,5,9,10,40,50,90,100,400,500,900,1000};
    String[] romans = {"I","IV","V","IX","X","XL","L","XC","C","CD","D","CM","M"};

    int index = integers.length - 1;
    StringBuilder romanNumber = new StringBuilder();

    while(num > 0) {
      while(num >= integers[index]) {
        romanNumber.append(romans[index]);
        num -= integers[index];
      }
      index--;
    }
    return romanNumber.toString();
  }

  private String getRomanNumber(Map<Integer, String> romanNumbersMap, int singleNum, int exponent) {
    if(singleNum == 0) return "";

    StringBuilder romanNumber = new StringBuilder();
    int roundNumber = (int) Math.pow(10, exponent);

    if(singleNum == 4 || singleNum == 9) {
      romanNumber.append(romanNumbersMap.get(singleNum * roundNumber));
    } else if(singleNum < 5) {
      String roman = romanNumbersMap.get(roundNumber);
      for(int n = singleNum; n > 0; n--) {
        romanNumber.append(roman);
      }
    } else {
      int largestNum = roundNumber * 5;
      romanNumber.append(romanNumbersMap.get(largestNum));
      romanNumber.append(getRomanNumber(romanNumbersMap, singleNum - 5, exponent));
    }
    return romanNumber.toString();
  }

  private Map<Integer, String> getRomanNumbersMap() {
    Map<Integer, String> romanNumbers = new HashMap<>();
    romanNumbers.put(1, "I");
    romanNumbers.put(4, "IV");
    romanNumbers.put(5, "V");
    romanNumbers.put(9, "IX");
    romanNumbers.put(10, "X");
    romanNumbers.put(40, "XL");
    romanNumbers.put(50, "L");
    romanNumbers.put(90, "XC");
    romanNumbers.put(100, "C");
    romanNumbers.put(400, "CD");
    romanNumbers.put(500, "D");
    romanNumbers.put(900, "CM");
    romanNumbers.put(1000, "M");
    return romanNumbers;
  }
}

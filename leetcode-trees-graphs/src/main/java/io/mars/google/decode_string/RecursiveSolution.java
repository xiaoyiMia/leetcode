package io.mars.google.decode_string;

public class RecursiveSolution implements DecodeString {

  private int index = 0;

  @Override
  public String decodeString(String s) {
    StringBuilder result = new StringBuilder();
    while (index < s.length() && s.charAt(index) != ']') {
      if(!Character.isDigit(s.charAt(index))) result.append(s.charAt(index++));
      else {
        int number = s.charAt(index++) - '0';
        while (Character.isDigit(s.charAt(index))) {
          number = number * 10 + s.charAt(index++) - '0';
        }
        // ignore '['
        index++;
        String decodedString = decodeString(s);
        // ignore ']'
        index++;

        while(number-- > 0) result.append(decodedString);
      }
    }
    return result.toString();
  }
}

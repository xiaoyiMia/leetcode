package io.mars.google.decode_string;

import java.util.Stack;

public class TwoStacksSolution implements DecodeString {
  public String decodeString(String s) {
    Stack<Character> encode = new Stack<>();
    Stack<StringBuilder> decode = new Stack<>();
    decode.push(new StringBuilder());

    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);

      if (c >= 'a' && c <= 'z') decode.peek().append(c); // append the letter into the last string in decode stack.
      else if ('[' == c) {
        // push a new empty string into decode stack.
        decode.push(new StringBuilder());
      } else if (']' == c) {
        // pop the repeating number and decode the current part. Appending decoded part behind the previous
        // decoded part.
        int count = encode.pop();
        String temp = decode.pop().toString().repeat(count);
        decode.peek().append(temp);
      } else {
        // convert the digits into a number and push into encode stack.
        int number = (i == 0 || !Character.isDigit(s.charAt(i - 1))) ? 0 : encode.pop();
        number = number * 10 + Character.getNumericValue(c);
        encode.push((char)number);
      }
    }
    return decode.pop().toString();
  }

}

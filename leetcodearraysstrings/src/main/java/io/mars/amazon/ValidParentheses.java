package io.mars.amazon;

import java.util.Stack;

/**
 * Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
 * <p>
 * An input string is valid if:
 * <p>
 * Open brackets must be closed by the same type of brackets.
 * Open brackets must be closed in the correct order.
 * Note that an empty string is also considered valid.
 * <p>
 * Example 1:
 * <p>
 * Input: "()"
 * Output: true
 * Example 2:
 * <p>
 * Input: "()[]{}"
 * Output: true
 * Example 3:
 * <p>
 * Input: "(]"
 * Output: false
 * Example 4:
 * <p>
 * Input: "([)]"
 * Output: false
 * Example 5:
 * <p>
 * Input: "{[]}"
 * Output: true
 */
public class ValidParentheses {
  public boolean isValid(String s) {
    Stack<Character> p = new Stack<>();
    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      switch (c) {
        case ')':
          if (!valid(p, '(')) return false;
          break;
        case '}':
          if (!valid(p, '{')) return false;
          break;
        case ']':
          if (!valid(p, '[')) return false;
          break;
        default:
          p.add(c);
          break;
      }
    }

    return p.isEmpty();
  }

  private boolean valid(Stack<Character> s, char t) {
    if(s.isEmpty()) return false;

    if(s.peek() == t) s.pop();
    else s.push(t);
    return true;
  }
}

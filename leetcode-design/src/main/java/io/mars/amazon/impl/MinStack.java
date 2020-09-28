package io.mars.amazon.impl;

import java.util.Stack;

/**
 * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
 *
 * push(x) -- Push element x onto stack.
 * pop() -- Removes the element on top of the stack.
 * top() -- Get the top element.
 * getMin() -- Retrieve the minimum element in the stack.
 *
 *
 * Example 1:
 *
 * Input
 * ["MinStack","push","push","push","getMin","pop","top","getMin"]
 * [[],[-2],[0],[-3],[],[],[],[]]
 *
 * Output
 * [null,null,null,null,-3,null,0,-2]
 *
 * Explanation
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.getMin(); // return -3
 * minStack.pop();
 * minStack.top();    // return 0
 * minStack.getMin(); // return -2
 *
 *
 * Constraints:
 *
 * Methods pop, top and getMin operations will always be called on non-empty stacks.
 */
public class MinStack {
  Stack<Node> stack;

  /** initialize your data structure here. */
  public MinStack() {
    stack = new Stack<>();
  }

  public void push(int x) {
    stack.push(new Node(x, stack.isEmpty() ? x : Math.min(x, stack.peek().minValue)));
  }

  public void pop() {
    if(stack.isEmpty()) throw  new NullPointerException("Stack is empty");
    stack.pop();
  }

  public int top() {
    if(stack.isEmpty()) throw  new NullPointerException("Stack is empty");
    return stack.peek().value;
  }

  public int getMin() {
    return stack.peek().minValue;
  }

  private static class Node {
    private int value;
    private int minValue;

    Node(int value, int minValue) {
      this.value = value;
      this.minValue = minValue;
    }
  }
}

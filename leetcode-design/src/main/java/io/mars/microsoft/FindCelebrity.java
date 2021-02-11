package io.mars.microsoft;

/**
 * Suppose you are at a party with n people (labeled from 0 to n - 1), and among them, there may exist one celebrity. The definition of a celebrity is that all the other n - 1 people know him/her, but he/she does not know any of them.
 * <p>
 * Now you want to find out who the celebrity is or verify that there is not one. The only thing you are allowed to do is to ask questions like: "Hi, A. Do you know B?" to get information about whether A knows B. You need to find out the celebrity (or verify there is not one) by asking as few questions as possible (in the asymptotic sense).
 * <p>
 * You are given a helper function bool knows(a, b) which tells you whether A knows B. Implement a function int findCelebrity(n). There will be exactly one celebrity if he/she is in the party. Return the celebrity's label if there is a celebrity in the party. If there is no celebrity, return -1.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * Input: graph = [[1,1,0],[0,1,0],[1,1,1]]
 * Output: 1
 * Explanation: There are three persons labeled with 0, 1 and 2. graph[i][j] = 1 means person i knows person j, otherwise graph[i][j] = 0 means person i does not know person j. The celebrity is the person labeled as 1 because both 0 and 2 know him but 1 does not know anybody.
 * <p>
 * <p>
 * Example 2:
 * Input: graph = [[1,0,1],[1,1,0],[0,1,1]]
 * Output: -1
 * Explanation: There is no celebrity.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * n == graph.length
 * n == graph[i].length
 * 2 <= n <= 100
 * graph[i][j] is 0 or 1.
 * graph[i][i] == 1
 */
public class FindCelebrity {
  private static final int[][] graph = {{1, 0, 1}, {1, 1, 0}, {0, 1, 1}};

  public int findCelebrity(int n) {
    if (n == 2 && !knows(0, 1) && !knows(1, 0))
      return -1;

    int candidate = 0;

    // If the candidate knows i, the candidate is not a Celebrity, and any n < i cannot be a Celebrity because the
    // candidate doesn't know n.
    // If the candidate doesn't know i, i is cannot be a Celebrity. Therefore, when the iteration end,
    // any n (n <> candidate) cannot be a Celebrity.
    for (int i = 0; i < n; i++) {
      if (knows(candidate, i)) candidate = i;
    }

    // If the candidate doesn't know any n > i, we need check if the candidate doesn't know any n < i.
    for (int i = 0; i < candidate; i++) {
      if (knows(candidate, i)) return -1;
    }

    // If the candidate doesn't now anyone, we need to check if all others know the candidate
    for (int i = 0; i < n; i++) {
      if (!knows(i, candidate)) return -1;
    }

    return candidate;
  }

  private boolean knows(int a, int b) {
    return graph[a][b] == 1;
  }
}

package io.mars.amazon;

import java.util.Arrays;

/**
 * You are given coins of different denominations and a total amount of money amount. Write a function to compute the
 * fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any
 * combination of the coins, return -1.
 *
 * Example 1:
 *
 * Input: coins = [1, 2, 5], amount = 11
 * Output: 3
 * Explanation: 11 = 5 + 5 + 1
 * Example 2:
 *
 * Input: coins = [2], amount = 3
 * Output: -1
 * Note:
 * You may assume that you have an infinite number of each kind of coin.
 */
public class CoinChange {
  public int coinChange(int[] coins, int amount) {
    return coinChange(coins, amount, new int[amount]);
  }

  //Backpack problem.
  //F(S) - minimum number of coins needed to make change for amount S using coin denominations [{c_0, ..., c_(n-1)}]
  //
  //F(S) = F(S - C) + 1, where C is the last denomination we picked up.
  //
  //But we don't know which is the denomination of the last coin C. We compute F(S - c_i) for each possible denomination
  //c_0, c_1, c_2, ..., c_{n -1} and choose the minimum among them. The following recurrence relation holds:
  //
  //F(S) = (min{i=0 ... n-1} F(S - c_i)) + 1
  //F(S)=0,whenS=0; F(S)=−1,when n=0
  private int coinChange(int[] coins, int remain, int[] count) {
    if(remain < 0) return -1;
    if(remain == 0) return 0;
    // Because of different combination of denominations, it's very possible that the calculation for current remained
    // amount already be done.
    if(count[remain - 1] != 0) return count[remain - 1];

    int min = Integer.MAX_VALUE;
    for(int coin: coins) {
      int result = coinChange(coins, remain - coin, count);
      if(result > 0 && result < min) min = result + 1;
    }

    count[remain - 1] = min == Integer.MAX_VALUE ? -1 : min;
    return count[remain - 1];
  }

  //dp[i] = F(i)
  //dp[i] = (min{0,...,j} dp[i - c_j]) + 1
  public int solution2(int[] coins, int amount) {
    int max = amount + 1;
    int[] dp = new int[amount + 1];
    Arrays.fill(dp, max);
    dp[0] = 0;

    for(int i = 1; i <= amount; i++) {
      for (int coin : coins) {
        if(i >= coin) dp[i] = Math.min(dp[i], dp[i - coin] + 1);
      }
    }

    return dp[amount] > amount ? -1: dp[amount];
  }
}

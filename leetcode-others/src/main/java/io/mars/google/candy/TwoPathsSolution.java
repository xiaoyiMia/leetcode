package io.mars.google.candy;

public class TwoPathsSolution implements MinimumCandy {
  @Override
  public int candy(int[] ratings) {
    int[] candy = new int[ratings.length];
    candy[0] = 1;

    // Iterate from left to right, adds up 1 if r[i] > r[i - 1]
    for (int i = 1; i < ratings.length; i++) {
      if (ratings[i] > ratings[i - 1]) candy[i] = candy[i - 1] + 1;
      else candy[i] = 1;
    }

    int sum = candy[candy.length - 1];
    // Iterate from right to left, fill in the larger number
    for (int i = ratings.length - 2; i >= 0; i--) {
      if (ratings[i] > ratings[i + 1]) candy[i] = Math.max(candy[i], candy[i + 1] + 1);
      sum = sum + candy[i];
    }

    return sum;
  }
}

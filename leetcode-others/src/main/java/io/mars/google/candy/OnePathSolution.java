package io.mars.google.candy;

public class OnePathSolution implements MinimumCandy {
  @Override
  public int candy(int[] ratings) {
    if (ratings.length == 1) return 1;

    int candies = 1;

    boolean isUp;
    boolean isDown = ratings[1] < ratings[0];
    int up = 0;
    int down = 0;

    for (int i = 1; i < ratings.length; i++) {
      if(ratings[i] == ratings[i - 1] || isDown && ratings[i] > ratings[i - 1]) {
        // calculation previous mountain
        candies += factorial(up) + factorial(down) + Math.max(up, down);
        // start a new calculation
        up = 0;
        down = 0;
      }

      isUp = ratings[i] > ratings[i - 1];
      isDown = ratings[i] < ratings[i - 1];

      if(isUp) up++;
      else if(isDown) down++;
      else candies++;
    }

    // Do the calculation for remaining marks.
    return candies + factorial(up) + factorial(down) + Math.max(up, down);
  }

  private int factorial(int n) {
    return (n * (n + 1)) / 2;
  }
}

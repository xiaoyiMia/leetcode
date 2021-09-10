package io.mars.google.bulls_cows;

public class TwoPassSolution implements BullsAndCows {
  @Override
  public String getHint(String secret, String guess) {
    int[] frequency = new int[10];
    int bulls = 0;
    int cows = 0;

    for (int i = 0; i < secret.length(); i++) {
      if (guess.charAt(i) == secret.charAt(i)) {
        bulls++;
      } else {
        frequency[secret.charAt(i) - '0'] += 1;
      }
    }

    for (int i = 0; i < guess.length(); i++) {
      char digitG = guess.charAt(i);
      if (secret.charAt(i) != guess.charAt(i) && frequency[digitG - '0'] > 0) {
        cows++;
        frequency[digitG - '0'] -= 1;
      }
    }

    StringBuilder result = new StringBuilder();
    result.append(bulls);
    result.append('A');
    result.append(cows);
    result.append('B');
    return result.toString();
  }
}

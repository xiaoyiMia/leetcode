package io.mars.google.bulls_cows;

public class OnePassSolution implements BullsAndCows {
  @Override
  public String getHint(String secret, String guess) {
    int[] frequency = new int[10];

    int bulls = 0;
    int cows = 0;
    for (int i = 0; i < secret.length(); i++) {
      if(secret.charAt(i) == guess.charAt(i)) bulls++;
      else {
        // if f[s] < 0, the 's' shown in 'guess' previously, cows increased by one.
        int digitS = secret.charAt(i) - '0';
        if(frequency[digitS] < 0) cows++;
        frequency[digitS] += 1;

        // if f[g] > 0, 'secret' contains 'g', cows increased by one
        int digitG = guess.charAt(i) - '0';
        if(frequency[digitG] > 0) cows++;
        frequency[digitG] -= 1;

      }
    }

    StringBuffer result = new StringBuffer("");
    result.append(bulls);
    result.append('A');
    result.append(cows);
    result.append('B');
    return result.toString();
  }
}

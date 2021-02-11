package io.mars.amazon;

import java.util.Arrays;
import java.util.HashMap;

public class PrisonCellAfterNDays {
  public int[] prisonAfterNDays(int[] cells, int N) {
    HashMap<Integer, Integer> stateMap = new HashMap<>();
    int stateBitmap = cellsToBitMap(cells);

    int restSteps = 0;
    for(int i = 0; i< N; i++) {
      stateBitmap = nextDay(stateBitmap);
      if(stateMap.containsKey(stateBitmap)) {
        int stepIndex = stateMap.get(stateBitmap);
        restSteps = ((N - stepIndex - 1) % (stateMap.size() - stepIndex));
        break;
      } else {
        stateMap.put(stateBitmap, i);
      }
    }

    for(int i = 0; i< restSteps; i++) stateBitmap = nextDay(stateBitmap);
    for (int i = cells.length - 1; i >= 0; i--) {
      cells[i] = (stateBitmap & 0x1);
      stateBitmap = stateBitmap >> 1;
    }
    return cells;
  }

  private int cellsToBitMap(int[] cells) {
    int stateBitMap = 0x0;
    for (int cell : cells) {
      stateBitMap <<= 1;
      stateBitMap = (stateBitMap | cell);
    }
    return stateBitMap;
  }

  private int nextDay(int stateBitmap) {
    stateBitmap = ~(stateBitmap << 1) ^ (stateBitmap >> 1);
    // set the head and tail to zero
    stateBitmap = stateBitmap & 0x7e;
    return stateBitmap;
  }
}

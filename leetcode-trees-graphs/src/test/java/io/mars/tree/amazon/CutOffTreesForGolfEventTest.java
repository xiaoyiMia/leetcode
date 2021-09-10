package io.mars.tree.amazon;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import io.mars.tree.amazon.CutOffTreesForGolfEvent;
import org.junit.jupiter.api.Test;

import java.util.List;

public class CutOffTreesForGolfEventTest {

  @Test
  void testAlgorithm() {
    List<List<Integer>> trees = ImmutableList.of(
        Lists.newArrayList(3,5,2,7),
        Lists.newArrayList(9,4,6,8)
//        Lists.newArrayList(668150,92178815,89819108,94701471),
//        Lists.newArrayList(83920491,22724204,46281641,47531096),
//        Lists.newArrayList(89078499,18904913,25462145,60813308)
    );

    System.out.println(new CutOffTreesForGolfEvent().cutOffTree(trees));
  }

}

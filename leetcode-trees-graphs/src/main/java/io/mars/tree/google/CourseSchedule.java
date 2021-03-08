package io.mars.tree.google;

import java.util.*;

public class CourseSchedule {

  public int[] findOrder(int numCourses, int[][] prerequisites) {
    Map<Integer, List<Integer>> coursePrerequisites = new HashMap<>();
    for (int[] prerequisite : prerequisites) {
      List<Integer> req = coursePrerequisites.get(prerequisite[0]);
      if(req == null) coursePrerequisites.put(prerequisite[0], new ArrayList<>());
      coursePrerequisites.get(prerequisite[0]).add(prerequisite[1]);
    }

    List<Integer> orderedCourses = new ArrayList<>();
    Boolean[] visited = new Boolean[numCourses];
    for(int node = 0; node < numCourses; node++) {
      if(dfs(node, coursePrerequisites, visited, orderedCourses)) return new int[0];
    }

    int[] result = new int[numCourses];
    for (int i = 0; i < orderedCourses.size(); i++) {
      result[i] = orderedCourses.get(i);
    }
    return result;
  }

  // Return if there is a cycle
  private boolean dfs(int node, Map<Integer, List<Integer>> graph, Boolean[] visited, List<Integer> orderedNodes) {
    if(visited[node] != null) return visited[node];

    visited[node] = true;
    if(graph.containsKey(node)) {
      for (Integer neighbor : graph.get(node)) {
        if(dfs(neighbor, graph, visited, orderedNodes)) return true;
      }
    }

    visited[node] = false;
    orderedNodes.add(node);

    return false;
  }
}

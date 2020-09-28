package io.mars.tree.amazon;

import java.util.*;

/**
 * There are a total of numCourses courses you have to take, labeled from 0 to numCourses-1.
 *
 * Some courses may have prerequisites, for example to take course 0 you have to first take course 1,
 * which is expressed as a pair: [0,1]
 *
 * Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?
 *
 *
 *
 * Example 1:
 *
 * Input: numCourses = 2, prerequisites = [[1,0]]
 * Output: true
 * Explanation: There are a total of 2 courses to take.
 *              To take course 1 you should have finished course 0. So it is possible.
 * Example 2:
 *
 * Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
 * Output: false
 * Explanation: There are a total of 2 courses to take.
 *              To take course 1 you should have finished course 0, and to take course 0 you should
 *              also have finished course 1. So it is impossible.
 *
 *
 * Constraints:
 *
 * The input prerequisites is a graph represented by a list of edges, not adjacency matrices.
 * Read more about how a graph is represented.
 * You may assume that there are no duplicate edges in the input prerequisites.
 * 1 <= numCourses <= 10^5
 */
public class CourseSchedule {
  public boolean canFinish(int numCourses, int[][] prerequisites) {
    // Construct a graph
    Set<Vertex> courseGroup = constructGraph(prerequisites);
    Set<Vertex> past = new HashSet<>();

    while (!courseGroup.isEmpty()) {
      Vertex course = courseGroup.iterator().next();
      past.add(course);
      if(!DFS(courseGroup, course, past)) return false;
    }
    return true;
  }

  private Set<Vertex> constructGraph(int[][] prerequisites) {
    Map<Integer, Vertex> courseMap = new HashMap<>();
    for(int[] pair: prerequisites) {
      Vertex preCourse = courseMap.getOrDefault(pair[0], new Vertex(pair[0]));
      Vertex course = courseMap.getOrDefault(pair[1], new Vertex(pair[1]));
      preCourse.adjVertices.add(course);
      courseMap.put(pair[0], preCourse);
      courseMap.put(pair[1], course);
    }
    return new HashSet<>(courseMap.values());
  }

  private boolean DFS(Set<Vertex> courseGroup, Vertex course, Set<Vertex> past) {
    List<Vertex> nextCourses = course.adjVertices;
    for(Vertex next: nextCourses) {
      if(courseGroup.contains(next)) {
        if(past.contains(next)) return false;

        past.add(next);
        if(!DFS(courseGroup, next, past)) return false;
      }
    }

    courseGroup.remove(course);
    return true;
  }

  private static class Vertex {
    private int label;
    private List<Vertex> adjVertices = new ArrayList<>();

    Vertex(int label, Vertex adjVertex) {
      this.label = label;
      if(adjVertex != null) this.adjVertices.add(adjVertex);
    }

    Vertex(int label) {
      this(label, null);
    }
  }
}


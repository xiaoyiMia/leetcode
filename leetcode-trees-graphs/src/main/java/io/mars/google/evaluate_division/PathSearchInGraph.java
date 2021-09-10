package io.mars.google.evaluate_division;

import java.util.*;

public class PathSearchInGraph implements EvaluateDivision {
  @Override
  public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
    Map<String, Map<String, Double>> equationGraph = buildGraph(equations, values);

    double[] results = new double[queries.size()];
    for (int i = 0; i < queries.size(); i++) {
      results[i] = findPath(equationGraph, queries.get(i).get(0), queries.get(i).get(1), new HashSet<>());
    }

    return results;
  }

  private Map<String, Map<String, Double>> buildGraph(List<List<String>> equations, double[] values) {
    Map<String, Map<String, Double>> equationGraph = new HashMap<>();
    for (int i = 0; i < equations.size(); i++) {
      addChild(equationGraph, equations.get(i).get(0), equations.get(i).get(1), values[i]);
      addChild(equationGraph, equations.get(i).get(1), equations.get(i).get(0), 1 / values[i]);
    }
    return equationGraph;
  }
  
  private void addChild(Map<String, Map<String, Double>> equationGraph, String parent, String child, double value) {
    Map<String, Double> children = equationGraph.get(parent);
    if(children == null) {
      children = new HashMap<>();
      equationGraph.put(parent, children);
    }
    children.put(child, value);
  }
  
  private double findPath(Map<String, Map<String, Double>> graph, String start, String target, Set<String> visited) {
    Map<String, Double> nextNodes = graph.get(start);
    if(nextNodes == null) return -1.0;

    for (Map.Entry<String, Double> nodeEntry : nextNodes.entrySet()) {
      if(nodeEntry.getKey().equals(target)) return nodeEntry.getValue();
      if(visited.contains(nodeEntry.getKey())) continue;

      visited.add(nodeEntry.getKey());
      double value = findPath(graph, nodeEntry.getKey(), target, visited);
      if(value > 0) return nodeEntry.getValue() * value;
    }
    return -1.0;
  }

}

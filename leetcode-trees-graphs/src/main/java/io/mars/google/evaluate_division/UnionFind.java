package io.mars.google.evaluate_division;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UnionFind implements EvaluateDivision {
  @Override
  public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
    //<nodeId, <groupId, weight>>
    Map<String, Map.Entry<String, Double>> equationGraph = new HashMap<>();
    for (int i = 0; i < equations.size(); i++) {
      union(equationGraph, equations.get(i).get(0), equations.get(i).get(1), values[i]);
    }

    double[] results = new double[queries.size()];
    for (int i = 0; i < queries.size(); i++) {
      String dividend = queries.get(i).get(0);
      String divisor = queries.get(i).get(1);
      if(!equationGraph.containsKey(dividend) || !equationGraph.containsKey(divisor)) results[i] = -1.0;
      else {
        Map.Entry<String, Double> dividendGroupItem = find(equationGraph, dividend);
        Map.Entry<String, Double> divisorGroupItem = find(equationGraph, divisor);

        if(!dividendGroupItem.getKey().equals(divisorGroupItem.getKey())) results[i] = -1.0;
        else results[i] = dividendGroupItem.getValue() / divisorGroupItem.getValue();
      }
    }

    return results;
  }

  private void union(Map<String, Map.Entry<String, Double>> equationGraph, String dividend, String divisor, double value) {
    Map.Entry<String, Double> dividendGroupItem = find(equationGraph, dividend);
    Map.Entry<String, Double> divisorGroupItem = find(equationGraph, divisor);

    // if dividend and divisor are not in the same group, merge them by updating the group id of originator of
    // dividend's group
    if(!dividendGroupItem.getKey().equals(divisorGroupItem.getKey())) {
      double weight = value * divisorGroupItem.getValue() / dividendGroupItem.getValue();
      equationGraph.put(dividendGroupItem.getKey(), new AbstractMap.SimpleEntry<>(divisorGroupItem.getKey(), weight));
    }
  }

  private Map.Entry<String, Double> find(Map<String, Map.Entry<String, Double>> equationGraph, String nodeId) {
    if (!equationGraph.containsKey(nodeId)) equationGraph.put(nodeId, new AbstractMap.SimpleEntry<>(nodeId, 1.0));

    // Update the group information of the current node (from the current node to the end of the group chain)
    Map.Entry<String, Double> currentGroupItem = equationGraph.get(nodeId);
    if(!nodeId.equals(currentGroupItem.getKey())) {
      Map.Entry<String, Double> newGroupItem = find(equationGraph, currentGroupItem.getKey());
      double newValue = newGroupItem.getValue() * currentGroupItem.getValue();
      equationGraph.put(nodeId, new AbstractMap.SimpleEntry<>(newGroupItem.getKey(), newValue));
    }
    return equationGraph.get(nodeId);
  }
}

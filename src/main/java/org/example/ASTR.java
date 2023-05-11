package org.example;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Iterables;
import com.google.common.collect.TreeMultimap;

import java.util.*;

public class ASTR implements Algorithm{
    int hashResult;
    HashMultimap<Integer, Fifteens> frontier = HashMultimap.create();
    TreeMultimap<Integer, Integer> sortedF = TreeMultimap.create();
    HashMap<Integer, Fifteens> closed = new HashMap<>();
    Fifteens result = null;
    String metric = "";
    int MaxDepth = 0;
    String[] enter;
    long startTime;
    @Override
    public Fifteens Solve(Fifteens f, String[] enter) throws Exception {
        this.enter = enter;
        startTime = System.nanoTime();
        this.result = AlgorithmsHelper.makeResult(f);
        this.hashResult = result.getHashCode();
        if (f.equals(result)) {
            returnSuccess(f);
            return f;
        }
        metric = enter[1];
        frontier.put(f.getHashCode(), f);
        sortedF.put(0, f.getHashCode());
        while (!frontier.isEmpty()) {
            Integer firstKeyInSortedF = Iterables.getFirst(sortedF.keySet(), null);
            Integer firstHeurystyk = sortedF.get(firstKeyInSortedF).first();
            Set<Fifteens> setFift = frontier.get(firstHeurystyk);
            Iterator<Fifteens> iter = setFift.iterator();
            Fifteens actual = iter.next();
            frontier.remove(actual.getHashCode(), actual);
            sortedF.remove(actual.getHeuristics(), actual.getHashCode());
            closed.put(actual.getHashCode(), actual);
            //////////Get neighbour and check result////////
            for (Fifteens neighbour : actual.getNeighbours()) {
                if (MaxDepth < neighbour.getDepth()) MaxDepth = neighbour.getDepth();
                if (closed.containsKey(neighbour.getHashCode())) {
                    if (closed.get(neighbour.getHashCode()).equals(neighbour)) {
                        continue;
                    }
                }
                if (hashResult == neighbour.getHashCode()) {
                    if (neighbour.equals(result)) {
                        returnSuccess(neighbour);
                        return neighbour;
                    }
                }
                //////Calcualte heuristic/////////
                if (Objects.equals(metric, "manh")) {
                    neighbour.calculateDistance(result);
                    neighbour.setHeuristics(neighbour.getDepth() + neighbour.getDistanceToEnd());
                } else if (Objects.equals(metric, "hamm")){
                    neighbour.calculateDistance(result);
                    neighbour.setHeuristics(neighbour.getDistanceToEnd());
                }
                /////////////////Add to the queue/////////////
                if (!frontier.containsKey(neighbour.getHashCode())) {
                    frontier.put(neighbour.getHashCode(), neighbour);
                    sortedF.put(neighbour.getHeuristics(), neighbour.getHashCode());
                } else {
                    boolean getNeighbour = false;
                    for (Fifteens fift : frontier.get(neighbour.getHashCode())) {
                        if (neighbour.equals(fift)) {
                            getNeighbour = true;
                            break;
                        }
                    }
                    if (!getNeighbour) {
                        frontier.put(neighbour.getHashCode(), neighbour);
                        sortedF.put(neighbour.getHeuristics(), neighbour.getHashCode());
                    }
                }
            }
        }
        AlgorithmsHelper.Statistics(-1, frontier.values().toArray().length + closed.values().toArray().length,
                closed.values().toArray().length, MaxDepth, System.nanoTime() - startTime,
                f.getSolution(), enter);
        System.out.println("!!!!!!!!!!!FAILURE!!!!!!!!!!!");
        return null;

    }

    public void returnSuccess(Fifteens result) {
        AlgorithmsHelper.Statistics(result.getDepth(),
                frontier.values().toArray().length + closed.values().toArray().length,
                closed.values().toArray().length, MaxDepth, System.nanoTime() - startTime,
                result.getSolution(), enter);
        //System.out.println("SUCCESS");
    }
}

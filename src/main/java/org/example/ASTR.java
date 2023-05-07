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
    String heuristics = "";
    @Override
    public Fifteens Solve(Fifteens f, String acronym) throws Exception {
        this.result = AlgorithmsHelper.makeResult(f);
        this.hashResult = result.getHashCode();
        if (f.equals(result)) {
            return f;
        }
        heuristics = acronym;
        frontier.put(f.getHashCode(), f);
        sortedF.put(0, f.getHashCode());
        while (!frontier.isEmpty()) {
            Integer firstKeyInSortedF = Iterables.getFirst(sortedF.keySet(), null);
            Integer firstHeurystyk = sortedF.get(firstKeyInSortedF).first();
            Set<Fifteens> setFift = frontier.get(firstHeurystyk);
            Iterator<Fifteens> iter = setFift.iterator();
            Fifteens actual = iter.next();
            //Fifteens actual = frontier.get(sortedF.get(Iterables.getFirst(sortedF.keySet(), null)).first()).iterator().next();
            frontier.remove(actual.getHashCode(), actual);
            sortedF.remove(actual.getHeuristics(), actual.getHashCode());
            closed.put(actual.getHashCode(), actual);
            for (Fifteens neighbour : actual.getNeighbours()) {
                if (hashResult == actual.getHashCode()) {
                    if (actual.equals(result)) {
                        return actual;
                    }
                }
                if (closed.containsKey(neighbour.getHashCode())) {
                    if (closed.get(neighbour.getHashCode()).equals(neighbour)) {
                        continue;
                    }
                }
                neighbour.calculateDistance(result);
                neighbour.setHeuristics(neighbour.getDepth() + neighbour.getDistanceToEnd());
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
        throw new Exception("Puzzle wasn't resolved");
        //return null;

    }
}

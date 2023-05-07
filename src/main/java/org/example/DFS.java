package org.example;

import com.google.common.collect.Iterables;
import com.google.common.collect.LinkedHashMultimap;

import java.util.HashMap;

public class DFS implements Algorithm {
    int hashResult;
    LinkedHashMultimap<Integer, Fifteens> frontier = LinkedHashMultimap.create();
    //LinkedHashMap<Integer, Fifteens> frontier = new LinkedHashMap<>();
    HashMap<Integer, Fifteens> closed = new HashMap<>();
    Fifteens result = null;
    String[] line = new String[4];
    @Override
    public Fifteens Solve(Fifteens f, String acronym) throws Exception {
        this.result = AlgorithmsHelper.makeResult(f);
        this.hashResult = result.getHashCode();
        if (f.equals(result)) {
            return f;
        }
        line = AlgorithmsHelper.DecodeAcronym(acronym);
        frontier.put(f.getHashCode(), f);
        closed.put(f.getHashCode(), f);
        while (!frontier.isEmpty()) {
            Fifteens actual = Iterables.getFirst(frontier.values(), null);
            frontier.remove(actual.getHashCode(), actual);
            /*Fifteens actual = frontier.entrySet().iterator().next().getValue();
            frontier.remove(actual.hashCode());*/
            for (int i = 0; i < 4; i++) {
                Fifteens neighbour = actual.getNeighbour(line[i]);
                if (neighbour == null) {
                    continue;
                }
                if (!closed.containsKey(neighbour.getHashCode())) {
                    if (neighbour.getHashCode() == hashResult) {
                        if (neighbour.equals(result)) {
                            return neighbour;
                        }
                    }
                    frontier.put(neighbour.getHashCode(), neighbour);
                    closed.put(neighbour.getHashCode(), neighbour);
                } else if (!closed.get(neighbour.getHashCode()).equals(neighbour)) {
                    if (neighbour.getHashCode() == hashResult) {
                        if (neighbour.equals(result)) {
                            return neighbour;
                        }
                    }
                    frontier.put(neighbour.getHashCode(), neighbour);
                    closed.put(neighbour.getHashCode(), neighbour);
                }
            }
        }
        throw new Exception("Puzzle wasn't resolved");
        //return null;
    }
}

package org.example;
import java.util.*;

import com.google.common.collect.Iterables;
import com.google.common.collect.LinkedHashMultimap;

public class BFS implements Algorithm {
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
        while (!frontier.isEmpty()) {
            Fifteens actual = Iterables.getFirst(frontier.values(), null);
            frontier.remove(actual.getHashCode(), actual);
            /*Fifteens actual = frontier.entrySet().iterator().next().getValue();
            frontier.remove(actual.hashCode());*/
            closed.put(actual.getHashCode(), actual);
            for (int i = 0; i < 4; i++) {
                Fifteens neighbour = actual.getNeighbour(line[i]);
                if (neighbour == null) {
                    continue;
                }
                if (hashResult == neighbour.getHashCode()) {
                    if (neighbour.equals(result)) {
                        return neighbour;
                    }
                }
                if (!closed.containsKey(neighbour.getHashCode())) {
                    if (!frontier.containsKey(neighbour.getHashCode())) {
                        frontier.put(neighbour.getHashCode(), neighbour);
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
                        }
                    }
                     } else if (!closed.get(neighbour.getHashCode()).equals(neighbour)) {
                        frontier.put(neighbour.getHashCode(), neighbour);
                    }
                }
            }
        throw new Exception("Puzzle wasn't resolved");
        //return null;
    }
}

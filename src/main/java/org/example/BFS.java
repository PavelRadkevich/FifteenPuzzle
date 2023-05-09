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
        ///////////////////////////START////////////////////////////////////////
        if (f.equals(result)) {
            return f;
        }
        line = AlgorithmsHelper.DecodeAcronym(acronym);
        frontier.put(f.getHashCode(), f);
        while (!frontier.isEmpty()) {
            //////////////////////Get First from Frontier/////////////////////////////////
            Fifteens actual = Iterables.getFirst(frontier.values(), null);
            frontier.remove(actual.getHashCode(), actual);
            //////////////////////Get Neighbours///////////////////////
            for (int i = 0; i < 4; i++) {
                Fifteens neighbour = actual.getNeighbour(line[i]);
                if (neighbour == null) {
                    continue;
                }
                /////////////////Check the result and closed list////////////////////
                if (!closed.containsKey(neighbour.getHashCode())) {
                    if (neighbour.getHashCode() == hashResult) {
                        if (neighbour.equals(result)) {
                            return neighbour;
                        }
                    }
                    /////////////////////////Put on the both of queue///////////////////
                    frontier.put(neighbour.getHashCode(), neighbour);
                    closed.put(neighbour.getHashCode(), neighbour);
                    /////////////If HashCode the Same, check the Objects//////////////
                } else if (!closed.get(neighbour.getHashCode()).equals(neighbour)) {
                    if (neighbour.getHashCode() == hashResult) {
                        if (neighbour.equals(result)) {
                            return neighbour;
                        }
                    }
                    /////////////////////////Put on the both of queue///////////////////
                    frontier.put(neighbour.getHashCode(), neighbour);
                    closed.put(neighbour.getHashCode(), neighbour);
                }
                ////////////////////////////END////////////////////////////////////
            }
        }
        throw new Exception("Puzzle wasn't resolved");
        //return null;
    }
}

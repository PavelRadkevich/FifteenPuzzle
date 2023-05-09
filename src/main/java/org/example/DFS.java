package org.example;

import com.google.common.collect.Iterables;
import com.google.common.collect.LinkedListMultimap;

import java.util.HashMap;

public class DFS implements Algorithm {
    int hashResult;
    LinkedListMultimap<Integer, Fifteens> frontier = LinkedListMultimap.create();
    //LinkedHashMap<Integer, Fifteens> frontier = new LinkedHashMap<>();
    HashMap<Integer, Fifteens> closed = new HashMap<>();
    Fifteens result = null;
    String[] line = new String[4];
    int maxDepthOfRecursion = 70;
    int MaxDepth = 0;

    @Override
    public Fifteens Solve(Fifteens f, String[] enter) throws Exception {
        long startTime = System.currentTimeMillis();
        this.result = AlgorithmsHelper.makeResult(f);
        this.hashResult = result.getHashCode();
        ///////////////////////////START////////////////////////////////////////
        if (f.equals(result)) {
            AlgorithmsHelper.Statistics(f.getDepth(), frontier.values().toArray().length,
                    closed.values().toArray().length, MaxDepth, startTime - System.currentTimeMillis(),
                    f.getSolution(), enter);
            return f;
        }
        line = AlgorithmsHelper.DecodeAcronym(enter[1]);
        line = AlgorithmsHelper.MirrorAcronym(line);
        frontier.put(f.getHashCode(), f);
        while (!frontier.isEmpty()) {
            //////////////////////Get Last from Frontier add to the Closed/////////////////////////////////
            Fifteens actual = Iterables.getLast(frontier.values(), null);
            frontier.remove(actual.getHashCode(), actual);
            closed.put(actual.getHashCode(), actual);
            //////////////////////Get Neighbours///////////////////////
            for (int i = 0; i < 4; i++) {
                Fifteens neighbour = actual.getNeighbour(line[i]);
                if (neighbour == null || neighbour.getDepth() == maxDepthOfRecursion) {
                    continue;
                }
                if (MaxDepth < neighbour.getDepth()) MaxDepth = neighbour.getDepth();
                /////////////////Check the result and closed list////////////////////
                if (!closed.containsKey(neighbour.getHashCode())) {
                    if (neighbour.getHashCode() == hashResult) {
                        if (neighbour.equals(result)) {
                            AlgorithmsHelper.Statistics(neighbour.getDepth(), frontier.values().toArray().length,
                                    closed.values().toArray().length, MaxDepth, startTime - System.currentTimeMillis(),
                                    neighbour.getSolution(), enter);
                            return neighbour;
                        }
                    }
                    if (!frontier.containsKey(neighbour.getHashCode())) {
                        /////////////////////////Put on the both of queue///////////////////
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
                    /////////////If HashCode the Same, check the Objects//////////////
                } else if (!closed.get(neighbour.getHashCode()).equals(neighbour)) {
                    if (neighbour.getHashCode() == hashResult) {
                        if (neighbour.equals(result)) {
                            AlgorithmsHelper.Statistics(neighbour.getDepth(), frontier.values().toArray().length,
                                    closed.values().toArray().length, MaxDepth, startTime - System.currentTimeMillis(),
                                    neighbour.getSolution(), enter);
                            return neighbour;
                        }
                    }
                    /////////////////////////Put on the both of queue///////////////////
                    frontier.put(neighbour.getHashCode(), neighbour);
                }
                ////////////////////////////END////////////////////////////////////
            }
        }
        AlgorithmsHelper.Statistics(-1, frontier.values().toArray().length,
                closed.values().toArray().length, MaxDepth, startTime - System.currentTimeMillis(),
                "", enter);
        return null;
    }
}

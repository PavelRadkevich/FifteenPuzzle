package org.example;
import java.util.*;

import com.google.common.collect.Iterables;
import com.google.common.collect.LinkedHashMultimap;

public class BFS implements Algorithm {
    int hashResult;
    LinkedHashMultimap<Integer, Fifteens> frontier = LinkedHashMultimap.create();
    HashMap<Integer, Fifteens> closed = new HashMap<>();
    Fifteens result = null;
    String[] line = new String[4];
    int MaxDepth = 0;
    String[] enter;
    long startTime;
    @Override
    public Fifteens Solve(Fifteens f, String[] enter) {
        this.enter = enter;
        startTime = System.nanoTime();
        this.result = AlgorithmsHelper.makeResult(f);
        this.hashResult = result.getHashCode();
        ///////////////////////////START////////////////////////////////////////
        if (f.equals(result)) {
            returnSuccess(f);
            return f;
        }
        line = AlgorithmsHelper.DecodeAcronym(enter[1]);
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
                if (MaxDepth < neighbour.getDepth()) MaxDepth = neighbour.getDepth();
                /////////////////Check the result and closed list////////////////////
                if (!closed.containsKey(neighbour.getHashCode())) {
                    if (neighbour.getHashCode() == hashResult) {
                        if (neighbour.equals(result)) {
                            returnSuccess(neighbour);
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
                            returnSuccess(neighbour);
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
        AlgorithmsHelper.Statistics(-1,
                frontier.values().toArray().length + closed.values().toArray().length,
                closed.values().toArray().length, MaxDepth, System.nanoTime() - startTime,
                "", enter);
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

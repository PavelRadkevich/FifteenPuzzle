package org.example;

import com.google.common.collect.Iterables;
import com.google.common.collect.LinkedListMultimap;

import java.util.HashMap;

public class DFS implements Algorithm {
    int hashResult;

    LinkedListMultimap<Integer, Fifteens> frontier = LinkedListMultimap.create();
    HashMap<Integer, Fifteens> closed = new HashMap<>();
    Fifteens result = null;
    String[] line = new String[4];
    int maxDepthOfRecursion = 20;
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
                if (neighbour == null || neighbour.getDepth() > maxDepthOfRecursion) {
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
                    /////////////////////////Put on the queue///////////////////
                    checkAndPutToTheFrontier(neighbour);
                    /////////////If HashCode the Same, check the Objects//////////////
                } else if (!closed.get(neighbour.getHashCode()).equals(neighbour)) {
                    if (neighbour.getHashCode() == hashResult) {
                        if (neighbour.equals(result)) {
                            returnSuccess(neighbour);
                            return neighbour;
                        }
                    }
                    /////////////////////////Put on the queue///////////////////
                    frontier.put(neighbour.getHashCode(), neighbour);
                }
                ////////////////////////////END////////////////////////////////////
            }
        }
        AlgorithmsHelper.Statistics(-1,
                frontier.values().toArray().length+ closed.values().toArray().length,
                closed.values().toArray().length, MaxDepth, System.nanoTime() - startTime ,
                "", enter);
        System.out.print("F");
        return null;
    }
    public void checkAndPutToTheFrontier(Fifteens neighbour) {
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
    }
    public void returnSuccess(Fifteens result) {
        AlgorithmsHelper.Statistics(result.getDepth(),
                frontier.values().toArray().length + closed.values().toArray().length,
                closed.values().toArray().length, MaxDepth, System.nanoTime() - startTime ,
                result.getSolution(), enter);
        //System.out.println("SUCCESS");
    }
}

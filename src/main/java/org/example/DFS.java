package org.example;

import com.google.common.collect.Iterables;
import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.LinkedListMultimap;

import java.util.HashMap;

public class DFS implements Algorithm {
    int hashResult;
    LinkedListMultimap<Integer, Fifteens> frontier = LinkedListMultimap.create();
    //LinkedHashMap<Integer, Fifteens> frontier = new LinkedHashMap<>();
    HashMap<Integer, Fifteens> closed = new HashMap<>();
    Fifteens result = null;
    String[] line = new String[4];
    int maxDepthOfRecursion = 20;

    @Override
    public Fifteens Solve(Fifteens f, String acronym) throws Exception {
        this.result = AlgorithmsHelper.makeResult(f);
        this.hashResult = result.getHashCode();
        ///////////////////////////START////////////////////////////////////////
        if (f.equals(result)) {
            return f;
        }
        line = AlgorithmsHelper.DecodeAcronym(acronym);
        line = AlgorithmsHelper.MirrorAcronym(line);
        frontier.put(f.getHashCode(), f);
        Fifteens odp = Rekurencja(maxDepthOfRecursion);
        if (odp != null){
            return odp;
        }
        /*while (!frontier.isEmpty()) {
            //////////////////////Get Last from Frontier/////////////////////////////////
            Fifteens actual = Iterables.getLast(frontier.values(), null);
            frontier.remove(actual.getHashCode(), actual);
            //////////////////////Get Neighbours///////////////////////
            int depth = actual.getDepth();
            for (int i = 0; i < 4; i++) {
                Fifteens neighbour = actual.getNeighbour(line[i]);
                if (neighbour == null || neighbour.getDepth() == maxDepthOfRecursion + depth) {
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
        }*/
        throw new Exception("Puzzle wasn't resolved");
    }

    private Fifteens Rekurencja(int gleb) {
        Fifteens actual = Iterables.getLast(frontier.values(), null);
        frontier.remove(actual.getHashCode(), actual);
        for (int i = 0; i < 4; i++) {
            Fifteens neighbour = actual.getNeighbour(line[i]);
            if (neighbour == null || neighbour.getDepth() > gleb) {
                return null;
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
            result = Rekurencja(gleb - 1);
            if (result != null){
                return result;
            }
        }
        return null;
    }
}

package org.example;

import java.util.*;

public class BFS implements Algorithm {
    int hashResult;
    LinkedHashMap<Integer, Fifteens> frontier = new LinkedHashMap<>();
    Set<Integer> closed = new HashSet<>();
    Fifteens result = null;
    String[] line = new String[4];
    @Override
    public Fifteens Solve(Fifteens f, String acronym) throws Exception {
        makeResult(f);
        DecodeAcronym(acronym);
        frontier.put(f.hashCode(), f);
        while (!frontier.isEmpty()) {
            Fifteens actual = frontier.entrySet().iterator().next().getValue();
            frontier.remove(actual.hashCode());
            closed.add(actual.hashCode());
            for (int i = 0; i < 4; i++) {
                Fifteens neighbour = actual.getNeighbour(line[i]);
                if (neighbour == null) {
                    continue;
                }
                if (neighbour.equals(result)) {
                    System.out.println(neighbour.hashCode());
                    return neighbour;
                }
                if (!(closed.contains(neighbour.hashCode()) && frontier.containsKey(neighbour.hashCode()))) {
                    frontier.put(neighbour.hashCode(), neighbour);
                }
            }
        }
        throw new Exception("Puzzle wasn't resolved");
        //return null;
    }

    private void DecodeAcronym(String acronym) {
        for (int i = 0; i < 4; i ++) {
            line[i] = String.valueOf(acronym.charAt(i));
        }
    }
    private void makeResult(Fifteens f) {
        int rows = f.getRows();
        int columns = f.getColumns();
        int resNum = 1;
        int[][] result = new int[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                result[i][j] = resNum;
                resNum++;
            }
        }
        result[rows - 1][columns - 1] = 0;
        this.result = new Fifteens(result, 0);
        hashResult = result.hashCode();
    }
}

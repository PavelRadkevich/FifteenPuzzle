package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class Fifteens {
    private final int rows;
    private final int columns;
    private final int[][] fifteens;
    private int zeroX;
    private int zeroY;
    private final int depth;
    private final int hashCode;
    private int distanceToEnd;
    private int heuristics;

    public Fifteens(int[][] f, int depth) {
        fifteens = f;
        this.depth = depth;
        rows = f.length;
        columns = f[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j ++) {
                if (fifteens[i][j] == 0) {
                    zeroX = j;
                    zeroY = i;
                }
            }
        }
        this.hashCode = this.hashCode();

    }
    public ArrayList<Fifteens> getNeighbours() {
        ArrayList<Fifteens> ret = new ArrayList<>();
        if (zeroX != rows - 1)
            ret.add(new Fifteens(swap(zeroX, zeroY, zeroX + 1, zeroY), depth + 1));
        if (zeroX != 0)
            ret.add(new Fifteens(swap(zeroX, zeroY, zeroX - 1, zeroY), depth + 1));
        if (zeroY != columns - 1)
            ret.add(new Fifteens(swap(zeroX, zeroY, zeroX, zeroY + 1), depth + 1));
        if (zeroY != 0)
            ret.add(new Fifteens(swap(zeroX, zeroY, zeroX, zeroY - 1), depth + 1));
        return ret;
    }
    public Fifteens getNeighbour(String direction) {
        int[][] newFifteen = new int[rows][columns];
        switch (direction) {
            case "R" -> {
                if (zeroX == rows - 1)
                    return null;
                newFifteen = swap(zeroX, zeroY, zeroX + 1, zeroY);
            }
            case "L" -> {
                if (zeroX == 0)
                    return null;
                newFifteen = swap(zeroX, zeroY, zeroX - 1, zeroY);
            }
            case "U" -> {
                if (zeroY == columns - 1)
                    return null;
                newFifteen = swap(zeroX, zeroY, zeroX, zeroY + 1);
            }
            case "D" -> {
                if (zeroY == 0)
                    return null;
                newFifteen = swap(zeroX, zeroY, zeroX, zeroY - 1);
            }
        }
        return new Fifteens(newFifteen, depth + 1);
    }
    private int[][] swap(int x1, int y1, int x2, int y2) {
        int[][] fift = new int[rows][columns];
        //System.arraycopy(fifteens, 0, fift, 0, fifteens.length);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++){
                fift[i][j] = fifteens[i][j];
            }
        }
        fift[y1][x1] = fifteens[y2][x2];
        fift[y2][x2] = fifteens[y1][x1];
        return fift;
    }
    public void calculateDistance(Fifteens result) throws Exception {
        int distance = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                int coordinatesb = findNumber(result.fifteens, fifteens[i][j]);
                distance += Math.abs(i - coordinatesb / 10) + Math.abs(j - coordinatesb % 10);
            }
        }
        this.distanceToEnd = distance;
    }

    public int findNumber(int[][] fift, int number) throws Exception {
        for (int i = 0; i < fift.length; i++) {
            for (int j = 0; j < fift[0].length; j++) {
                if (fift[i][j] == number) return i * 10 + j;
            }
        }
        throw new Exception("Can't find number in result matrix");
    }
    public void solveGame(Algorithm a, String acronym) throws Exception {
        Fifteens result = a.Solve(this, acronym);
        System.out.println(Arrays.deepToString(result.fifteens));
    }
    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public int getHashCode() {
        return hashCode;
    }

    public int getDistanceToEnd() {
        return distanceToEnd;
    }

    public int getDepth() {
        return depth;
    }

    public int getHeuristics() {
        return heuristics;
    }

    public void setHeuristics(int heuristics) {
        this.heuristics = heuristics;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Fifteens fifteens1)) return false;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (((Fifteens) o).fifteens[i][j] != fifteens[i][j]) return false;
            }
        }
        return rows == fifteens1.rows && columns == fifteens1.columns && zeroX == fifteens1.zeroX && zeroY == fifteens1.zeroY;
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(zeroX, zeroY);
        result = 31 * result + Arrays.deepHashCode(fifteens);
        return result;
    }


}

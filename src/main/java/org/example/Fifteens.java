package org.example;

import java.util.Arrays;
import java.util.Objects;

public class Fifteens {
    private final int rows;
    private final int columns;
    private final int[][] fifteens;
    private int[][] result;
    private int zeroX;
    private int zeroY;
    private int depth;

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

    }
    public Fifteens getNeighbour(String direction) {
        int[][] newFifteen = new int[rows][columns];
        switch (direction) {
            case "R": {
                if (zeroX == rows - 1)
                    return null;
                newFifteen = swap(zeroX, zeroY, zeroX + 1, zeroY);
                break;
            }
            case "L": {
                if (zeroX == 0)
                    return null;
                newFifteen = swap(zeroX, zeroY, zeroX - 1, zeroY);
                break;
            }
            case "U": {
                if (zeroY == columns - 1)
                    return null;
                newFifteen = swap(zeroX, zeroY, zeroX, zeroY + 1);
                break;
            }
            case "D": {
                if (zeroY == 0)
                    return null;
                newFifteen = swap(zeroX, zeroY, zeroX, zeroY - 1);
                break;
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


    public void solveGame(Algorithm a, String acronym) throws Exception {
        Fifteens result = a.Solve(this, acronym);
        System.out.println(Arrays.deepToString(result.fifteens));
    }
    public int[][] getFifteens() {
        return fifteens;
    }

    public int getZeroX() {
        return zeroX;
    }

    public int getZeroY() {
        return zeroY;
    }

    public int getDepth() {
        return depth;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }


    @Override
    public int hashCode() {
        //int result = Objects.hash(zeroX, zeroY);
        int result = 31 + Arrays.hashCode(fifteens[0]);
        for ( int i = 1; i < rows; i++) {
            result += Arrays.hashCode(fifteens[i]);
        }
        /*System.out.print(Arrays.deepToString(fifteens));
        System.out.println("    " + result);*/
        return result;
    }

    /*public int hashCode() {
        int hash = 0;
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < columns; j++)
                hash = (hash) ^ ( (Math.pow(34,i)+Math.pow(43,j)) * fifteens[i][j]);
        return hash;
    }*/


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Fifteens fifteens1)) return false;
        return rows == fifteens1.rows && columns == fifteens1.columns && zeroX == fifteens1.zeroX && zeroY == fifteens1.zeroY && Arrays.deepEquals(fifteens, fifteens1.fifteens);
    }
}

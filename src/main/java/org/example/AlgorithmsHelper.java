package org.example;

import static org.example.Files.WriteResultFile;

public class AlgorithmsHelper {

    public static Fifteens makeResult(Fifteens f) {
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
        return new Fifteens(result, 0, "");
    }
    public static String[] DecodeAcronym(String acronym) {
        String[] line = new String[4];
        for (int i = 0; i < 4; i ++) {
            line[i] = String.valueOf(acronym.charAt(i));
        }
        return line;
    }

    public static String[] MirrorAcronym(String[] line) {
        String[] retline = new String[line.length];
        for (int i = 0; i < line.length; i++) {
            retline[i] = line[line.length - i - 1];
        }
        return retline;
    }

    public static void Statistics (int DZR, int LSO , int LSP, int MOGR, long CTPO, String Solution, String[] enter) {
        WriteResultFile(DZR, Solution, enter[3]);
    }

}

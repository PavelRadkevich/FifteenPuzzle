package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Files {
    static public int[][] ReadFile(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        Scanner sc = new Scanner(file);
        String[] colRow = sc.nextLine().trim().split("," + " ");
        int[][] fifteen = new int [Integer.parseInt(colRow[0])] [Integer.parseInt(colRow[1])];
         while (sc.hasNextLine()) {
             for (int i = 0; i < fifteen.length; i++) {
             String[] line = sc.nextLine().trim().split("," + " ");
                for (int j = 0; j < line.length; j++) {
                fifteen[i][j] = Integer.parseInt(line[j]);
                }
             }
        }



        return fifteen;
    }

}

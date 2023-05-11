package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Files {
    public static int[][] ReadFile(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        Scanner sc = new Scanner(file);
        String[] colRow = sc.nextLine().trim().split(" ");
        int[][] fifteen = new int [Integer.parseInt(colRow[0])] [Integer.parseInt(colRow[1])];
         while (sc.hasNextLine()) {
             for (int i = 0; i < fifteen.length; i++) {
             String[] line = sc.nextLine().trim().split(" ");
                for (int j = 0; j < line.length; j++) {
                fifteen[i][j] = Integer.parseInt(line[j]);
                }
             }
        }
        return fifteen;
    }

    static void WriteResultFile(int dzr, String solution, String nameOfFile) {
        File resultFile = new File(nameOfFile);
        try(FileWriter writer = new FileWriter(resultFile, false))
        {

            writer.write(dzr + "\n" + solution);
            writer.flush();
        }
        catch(IOException ex){
            throw new RuntimeException(ex);
        }
    }

    static void WriteStatisticFile(int DZR, int LSO, int LSP, int MOGR, long CTPO, String nameOfFile) {
        File statisticFile = new File(nameOfFile);
        String time = String.valueOf(CTPO / 1000);
        time = new StringBuilder(time).insert(time.length()-3, ".").toString();
        try(FileWriter writer = new FileWriter(statisticFile, false)) {
            writer.write(DZR + "\n" + LSO + "\n" + LSP + "\n" + MOGR + "\n" + time );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}

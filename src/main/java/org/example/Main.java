package org.example;

public class Main {
    public static void main(String[] args) throws Exception {
        int[][] f = Files.ReadFile("D:/Sise/uklady/4x4_15_00001.txt");
        Fifteens fift = new Fifteens(f, 0, "");
        String alg = "org.example." + args[0].toUpperCase();
        Algorithm a = (Algorithm) Class.forName(alg).newInstance();
        fift.solveGame(a, args);
    }
}
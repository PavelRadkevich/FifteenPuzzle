package org.example;

public class Main {
    public static void main(String[] args) throws Exception {
        int[][] f = Files.ReadFile("H:/Study/IV Semestr/2.2 Sztuczna inteligencja i systemy ekspertowe/uklady/4x4_01_00002.txt");
        Fifteens fift = new Fifteens(f, 0);
        String alg = "org.example." + args[0].toUpperCase();
        Algorithm a = (Algorithm) Class.forName(alg).newInstance();
        fift.solveGame(a, args[1]);
    }
}
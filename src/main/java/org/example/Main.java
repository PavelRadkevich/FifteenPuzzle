package org.example;

import java.util.spi.AbstractResourceBundleProvider;

public class Main {
    public static void main(String[] args) throws Exception {
        int[][] f = Files.ReadFile(args[2]);
        //"H:/Study/IV_Semestr/2.2_Sztuczna_inteligencja_i_systemy_ekspertowe/uklady/4x4_04_00016.txt"
        Fifteens fift = new Fifteens(f, 0, "");
        String alg = "org.example." + args[0].toUpperCase();
        Algorithm a = (Algorithm) Class.forName(alg).newInstance();
        fift.solveGame(a, args);
    }
}
package com.main;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Main {
    public static void main(String[] args) {
        // TODO Encoding Arabic UTF8
        String[] arr [] = {{"src/main/java/com/excel/sheets/locations.xlsx","src/main/java/com/excel/sheets/locations2.xlsx"},{"src/main/java/com/excel/sheets/locations3.xlsx","src/main/java/com/excel/sheets/locations4.xlsx"}};
        String tables[] = {"locations","locations2","locations3","locations4","locations5","locations6","locations7","locations8","locations9","locations10"};
        
        ExecutorService executor = Executors.newFixedThreadPool(1);
        // int locCounter = 0;
        for (int i = 0; i < 1; i+=2) {
            // System.out.println("Hena");
            App worker = new App(arr[0]);
            executor.execute(worker);
        }
        executor.shutdown();
       
    }
}
 
package com.main;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        // TODO Encoding Arabic UTF8
        String[] arr [] = {{"src/main/java/com/excel/sheets/source/locations.xlsx","src/main/java/com/excel/sheets/target/locations2.xlsx"},{"src/main/java/com/excel/sheets/locations3.xlsx","src/main/java/com/excel/sheets/locations4.xlsx"}};
        String tables[] = {"locations","locations2","locations3","locations4","locations5","locations6","locations7","locations8","locations9","locations10"};
        
        ExecutorService executor = Executors.newFixedThreadPool(1);
        for (int i = 0; i < 1; i+=2) {
            App worker = new App(arr[0],tables[0],tables[1]);
            executor.execute(worker);
        }
        executor.shutdown();
    }
}
 
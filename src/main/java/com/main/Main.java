package com.main;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Main {
    public static void main(String[] args) {
        // long arr [] = {100, 10, 2000000000, 2};
        ExecutorService executor = Executors.newFixedThreadPool(1);
        // for (int i = 0; i < arr.length; i++) {
            App worker = new App();
            executor.execute(worker);
        // }
        executor.shutdown();
       
    }
}
 
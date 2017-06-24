/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.upjs.ics.apiclient;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author Juraj
 */
public class Main {

    public static void main(String[] args) throws Exception {

//        Client http = new Client();
//
//        System.out.println("Testing 1 - Send Http GET request");
//        http.sendGet();
//
//        System.out.println("\nTesting 2 - Send Http POST request");
//        http.sendGetByRanges("Amanda");
        List<Callable<Void>> requests = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            requests.add(new Client());
        }
        ExecutorService executor = Executors.newFixedThreadPool(20);
        long start = System.currentTimeMillis();
        executor.invokeAll(requests);
        long end = System.currentTimeMillis();
        System.out.println(end - start);

    }

}

package sk.upjs.ics.apiclient;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) throws Exception {

//        GetAllReports http = new GetAllReports();
//
//        System.out.println("Testing 1 - Send Http GET request");
//        http.sendGet();
//
//        System.out.println("\nTesting 2 - Send Http POST request");
//        http.sendGetByRanges("Amanda");
        List<Callable<Void>> requests = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            requests.add(new GetReportsByNoOfDaysAndRangeHigh());
        }
        ExecutorService executor = Executors.newFixedThreadPool(20);
        long start = System.currentTimeMillis();
        executor.invokeAll(requests);
        long end = System.currentTimeMillis();
        System.out.println(end - start);

    }

}

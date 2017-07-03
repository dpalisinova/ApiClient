package sk.upjs.ics.apiclient;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {

    public static void main(String[] args) throws Exception {

        List<Callable<Long>> requests = new ArrayList<>();

        for (int i = 0; i < 1000; i++) {
            //pre testovanie jednotlivych dopytov odkomentujeme potrebny riadok
            requests.add(new GetReportsByNoOfDaysAndRangeHigh());
            //requests.add(new GetReportByPossibleCause()));
            // requests.add(new GetReportsByNoOfDaysAndRangeHigh());
            //requests.add(new GetReportsByNoOfDaysAndRangeHigh());
        }
        ExecutorService executor = Executors.newFixedThreadPool(16);
        long start = System.currentTimeMillis();
        List<Future<Long>> futures = executor.invokeAll(requests);
        long end = System.currentTimeMillis();
        System.out.println("Ended in time: " + (end - start));
        Long sumOfTimes = 0L;

        try {
            for (Future<Long> future : futures) {
                sumOfTimes = Long.sum(sumOfTimes, future.get());
            }
        } catch (CancellationException ce) {
            ce.printStackTrace();
        } catch (ExecutionException ee) {
            ee.printStackTrace();
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt(); // ignore/reset
        }

        Long avarage = sumOfTimes / futures.size();
        System.out.println("Priemerný čas vykonania 1 klienta: " + avarage);

    }
}

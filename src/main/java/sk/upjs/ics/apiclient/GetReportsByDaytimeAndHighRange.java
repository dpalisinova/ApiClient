package sk.upjs.ics.apiclient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.util.concurrent.Callable;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class GetReportsByDaytimeAndHighRange implements Callable<Long> {

    private Long responseTime;

    public void sendGetReports(String daytime, double rangeHigh) throws Exception {

        String url = "http://localhost:8080/reports/" + "daytime" + URLEncoder.encode(daytime, "UTF-8") + "," + rangeHigh;

        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet(url);

        // add request header
        request.addHeader("accept", "application/json");

        Long before = System.currentTimeMillis();
        HttpResponse response = client.execute(request);
        Long after = System.currentTimeMillis();
        responseTime = after - before;

        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : "
                + response.getStatusLine().getStatusCode());

        BufferedReader rd = new BufferedReader(
                new InputStreamReader(response.getEntity().getContent()));

        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }

        System.out.println(result.toString());

    }

    @Override
    public Long call() throws Exception {
        sendGetReports("Post-dinner", 33);
        return responseTime;
    }
}

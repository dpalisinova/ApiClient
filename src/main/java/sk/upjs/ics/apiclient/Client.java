/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.upjs.ics.apiclient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class Client implements Callable<Void> {

    private final String USER_AGENT = "Mozilla/5.0";

    // HTTP GET request
    public void sendGet() throws Exception {

        String url = "http://localhost:8080/reports";

        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet(url);

        // add request header
        request.addHeader("accept", "application/json");

        HttpResponse response = client.execute(request);

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

    public void sendGetByRanges(String patientName) throws Exception {

        String url1 = "http://localhost:8080/reports/ranges";
        String url = "http://localhost:8080/reports/ranges" + patientName;

        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet(url);

        // add request header
        request.addHeader("accept", "application/json");

        HttpResponse response = client.execute(request);

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

    // HTTP POST request
    private void sendPost() throws Exception {
        /*
         String url = "http://localhost:8080/reports";
        
         HttpClient client = new DefaultHttpClient();
         HttpPost post = new HttpPost(url);

         // add header
         post.setHeader("User-Agent", USER_AGENT);

         Report urlParameters = new ArrayList<NameValuePair>();
         urlParameters.add(new BasicNameValuePair("sn", "C02G8416DRJM"));
         urlParameters.add(new BasicNameValuePair("cn", ""));
         urlParameters.add(new BasicNameValuePair("locale", ""));
         urlParameters.add(new BasicNameValuePair("caller", ""));
         urlParameters.add(new BasicNameValuePair("num", "12345"));

         post.setEntity(new UrlEncodedFormEntity(urlParameters));

         HttpResponse response = client.execute(post);
         System.out.println("\nSending 'POST' request to URL : " + url);
         System.out.println("Post parameters : " + post.getEntity());
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
         */
    }

    @Override
    public Void call() throws Exception {
        sendGet();
        return null;
    }

}

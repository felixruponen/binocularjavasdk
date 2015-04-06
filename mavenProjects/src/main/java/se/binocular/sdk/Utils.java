/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.binocular.sdk;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 * @author felix
 */
public class Utils {

    public static HttpResponse excuteGet(String targetURL) throws IOException {
        URL url;
        HttpURLConnection connection = null;
        try {
            //Create connection
            url = new URL(targetURL);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type",
                    "application/json");

            connection.setUseCaches(false);
            connection.setDoInput(true);

            //Get Response    
            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            String line;
            StringBuffer responseBuffer = new StringBuffer();
            while ((line = rd.readLine()) != null) {
                responseBuffer.append(line);
                responseBuffer.append('\r');
            }
            rd.close();
            return new HttpResponse(connection.getResponseCode(), responseBuffer.toString());

        } catch (Exception e) {

            e.printStackTrace();
            return new HttpResponse(connection.getResponseCode(), null);

        } finally {

            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    public static HttpResponse excutePost(String targetURL, String content) {
        
        HttpResponse response = new HttpResponse();
        URL url;
        HttpURLConnection connection = null;
        try {
            //Create connection
            url = new URL(targetURL);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type",
                    "application/json");

            connection.setRequestProperty("Content-Length", ""
                    + Integer.toString(content.getBytes().length));

            connection.setUseCaches(false);
            connection.setDoInput(true);
            connection.setDoOutput(true);

            //Send request
            DataOutputStream wr = new DataOutputStream(
                    connection.getOutputStream());
            wr.writeBytes(content);
            wr.flush();
            wr.close();

            //Get Response    
            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            String line;
            StringBuffer responseBuffer = new StringBuffer();
            while ((line = rd.readLine()) != null) {
                responseBuffer.append(line);
                responseBuffer.append('\r');
            }
            rd.close();
            return new HttpResponse(connection.getResponseCode(), responseBuffer.toString());

        } catch (Exception e) {

            e.printStackTrace();
            return null;

        } finally {

            if (connection != null) {
                connection.disconnect();
            }
        }
    }
}

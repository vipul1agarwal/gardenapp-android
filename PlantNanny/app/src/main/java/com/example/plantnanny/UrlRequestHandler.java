package com.example.plantnanny;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class UrlRequestHandler {

    String getResponse (String x, String y)
    {
        URL url = null;
        try {
            if(x.equals("2"))
            {
                url = new URL("http://192.168.2.42:8080/garden-app/save");
            }
            else if (x.equals("3"))
            {
                url = new URL("http://192.168.2.42:8080/garden-app/home");
            }
            else if (x.equals("4"))
            {
                url = new URL("http://192.168.2.42:8080/garden-app/scan");
            }
            else
            {
                url = new URL("http://192.168.2.42:8080/garden-app/moveto?x=" + x + "&y=" + y);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        HttpURLConnection urlConnection = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            InputStream in = null;
            try {
                in = new BufferedInputStream(urlConnection.getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
            String str  = readStream(in);
            return str;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            urlConnection.disconnect();
        }
        return null;
    }


    private String readStream(InputStream in) throws IOException {
        BufferedReader reader = null;
        reader = new BufferedReader( new InputStreamReader(in));

        StringBuffer buffer = new StringBuffer();
        String line = "";
        while ((line = reader.readLine())!=null)
        {
            buffer.append(line);
        }

        return buffer.toString();
    }
}

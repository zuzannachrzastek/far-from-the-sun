package pl.edu.agh.farfromthesun.forecast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class URLConnectionReader {

    //2fdf99a36a9af682 - klucz do WU
    private static final String HTTP = "http://api.wunderground.com/api/2fdf99a36a9af682/forecast10day/PL/q/";
    private static final String FORMAT = ".json";

    public String getForecastData(String coordinates) throws IOException {
        BufferedReader in = null;
        try {
            URL url = new URL(HTTP + coordinates + FORMAT); //cordinates: latitude,longitude
            URLConnection yc = url.openConnection();
            in = new BufferedReader(new InputStreamReader(
                    yc.getInputStream()));
            String inputLine;
            StringBuilder builder = new StringBuilder();
            while ((inputLine = in.readLine()) != null)
                builder.append(inputLine);
            in.close();
            return builder.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if(in != null) in.close();
        }
        return null;
    }
}
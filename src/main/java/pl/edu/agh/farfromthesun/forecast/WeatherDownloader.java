package pl.edu.agh.farfromthesun.forecast;

import pl.edu.agh.farfromthesun.map.Point;

import java.time.LocalDate;
import java.time.Month;

/**
 * Created by Zuzia on 2015-12-01.
 */


public class WeatherDownloader {
    public ForecastData GetForecast(){
        JSONParser parser = new JSONParser();
        return parser.GetForecastDataForSpecificDateAndPoint(LocalDate.of(2015, Month.DECEMBER, 15), new Point(50.060, 19.959));
    }
    public void writeData(){
        ForecastData fd = GetForecast();
        System.out.println(fd.getDate().toString());
        System.out.println(String.valueOf(fd.getCoordinates().getLatitude()));
        System.out.println(String.valueOf(fd.getCoordinates().getLongitude()));
        System.out.println(fd.getIconUrl());
        System.out.println(String.valueOf(fd.getHighTemp()));
        System.out.println(String.valueOf(fd.getLowTemp()));
        System.out.println(fd.getPrecipitationType().toString());
        System.out.println(String.valueOf(fd.getPrecipitationLevel()));
        System.out.println(String.valueOf(fd.getAveWind()));
        System.out.println(String.valueOf(fd.getAveHumidity()));
    }
}

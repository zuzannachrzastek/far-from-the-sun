package pl.edu.agh.farfromthesun.forecast;

import pl.edu.agh.farfromthesun.map.Location;

import java.time.LocalDate;

public class WeatherDownloader implements IWeatherDownloader{

    public WeatherLocation GetForecast(LocalDate date, Location location){
        return ForecastCache.GetCachedForecastData(date, location);
    }

    public void writeData(){
        WeatherLocation fd = GetForecast(LocalDate.now(), new Location(50.060, 19.959));
        if(fd == null){
            System.out.println("Forecast has not been downloaded - date out of range or bad coordinates");
            return;
        }
        System.out.println(fd.getDate().toString());
        System.out.println(String.valueOf(fd.getLat()));
        System.out.println(String.valueOf(fd.getLon()));
        System.out.println(fd.getIconUrl());
        System.out.println(String.valueOf(fd.getHighTemp()));
        System.out.println(String.valueOf(fd.getLowTemp()));
        System.out.println(fd.getPrecipitationType().toString());
        System.out.println(String.valueOf(fd.getPrecipitationLevel()));
        System.out.println(String.valueOf(fd.getAveWind()));
        System.out.println(String.valueOf(fd.getAveHumidity()));
    }
}

package pl.edu.agh.farfromthesun.forecast;

import pl.edu.agh.farfromthesun.map.Location;

import java.time.LocalDate;
import java.util.ArrayList;

public final class ForecastCache {

    public static ArrayList<WeatherLocation> cacheList = new ArrayList<>();

    public static void addToCache(WeatherLocation weatherLocation){
        for( WeatherLocation wl : cacheList){
            if(wl.getDate().equals(weatherLocation.getDate()) && wl.getLon() == weatherLocation.getLon() && wl.getLat() == weatherLocation.getLat())
                return;
        }
        cacheList.add(weatherLocation);
    }

    public static WeatherLocation getCachedForecastData(LocalDate date, Location location){
        for( WeatherLocation wl : cacheList){
            if(wl.getDate().equals(date) && wl.getLon() == location.getLon() && wl.getLat() == location.getLat())
                return wl;
        }
        return null;
    }
}

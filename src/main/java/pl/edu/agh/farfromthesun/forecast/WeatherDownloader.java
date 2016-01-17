package pl.edu.agh.farfromthesun.forecast;

import pl.edu.agh.farfromthesun.map.Location;

import java.io.IOException;
import java.time.LocalDate;

public class WeatherDownloader implements IWeatherDownloader{

    public WeatherLocation getForecast(LocalDate date, Location location){
        WeatherLocation wl = ForecastCache.getCachedForecastData(date, location);
        if(wl == null){
            try {
                URLConnectionReader urlConnReader = new URLConnectionReader();
                String forecastDataString = urlConnReader.getForecastData(getStringCoordinates(location));
                IWeatherParser parser = new JSONParser();
                return parser.getForecast(date, location, forecastDataString);
            } catch (IOException e) {
                e.printStackTrace(); //?
                IWeatherParser parser = new JSONParser();
                return parser.getEmptyForecastDataObject(date, location);
            }
        }

        return wl;
    }

    private String getStringCoordinates(Location point){
        return String.valueOf(point.getLat()) + "," + String.valueOf(point.getLon());
    }

    public void writeData(){
        WeatherLocation fd = getForecast(LocalDate.now(), new Location(50.060, 19.959));
        if(fd == null){
            System.out.println("Forecast has not been downloaded - date out of range or bad coordinates");
            return;
        }
        Write(fd);
        LocalDate currDate  = LocalDate.now();
        WeatherLocation ff = ForecastCache.getCachedForecastData( LocalDate.of(currDate.getYear(), currDate.getMonthValue(),currDate.getDayOfMonth() + 3),new Location(50.060, 19.959));
        Write(ff);

        System.out.print("cache list size:" + ForecastCache.cacheList.size());
    }

    private void Write(WeatherLocation fd){
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

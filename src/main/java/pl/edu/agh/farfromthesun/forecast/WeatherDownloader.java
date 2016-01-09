package pl.edu.agh.farfromthesun.forecast;

import pl.edu.agh.farfromthesun.map.Location;

import java.time.LocalDate;

public class WeatherDownloader implements IWeatherDownloader{

    public WeatherLocation GetForecast(LocalDate date, Location location){
        IWeatherParser parser = new JSONParser();
        return parser.GetForecast(date, location);
    }

    public void writeData(){
        WeatherLocation fd = GetForecast(LocalDate.now(), new Location(50.060, 19.959));
        if(fd == null){
            System.out.println("Forecast has not been downloaded - date out of range or bad coordinates");
            return;
        }
        System.out.println(fd);
    }
}

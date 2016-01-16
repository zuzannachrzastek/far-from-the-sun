package pl.edu.agh.farfromthesun.forecast;


import pl.edu.agh.farfromthesun.map.Location;

import java.time.LocalDate;

public interface IWeatherDownloader {
    WeatherLocation getForecast(LocalDate date, Location location);
}

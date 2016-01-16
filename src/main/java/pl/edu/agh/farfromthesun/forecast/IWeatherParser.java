package pl.edu.agh.farfromthesun.forecast;

import pl.edu.agh.farfromthesun.map.Location;

import java.time.LocalDate;

public interface IWeatherParser {
    WeatherLocation getForecast(LocalDate date, Location location, String forecastDataString);
    WeatherLocation getEmptyForecastDataObject(LocalDate date, Location location);
}

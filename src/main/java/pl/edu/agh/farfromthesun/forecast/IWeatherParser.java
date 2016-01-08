package pl.edu.agh.farfromthesun.forecast;

import pl.edu.agh.farfromthesun.map.Point;

import java.time.LocalDate;

public interface IWeatherParser {
    ForecastData GetForecastDataForSpecificDateAndPoint(LocalDate date, Point point);
}

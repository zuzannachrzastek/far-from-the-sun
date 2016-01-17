package pl.edu.agh.farfromthesun.algorithm;

import java.util.List;

import pl.edu.agh.farfromthesun.forecast.WeatherLocation;

public interface AlgorithmObserver {
	public void handleResults(List<WeatherLocation> locations);
}
package pl.edu.agh.farfromthesun.algorithm;

import java.util.ArrayList;

import pl.edu.agh.farfromthesun.forecast.WeatherLocation;

public interface AlgorithmObserver {
	public void handleResults(ArrayList<WeatherLocation> locations);
}
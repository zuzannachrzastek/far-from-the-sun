package pl.edu.agh.farfromthesun.algorithm.model;

import java.util.ArrayList;
import java.util.List;

import pl.edu.agh.farfromthesun.forecast.Forecast;
import pl.edu.agh.farfromthesun.forecast.WeatherLocation;

public class TourManager {
	private List<WeatherLocation> points;
	private final Parameters params;
	private final Forecast forecast;

	public TourManager(List<WeatherLocation> points, Parameters params, Forecast forecast) {
		this.points = new ArrayList<WeatherLocation>(points);
		this.params = params;
		this.forecast = forecast;
	}

	public WeatherLocation getPoint(int i) {
		return points.get(i);
	}

	public int numberOfPoints() {
		return points.size();
	}

	public Parameters getParameters() {
		return params;
	}
	
	public Forecast getForecast(){
		return forecast;
	}
}

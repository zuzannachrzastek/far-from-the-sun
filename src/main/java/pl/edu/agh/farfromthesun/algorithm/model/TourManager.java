package pl.edu.agh.farfromthesun.algorithm.model;

import java.util.ArrayList;

import pl.edu.agh.farfromthesun.forecast.WeatherLocation;

public class TourManager {
	private static ArrayList<WeatherLocation> points = new ArrayList<WeatherLocation>();

	public static void initialize(ArrayList<WeatherLocation> points) {
		TourManager.points = new ArrayList<WeatherLocation>(points);
	}

	public static WeatherLocation getPoint(int i) {
		return points.get(i);
	}

	public static int numberOfPoints() {
		return points.size();
	}
}

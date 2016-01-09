package pl.edu.agh.farfromthesun.algorithm.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import pl.edu.agh.farfromthesun.app.App;
import pl.edu.agh.farfromthesun.forecast.WeatherLocation;
import pl.edu.agh.farfromthesun.map.Location;

public class Tour {
	private ArrayList<WeatherLocation> tour = new ArrayList<WeatherLocation>();
	private double distance = 0;
	private double fitness = 0;
	private double temperature = Double.MAX_VALUE;

	public Tour(List<Location> points) {
		for (Location i : points) {
			tour.add(new WeatherLocation(i));
		}
	}

	public Tour() {
		for (int i = 1; i < TourManager.numberOfPoints(); i++) {
			tour.add(new WeatherLocation(TourManager.getPoint(i)));
		}
		Collections.shuffle(tour);
		tour.add(0, new WeatherLocation(TourManager.getPoint(0))); // setting starting point to be
												// first
	}

	public void setPoint(int position, WeatherLocation point) {
		tour.set(position, point);
		distance = 0;
		fitness = 0;
	}

	public double getDistance() {
		if (distance == 0) {
			distance += getFirstPoint().distanceTo(getLastPoint());

			for (int i = 1; i < tour.size(); i++) {
				distance += getPointAt(i).distanceTo(getPointAt(i - 1));
			}
		}

		return distance;
	}

	public double getAvgTemperature() {
		if (temperature == Double.MAX_VALUE) {
			temperature = 0;

			WeatherLocation loc;
			LocalDate date = Parameters.getInstance().getDate();
			int c = 0;

			for (int i = 0; i < tour.size(); i++) {
				loc = App.getForecast().getForecast(date.plusDays(i),
						getPointAt(i));

				if (loc != null){
					setPoint(i, loc);
					temperature += loc.getHighTemp();
				}
			}

			temperature /= c;
		}

		return temperature;
	}

	// TODO add weather dependency
	public double getFitness() {
		if (fitness == 0) {
			Parameters params = Parameters.getInstance();
			double temp = params.getTemperature();
			fitness = (100 / getDistance()) * (1.0-0.05*Math.abs(temp-getAvgTemperature()));
		}
		return fitness;
	}

	@Override
	public String toString() {
		String result = "|";
		for (int i = 0; i < tour.size(); i++) {
			result += tour.get(i) + "|";
		}
		return result;
	}

	public int tourSize() {
		return tour.size();
	}

	public WeatherLocation getFirstPoint() {
		return getPointAt(0);
	}

	public WeatherLocation getLastPoint() {
		return getPointAt(tour.size() - 1);
	}

	public WeatherLocation getPointAt(int i) {
		return tour.get(i);
	}

	public ArrayList<WeatherLocation> getPoints() {
		return tour;
	}
}

package pl.edu.agh.farfromthesun.algorithm.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

import pl.edu.agh.farfromthesun.forecast.WeatherLocation;

public class Tour {
	private ArrayList<WeatherLocation> tour = new ArrayList<WeatherLocation>();
	private double distance = 0;
	private double fitness = 0;
	private double temperature = Double.MAX_VALUE;
	private TourManager manager;

	public Tour(TourManager manager) {
		this.manager = manager;
		this.shuffleLocations();
	}

	private void shuffleLocations() {
		for (int i = 1; i < manager.numberOfPoints(); i++) {
			tour.add(new WeatherLocation(manager.getPoint(i)));
		}
		Collections.shuffle(tour);
		tour.add(tour.get(0));
		tour.set(0, new WeatherLocation(manager.getPoint(0)));
	}

	public void setPoint(int position, WeatherLocation point) {
		tour.set(position, point);
		distance = 0;
		fitness = 0;
		temperature = Double.MAX_VALUE;
	}

	private void calculateDistance() {
		distance += getFirstPoint().distanceTo(getLastPoint());

		for (int i = 1; i < tour.size(); i++) {
			distance += getPointAt(i).distanceTo(getPointAt(i - 1));
		}
	}

	public double getDistance() {
		if (distance == 0) {
			calculateDistance();
		}

		return distance;
	}

	private void calculateTemperature() {
		temperature = 0;

		WeatherLocation loc;
		LocalDate date = manager.getParameters().getDate();
		int c = 0;

		for (int i = 0; i < tour.size(); i++) {
			loc = manager.getForecast()
					.getForecast(date.plusDays(i), getPointAt(i));

			if (loc != null) {
				tour.set(i, loc);
				temperature += loc.getHighTemp();
			}
		}

		temperature /= c;
	}

	public double getAvgTemperature() {
		if (temperature == Double.MAX_VALUE) {
			calculateTemperature();
		}

		return temperature;
	}

	// TODO add weather dependency
	public double getFitness() {
		if (fitness == 0) {
			double temp = manager.getParameters().getTemperature();
			fitness = (100 / getDistance())
					* (1.0 - 0.05 * Math.abs(temp - getAvgTemperature()));
		}
		return fitness;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(tour.get(0).toString());
		for (int i = 1; i < tour.size(); i++) {
			sb.append("\n\n  |\n  V\n\n");
			sb.append(tour.get(i));
		}
		return sb.toString();
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

	public TourManager getManager() {
		return manager;
	}
}

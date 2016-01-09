package pl.edu.agh.farfromthesun.algorithm.model;

import java.util.ArrayList;
import java.util.List;

import pl.edu.agh.farfromthesun.forecast.WeatherLocation;

public class InversionMutation implements Mutation, Nameable {

	@Override
	public void mutate(Tour tour) {
		int from = (int) ((Math.random() * (tour.tourSize() - 1)) + 1);
		int to = (int) ((Math.random() * (tour.tourSize() - 1)) + 1);
		while (to == from) {
			to = (int) ((Math.random() * (tour.tourSize() - 1)) + 1);
		}
		if (from > to) {
			from += to;
			to = from - to;
			from -= to;
		}
		List<WeatherLocation> reversedValues = new ArrayList<WeatherLocation>();
		for (int i = to; i >= from; i--) {
			reversedValues.add(tour.getPointAt(i));
		}
		for (int i = from; i <= to; i++) {
			tour.setPoint(i, reversedValues.get(i - from));
			;
		}
	}

	@Override
	public String getName() {
		return "Inversion mutation";
	}

}

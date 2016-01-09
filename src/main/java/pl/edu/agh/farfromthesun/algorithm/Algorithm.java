package pl.edu.agh.farfromthesun.algorithm;

import java.util.ArrayList;

import javax.swing.JFrame;

import pl.edu.agh.farfromthesun.algorithm.model.GeneticAlgorithm;
import pl.edu.agh.farfromthesun.algorithm.model.Parameters;
import pl.edu.agh.farfromthesun.algorithm.model.Population;
import pl.edu.agh.farfromthesun.algorithm.model.TourManager;
import pl.edu.agh.farfromthesun.app.Component;
import pl.edu.agh.farfromthesun.forecast.WeatherLocation;
import pl.edu.agh.farfromthesun.map.Location;

public class Algorithm implements Component {
	private Population pop;
	private final Parameters params = Parameters.getInstance();
	private final GeneticAlgorithm algo = new GeneticAlgorithm();

	@Override
	public void initialize(JFrame frame) {
		new AlgorithmController(frame, this);
	}

	public ArrayList<WeatherLocation> findOptimalTour(ArrayList<Location> points) {
		if (points.size() < 3) {
			ArrayList<WeatherLocation> result = new ArrayList<WeatherLocation>();
			for (Location location : points) {
				result.add(new WeatherLocation(location));
			}
			return result;
		}

		TourManager.initialize(points);

		pop = algo.evolvePopulation(new Population(params.getPopulationSize(),
				true));

		for (int i = 0, n = params.getNumberOfGenerations(); i < n; i++) {
			pop = algo.evolvePopulation(pop);
		}

		System.out.print(pop.getFittest());
		return pop.getFittest().getPoints();
	}

}

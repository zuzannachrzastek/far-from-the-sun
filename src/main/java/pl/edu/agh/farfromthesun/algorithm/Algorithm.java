package pl.edu.agh.farfromthesun.algorithm;

import java.util.List;

import javax.swing.JFrame;

import pl.edu.agh.farfromthesun.algorithm.model.GeneticAlgorithm;
import pl.edu.agh.farfromthesun.algorithm.model.Parameters;
import pl.edu.agh.farfromthesun.algorithm.model.Population;
import pl.edu.agh.farfromthesun.algorithm.model.TourManager;
import pl.edu.agh.farfromthesun.app.Component;
import pl.edu.agh.farfromthesun.map.Location;

public class Algorithm implements Component {
	private Population pop;
	private final Parameters params = new Parameters();
	private final GeneticAlgorithm algo = new GeneticAlgorithm();

	@Override
	public void initialize(JFrame frame) {
		new AlgorithmModel(frame, this);
	}

	public Parameters getParametersInstance() {
		return params;
	}

	public List<Location> findOptimalTour(List<Location> points) {
		if (points.size() < 3) {
			return points;
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

package pl.edu.agh.farfromthesun.algorithm;

import java.beans.PropertyChangeListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import javax.swing.JFrame;
import javax.swing.SwingWorker;

import pl.edu.agh.farfromthesun.algorithm.model.GeneticAlgorithm;
import pl.edu.agh.farfromthesun.algorithm.model.Parameters;
import pl.edu.agh.farfromthesun.algorithm.model.Population;
import pl.edu.agh.farfromthesun.algorithm.model.TourManager;
import pl.edu.agh.farfromthesun.app.App;
import pl.edu.agh.farfromthesun.app.Component;
import pl.edu.agh.farfromthesun.forecast.WeatherLocation;

public class Algorithm implements Component {
	private Population pop;
	private final Parameters params = Parameters.getInstance();
	private final GeneticAlgorithm algo = new GeneticAlgorithm();

	@Override
	public void initialize(JFrame frame) {
		new AlgorithmController(frame, this);
	}

	public void findOptimalTour(ArrayList<WeatherLocation> points, PropertyChangeListener listener) {
		System.out.println("...");

		if (points.size() < 3) {
			ArrayList<WeatherLocation> result = new ArrayList<WeatherLocation>();
			LocalDate date = params.getDate();
			for (int i = 0; i < points.size(); i++) {
				result.add(App.getForecast().getForecast(date, points.get(i)));
			}

			App.getMap().handleResults(result);
			App.getForecast().handleResults(result);
		}

		TourManager.initialize(points);
		
		Task task = new Task();
		task.addPropertyChangeListener(listener);
		task.execute();
	}

	class Task extends SwingWorker<ArrayList<WeatherLocation>, Void> {
		@Override
		public ArrayList<WeatherLocation> doInBackground() {
			int progress = 0;
			setProgress(0);
			pop = algo.evolvePopulation(new Population(params
					.getPopulationSize(), true));

			for (int i = 0, n = params.getNumberOfGenerations(); i < n; i++) {
				try { Thread.sleep(10); } catch (InterruptedException e){};
				pop = algo.evolvePopulation(pop);
				progress = 100 * (i+1)/n;
				setProgress(Math.min(100, progress));
			}
			
			setProgress(100);

			System.out.print(pop.getFittest());
			return pop.getFittest().getPoints();
		}

		@Override
		public void done() {
			try {
				App.getMap().handleResults(get());
				App.getForecast().handleResults(get());
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}
	}

}

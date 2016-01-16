package pl.edu.agh.farfromthesun.algorithm;

import java.beans.PropertyChangeListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.swing.JFrame;
import javax.swing.SwingWorker;

import pl.edu.agh.farfromthesun.algorithm.model.GeneticAlgorithm;
import pl.edu.agh.farfromthesun.algorithm.model.Parameters;
import pl.edu.agh.farfromthesun.algorithm.model.Population;
import pl.edu.agh.farfromthesun.algorithm.model.TourManager;
import pl.edu.agh.farfromthesun.app.Component;
import pl.edu.agh.farfromthesun.forecast.Forecast;
import pl.edu.agh.farfromthesun.forecast.WeatherLocation;
import pl.edu.agh.farfromthesun.map.Map;

public class AlgorithmController implements Component {
	private Parameters params = new Parameters();
	private final LinkedList<AlgorithmObserver> observers = new LinkedList<AlgorithmObserver>();
	private Map map;
	private Forecast forecast;
	private JFrame frame = null;
	
	public AlgorithmController(Map map, Forecast forecast){
		this.map = map;
		this.forecast = forecast;
	}

	@Override
	public void initialize(JFrame frame) {
		this.frame = frame;
		new AlgorithmConfigurationController(this);
	}
	
	public JFrame getFrame(){
		return frame;
	}
	
	public Parameters getParameters(){
		return params;
	}
	
	public void setParameters(Parameters params){
		this.params = params;
	}
	
	public Map getMap(){
		return map;
	}

	public void addObserver(AlgorithmObserver observer) {
		if (!this.observers.contains(observer)) {
			this.observers.add(observer);
		}
	}

	private void handleResults(List<WeatherLocation> result) {
		for (AlgorithmObserver observer : this.observers) {
			observer.handleResults(result);
		}
	}

	private List<WeatherLocation> wrapLocations(List<WeatherLocation> points) {
		List<WeatherLocation> result = new ArrayList<WeatherLocation>();
		LocalDate date = params.getDate();
		for (int i = 0; i < points.size(); i++) {
			result.add(forecast.getForecast(date, points.get(i)));
		}

		return result;
	}

	public void findOptimalTour(List<WeatherLocation> points,
			PropertyChangeListener listener) {
		if (points.size() < 3) {
			handleResults(wrapLocations(points));
		} else {

			TourManager manager = new TourManager(points, params, forecast);

			Task task = new Task(manager);
			task.addPropertyChangeListener(listener);
			task.execute();
		}
	}

	class Task extends SwingWorker<List<WeatherLocation>, Void> {
		private Population pop;
		private TourManager manager;
		private GeneticAlgorithm algo;
		
		public Task(TourManager manager){
			this.manager = manager;
			this.algo = new GeneticAlgorithm(manager);
		}
		
		@Override
		public List<WeatherLocation> doInBackground() {
			int progress = 0;
			setProgress(0);
			
			pop = algo.evolvePopulation(new Population(params
					.getPopulationSize(), true, manager));

			for (int i = 0, n = params.getNumberOfGenerations(); i < n; i++) {
				pop = algo.evolvePopulation(pop);
				progress = 100 * (i + 1) / n;
				setProgress(Math.min(100, progress));
			}

			setProgress(100);
			
			return pop.getFittest().getPoints();
		}

		@Override
		public void done() {
			try {
				handleResults(get());
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}
	}

}

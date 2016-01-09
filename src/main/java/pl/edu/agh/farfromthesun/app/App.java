package pl.edu.agh.farfromthesun.app;

import java.awt.Dimension;

import javax.swing.JFrame;

import pl.edu.agh.farfromthesun.algorithm.Algorithm;
import pl.edu.agh.farfromthesun.forecast.Forecast;
import pl.edu.agh.farfromthesun.map.Map;

public class App extends JFrame {

	private static final long serialVersionUID = 3066593449554111970L;

	private static Map map;
	private static Forecast forecast;
	private static Algorithm algorithm;
	
	public App() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(800, 600));
		pack();
		setLocationRelativeTo(null);
		setTitle("Far From The Sun");
		setVisible(true);
	}

	public static void main(String[] args) {
		App app = new App();
		Map map = new Map();
		Forecast forecast = new Forecast();
		Algorithm algorithm = new Algorithm();

		map.initialize(app);
		forecast.initialize(app);
		algorithm.initialize(app);
		
		App.setMap(map);
		App.setForecast(forecast);
		App.setAlgorithm(algorithm);

		app.revalidate();
	}

	public static Map getMap() {
		return map;
	}

	public static void setMap(Map map) {
		App.map = map;
	}

	public static Forecast getForecast() {
		return forecast;
	}

	public static void setForecast(Forecast forecast) {
		App.forecast = forecast;
	}

	public static Algorithm getAlgorithm() {
		return algorithm;
	}

	public static void setAlgorithm(Algorithm algorithm) {
		App.algorithm = algorithm;
	}
}

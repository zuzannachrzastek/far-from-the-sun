package pl.edu.agh.farfromthesun.forecast;

import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.JFrame;

import pl.edu.agh.farfromthesun.algorithm.AlgorithmObserver;
import pl.edu.agh.farfromthesun.app.Component;
import pl.edu.agh.farfromthesun.map.Location;

public class Forecast implements AlgorithmObserver, Component {
	
	private final WeatherDownloader wd = new WeatherDownloader();

	@Override
	public void handleResults(ArrayList<WeatherLocation> locations) {
		// TODO Auto-generated method stub

	}

	@Override
	public void initialize(JFrame frame) {
		// TODO Auto-generated method stub

	}
	
	public WeatherLocation getForecast(LocalDate date, Location location){
		return wd.GetForecast(date, location);
	}

}

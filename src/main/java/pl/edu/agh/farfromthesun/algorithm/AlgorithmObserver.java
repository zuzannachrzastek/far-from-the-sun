package pl.edu.agh.farfromthesun.algorithm;

import java.util.List;

import pl.edu.agh.farfromthesun.map.Point;

public interface AlgorithmObserver {
	public void handleResults(List<Point> locations);
}
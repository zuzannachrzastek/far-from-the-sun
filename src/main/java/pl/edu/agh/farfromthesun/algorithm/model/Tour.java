package pl.edu.agh.farfromthesun.algorithm.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import pl.edu.agh.farfromthesun.map.Point;

public class Tour {
	private ArrayList<Point> tour = new ArrayList<Point>();
	private double distance = 0;
	private double fitness = 0;

	public Tour(List<Point> points) {
		for (Point i : points) {
			tour.add(i);
		}
	}

	public Tour() {
		for (int i = 1; i < TourManager.numberOfPoints(); i++) {
			tour.add(TourManager.getPoint(i));
		}
		Collections.shuffle(tour);
		tour.add(0, TourManager.getPoint(0)); // setting starting point to be
												// first
	}

	public void setPoint(int position, Point point) {
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

	// TODO add weather dependency
	public double getFitness() {
		if (fitness != 0) {
			return fitness;
		}
		return 1.0 / getDistance();
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

	public Point getFirstPoint() {
		return getPointAt(0);
	}

	public Point getLastPoint() {
		return getPointAt(tour.size() - 1);
	}

	public Point getPointAt(int i) {
		return tour.get(i);
	}

	public List<Point> getPoints() {
		return tour;
	}
}

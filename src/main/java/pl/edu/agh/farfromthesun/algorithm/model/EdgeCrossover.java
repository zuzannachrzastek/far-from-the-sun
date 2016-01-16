package pl.edu.agh.farfromthesun.algorithm.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import pl.edu.agh.farfromthesun.map.Location;

public class EdgeCrossover implements Crossover, Nameable {

	@Override
	public Tour cross(Tour a, Tour b) {
		Map<Integer, HashMap<Integer, Integer>> points = new HashMap<Integer, HashMap<Integer, Integer>>();
		Map<Location, Integer> point2int = new HashMap<Location, Integer>();
		Set<Integer> added = new HashSet<Integer>();
		for (int i = 0; i < a.tourSize(); i++) {
			points.put(i, new HashMap<Integer, Integer>());
			for (int j = 0; j < a.tourSize(); j++) {
				points.get(i).put(j, 0);
			}
		}
		Tour child = new Tour( a.getManager() );
		for (int i = 0; i < a.tourSize(); i++) {
			point2int.put(a.getPointAt(i), i);
		}
		int tmp = point2int.get(a.getPointAt(1));
		points.get(0).replace(tmp, points.get(0).get(tmp) + 1);
		
		tmp = point2int.get(a.getPointAt(a.tourSize() - 1));
		points.get(0).replace(tmp, points.get(0).get(tmp) + 1);
		
		tmp = point2int.get(b.getPointAt(1));
		points.get(0).replace(tmp, points.get(0).get(tmp) + 1);
		
		tmp = point2int.get(b.getPointAt(a.tourSize() - 1));
		points.get(0).replace(tmp, points.get(0).get(tmp) + 1);
		
		for (int i = 1; i < a.tourSize(); i++) {
			tmp = point2int.get(a.getPointAt((i + 1) % a.tourSize()));
			points.get(i).replace(tmp, points.get(i).get(tmp) + 1);
			
			tmp = point2int.get(a.getPointAt(i - 1));
			points.get(i).replace(tmp, points.get(i).get(tmp) + 1);
			
			tmp = point2int.get(b.getPointAt((i + 1) % a.tourSize()));
			points.get(i).replace(tmp, points.get(i).get(tmp) + 1);
			
			tmp = point2int.get(b.getPointAt(i - 1));
			points.get(i).replace(tmp, points.get(i).get(tmp) + 1);
		}
		
		child.setPoint(0, a.getPointAt(0));
		added.add(0);
		int lastPoint = 0;
		int nextPoint = 0;
		for (int i = 1; i < a.tourSize(); i++) {
			nextPoint = getNextPoint(points, lastPoint, added);
			added.add(nextPoint);
			removePoint(points, lastPoint);
			child.setPoint(i, a.getPointAt(nextPoint));
			lastPoint = nextPoint;
		}
		return child;
	}

	@Override
	public String getName() {
		return "Edge crossover";
	}

	private int getNextPoint(Map<Integer, HashMap<Integer, Integer>> points, int actualPoint, Set<Integer> added) {
		int next = 0;
		int shortest = 5;
		for (int i = 0; i < points.size(); i++) {
			if (points.get(i).get(actualPoint) == 2) {
				return i;
			}
			if (points.get(i).get(actualPoint) > 0) {
				if (shortest > mapNotZeroElements(points.get(i))) {
					shortest = mapNotZeroElements(points.get(i));
					next = i;
				}
			}
		}
		while (added.contains(next)) {
			next = (int) (Math.random() * points.size());
		}
		return next;
	}

	private int mapNotZeroElements(HashMap<Integer, Integer> points) {
		int sum = 0;
		for (int i = 0; i < points.size(); i++) {
			if (points.get(i) != 0) {
				sum++;
			}
		}
		return sum;
	}

	private void removePoint(Map<Integer, HashMap<Integer, Integer>> points, int actualPoint) {
		for (int i = 0; i < points.size(); i++) {
			points.get(i).replace(actualPoint, 0);
			points.get(actualPoint).replace(i, 0);
		}
	}
}

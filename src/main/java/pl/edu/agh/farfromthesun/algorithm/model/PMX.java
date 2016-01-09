package pl.edu.agh.farfromthesun.algorithm.model;

import java.util.HashMap;
import java.util.Map;

import pl.edu.agh.farfromthesun.map.Point;

public class PMX implements Crossover, Nameable {

	@Override
	public Tour cross(Tour a, Tour b) {
		int from = (int) ((Math.random() * (a.tourSize() - 1)) + 1);
		int to = (int) ((Math.random() * (a.tourSize() - 1)) + 1);
		Tour child = new Tour();
		Map<Point, Integer> parentA = new HashMap<Point, Integer>();
		Map<Point, Integer> parentB = new HashMap<Point, Integer>();
		for (int i = 1; i < a.tourSize(); i++) {
			parentA.put(a.getPointAt(i), i);
			parentB.put(b.getPointAt(i), i);
		}
		while (to == from) {
			to = (int) ((Math.random() * (a.tourSize() - 1)) + 1);
		}
		if (from > to) {
			from += to;
			to = from - to;
			from -= to;
		}
		for (int i = 1; i < a.tourSize(); i++) {
			child.setPoint(i, null);
		}
		for (int i = from; i <= to; i++) {
			child.setPoint(i, a.getPointAt(i));
			if (parentA.get(b.getPointAt(i)) != null && ((parentA.get(b.getPointAt(i)) < from) || (parentA.get(b.getPointAt(i)) > to))) {
				int newPos = parentB.get(a.getPointAt(i));
				while (newPos >= from && newPos <= to) {
					if(parentB.get(a.getPointAt(newPos)) == newPos){
						break;
					}
					newPos = parentB.get(a.getPointAt(newPos));
				}
				child.setPoint(newPos, b.getPointAt(i));
			}
		}
		for (int i = 1; i < a.tourSize(); i++) {
			if(child.getPointAt(i) == null){
				child.setPoint(i, b.getPointAt(i));
			}
		}
		return child;
	}

	@Override
	public String getName() {
		return "Partially matched crossover";
	}

}

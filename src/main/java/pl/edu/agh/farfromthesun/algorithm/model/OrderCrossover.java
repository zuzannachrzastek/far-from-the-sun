package pl.edu.agh.farfromthesun.algorithm.model;

import java.util.HashSet;
import java.util.Set;

import pl.edu.agh.farfromthesun.map.Location;

public class OrderCrossover implements Crossover, Nameable{

	@Override
	public Tour cross(Tour a, Tour b) {
		int from = (int) ((Math.random() * (a.tourSize() - 1)) + 1);
		int to = (int) ((Math.random() * (a.tourSize() - 1)) + 1);
		Set<Location> assignedPoints = new HashSet<Location>();
		Tour child = new Tour();
		
		while (to == from) {
			to = (int) ((Math.random() * (a.tourSize() - 1)) + 1);
		}
		if (from > to) {
			from += to;
			to = from - to;
			from -= to;
		}
		for(int i = 1; i < a.tourSize(); i++){
			child.setPoint(i, null);
		}
		assignedPoints.add(a.getPointAt(0));
		for (int i = from; i <= to; i++) {
			child.setPoint(i, a.getPointAt(i));	
			assignedPoints.add(a.getPointAt(i));
		}
		int iteratorB = (to + 1) % a.tourSize();
		for (int i = (to + 1); i < a.tourSize(); i++) {
			while(assignedPoints.contains(b.getPointAt(iteratorB))){
				iteratorB = (iteratorB + 1) % a.tourSize();
			}
			child.setPoint(i, b.getPointAt(iteratorB));
			assignedPoints.add(b.getPointAt(iteratorB));
		}
		for (int i = 1; i < from; i++) {
			while(assignedPoints.contains(b.getPointAt(iteratorB))){
				iteratorB = (iteratorB + 1) % a.tourSize();
			}
			child.setPoint(i, b.getPointAt(iteratorB));
			assignedPoints.add(b.getPointAt(iteratorB));
		}
		return child;
	}

	@Override
	public String getName() {
		return "Order crossover";
	}

}

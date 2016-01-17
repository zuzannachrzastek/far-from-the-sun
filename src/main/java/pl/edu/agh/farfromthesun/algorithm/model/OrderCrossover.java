package pl.edu.agh.farfromthesun.algorithm.model;

import java.util.HashSet;
import java.util.Set;

import pl.edu.agh.farfromthesun.map.Location;

public class OrderCrossover implements Crossover, Nameable{

	@Override
	public Tour cross(Tour a, Tour b) {
		int from = (int) ((Math.random() * (a.tourSize() - 2)) + 1);
		int to = (int) ((Math.random() * (a.tourSize() - from - 1)) + from);
		
		Set<Location> assignedPoints = new HashSet<Location>();
		Tour child = new Tour(a.getManager());

		for(int i = 1; i < a.tourSize(); i++){ //first points remains unchanged, rest will be changed
			child.setPoint(i, null);
		}
		assignedPoints.add(a.getPointAt(0));
		
		for (int i = from; i <= to; i++) { //copy points in range(from, to) from parent A
			child.setPoint(i, a.getPointAt(i));	
			assignedPoints.add(a.getPointAt(i));
		}
		
		int iteratorB = (to + 1) % a.tourSize();
		int iteratorChild = (to + 1) % a.tourSize();
		while(assignedPoints.size() < a.tourSize()){ //copy rest in order in which they are in parent B
			if(!assignedPoints.contains(b.getPointAt(iteratorB))){ //copy only not copied from parent A
				child.setPoint(iteratorChild, b.getPointAt(iteratorB));
				assignedPoints.add(b.getPointAt(iteratorB));
				iteratorChild = (iteratorChild % (a.tourSize() - 1)) + 1; //skip 0;
			}
			iteratorB = (iteratorB % (a.tourSize() - 1)) + 1;
		}
		return child;
	}
	
	

	@Override
	public String getName() {
		return "Order crossover";
	}

}

package pl.edu.agh.farfromthesun.algorithm.model;

import java.util.HashMap;
import java.util.Map;

import pl.edu.agh.farfromthesun.map.Location;

public class PMX implements Crossover, Nameable {

	@Override
	public Tour cross(Tour a, Tour b) {
		int from = (int) ((Math.random() * (a.tourSize() - 2)) + 1);
		int to = (int) ((Math.random() * (a.tourSize() - from - 1)) + from);
		Tour child = new Tour(a.getManager());
		Map<Location, Integer> parentA = point2int(a);
		Map<Location, Integer> parentB = point2int(b);
		
		for (int i = 1; i < a.tourSize(); i++) { //first point remains unchanged, rest will be changed
			child.setPoint(i, null);
		}
		
		for (int i = from; i <= to; i++) {
			child.setPoint(i, a.getPointAt(i)); //set points as they are in parent A
			if ((parentA.get(b.getPointAt(i)) < from) || (parentA.get(b.getPointAt(i)) > to)) { //if point at position 'i' in parent B is not in range(from, to)
				int newPos = parentB.get(a.getPointAt(i));
				while (newPos >= from && newPos <= to) {
					newPos = parentB.get(a.getPointAt(newPos));
				}
				child.setPoint(newPos, b.getPointAt(i));
			}
		}
		
		for (int i = 1; i < a.tourSize(); i++) {
			if(child.getPointAt(i) == null){
				child.setPoint(i, b.getPointAt(i)); //set remaining points as they are in parent B
			}
		}
		return child;
	}
	
	private Map<Location, Integer> point2int(Tour tour){
		Map<Location, Integer> map = new HashMap<Location, Integer>();
		for (int i = 1; i < tour.tourSize(); i++) {
			map.put(tour.getPointAt(i), i);
		}
		return map;
	}

	@Override
	public String getName() {
		return "Partially matched crossover";
	}

}

package pl.edu.agh.farfromthesun.algorithm.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import pl.edu.agh.farfromthesun.map.Point;

public class ScrambleMutation implements Mutation, Nameable {

	@Override
	public void mutate(Tour tour) {
		int k = tour.tourSize() / 7 + 2;
		List<Integer> affectedPositions = new ArrayList<Integer>();
		List<Point> values = new ArrayList<Point>();
		for (int i = 0; i < k; i++) {
			int rand = (int) (Math.random() * (tour.tourSize() - 1)) + 1; // first must not be affected
			while (affectedPositions.contains(rand)) {
				rand = (int) (Math.random() * (tour.tourSize() - 1)) + 1;
			}
			affectedPositions.add(rand);
		}
		for (Integer p : affectedPositions) {
			values.add(tour.getPointAt(p));
		}
		Collections.shuffle(values);
		for (int i = 0; i < k; i++) {
			tour.setPoint(affectedPositions.get(i), values.get(i));
		}
	}

	@Override
	public String getName() {
		return "Scramble mutation";
	}

}

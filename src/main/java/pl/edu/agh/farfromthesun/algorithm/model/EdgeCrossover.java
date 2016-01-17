package pl.edu.agh.farfromthesun.algorithm.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import pl.edu.agh.farfromthesun.map.Location;

public class EdgeCrossover implements Crossover, Nameable {

	@Override
	public String getName() {
		return "Edge crossover";
	}

	@Override
	public Tour cross(Tour a, Tour b) {
		int[][] edges = generateEdgeArray(a, b);
		Set<Integer> added = new HashSet<Integer>();
		Tour child = new Tour( a.getManager() );
		
		child.setPoint(0, a.getPointAt(0));
		added.add(0);
		int lastPoint = 0;
		int nextPoint = 0;
		for (int i = 1; i < a.tourSize(); i++) {
			nextPoint = getNextPoint(edges, lastPoint, added);
			added.add(nextPoint);
			removePoint(edges, lastPoint);
			child.setPoint(i, a.getPointAt(nextPoint));
			lastPoint = nextPoint;
		}
		return child;
	}
	
	private int[][] generateEdgeArray(Tour a, Tour b){
		int[][] edges = new int[a.tourSize()][];
		for(int i = 0; i < a.tourSize(); i++){
			edges[i] = new int[a.tourSize()];
		}
		
		Map<Location, Integer> point2int = new HashMap<Location, Integer>();
		for (int i = 0; i < a.tourSize(); i++) {
			point2int.put(a.getPointAt(i), i);
		}
		
		//add edges between first point and his neighbors in both parents
		edges[0][point2int.get(a.getPointAt(1))] += 1;
		edges[0][point2int.get(a.getPointAt(a.tourSize() - 1))] += 1;
		edges[0][point2int.get(b.getPointAt(1))] += 1;
		edges[0][point2int.get(b.getPointAt(a.tourSize() - 1))] += 1;
		
		for (int i = 1; i < a.tourSize(); i++) {
			edges[i][point2int.get(a.getPointAt((i + 1) % a.tourSize()))] += 1; //add edge between points actual point and next point
			edges[i][point2int.get(a.getPointAt(i - 1))] += 1; //add edge between points actual point and previous point
			edges[i][point2int.get(b.getPointAt((i + 1) % a.tourSize()))] += 1; //same for the second parent
			edges[i][point2int.get(b.getPointAt(i - 1))] += 1;
		}
		return edges;		
	}

	private int getNextPoint(int[][] edges, int actualPoint, Set<Integer> added) {
		int next = 0;
		int shortest = 5;
		for (int i = 0; i < edges[0].length; i++) {
			if (edges[i][actualPoint] == 2) {
				return i;
			}
			if (edges[i][actualPoint] > 0) {
				if (shortest > tabNotZeroElements(edges[i])) {
					shortest = tabNotZeroElements(edges[i]);
					next = i;
				}
			}
		}
		while (added.contains(next)) {
			next = (int) (Math.random() * edges.length);
		}
		return next;
	}

	private int tabNotZeroElements(int[] tab) {
		int sum = 0;
		for (int i = 0; i < tab.length; i++) {
			if (tab[i] != 0) {
				sum++;
			}
		}
		return sum;
	}

	private void removePoint(int[][] edges, int actualPoint) {
		for (int i = 0; i < edges.length; i++) {
			edges[i][actualPoint] = 0;
			edges[actualPoint][i] = 0;
		}
	}
}

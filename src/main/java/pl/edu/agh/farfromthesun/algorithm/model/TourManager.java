package pl.edu.agh.farfromthesun.algorithm.model;

import java.util.ArrayList;
import java.util.List;

import pl.edu.agh.farfromthesun.map.Location;

public class TourManager {
    private static ArrayList<Location> points = new ArrayList<Location>();
    
	public static void initialize(List<Location> points) {
		TourManager.points = new ArrayList<Location>();
		for (Location i : points) {
			TourManager.points.add(i);
		}
	}
    
    public static Location getPoint(int i){
        return (Location)points.get(i);
    }

    public static int numberOfPoints(){
        return points.size();
    }
}

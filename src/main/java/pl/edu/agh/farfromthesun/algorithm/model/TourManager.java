package pl.edu.agh.farfromthesun.algorithm.model;

import java.util.ArrayList;
import java.util.List;

import pl.edu.agh.farfromthesun.map.Point;

public class TourManager {
    private static ArrayList<Point> points = new ArrayList<Point>();
    
	public static void initialize(List<Point> points) {
		TourManager.points = new ArrayList<Point>();
		for (Point i : points) {
			TourManager.points.add(i);
		}
	}
    
    public static Point getPoint(int i){
        return (Point)points.get(i);
    }

    public static int numberOfPoints(){
        return points.size();
    }
}

package main.java.pl.edu.agh.farfromthesun.algorithm;

import java.util.List;

import main.java.pl.edu.agh.farfromthesun.map.Point;

public interface AlgorithmObserver {
    public void handleResults(List<Point> locations);
}
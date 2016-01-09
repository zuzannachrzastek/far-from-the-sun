package pl.edu.agh.farfromthesun.map;

import org.openstreetmap.gui.jmapviewer.Coordinate;

import java.util.ArrayList;

/**
 * Created by M on 2015-12-01.
 */
public class LocationConverter {

    private ArrayList<Coordinate> coordinates = new ArrayList<Coordinate>();
    private ArrayList<Location> places = new ArrayList<Location>();

    public LocationConverter() {}

    public void setCoordinates(ArrayList<Coordinate> coordinates) {
        this.coordinates = coordinates;
    }

    public void setPlaces(ArrayList<Location> places) {
        this.places = places;
    }

    public ArrayList<Location> getPlaces() {
        makePlaces();
        return places;
    }

    public ArrayList<Coordinate> getCoordinates() {
        makeCoordinates();
        return coordinates;
    }

    /*
    Nie jest FoolProof!!!
    Jest problem z kolejnoscia, np. ktos chce zrobic dwa razy pod rzad makePlaces
     */


    private void makePlaces() {
        if (coordinates.isEmpty()) return;
        for (Coordinate c : coordinates) {
            Location p = new Location(c.getLat(), c.getLon());
            places.add(p);
        }
    }

    private void makeCoordinates() {
        if (places.isEmpty()) return;
        for (Location p : places) {
            Coordinate c = new Coordinate(p.getLat(), p.getLon());
            coordinates.add(c);
        }
    }



}
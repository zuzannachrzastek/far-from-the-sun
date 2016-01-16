package pl.edu.agh.farfromthesun.map;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import pl.edu.agh.farfromthesun.forecast.WeatherLocation;

import java.util.ArrayList;
import java.util.List;

public class LocationConverter {

    private List<Coordinate> coordinates = new ArrayList<>();
    private List<WeatherLocation> places = new ArrayList<>();

    public LocationConverter() {}

    public void setCoordinates(List<Coordinate> coordinates) {
        this.coordinates = coordinates;
        makePlaces();
    }

    public void setPlaces(List<WeatherLocation> places) {
        this.places = places;
        makeCoordinates();
    }

    public List<WeatherLocation> getPlaces() {
        return places;
    }

    public List<Coordinate> getCoordinates() {
        return coordinates;
    }

    private void makePlaces() {
        if (coordinates.isEmpty()) return;
        for (Coordinate c : coordinates) {
            WeatherLocation p = new WeatherLocation(c.getLat(), c.getLon());
            places.add(p);
        }
    }

    private void makeCoordinates() {
        if (places.isEmpty()) return;
        for (WeatherLocation p : places) {
            Coordinate c = new Coordinate(p.getLat(), p.getLon());
            coordinates.add(c);
        }
    }
}
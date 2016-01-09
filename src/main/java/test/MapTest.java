package test;

import junit.framework.Assert;
import org.openstreetmap.gui.jmapviewer.Coordinate;
import pl.edu.agh.farfromthesun.forecast.WeatherLocation;
import pl.edu.agh.farfromthesun.map.Location;
import pl.edu.agh.farfromthesun.map.LocationConverter;

import java.util.ArrayList;

public class MapTest {

    @org.junit.Test
    public void testMakeCoordinates() {
        Coordinate c1 = new Coordinate(10,10);
        Coordinate c2 = new Coordinate(15,10);
        Coordinate c3 = new Coordinate(15,20);
        WeatherLocation p1 = new WeatherLocation(c1.getLon(), c1.getLat());
        WeatherLocation p2 = new WeatherLocation(c2.getLat(), c2.getLon());
        WeatherLocation p3 = new WeatherLocation(c3.getLat(), c3.getLon());

        ArrayList<Coordinate> coordinates = new ArrayList<Coordinate>();
        ArrayList<WeatherLocation> places = new ArrayList<WeatherLocation>();
        ArrayList<Coordinate> coordinatesAuto = new ArrayList<Coordinate>();

        coordinates.add(c1);
        coordinates.add(c2);
        coordinates.add(c3);
        places.add(p1);
        places.add(p2);
        places.add(p3);

        LocationConverter placeConverter = new LocationConverter();
        placeConverter.setPlaces(places);
        coordinatesAuto = placeConverter.getCoordinates();

        org.junit.Assert.assertEquals(coordinates, coordinatesAuto);
    }
    @org.junit.Test
    public void testMakePlaces() {
        Coordinate c1 = new Coordinate(10,10);
        Coordinate c2 = new Coordinate(15,10);
        Coordinate c3 = new Coordinate(15,20);
        WeatherLocation p1 = new WeatherLocation(c1.getLat(), c1.getLon());
        WeatherLocation p2 = new WeatherLocation(c2.getLat(), c2.getLon());
        WeatherLocation p3 = new WeatherLocation(c3.getLat(), c3.getLon());

        ArrayList<Coordinate> coordinates = new ArrayList<Coordinate>();
        ArrayList<WeatherLocation> places = new ArrayList<WeatherLocation>();
        ArrayList<WeatherLocation> placesAuto = new ArrayList<WeatherLocation>();

        coordinates.add(c1);
        coordinates.add(c2);
        coordinates.add(c3);
        places.add(p1);
        places.add(p2);
        places.add(p3);

        LocationConverter placeConverter = new LocationConverter();
        placeConverter.setCoordinates(coordinates);
        placesAuto = placeConverter.getPlaces();

        Assert.assertEquals(places, placesAuto);
    }

}

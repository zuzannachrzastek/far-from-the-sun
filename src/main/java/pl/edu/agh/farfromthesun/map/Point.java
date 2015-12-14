package pl.edu.agh.farfromthesun.map;

public class Point {
    private double latitude; //szerokosc
    private double longitude; //dlugosc

    public Point(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
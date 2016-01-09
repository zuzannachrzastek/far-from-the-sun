package pl.edu.agh.farfromthesun.map;

import org.openstreetmap.gui.jmapviewer.Coordinate;

public class Location extends Coordinate implements Comparable<Location> {
	public Location(double lat, double lon) {
		super(lat, lon);
	}

	public double distanceTo(Location target) {
		return Math.sqrt((this.getLat() - target.getLat())
				* (this.getLat() - target.getLat())
				+ (this.getLon() - target.getLon())
				* (this.getLon() - target.getLon()));
	}

	@Override
	public String toString() {
		return this.getLat() + ", " + this.getLon();
	}

	@Override
	public int compareTo(Location p) {
		if (this.getLat() == p.getLat() && this.getLon() == p.getLon()) {
			return 0;
		}
		return 1;
	}
}
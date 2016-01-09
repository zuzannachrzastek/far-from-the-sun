package pl.edu.agh.farfromthesun.map;

public class Location implements Comparable<Location>{
	private double latitude; // szerokosc
	private double longitude; // dlugosc

	public Location(double latitude, double longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public double distanceTo(Location target) {
		return Math.sqrt((this.latitude - target.getLatitude()) * (this.latitude - target.getLatitude())
				+ (this.longitude - target.getLongitude()) * (this.longitude - target.getLongitude()));
	}

	@Override
	public String toString() {
		return this.getLatitude() + ", " + this.getLongitude();
	}

	@Override
	public int compareTo(Location p) {
		if(this.getLatitude() == p.getLatitude() && this.getLongitude() == p.getLongitude()){
			return 0;
		}
		return 1;
	}
}
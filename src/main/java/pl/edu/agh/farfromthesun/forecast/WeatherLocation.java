package pl.edu.agh.farfromthesun.forecast;

import java.time.LocalDate;

import pl.edu.agh.farfromthesun.map.Location;

public class WeatherLocation extends Location {
	private LocalDate date;
	private String iconUrl;
	private int highTemp;
	private int lowTemp;
	private PrecipitationTypeEnum precipitationType;
	private double precipitationLevel;
	private int aveWind;
	private int aveHumidity;

	public WeatherLocation(double latitude, double longitude) {
		super(latitude, longitude);
	}

	public WeatherLocation(Location point) {
		super(point.getLatitude(), point.getLongitude());
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getIconUrl() {
		return iconUrl;
	}

	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}

	public int getHighTemp() {
		return highTemp;
	}

	public void setHighTemp(int highTemp) {
		this.highTemp = highTemp;
	}

	public int getLowTemp() {
		return lowTemp;
	}

	public void setLowTemp(int lowTemp) {
		this.lowTemp = lowTemp;
	}

	public PrecipitationTypeEnum getPrecipitationType() {
		return precipitationType;
	}

	public void setPrecipitationType(PrecipitationTypeEnum precipitationType) {
		this.precipitationType = precipitationType;
	}

	public double getPrecipitationLevel() {
		return precipitationLevel;
	}

	public void setPrecipitationLevel(double precipitationLevel) {
		this.precipitationLevel = precipitationLevel;
	}

	public int getAveWind() {
		return aveWind;
	}

	public void setAveWind(int aveWind) {
		this.aveWind = aveWind;
	}

	public int getAveHumidity() {
		return aveHumidity;
	}

	public void setAveHumidity(int aveHumidity) {
		this.aveHumidity = aveHumidity;
	}
}

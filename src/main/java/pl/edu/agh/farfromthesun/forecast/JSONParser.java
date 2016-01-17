package pl.edu.agh.farfromthesun.forecast;

import org.json.JSONArray;
import org.json.JSONObject;
import pl.edu.agh.farfromthesun.map.Location;

import java.io.IOException;
import java.time.LocalDate;

public class JSONParser implements IWeatherParser{

    private static final int errorWeatherStringLength = 300;

    public WeatherLocation getForecast(LocalDate date, Location point, String forecastDataString) {

        WeatherLocation forecastToReturn = getEmptyForecastDataObject(date, point);
            if (forecastDataString == null || forecastDataString.length() < errorWeatherStringLength)
                return getEmptyForecastDataObject(date, point);

            //System.out.print(forecastDataString);
            final JSONObject obj = new JSONObject(forecastDataString);
            final JSONObject forecast = obj.getJSONObject("forecast");
            final JSONObject simpleFormat = forecast.getJSONObject("simpleforecast");
            final JSONArray forecastData = simpleFormat.getJSONArray("forecastday");
            final int n = forecastData.length();

            LocalDate currDate = LocalDate.now();
            for (int i = 0; i < n; ++i) {
                final JSONObject fData = forecastData.getJSONObject(i);
                final JSONObject day = fData.getJSONObject("date");
                if (day.getInt("day") == date.getDayOfMonth()
                        && day.getInt("month") == date.getMonthValue()
                        && day.getInt("year") == date.getYear()) {
                    forecastToReturn = createForecastData(fData, date, point);
                    ForecastCache.addToCache(forecastToReturn);
                    continue;
                }
                ForecastCache.addToCache(createForecastData(fData, LocalDate.of(currDate.getYear(), currDate.getMonthValue(), currDate.getDayOfMonth() + i), point));
            }
        return forecastToReturn;
    }

    private WeatherLocation createForecastData(JSONObject data, LocalDate date, Location point){
        WeatherLocation forecastDataObject = new WeatherLocation(point);
        forecastDataObject.setDate(date);
        forecastDataObject.setIconUrl(data.getString("icon_url"));
        final JSONObject highTemp = data.getJSONObject("high");
        forecastDataObject.setHighTemp(highTemp.getInt("celsius"));
        final JSONObject lowTemp = data.getJSONObject("low");
        forecastDataObject.setLowTemp(lowTemp.getInt("celsius"));
        final JSONObject rainData = data.getJSONObject("qpf_allday");
        double rain = rainData.getDouble("mm");
        final JSONObject snowData = data.getJSONObject("snow_allday");
        double snow = snowData.getDouble("cm");
        if(rain != 0.0){
            if(snow != 0.0){
                forecastDataObject.setPrecipitationType(PrecipitationTypeEnum.Sleet);
                forecastDataObject.setPrecipitationLevel(rain + snow * 10);
            }
            else{
                forecastDataObject.setPrecipitationType(PrecipitationTypeEnum.Rain);
                forecastDataObject.setPrecipitationLevel(rain);
            }
        }
        else{
            if(snow != 0.0){
                forecastDataObject.setPrecipitationType(PrecipitationTypeEnum.Snow);
                forecastDataObject.setPrecipitationLevel(snow * 10);
            }
            else{
                forecastDataObject.setPrecipitationType(PrecipitationTypeEnum.None);
                forecastDataObject.setPrecipitationLevel(0.0);
            }
        }
        final JSONObject windData = data.getJSONObject("avewind");
        forecastDataObject.setAveWind(windData.getInt("kph"));
        forecastDataObject.setAveHumidity(data.getInt("avehumidity"));
        return forecastDataObject;
    }
    public WeatherLocation getEmptyForecastDataObject(LocalDate date, Location point){
        WeatherLocation fd = new WeatherLocation(point);
        fd.setDate(date);
        return fd;
    }
}

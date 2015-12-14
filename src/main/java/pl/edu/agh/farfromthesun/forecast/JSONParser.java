package pl.edu.agh.farfromthesun.forecast;

import org.json.JSONArray;
import org.json.JSONObject;
import pl.edu.agh.farfromthesun.map.Point;

import java.io.IOException;
import java.time.LocalDate;


/**
 * Created by Renata Domagalska on 13.12.2015.
 */
public class JSONParser {

    public ForecastData GetForecastDataForSpecificDateAndPoint(LocalDate date, Point point){
        URLConnectionReader urlConnReader = new URLConnectionReader();
        try {
            String forecastDataString = urlConnReader.GetForecastData(GetStringCooridnates(point));
            System.out.print(forecastDataString);
            final JSONObject obj = new JSONObject(forecastDataString);
            final JSONObject forecast = obj.getJSONObject("forecast");
            final JSONObject simpleFormat = forecast.getJSONObject("simpleforecast");
            final JSONArray forecastData = simpleFormat.getJSONArray("forecastday");
            final int n = forecastData.length();

            for (int i = 0; i < n; ++i) {
                final JSONObject fData = forecastData.getJSONObject(i);
                final JSONObject day = fData.getJSONObject("date");
                if(day.getInt("day") == date.getDayOfMonth()
                        && day.getInt("month") == date.getMonthValue()
                        && day.getInt("year") == date.getYear()){
                    return GetForecastData(fData, date, point);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String GetStringCooridnates(Point point){
        return String.valueOf(point.getLatitude()) + "," + String.valueOf(point.getLongitude());
    }

    private ForecastData GetForecastData(JSONObject data, LocalDate date, Point point){
        ForecastData forecastDataObject = new ForecastData();
        forecastDataObject.setDate(date);
        forecastDataObject.setCoordinates(point);
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
}

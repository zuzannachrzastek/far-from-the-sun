package pl.edu.agh.farfromthesun.forecast;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

public class List extends JPanel {

    public void printWeather(JTextPane pane, WeatherLocation wl){
        if(wl.getIconUrl() == null){
            System.out.println("Forecast has not been downloaded - date out of range or bad coordinates");
            return;
        }
        //appendIcon(icon);
        appendText("Date: ", pane);
        appendText(wl.getDate().toString() + "\n", pane);
        appendText("Latitude: ", pane);
        appendText(String.valueOf(wl.getLat()) + "\n",pane);
        appendText("Longitude: ", pane);
        appendText(String.valueOf(wl.getLon()) + "\n", pane);
        appendText("Highest Temperature: ", pane);
        appendText(String.valueOf(wl.getHighTemp()), pane);
        appendText(" st. C \n", pane);
        appendText("Lowest Temperature: ", pane);
        appendText(String.valueOf(wl.getLowTemp()), pane);
        appendText(" st. C \n", pane);
        appendText("Precipitation Type: ", pane);
        appendText(wl.getPrecipitationType().toString() + "\n", pane);
        appendText("Precipitation Level: ", pane);
        appendText(String.valueOf(wl.getPrecipitationLevel()), pane);
        appendText(" mm \n", pane);
        appendText("Average Wind: ", pane);
        appendText(String.valueOf(wl.getAveWind()), pane);
        appendText(" km/h \n", pane);
        appendText("Average Humidity: ", pane);
        appendText(String.valueOf(wl.getAveHumidity()), pane);
        appendText(" % \n", pane);

    }

    public void appendText(String appended, JTextPane pane){
        Document doc = pane.getDocument();
        try{
            doc.insertString(doc.getLength(), appended, null);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }
}

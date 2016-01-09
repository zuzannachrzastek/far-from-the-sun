package pl.edu.agh.farfromthesun.forecast;

import pl.edu.agh.farfromthesun.algorithm.AlgorithmObserver;
import pl.edu.agh.farfromthesun.app.Component;
import pl.edu.agh.farfromthesun.map.Location;

import javax.swing.*;
import javax.swing.text.Style;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.util.ArrayList;

public class Forecast implements AlgorithmObserver, Component {
	
	private final WeatherDownloader wd = new WeatherDownloader();

	private JList jList;
	private final JTextPane text = new JTextPane();
	private JScrollPane pane1;
	private JScrollPane pane2;
	private JLabel label1;
	private JLabel label2;
	private Icon icon;
	private JLabel labelicon;
	private StyleContext context;
	private StyledDocument document;
	private Style labelStyle;

	@Override
	public void handleResults(ArrayList<WeatherLocation> locations) {
		//nie działa
		ArrayList<String> citiesList = new ArrayList<>();
		for(WeatherLocation wl : locations){
			citiesList.add(wl.toString());
		}
		jList = new JList(citiesList.toArray());

		List list = new List();
		jList.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				text.setText("");
				list.printWeather(text, locations.get(e.getID()));
			}
		});
	}

	@Override
	public void initialize(JFrame frame) {
        // TODO Auto-generated method stub

		label1 = new JLabel("List of Places");
		label2 = new JLabel("Weather Forecast");

		pane1 = new JScrollPane(jList);
		pane2 = new JScrollPane(text);

		pane1.setMaximumSize(new Dimension(Short.MAX_VALUE, Short.MAX_VALUE));
		pane1.setPreferredSize(new Dimension(200,200));
		pane1.setMinimumSize(new Dimension(200,200));

		pane2.setMaximumSize(new Dimension(Short.MAX_VALUE, Short.MAX_VALUE));
		pane2.setPreferredSize(new Dimension(100,250));
		pane2.setMinimumSize(new Dimension(100,250));

		//frame.setLayout(new BoxLayout(frame, BoxLayout.PAGE_AXIS));
		text.setEditable(false);

		frame.add(label1);
		frame.add(pane1);
		frame.add(Box.createRigidArea(new Dimension(0,5)));
		frame.add(label2);
		frame.add(pane2);
	}
	
	public WeatherLocation getForecast(LocalDate date, Location location){
		return wd.GetForecast(date, location);
	}

}

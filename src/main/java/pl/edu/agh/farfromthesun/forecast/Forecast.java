package pl.edu.agh.farfromthesun.forecast;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

import pl.edu.agh.farfromthesun.algorithm.AlgorithmObserver;
import pl.edu.agh.farfromthesun.app.Component;
import pl.edu.agh.farfromthesun.map.Location;

public class Forecast implements AlgorithmObserver, Component {
	
	private final WeatherDownloader wd = new WeatherDownloader();
	private ArrayList<String> singleCoord = new ArrayList<>();
	private JList listOfPlaces;
	private final JTextPane details = new JTextPane();
	private JScrollPane listPane;
	private JScrollPane detailsPane;
	private JLabel listLabel;
	private JLabel detailsLabel;
	private JPanel jpanel;
	private Pattern findCoords;
	private Matcher matcher;
	private static String COORDSPATTERN = "\\-?\\d{1,3}\\.\\d+";
	private DefaultListModel listModel = new DefaultListModel();
	private static int WIDTH = 270;
	private static int HEIGHT = 200;

	@Override
	public void handleResults(java.util.List<WeatherLocation> locations) {
		singleCoord.clear();
		for(WeatherLocation l : locations){ findCoordinatesInArray(l); }

		writeTwoCoordsByPlaceIntoArray();

		List list = new List();
		listOfPlaces.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				JList thelist = (JList)e.getSource();
				details.setText("");
				int index = thelist.locationToIndex(e.getPoint());
				if(index >= 0){
					list.printWeather(details, locations.get(index));
				}
			}
		});
	}

	@Override
	public void initialize(JFrame frame) {

		listLabel = new JLabel("List of Places");
		detailsLabel = new JLabel("Weather Forecast");

		listOfPlaces = new JList(listModel);
		listPane = new JScrollPane(listOfPlaces);
		detailsPane = new JScrollPane(details);

		setListSize();
		setDetailsSize();

		details.setEditable(false);

		jpanel = new JPanel();
		makeJPanel(frame);
	}

	private void findCoordinatesInArray(WeatherLocation l){
		findCoords = Pattern.compile(COORDSPATTERN);
		matcher = findCoords.matcher(l.toString());
		while (matcher.find()){
			singleCoord.add(matcher.group());
		}
	}

	private void writeTwoCoordsByPlaceIntoArray(){
		listModel.clear();
		for(int c = 0; c < singleCoord.size()-1; c = c+2){
			if(c % 2 == 0){
				listModel.addElement(singleCoord.get(c) + ", " + singleCoord.get(c+1));
			}
		}
	}

	private void setListSize(){
		listPane.setMaximumSize(new Dimension(Short.MAX_VALUE, Short.MAX_VALUE));
		listPane.setPreferredSize(new Dimension(WIDTH,HEIGHT));
		listPane.setMinimumSize(new Dimension(WIDTH,HEIGHT));
	}

	private void setDetailsSize(){
		detailsPane.setMaximumSize(new Dimension(Short.MAX_VALUE, Short.MAX_VALUE));
		detailsPane.setPreferredSize(new Dimension(WIDTH,HEIGHT));
		detailsPane.setMinimumSize(new Dimension(WIDTH,HEIGHT));
	}

	private void makeJPanel(JFrame frame){
		jpanel.setLayout(new BoxLayout(jpanel, BoxLayout.PAGE_AXIS));
		jpanel.add(Box.createHorizontalGlue());
		jpanel.add(listLabel);
		jpanel.add(listPane);
		jpanel.add(Box.createRigidArea(new Dimension(10, 0)));
		jpanel.add(detailsLabel);
		jpanel.add(detailsPane);
		frame.getContentPane().add(jpanel, BorderLayout.WEST);
	}

	public WeatherLocation getForecast(LocalDate date, Location location){
		return wd.getForecast(date, location);
	}
}

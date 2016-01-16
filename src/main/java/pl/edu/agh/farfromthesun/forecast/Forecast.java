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
	private ArrayList<String> places = new ArrayList<>();
	private JList jList;
	private final JTextPane text = new JTextPane();
	private JScrollPane pane1;
	private JScrollPane pane2;
	private JLabel label1;
	private JLabel label2;
	private JPanel jpanel;
	private Pattern findCoords;
	private Matcher matcher;
	private DefaultListModel listModel = new DefaultListModel();

	@Override
	public void handleResults(java.util.List<WeatherLocation> locations) {

		for(WeatherLocation l : locations){
			System.out.print(l.toString() + "\n\n");

			findCoords = Pattern.compile("\\d{1,3}\\.\\d+");
			matcher = findCoords.matcher(l.toString());
			while (matcher.find()){
				places.add(matcher.group());
			}
		}

		for(int c = 0; c < places.size()-1; c = c+2){
			if(c % 2 == 0){
				listModel.addElement(places.get(c) + ", " + places.get(c+1));
			}
		}

		List list = new List();
		jList.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				JList thelist = (JList)e.getSource();
				text.setText("");
				int index = thelist.locationToIndex(e.getPoint());
				if(index >= 0){
					list.printWeather(text, locations.get(index));
				}
			}
		});
	}

	@Override
	public void initialize(JFrame frame) {

		label1 = new JLabel("List of Places");
		label2 = new JLabel("Weather Forecast");

		jList = new JList(listModel);

		pane1 = new JScrollPane(jList);
		pane2 = new JScrollPane(text);

		pane1.setMaximumSize(new Dimension(Short.MAX_VALUE, Short.MAX_VALUE));
		pane1.setPreferredSize(new Dimension(270,200));
		pane1.setMinimumSize(new Dimension(270,200));

		pane2.setMaximumSize(new Dimension(Short.MAX_VALUE, Short.MAX_VALUE));
		pane2.setPreferredSize(new Dimension(270,250));
		pane2.setMinimumSize(new Dimension(270,250));

		text.setEditable(false);

		jpanel = new JPanel();

		jpanel.setLayout(new BoxLayout(jpanel, BoxLayout.PAGE_AXIS));
		jpanel.add(Box.createHorizontalGlue());
		jpanel.add(label1);
		jpanel.add(pane1);
		jpanel.add(Box.createRigidArea(new Dimension(10, 0)));
		jpanel.add(label2);
		jpanel.add(pane2);


		frame.getContentPane().add(jpanel, BorderLayout.WEST);
	}
	
	public WeatherLocation getForecast(LocalDate date, Location location){
		return wd.getForecast(date, location);
	}
}

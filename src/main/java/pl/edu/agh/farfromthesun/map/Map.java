package pl.edu.agh.farfromthesun.map;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.JMapViewerTree;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import org.openstreetmap.gui.jmapviewer.events.JMVCommandEvent;
import org.openstreetmap.gui.jmapviewer.interfaces.JMapViewerEventListener;

import pl.edu.agh.farfromthesun.algorithm.AlgorithmObserver;
import pl.edu.agh.farfromthesun.app.Component;
import pl.edu.agh.farfromthesun.forecast.WeatherLocation;


public class Map implements AlgorithmObserver, JMapViewerEventListener, Component {

	private List<Coordinate> coordinates = new ArrayList<>();
	private List<WeatherLocation> places = new ArrayList<>();
	private JMapViewer treeMap;
	private boolean listenerFlag = true;
	final Dimension MAP_DIMENSION = new Dimension(700,600);
	@Override
	public void initialize(JFrame frame) {

		treeMap = new JMapViewer();
		treeMap.setPreferredSize(MAP_DIMENSION);
		treeMap.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (listenerFlag) {
					Point p = e.getPoint();
					System.out.println(treeMap.getPosition(p));
					addMarker(p);
				}
			}});

		JButton btnDelete = new JButton("Delete");
		JButton btnReset = new JButton("Reset");
		JPanel container = new JPanel();

		btnDelete.addActionListener(e -> deleteLast());
		btnReset.addActionListener(e -> {
			listenerFlag = true;
			treeMap.removeAllMapMarkers();
			coordinates.clear();
			treeMap.removeAllMapPolygons();
			System.out.println("Removed all coordinates");
		});

		container.add(btnDelete);
		container.add(btnReset);

		frame.getContentPane().add(treeMap, BorderLayout.EAST);
		frame.getContentPane().add(container, BorderLayout.NORTH);

	}

	private void addMarker(Point p) {
		Coordinate marker = (Coordinate) treeMap.getPosition(p);
		treeMap.addMapMarker(new MapMarkerDot(marker));
		coordinates.add(marker);
	}

	//listener methods
	/*

	deleteLast to listener do btnDelete, przepiac do btnDelete jest zostanie przeniesiony

	 */
	public void deleteLast() {
		if (!listenerFlag) return;
		Coordinate c = coordinates.get(coordinates.size()-1);
		treeMap.removeAllMapMarkers();
		this.coordinates.remove(c);
		for (Coordinate cor : coordinates) {
			treeMap.addMapMarker(new MapMarkerDot(cor));
		}
	}

	private void drawRoute() {

		Coordinate one,two;
		List<Coordinate> route;

		if (coordinates.isEmpty()) return;
		for (int i = 0; i<coordinates.size()-1; i++ ) {
			one = coordinates.get(i);
			two = coordinates.get(i+1);
			route = new ArrayList<>(Arrays.asList(one, two, two));
			treeMap.addMapPolygon(new MyMapMarkerArrow(route));
		}
	}
	/*

	Tutaj znajduje sie czesc, ktora powinna byc wykonana natcyhmiast po kliknieciu przyisku START
	Wysyla do podsystemu Algorithm ArrayList<Loocation> places - wybranych przez uzytkownika lokacji
	Dodatkowo ustawia listenerFlag=false - nie mozna dodawac kolejnych punktow

	*/

	public List<WeatherLocation> sendPlaces() {
		listenerFlag = false;
		LocationConverter placeConverter = new LocationConverter();
		placeConverter.setCoordinates(coordinates);
		places = placeConverter.getPlaces();
		return places;
	}


	/*

	Tutaj znajduje sie czesc, ktora wykonuje sie po otrzymaniu przez Algorithm
	Konwersja Location na Coordinate, rysowanie trasy

	 */

	@Override
	public void handleResults(List<WeatherLocation> locations) {
		LocationConverter placeConverter = new LocationConverter();
		placeConverter.setPlaces(locations);
		coordinates = placeConverter.getCoordinates();
		drawRoute();
	}

	@Override
	public void processCommand(JMVCommandEvent command) {

	}
}
package pl.edu.agh.farfromthesun.map;


import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import org.openstreetmap.gui.jmapviewer.MapPolygonImpl;
import org.openstreetmap.gui.jmapviewer.events.JMVCommandEvent;
import org.openstreetmap.gui.jmapviewer.interfaces.JMapViewerEventListener;
import pl.edu.agh.farfromthesun.algorithm.AlgorithmObserver;
import pl.edu.agh.farfromthesun.app.*;
import pl.edu.agh.farfromthesun.app.Component;
import pl.edu.agh.farfromthesun.forecast.WeatherLocation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Map implements AlgorithmObserver, JMapViewerEventListener, Component {

	private ArrayList<Coordinate> coordinates = new ArrayList<>();
	private ArrayList<Location> places = new ArrayList<>();
	private JMapViewer treeMap;
	private boolean listenerFlag = true;

	@Override
	public void initialize(JFrame frame) {

		treeMap = new JMapViewer();
		treeMap.setPreferredSize(new Dimension(700,600));
		treeMap.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (listenerFlag) {
					Point p = e.getPoint();
					Coordinate marker = (Coordinate) treeMap.getPosition(p);
					System.out.println(treeMap.getPosition(p));
					treeMap.addMapMarker(new MapMarkerDot(marker));
					coordinates.add(marker);
				}
			}});

		JButton btnDelete = new JButton("Delete");
		JButton btnStart = new JButton("Start");
		JPanel container = new JPanel();
		btnDelete.addActionListener(e -> deleteLast());
		btnStart.addActionListener(e -> startListener());
		container.add(btnDelete);
		container.add(btnStart);
		frame.getContentPane().add(treeMap, BorderLayout.EAST);
		frame.getContentPane().add(container, BorderLayout.NORTH);

	}



	//listener methods


	/*

	deleteLast to listener do btnDelete, przepiac do btnDelete jest zostanie przeniesiony

	 */
	public void deleteLast() {
		if (listenerFlag == false) return;
		Coordinate c = coordinates.get(coordinates.size()-1);
		treeMap.removeAllMapMarkers();;
		this.coordinates.remove(c);
		for (Coordinate cor : coordinates) {
			treeMap.addMapMarker(new MapMarkerDot(cor));
		}
	}

	/*

	CHWILOWY listener, usunac razem z btnStart jesli bedzie gotowy ostateczny przycisk Start

	 */
		private void startListener() {
		listenerFlag = false;
		//drawRoute();
	}

	private void drawRoute() {

		Coordinate one,two;
		List<Coordinate> route;

		if (coordinates.isEmpty()) return;
		for (int i = 0; i<coordinates.size()-1; i++ ) {
			one = coordinates.get(i);
			two = coordinates.get(i+1);
			route = new ArrayList<Coordinate>(Arrays.asList(one, two, two));
			treeMap.addMapPolygon(new MapPolygonImpl(route));
		}

		one = coordinates.get(0);
		two = coordinates.get(coordinates.size()-1);
		route = new ArrayList<Coordinate>(Arrays.asList(one, two, two));
		treeMap.addMapPolygon(new MapPolygonImpl(route));

	}



	/*

	Tutaj znajduje sie czesc, ktora powinna byc wykonana natcyhmiast po kliknieciu przyisku START
	Wysyla do podsystemu Algorithm ArrayList<Loocation> places - wybranych przez uzytkownika lokacji
	Dodatkowo ustawia listenerFlag=false - nie mozna dodawac kolejnych punktow

	*/

	public ArrayList<Location> sendPlaces() {
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
	public void handleResults(ArrayList<WeatherLocation> locations) {
		LocationConverter placeConverter = new LocationConverter();
		placeConverter.setPlaces(places);
		coordinates = placeConverter.getCoordinates();
		drawRoute();
	}

	@Override
	public void processCommand(JMVCommandEvent command) {

	}
}
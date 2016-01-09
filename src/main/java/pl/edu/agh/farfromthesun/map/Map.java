package pl.edu.agh.farfromthesun.map;

import com.sun.xml.internal.bind.v2.TODO;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import org.openstreetmap.gui.jmapviewer.MapPolygonImpl;
import org.openstreetmap.gui.jmapviewer.interfaces.MapMarker;
import pl.edu.agh.farfromthesun.algorithm.AlgorithmObserver;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.*;


import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.events.JMVCommandEvent;
import org.openstreetmap.gui.jmapviewer.interfaces.JMapViewerEventListener;
import pl.edu.agh.farfromthesun.forecast.WeatherLocation;


public class Map extends Component implements AlgorithmObserver, JMapViewerEventListener {

	private ArrayList<Coordinate> coordinates = new ArrayList<>();
	private ArrayList<Location> places = new ArrayList<>();
	private JMapViewer treeMap;
	boolean listenerFlag = true;

	@Override
	public void initialize(JFrame frame) {

		treeMap = new JMapViewer();
		treeMap.setPreferredSize(new Dimension(700,600));
		treeMap.addMouseListener(new MouseAdapter() {
			@Override
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


	@Override
	public void processCommand(JMVCommandEvent jmvCommandEvent) {

	}

	//listener methods

	public void deleteLast() {
		if (listenerFlag == false) return;
		Coordinate c = coordinates.get(coordinates.size()-1);
		treeMap.removeAllMapMarkers();;
		this.coordinates.remove(c);
		for (Coordinate cor : coordinates) {
			treeMap.addMapMarker(new MapMarkerDot(cor));
		}
	}


	private void startListener() {
		listenerFlag = false;
		drawRoute();
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

	public List<Location> sendPlaces() {
		LocationConverter placeConverter = new LocationConverter();
		placeConverter.setCoordinates(coordinates);
		places = placeConverter.getPlaces();
		return places;
	}

	@Override
	public void handleResults(ArrayList<WeatherLocation> locations) {
		LocationConverter placeConverter = new LocationConverter();
		placeConverter.setPlaces(places);
		coordinates = placeConverter.getCoordinates();
		drawRoute();
	}
}
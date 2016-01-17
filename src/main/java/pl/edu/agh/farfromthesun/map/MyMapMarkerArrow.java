package pl.edu.agh.farfromthesun.map;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Path2D;
import java.util.List;
import java.awt.Graphics;

import org.openstreetmap.gui.jmapviewer.MapPolygonImpl;
import org.openstreetmap.gui.jmapviewer.interfaces.ICoordinate;

public class MyMapMarkerArrow extends MapPolygonImpl {

    public MyMapMarkerArrow(List<? extends ICoordinate> points) {
        super(null, null, points);
    }
    final int ARROW_LENGHT = 20;
    @Override
    public void paint(Graphics g, List<Point> points) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setColor(getColor());
        g2d.setStroke(getStroke());
        Path2D path = buildPath(points);
        g2d.draw(path);
        g2d.dispose();
    }

    private Path2D buildPath(List<Point> points) {
        Path2D path = new Path2D.Double();
        if (points != null && points.size() > 0) {
            Point firstPoint = points.get(0);
            path.moveTo(firstPoint.getX(), firstPoint.getY());
            for (Point p : points) {
                path.lineTo(p.getX(), p.getY());
            }

            int pointsSize = points.size() - 1;

            //pierwszy punkt nizej
            if (points.get(0).getY() > points.get(1).getY()) {
                // i po lewej
                if (points.get(0).getX() > points.get(1).getX()) {
                    downLeft(path,points,pointsSize);
                }
                // i po prawej
                else {
                    downRight(path,points,pointsSize);
                }
            //pierwszy punkt wyzej
            } else {
                // i po lewej
                if (points.get(0).getX() > points.get(1).getX()) {
                    upLeft(path,points,pointsSize);
                }
                else {
                    upRight(path,points,pointsSize);
                }
            }
        }
        return path;
    }
    //metody do rysowania strzalki
    private void downLeft(Path2D path, List<Point> points, int pointsSize){
        path.lineTo(points.get(pointsSize).getX(),
                points.get(pointsSize).getY() + ARROW_LENGHT);

        path.moveTo(points.get(pointsSize).getX(),
                points.get(pointsSize).getY());

        path.lineTo(points.get(pointsSize).getX() + ARROW_LENGHT,
                points.get(pointsSize).getY());
    }
    private void downRight(Path2D path, List<Point> points, int pointsSize){
        path.lineTo(points.get(pointsSize).getX(),
                points.get(pointsSize).getY() + ARROW_LENGHT);

        path.moveTo(points.get(pointsSize).getX(),
                points.get(pointsSize).getY());

        path.lineTo(points.get(pointsSize).getX() - ARROW_LENGHT,
                points.get(pointsSize).getY());
    }
    private void upLeft(Path2D path, List<Point> points, int pointsSize){
        path.lineTo(points.get(pointsSize).getX(),
                points.get(pointsSize).getY() - ARROW_LENGHT);

        path.moveTo(points.get(pointsSize).getX(),
                points.get(pointsSize).getY());

        path.lineTo(points.get(pointsSize).getX() + ARROW_LENGHT,
                points.get(pointsSize).getY());
    }
    private void upRight(Path2D path, List<Point> points, int pointsSize){
        path.lineTo(points.get(pointsSize).getX(),
                points.get(pointsSize).getY() - ARROW_LENGHT);

        path.moveTo(points.get(pointsSize).getX(),
                points.get(pointsSize).getY());

        path.lineTo(points.get(pointsSize).getX() - ARROW_LENGHT,
                points.get(pointsSize).getY());
    }
}
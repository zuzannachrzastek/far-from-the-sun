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
    final double ANGLE_CONST = (1/Math.sqrt(2));

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
            double x = points.get(pointsSize).getX()-points.get(0).getX();
            double y = points.get(pointsSize).getY()-points.get(0).getY();


            double length = (20/Math.sqrt((Math.pow(x, 2)+ Math.pow(y, 2))));

            double x1 = (x*ANGLE_CONST + y*ANGLE_CONST)*length;
            double y1 = (-x*ANGLE_CONST + y*ANGLE_CONST)*length;

            double x2 = ((x*ANGLE_CONST) + (y*(-ANGLE_CONST)))*length;
            double y2 = ((x*ANGLE_CONST) + (y*ANGLE_CONST))*length;

            path.lineTo(points.get(pointsSize).getX()- x1,
                    points.get(pointsSize).getY() - y1);

            path.moveTo(points.get(pointsSize).getX(),
                    points.get(pointsSize).getY());

            path.lineTo(points.get(pointsSize).getX() - x2,
                    points.get(pointsSize).getY() - y2);

        }
        return path;
    }

}
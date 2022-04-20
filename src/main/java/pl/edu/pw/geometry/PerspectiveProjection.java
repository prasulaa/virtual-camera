package pl.edu.pw.geometry;

import java.util.ArrayList;
import java.util.List;

public class PerspectiveProjection {

    public List<Triangle<Point2D>> projection(List<Triangle<Point3D>> triangles, double d) {
        List<Triangle<Point2D>> resultTriangles = new ArrayList<>();

        for (Triangle<Point3D> triangle: triangles) {
            resultTriangles.add(projection(triangle, d));
        }

        return resultTriangles;
    }

    public Triangle<Point2D> projection(Triangle<Point3D> triangle, double d) {
        List<Point2D> resultPoints = new ArrayList<>();

        for (Point3D point: triangle.getPoints()) {
            resultPoints.add(projection(point, d));
        }

        return new Triangle<>(resultPoints);
    }

    public Point2D projection(Point3D point, double d) {
        double zp = point.getZ();

        if (zp == 0) {
            throw new IllegalArgumentException("z cannot be 0");
        } else {
            double x = point.getX() * d / zp;
            double y = point.getY() * d / zp;

            return new Point2D(x, y);
        }
    }

}

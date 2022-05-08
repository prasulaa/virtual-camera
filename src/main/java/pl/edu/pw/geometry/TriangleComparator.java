package pl.edu.pw.geometry;

import java.util.Comparator;

public class TriangleComparator implements Comparator<Triangle<Point3D>> {

    private final double err = 1e-10;

    @Override
    public int compare(Triangle<Point3D> t1, Triangle<Point3D> t2) {
        Surface surfaceT1 = new Surface(t1);
        Surface surfaceT2 = new Surface(t2);
        Point3D centerPointT1 = centerPoint(t1);
        Point3D centerPointT2 = centerPoint(t2);
        Point3D observerPoint = new Point3D(0, 0, 0);
        double resultS2P1 = roundToZeroIfSmallerThanError(multipleSurfaceAndPoint(surfaceT2, centerPointT1) * multipleSurfaceAndPoint(surfaceT2, observerPoint));
        double resultS1P2 = roundToZeroIfSmallerThanError(multipleSurfaceAndPoint(surfaceT1, centerPointT2) * multipleSurfaceAndPoint(surfaceT1, observerPoint));
        double result = resultS1P2 * resultS2P1;

        if (result < 0) {
            return resultS1P2 < 0 ? -1 : 1;
        } else if (result > 0) {
            return 0;
        } else {
            return 0;
        }
    }

    private double roundToZeroIfSmallerThanError(double x) {
        if (x < err && x > -1*err) {
            return 0.0;
        } else {
            return x;
        }
    }

    private double multipleSurfaceAndPoint(Surface surface, Point3D point) {
        return surface.getA()*point.getX() + surface.getB()*point.getY() + surface.getC()*point.getZ() + surface.getD();
    }

    private Point3D centerPoint(Triangle<Point3D> triangle) {
        double x = triangle.getPoints().stream().map(Point3D::getX).reduce(0d, Double::sum) / 3;
        double y = triangle.getPoints().stream().map(Point3D::getY).reduce(0d, Double::sum) / 3;
        double z = triangle.getPoints().stream().map(Point3D::getZ).reduce(0d, Double::sum) / 3;

        return new Point3D(x, y, z);
    }

}

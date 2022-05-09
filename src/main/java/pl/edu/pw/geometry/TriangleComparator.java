package pl.edu.pw.geometry;

import java.util.Comparator;

public class TriangleComparator implements Comparator<Triangle<Point3D>> {

    private final double err = 0.0;

    @Override
    public int compare(Triangle<Point3D> t1, Triangle<Point3D> t2) {
        int comparedT1T2 = compareTriangles(t1, t2);
        int comparedT2T1 = compareTriangles(t2, t1);

        if (comparedT1T2 == 1 && comparedT2T1 == -1) {
            return 1;
        } else if (comparedT1T2 == 1 && comparedT2T1 == 0) {
            return 1;
        } else if (comparedT1T2 == 1 && comparedT2T1 == 1) {
            return 0;
        } else if (comparedT1T2 == 0 && comparedT2T1 == -1) {
            return 1;
        } else if (comparedT1T2 == 0 && comparedT2T1 == 0) {
            return 0;
        } else if (comparedT1T2 == 0 && comparedT2T1 == 1) {
            return -1;
        } else if (comparedT1T2 == -1 && comparedT2T1 == -1) {
            return 0;
        } else if (comparedT1T2 == -1 && comparedT2T1 == 0) {
            return -1;
        } else if (comparedT1T2 == -1 && comparedT2T1 == 1) {
            return -1;
        } else {
            return 0;
        }
    }

    private int compareTriangles(Triangle<Point3D> t1, Triangle<Point3D> t2) {
        Surface surfaceT2 = new Surface(t2);
        Point3D centerPointT1 = centerPoint(t1);
        Point3D observerPoint = new Point3D(0, 0, 0);
        double resultS2P1 = roundToZeroIfSmallerThanError(multipleSurfaceAndPoint(surfaceT2, centerPointT1) * multipleSurfaceAndPoint(surfaceT2, observerPoint));

        if (resultS2P1 > 0) {
            return -1;
        } else if (resultS2P1 < 0){
            return 1;
        } else {
            return 0;
        }

//        if (result < 0) {
//            return resultS1P2 < 0 ? -1 : 1;
//        } else if (result > 0) {
//            return 0;
//        } else {
//            return 0;
//        }
    }

    private double roundToZeroIfSmallerThanError(double x) {
        if (x < err && x > -1 * err) {
            return 0.0;
        } else {
            return x;
        }
    }

    private double multipleSurfaceAndPoint(Surface surface, Point3D point) {
        return surface.getA() * point.getX() + surface.getB() * point.getY() + surface.getC() * point.getZ() + surface.getD();
    }

    private Point3D centerPoint(Triangle<Point3D> triangle) {
        double x = triangle.getPoints().stream().map(Point3D::getX).reduce(0d, Double::sum) / 3;
        double y = triangle.getPoints().stream().map(Point3D::getY).reduce(0d, Double::sum) / 3;
        double z = triangle.getPoints().stream().map(Point3D::getZ).reduce(0d, Double::sum) / 3;

        return new Point3D(x, y, z);
    }

}

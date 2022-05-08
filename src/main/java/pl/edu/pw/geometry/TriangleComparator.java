package pl.edu.pw.geometry;

import java.util.Comparator;
import java.util.List;

public class TriangleComparator implements Comparator<Triangle<Point3D>> {

    private final double err = 1e-5;

    @Override
    public int compare(Triangle<Point3D> t1, Triangle<Point3D> t2) {
        int compared = compareTriangles(t1, t2);

        if (compared == 0) {
            return compareTriangles(t2, t1);
        } else {
            return compared;
        }
    }

    private int compareTriangles(Triangle<Point3D> t1, Triangle<Point3D> t2) {
        Surface surfaceT1 = surface(t1);
        List<Boolean> ifZ0Closer = new ArrayList<>();

        if (surfaceT1.isXeq0() || surfaceT1.isYeq0()) {
            return -1;
        }

        for (Point3D pointT2: t2.getPoints()) {
            double t0 = t0(surfaceT1, pointT2);
            double z = pointT2.getZ();
            double z0 = z + surfaceT1.getC()*t0;

            if (z > z0 + err) {
                ifZ0Closer.add(true);
            } else if (z < z0 - err) {
                ifZ0Closer.add(false);
            }
        }

        if (and(ifZ0Closer)) {
            return -1;
        } else if (!or(ifZ0Closer)) {
            return 1;
        } else {
            return 0;
        }
    }

    private boolean and(List<Boolean> booleans) {
        boolean result = true;
        for (boolean b : booleans) {
            result = result && b;
        }
        return result;
    }

    private boolean or(List<Boolean> booleans) {
        boolean result = false;
        for (boolean b : booleans) {
            result = result || b;
        }
        return result;
    }

    private double t0(Surface surface, Point3D point) {
        double A = surface.getA();
        double B = surface.getB();
        double C = surface.getC();
        double D = surface.getD();

        return -1 * (A * point.getX() + B * point.getY() + C * point.getZ() + D) / (A * A + B * B
                                                                                        + C * C);
    }

    private Surface surface(Triangle<Point3D> t1) {
        List<Point3D> points = t1.getPoints();
        Point3D p1 = points.get(0);
        Point3D p2 = points.get(1);
        Point3D p3 = points.get(2);

        Vector3D p1p2 = new Vector3D(p1, p2);
        Vector3D p1p3 = new Vector3D(p1, p3);
        Vector3D product = p1p2.product(p1p3);

        double A = product.getX();
        double B = product.getY();
        double C = product.getZ();
        double D = -1 * (A * p1.getX() + B * p1.getY() + C * p1.getZ());

        return new Surface(A, B, C, D);
    }

}

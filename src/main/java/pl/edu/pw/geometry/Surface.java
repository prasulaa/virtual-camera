package pl.edu.pw.geometry;

import java.util.List;

public class Surface {

    private double A;
    private double B;
    private double C;
    private double D;

    public Surface() {
    }

    public Surface(double a, double b, double c, double d) {
        A = a;
        B = b;
        C = c;
        D = d;
    }

    public Surface(Triangle<Point3D> triangle) {
        List<Point3D> points = triangle.getPoints();
        Point3D p1 = points.get(0);
        Point3D p2 = points.get(1);
        Point3D p3 = points.get(2);

        Vector3D p1p2 = new Vector3D(p1, p2);
        Vector3D p1p3 = new Vector3D(p1, p3);
        Vector3D product = p1p2.product(p1p3);

        A = product.getX();
        B = product.getY();
        C = product.getZ();
        D = -1 * (A * p1.getX() + B * p1.getY() + C * p1.getZ());
    }

    public double getA() {
        return A;
    }

    public void setA(double a) {
        A = a;
    }

    public double getB() {
        return B;
    }

    public void setB(double b) {
        B = b;
    }

    public double getC() {
        return C;
    }

    public void setC(double c) {
        C = c;
    }

    public double getD() {
        return D;
    }

    public void setD(double d) {
        D = d;
    }
}

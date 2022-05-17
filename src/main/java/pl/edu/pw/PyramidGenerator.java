package pl.edu.pw;

import pl.edu.pw.geometry.Point3D;

public class PyramidGenerator {

    private static void generate(Point3D A, Point3D B, Point3D C, Point3D D, int step) {


        if (++step > 2) {
            print(A, B, C, D);
            return;
        }

        Point3D AB = middlePoint(A, B);
        Point3D AC = middlePoint(A, C);
        Point3D AD = middlePoint(A, D);
        Point3D BC = middlePoint(B, C);
        Point3D BD = middlePoint(B, D);
        Point3D CD = middlePoint(C, D);

        generate(A, AB, AC, AD, step);
        generate(AB, B, BC, BD, step);
        generate(AC, BC, C, CD, step);
        generate(AD, BD, CD, D, step);
    }

    private static void print(Point3D a, Point3D b, Point3D c, Point3D d) {
        System.out.println(a + " " + b + " " + c);
        System.out.println(a + " " + b + " " + d);
        System.out.println(a + " " + c + " " + d);
        System.out.println(b + " " + c + " " + d);
    }

    private static Point3D middlePoint(Point3D A, Point3D B) {
        return new Point3D(
                (A.getX() + B.getX()) / 2,
                (A.getY() + B.getY()) / 2,
                (A.getZ() + B.getZ()) / 2
        );
    }

    public static void main(String[] args) {
        double dx = 0.0;
        double dy = 0.0;
        double dz = 50.0;
        double ax = 1.0;
        double ay = 1.0;
        double az = 1.0;

        Point3D A = new Point3D(ax * -30.0 + dx, ay * 0.0 + dy, az * -17.3205 + dz);
        Point3D B = new Point3D(ax * 30.0 + dx, ay * 0.0 + dy, az * -17.3205 + dz);
        Point3D C = new Point3D(ax * 0.0 + dx, ay * 0.0 + dy, az * 34.641 + dz);
        Point3D D = new Point3D(ax * 0.0 + dx, ay * 48.9898 + dy, az * 0.0 + dz);

        generate(A, B, C, D, 1);
    }

}

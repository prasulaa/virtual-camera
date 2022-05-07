package pl.edu.pw.geometry;

public class Vector3D {

    private double x;
    private double y;
    private double z;

    public Vector3D() {
    }

    public Vector3D(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3D(Point3D p1, Point3D p2) {
        x = p2.getX() - p1.getX();
        y = p2.getY() - p1.getY();
        z = p2.getZ() - p1.getZ();
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public Vector3D product(Vector3D vector) {
        double xv = vector.getX();
        double yv = vector.getY();
        double zv = vector.getZ();

        return new Vector3D(
                y*zv - z*yv,
                z*xv - x*zv,
                x*yv - y*xv
        );
    }

}

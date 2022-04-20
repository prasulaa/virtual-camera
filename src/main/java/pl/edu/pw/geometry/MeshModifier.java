package pl.edu.pw.geometry;

import java.util.List;

public class MeshModifier {

    public enum Axis {
        X, Y, Z
    }

    private final List<Mesh> meshes;

    public MeshModifier(List<Mesh> meshes) {
        this.meshes = meshes;
    }

    public void addValue(double value, Axis axis) {
        for (Mesh mesh : meshes) {
            for (Triangle<Point3D> triangle : mesh.getTriangles()) {
                for (Point3D point : triangle.getPoints()) {
                    addValue(point, value, axis);
                }
            }
        }
    }

    public void addValue(Point3D point, double value, Axis axis) {
        switch (axis) {
            case X -> addToX(point, value);
            case Y -> addToY(point, value);
            case Z -> addToZ(point, value);
            default -> throw new IllegalArgumentException("Wrong axis");
        }
    }

    public void addToX(Point3D point, double value) {
        point.setX(point.getX() + value);
    }

    public void addToY(Point3D point, double value) {
        point.setY(point.getY() + value);
    }

    public void addToZ(Point3D point, double value) {
        point.setZ(point.getZ() + value);
    }

    public void rotate(double alpha, Axis axis) {
        for (Mesh mesh : meshes) {
            for (Triangle<Point3D> triangle : mesh.getTriangles()) {
                for (Point3D point : triangle.getPoints()) {
                    rotate(point, alpha, axis);
                }
            }
        }
    }

    public void rotate(Point3D point, double alpha, Axis axis) {
        switch (axis) {
            case X -> rotateAroundAxisX(point, alpha);
            case Y -> rotateAroundAxisY(point, alpha);
            case Z -> rotateAroundAxisZ(point, alpha);
            default -> throw new IllegalArgumentException("Wrong axis");
        }
    }

    public void rotateAroundAxisX(Point3D point, double alpha) {
        double y = point.getY();
        double z = point.getZ();

        point.setY(y * Math.cos(alpha) - z * Math.sin(alpha));
        point.setZ(y * Math.sin(alpha) + z * Math.cos(alpha));
    }

    public void rotateAroundAxisY(Point3D point, double alpha) {
        double x = point.getX();
        double z = point.getZ();

        point.setX(z * Math.sin(alpha) + x * Math.cos(alpha));
        point.setZ(z * Math.cos(alpha) - x * Math.sin(alpha));
    }

    public void rotateAroundAxisZ(Point3D point, double alpha) {
        double x = point.getX();
        double y = point.getY();

        point.setX(x * Math.cos(alpha) - y * Math.sin(alpha));
        point.setY(x * Math.sin(alpha) + y * Math.cos(alpha));
    }

}
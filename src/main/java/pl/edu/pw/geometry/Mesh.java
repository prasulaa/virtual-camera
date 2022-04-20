package pl.edu.pw.geometry;

import java.util.ArrayList;
import java.util.List;

public class Mesh {

    public static List<Mesh> meshes = new ArrayList<>();

    private List<Triangle<Point3D>> triangles;

    public Mesh() {
    }

    public Mesh(List<Triangle<Point3D>> triangles) {
        this.triangles = triangles;
    }

    public List<Triangle<Point3D>> getTriangles() {
        return triangles;
    }

    public void setTriangles(List<Triangle<Point3D>> triangles) {
        this.triangles = triangles;
    }

}

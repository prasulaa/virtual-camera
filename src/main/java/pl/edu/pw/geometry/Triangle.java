package pl.edu.pw.geometry;

import java.util.ArrayList;
import java.util.List;

public class Triangle<K extends Point> {

    private List<K> points;

    public Triangle() {
    }

    public Triangle(K point1, K point2, K point3) {
        points = new ArrayList<>();
        points.add(point1);
        points.add(point2);
        points.add(point3);
    }

    public Triangle(List<K> points) {
        this.points = points;
    }
}

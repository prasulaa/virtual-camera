package pl.edu.pw.controller;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.util.Pair;
import pl.edu.pw.geometry.Mesh;
import pl.edu.pw.geometry.MeshModifier;
import pl.edu.pw.geometry.MeshModifier.Axis;
import pl.edu.pw.geometry.PerspectiveProjection;
import pl.edu.pw.geometry.Point2D;
import pl.edu.pw.geometry.Point3D;
import pl.edu.pw.geometry.Triangle;
import pl.edu.pw.geometry.TriangleComparator;

public class Controller {

    private final static double PLANCK_LENGTH = 0.25;
    private final static double PLANCK_ANGLE = 0.03;
    private final static double PLANCK_SCOPE = 5.0;

    @FXML
    private Canvas canvas;

    private List<Mesh> meshes;
    private MeshModifier meshModifier;
    private PerspectiveProjection perspectiveProjection;
    private double d;

    @FXML
    private void initialize() {
        meshes = Mesh.meshes;
        meshModifier = new MeshModifier(meshes);
        perspectiveProjection = new PerspectiveProjection();
        d = 1500;

        canvas.setFocusTraversable(true);
        repaintCanvas();
    }

    @FXML
    private void keyPressed(KeyEvent e) {
        boolean haveMatched = true;
        switch (e.getCode()) {
            case W -> meshModifier.addValue(-PLANCK_LENGTH, Axis.Z);
            case S -> meshModifier.addValue(PLANCK_LENGTH, Axis.Z);
            case A -> meshModifier.addValue(PLANCK_LENGTH, Axis.X);
            case D -> meshModifier.addValue(-PLANCK_LENGTH, Axis.X);
            case Q -> meshModifier.addValue(-PLANCK_LENGTH, Axis.Y);
            case E -> meshModifier.addValue(PLANCK_LENGTH, Axis.Y);
            case NUMPAD4 -> meshModifier.rotate(PLANCK_ANGLE, Axis.Y);
            case NUMPAD6 -> meshModifier.rotate(-PLANCK_ANGLE, Axis.Y);
            case NUMPAD8 -> meshModifier.rotate(PLANCK_ANGLE, Axis.X);
            case NUMPAD5 -> meshModifier.rotate(-PLANCK_ANGLE, Axis.X);
            case NUMPAD7 -> meshModifier.rotate(PLANCK_ANGLE, Axis.Z);
            case NUMPAD9 -> meshModifier.rotate(-PLANCK_ANGLE, Axis.Z);
            case Z -> zoom(-PLANCK_SCOPE);
            case X -> zoom(PLANCK_SCOPE);
            default -> haveMatched = false;
        }
        if (haveMatched) {
            repaintCanvas();
        }
    }

    private void zoom(double value) {
        if (d + value > 0) {
            d += value;
        }
    }

    private void repaintCanvas() {
        clearCanvas();

        List<Triangle<Point3D>> triangles = getAllTriangles();
        triangles.sort(new TriangleComparator().reversed());
        List<Triangle<Point2D>> projectedTriangles = perspectiveProjection.projection(triangles, d);

        for (Triangle<Point2D> triangle : projectedTriangles) {
            drawTriangle(triangle, canvas.getGraphicsContext2D());
        }
    }

    private List<Triangle<Point3D>> getAllTriangles() {
        return meshes.stream()
            .flatMap(mesh -> mesh.getTriangles().stream())
            .collect(Collectors.toList());
    }

    private void clearCanvas() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    private void drawTriangle(Triangle<Point2D> triangle, GraphicsContext gc) {
        Pair<double[], double[]> convertedPoints = convertTrianglePointsToArrays(triangle);
        double[] x = convertedPoints.getKey();
        double[] y = convertedPoints.getValue();
        int n = x.length == y.length ? x.length : 3;

        gc.setFill(Color.PINK);
        gc.fillPolygon(x, y, n);
        gc.setStroke(Color.BLACK);
        gc.strokePolygon(x, y, n);
    }

    private Pair<double[], double[]> convertTrianglePointsToArrays(Triangle<Point2D> triangle) {
        List<Point2D> points = triangle.getPoints();
        int n = points.size();
        double[] x = new double[n];
        double[] y = new double[n];

        int i = 0;
        for (Point2D point : points) {
            Point2D screenPoint = screenPoint(point);
            x[i] = screenPoint.getX();
            y[i] = screenPoint.getY();
            i++;
        }

        return new Pair<>(x, y);
    }

    private Point2D screenPoint(Point2D point) {
        double x = canvas.getWidth() / 2 + point.getX();
        double y = canvas.getHeight() / 2 - point.getY();

        return new Point2D(x, y);
    }

}

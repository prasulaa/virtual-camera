package pl.edu.pw.reading;

import pl.edu.pw.geometry.Mesh;
import pl.edu.pw.geometry.Point3D;
import pl.edu.pw.geometry.Triangle;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class MeshReader {

    public Mesh readFile(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        scanner.useLocale(Locale.US);

        try {
            Mesh mesh = readMesh(scanner);
            scanner.close();
            return mesh;
        } catch (NoSuchElementException e) {
            scanner.close();
            throw new IllegalArgumentException("Wrong file format");
        }
    }

    private Mesh readMesh(Scanner scanner) {
        List<Triangle<Point3D>> triangles = new ArrayList<>();

        while (scanner.hasNextDouble()) {
            Triangle<Point3D> triangle = readTriangle(scanner);
            triangles.add(triangle);
        }

        return new Mesh(triangles);
    }

    private Triangle<Point3D> readTriangle(Scanner scanner) {
        Point3D point1 = readPoint(scanner);
        Point3D point2 = readPoint(scanner);
        Point3D point3 = readPoint(scanner);

        return new Triangle<>(point1, point2, point3);
    }

    private Point3D readPoint(Scanner scanner) {
        double x = scanner.nextDouble();
        double y = scanner.nextDouble();
        double z = scanner.nextDouble();

        return new Point3D(x, y, z);
    }
}

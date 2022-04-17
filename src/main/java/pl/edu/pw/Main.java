package pl.edu.pw;

import pl.edu.pw.geometry.Mesh;
import pl.edu.pw.geometry.Triangle;
import pl.edu.pw.reading.MeshReader;

import java.io.File;
import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        Mesh tetrahedron = MeshReader.readFile(new File("src/main/resources/meshes/tetrahedron.txt"));
    }

}

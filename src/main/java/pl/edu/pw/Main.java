package pl.edu.pw;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import pl.edu.pw.geometry.Mesh;
import pl.edu.pw.geometry.Triangle;
import pl.edu.pw.reading.MeshReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main extends Application {

    public static void main(String[] args) throws FileNotFoundException {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        Pane mainPane = FXMLLoader.load(getClass().getResource("/fxml/mainPane.fxml"));
        Scene scene = new Scene(mainPane);
        stage.setScene(scene);
        stage.setTitle("Virtual Camera");
        stage.show();
    }

}

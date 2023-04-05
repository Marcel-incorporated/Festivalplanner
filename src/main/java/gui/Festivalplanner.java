package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class Festivalplanner extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        File file = new File("src/main/resources/planning.txt");
        file.delete();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXML/Festivalplanner.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 600);
        stage.setTitle("Festivalplanner");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}

package controllers;

import classes.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.shape.SVGPath;
import javafx.scene.image.*;
import javafx.stage.*;

import java.awt.event.MouseEvent;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class FestivalplannerController {

    @FXML
    private Button addEventButton;
    @FXML
    private TabPane tabPane;

    // Schedule/main tab controller
    @FXML
    void onAddEditEventButton(ActionEvent event) throws IOException {
        tabPane.getSelectionModel().select(1);

    }

    // File editor/generator controller


    private Desktop desktop = Desktop.getDesktop();
    @FXML
    private Button exportButton;
    @FXML
    private Button importButton;
    @FXML
    private TextField artistNameTextfield;
    @FXML
    private TextField genreTextfield;
    @FXML
    private SVGPath firstStar;
    @FXML
    private SVGPath secondStar;
    @FXML
    private SVGPath thirdStar;
    @FXML
    private SVGPath fourthStar;
    @FXML
    private SVGPath fifthStar;
    private int popularity;

    @FXML
    void onExportButton(ActionEvent event) {
        System.out.println("exporting");

//        Song song = new Song();
        ArrayList<Song> songs = new ArrayList<>();
//        Visitor visitor = new Visitor();
        ArrayList<Visitor> visitors = new ArrayList<>();
        Artist artist = new Artist(artistNameTextfield.getText(), popularity, songs, 0);
        Performance performance = new Performance(artist, "", "", "");
        ArrayList<Performance> performances = new ArrayList<>();
        Festival festival = new Festival(visitors,"","","", performances, "");
        try {
            Serializer.Serialize(festival);
        } catch(IOException e) {

        }
    }

    @FXML
    void onImportButton(ActionEvent event) {
//        System.out.println("importing");

        final FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Pick a file");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text file", "*.txt"));
//        System.out.println("filechooser created");

//        System.out.println("handle method started");
        List<File> list =
                fileChooser.showOpenMultipleDialog(((Node) event.getTarget()).getScene().getWindow());
//        System.out.println("opened filechooser");
        if (list != null) {
            for (File file : list) {
                try {
                    desktop.open(file);
                } catch (IOException ex) {
                    Logger.getLogger(
                            getClass().getName()).log(
                            Level.SEVERE, null, ex
                    );
                }
            }
        }
    }

    @FXML
    public void onFirstStarClicked(javafx.scene.input.MouseEvent event) {
        popularity = 1;

        firstStar.setStyle("-fx-fill: yellow");
        secondStar.setStyle("-fx-fill: white");
        thirdStar.setStyle("-fx-fill: white");
        fourthStar.setStyle("-fx-fill: white");
        fifthStar.setStyle("-fx-fill: white");
    }

    @FXML
    public void onSecondStarClicked(javafx.scene.input.MouseEvent event) {
        popularity = 2;

        firstStar.setStyle("-fx-fill: yellow");
        secondStar.setStyle("-fx-fill: yellow");
        thirdStar.setStyle("-fx-fill: white");
        fourthStar.setStyle("-fx-fill: white");
        fifthStar.setStyle("-fx-fill: white");
    }

    @FXML
    public void onThirdStarClicked(javafx.scene.input.MouseEvent event) {
        popularity = 3;

        firstStar.setStyle("-fx-fill: yellow");
        secondStar.setStyle("-fx-fill: yellow");
        thirdStar.setStyle("-fx-fill: yellow");
        fourthStar.setStyle("-fx-fill: white");
        fifthStar.setStyle("-fx-fill: white");
    }

    @FXML
    public void onFourthStarClicked(javafx.scene.input.MouseEvent event) {
        popularity = 4;

        firstStar.setStyle("-fx-fill: yellow");
        secondStar.setStyle("-fx-fill: yellow");
        thirdStar.setStyle("-fx-fill: yellow");
        fourthStar.setStyle("-fx-fill: yellow");
        fifthStar.setStyle("-fx-fill: white");
    }

    @FXML
    public void onFifthStarClicked(javafx.scene.input.MouseEvent event) {
        popularity = 5;

        firstStar.setStyle("-fx-fill: yellow");
        secondStar.setStyle("-fx-fill: yellow");
        thirdStar.setStyle("-fx-fill: yellow");
        fourthStar.setStyle("-fx-fill: yellow");
        fifthStar.setStyle("-fx-fill: yellow");
    }
}




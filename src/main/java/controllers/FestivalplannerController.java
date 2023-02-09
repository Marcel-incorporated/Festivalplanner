package controllers;

import classes.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.SVGPath;
import javafx.scene.image.*;
import javafx.stage.*;

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
    public Button addArtistButton;
    @FXML
    public TextField amountOfVisitorsTextfield;
    @FXML
    public Button confirmVisitorsButton;
    @FXML
    public TextField festivalNameTextfield;
    @FXML
    public Button confirmFestivalNameButton;
    @FXML
    public TextField startingTimeTextfield;
    @FXML
    public TextField artistNameTextfield1;
    @FXML
    public TextField setDurationTextfield;
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
    private int popularity = 0;
    private boolean popularitySelected = false;
    private int amountOfArtistsAdded = 0;
    @FXML
    private Label artistLabel1;
    @FXML
    private Label artistLabel2;
    @FXML
    private Label artistLabel3;
    @FXML
    private Label artistLabel4;
    @FXML
    private Label artistLabel5;
    @FXML
    private Label artistLabel6;
    @FXML
    private Label artistLabel7;
    @FXML
    private Label artistLabel8;
    @FXML
    private Label artistLabel9;
    @FXML
    private Label artistLabel10;
    @FXML
    private Label artistLabel11;
    @FXML
    private Label artistLabel12;
    @FXML
    private Label artistLabel13;
    @FXML
    private Label artistLabel14;
    @FXML
    private Label artistLabel15;
    @FXML
    private Label artistLabel16;

    private int visitorCount;
    private String festivalName;

    private List<Artist> artists = new ArrayList<>();

    @FXML
    void onExportButton(ActionEvent event) {
        System.out.println("exporting");

//        Song song = new Song();
        ArrayList<Song> songs = new ArrayList<>();
        ArrayList<Visitor> visitors = new ArrayList<>();
        for (int i = 0; i < visitorCount; i++) {
            visitors.add(new Visitor());
        }

        Artist artist = new Artist(artistNameTextfield.getText(), genreTextfield.getText(), popularity, startingTimeTextfield.getText(), Integer.parseInt(setDurationTextfield.getText()));

        Performance performance = new Performance(artist, startingTimeTextfield.getText(), setDurationTextfield.getText(), "");

        ArrayList<Performance> performances = new ArrayList<>();
        performances.add(performance);

        Festival festival = new Festival(visitors.size(), festivalName, performances);

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

    public void noStarsClicked() {
        popularity = 0;

        firstStar.setStyle("-fx-fill: white");
        secondStar.setStyle("-fx-fill: white");
        thirdStar.setStyle("-fx-fill: white");
        fourthStar.setStyle("-fx-fill: white");
        fifthStar.setStyle("-fx-fill: white");
    }

    @FXML
    public void onFirstStarClicked(javafx.scene.input.MouseEvent event) {
        popularity = 1;
        popularitySelected = true;

        firstStar.setStyle("-fx-fill: yellow");
        secondStar.setStyle("-fx-fill: white");
        thirdStar.setStyle("-fx-fill: white");
        fourthStar.setStyle("-fx-fill: white");
        fifthStar.setStyle("-fx-fill: white");
    }

    @FXML
    public void onSecondStarClicked(javafx.scene.input.MouseEvent event) {
        popularity = 2;
        popularitySelected = true;

        firstStar.setStyle("-fx-fill: yellow");
        secondStar.setStyle("-fx-fill: yellow");
        thirdStar.setStyle("-fx-fill: white");
        fourthStar.setStyle("-fx-fill: white");
        fifthStar.setStyle("-fx-fill: white");
    }

    @FXML
    public void onThirdStarClicked(javafx.scene.input.MouseEvent event) {
        popularity = 3;
        popularitySelected = true;

        firstStar.setStyle("-fx-fill: yellow");
        secondStar.setStyle("-fx-fill: yellow");
        thirdStar.setStyle("-fx-fill: yellow");
        fourthStar.setStyle("-fx-fill: white");
        fifthStar.setStyle("-fx-fill: white");
    }

    @FXML
    public void onFourthStarClicked(javafx.scene.input.MouseEvent event) {
        popularity = 4;
        popularitySelected = true;

        firstStar.setStyle("-fx-fill: yellow");
        secondStar.setStyle("-fx-fill: yellow");
        thirdStar.setStyle("-fx-fill: yellow");
        fourthStar.setStyle("-fx-fill: yellow");
        fifthStar.setStyle("-fx-fill: white");
    }

    @FXML
    public void onFifthStarClicked(javafx.scene.input.MouseEvent event) {
        popularity = 5;
        popularitySelected = true;

        firstStar.setStyle("-fx-fill: yellow");
        secondStar.setStyle("-fx-fill: yellow");
        thirdStar.setStyle("-fx-fill: yellow");
        fourthStar.setStyle("-fx-fill: yellow");
        fifthStar.setStyle("-fx-fill: yellow");
    }

    private void addArtistToList(String name, String genre, int popularity) {
        if (amountOfArtistsAdded <= 16) {
            artists.add(new Artist(name, genre, popularity, startingTimeTextfield.getText(), Integer.parseInt(setDurationTextfield.getText())));

            switch (amountOfArtistsAdded) {
                case 1 -> {
                    artistLabel1.setOpacity(1);
                    artistLabel1.setText(name);
                }
                case 2 -> {
                    artistLabel2.setOpacity(1);
                    artistLabel2.setText(name);
                }
                case 3 -> {
                    artistLabel3.setOpacity(1);
                    artistLabel3.setText(name);
                }
                case 4 -> {
                    artistLabel4.setOpacity(1);
                    artistLabel4.setText(name);
                }
                case 5 -> {
                    artistLabel5.setOpacity(1);
                    artistLabel5.setText(name);
                }
                case 6 -> {
                    artistLabel6.setOpacity(1);
                    artistLabel6.setText(name);
                }
                case 7 -> {
                    artistLabel7.setOpacity(1);
                    artistLabel7.setText(name);
                }
                case 8 -> {
                    artistLabel8.setOpacity(1);
                    artistLabel8.setText(name);
                }
                case 9 -> {
                    artistLabel9.setOpacity(1);
                    artistLabel9.setText(name);
                }
                case 10 -> {
                    artistLabel10.setOpacity(1);
                    artistLabel10.setText(name);
                }
                case 11 -> {
                    artistLabel11.setOpacity(1);
                    artistLabel11.setText(name);
                }
                case 12 -> {
                    artistLabel12.setOpacity(1);
                    artistLabel12.setText(name);
                }
                case 13 -> {
                    artistLabel13.setOpacity(1);
                    artistLabel13.setText(name);
                }
                case 14 -> {
                    artistLabel14.setOpacity(1);
                    artistLabel14.setText(name);
                }
                case 15 -> {
                    artistLabel15.setOpacity(1);
                    artistLabel15.setText(name);
                }
                case 16 -> {
                    artistLabel16.setOpacity(1);
                    artistLabel16.setText(name);
                }
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error");
            alert.setContentText("Maximum amount of artists reached!");

            alert.showAndWait();
        }

    }

    @FXML
    public void onAddArtistButton(ActionEvent actionEvent) {
        if (artistNameTextfield.getText().isEmpty() || genreTextfield.getText().isEmpty() || popularity == 0 ||
                setDurationTextfield.getText().isEmpty() || startingTimeTextfield.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error");
            alert.setContentText("Make sure to fill all fields!");

            noStarsClicked();

            alert.showAndWait();

            return;
        }

        amountOfArtistsAdded++;
        addArtistToList(artistNameTextfield.getText(), genreTextfield.getText(), popularity);
        artistNameTextfield.clear();
        genreTextfield.clear();
        startingTimeTextfield.clear();
        setDurationTextfield.clear();
        noStarsClicked();

    }

    @FXML
    public void onHoverOverFirstStar(MouseEvent event) {
        if (!popularitySelected) {
            firstStar.setStyle("-fx-fill: yellow");
            secondStar.setStyle("-fx-fill: white");
            thirdStar.setStyle("-fx-fill: white");
            fourthStar.setStyle("-fx-fill: white");
            fifthStar.setStyle("-fx-fill: white");
        }
    }

    @FXML
    public void onHoverOverSecondStar(MouseEvent event) {
        if (!popularitySelected) {
            firstStar.setStyle("-fx-fill: yellow");
            secondStar.setStyle("-fx-fill: yellow");
            thirdStar.setStyle("-fx-fill: white");
            fourthStar.setStyle("-fx-fill: white");
            fifthStar.setStyle("-fx-fill: white");
        }
    }

    @FXML
    public void onHoverOverThirdStar(MouseEvent event) {
        if (!popularitySelected) {
            firstStar.setStyle("-fx-fill: yellow");
            secondStar.setStyle("-fx-fill: yellow");
            thirdStar.setStyle("-fx-fill: yellow");
            fourthStar.setStyle("-fx-fill: white");
            fifthStar.setStyle("-fx-fill: white");
        }
    }

    @FXML
    public void onHoverOverFourthStar(MouseEvent event) {
        if (!popularitySelected) {
            firstStar.setStyle("-fx-fill: yellow");
            secondStar.setStyle("-fx-fill: yellow");
            thirdStar.setStyle("-fx-fill: yellow");
            fourthStar.setStyle("-fx-fill: yellow");
            fifthStar.setStyle("-fx-fill: white");
        }
    }

    @FXML
    public void onHoverOverFifthStar(MouseEvent event) {
        if (!popularitySelected) {
            firstStar.setStyle("-fx-fill: yellow");
            secondStar.setStyle("-fx-fill: yellow");
            thirdStar.setStyle("-fx-fill: yellow");
            fourthStar.setStyle("-fx-fill: yellow");
            fifthStar.setStyle("-fx-fill: yellow");
        }
    }

    @FXML
    public void onMouseNotOnStars(MouseEvent event) {
        if (!popularitySelected) {
            firstStar.setStyle("-fx-fill: white");
            secondStar.setStyle("-fx-fill: white");
            thirdStar.setStyle("-fx-fill: white");
            fourthStar.setStyle("-fx-fill: white");
            fifthStar.setStyle("-fx-fill: white");
        }

    }

    @FXML
    public void onConfirmVisitorsButton(ActionEvent actionEvent) {
        visitorCount = Integer.parseInt(amountOfVisitorsTextfield.getText());
    }

    @FXML
    public void onConfirmFestivalNameButton(ActionEvent actionEvent) {
        festivalName = confirmFestivalNameButton.getText();
    }
}
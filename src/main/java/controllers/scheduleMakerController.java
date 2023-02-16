package controllers;

import classes.*;

import javafx.collections.ObservableList;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.shape.SVGPath;

import java.io.IOException;
import java.util.ArrayList;

public class scheduleMakerController {
    @FXML
    public Button saveFestival;
    @FXML
    public Canvas mapCanvas;
    @FXML
    public Canvas mapCanvasMaker;
    @FXML
    public Button addArtistButton;
    @FXML
    public TextField amountOfVisitorsTextfield;
    @FXML
    public TextField festivalNameTextfield;
    @FXML
    public TextField startingTimeTextfield;
    @FXML
    public TextField setDurationTextfield;
    @FXML
    private TextField podiumNameTextfield;
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
    @FXML
    private ListView artistsListView;

    @FXML
    private ChoiceBox<String> stagePickerChoicebox;

    //Actual attributes to save data

    private int popularity = 0;
    private boolean popularitySelected = false;
    private int amountOfArtistsAdded = 0;
    private int visitorCount;
    private String festivalName;
    private ArrayList<Artist> artists = new ArrayList<>();
    private ArrayList<Visitor> visitors = new ArrayList<>();
    private ArrayList<Song> songs = new ArrayList<>();
    private String selectedStage;




    //SCHEDULE MAKER
    @FXML
    public void initialize() {
        stagePickerChoicebox.getItems().addAll("Main stage", "Stage 2", "Stage 3", "Stage 4");
        stagePickerChoicebox.setValue("Main stage");
    }

    @FXML
    void onExportButton() {
        Festival festival = new Festival(visitors, festivalName, artists);      //create festival object with all saved information from user
        System.out.println(artists);

        try {                                               //try serializing all data into .txt file, showing error when unsuccessfull
            Serializer.Serialize(festival);
            notificationPrompt(false, "Successfully exported festival file :)");
        } catch (IOException e) {
            notificationPrompt(true, "Unable to import festival file :(");
        }
    }

    @FXML
    void onImportButton() {
        try {                                           //try importing file, showing error when unsuccessfull
            Serializer.DeserializeFestival();
            notificationPrompt(false, "Successfully import festival file :)");
        } catch (Exception e) {
            notificationPrompt(true, "Unable to import festival file :(");
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
    public void onFirstStarClicked() {
        popularity = 1;
        popularitySelected = true;

        firstStar.setStyle("-fx-fill: yellow");
        secondStar.setStyle("-fx-fill: white");
        thirdStar.setStyle("-fx-fill: white");
        fourthStar.setStyle("-fx-fill: white");
        fifthStar.setStyle("-fx-fill: white");
    }

    @FXML
    public void onSecondStarClicked() {
        popularity = 2;
        popularitySelected = true;

        firstStar.setStyle("-fx-fill: yellow");
        secondStar.setStyle("-fx-fill: yellow");
        thirdStar.setStyle("-fx-fill: white");
        fourthStar.setStyle("-fx-fill: white");
        fifthStar.setStyle("-fx-fill: white");
    }

    @FXML
    public void onThirdStarClicked() {
        popularity = 3;
        popularitySelected = true;

        firstStar.setStyle("-fx-fill: yellow");
        secondStar.setStyle("-fx-fill: yellow");
        thirdStar.setStyle("-fx-fill: yellow");
        fourthStar.setStyle("-fx-fill: white");
        fifthStar.setStyle("-fx-fill: white");
    }

    @FXML
    public void onFourthStarClicked() {
        popularity = 4;
        popularitySelected = true;

        firstStar.setStyle("-fx-fill: yellow");
        secondStar.setStyle("-fx-fill: yellow");
        thirdStar.setStyle("-fx-fill: yellow");
        fourthStar.setStyle("-fx-fill: yellow");
        fifthStar.setStyle("-fx-fill: white");
    }

    @FXML
    public void onFifthStarClicked() {
        popularity = 5;
        popularitySelected = true;

        firstStar.setStyle("-fx-fill: yellow");
        secondStar.setStyle("-fx-fill: yellow");
        thirdStar.setStyle("-fx-fill: yellow");
        fourthStar.setStyle("-fx-fill: yellow");
        fifthStar.setStyle("-fx-fill: yellow");
    }

    private void addArtistToList(String name, String genre, int popularity, String startingTime, String duration, String podiumName) {
        if (amountOfArtistsAdded <= 16) {
            artists.add(new Artist(name, genre, popularity, startingTime, Integer.parseInt(duration), podiumNameTextfield.getText()));
        } else {
            notificationPrompt(true, "Maximum amount of artists reached!");
        }
    }

    @FXML
    public void onAddArtistButton() {
        if (artistNameTextfield.getText().isEmpty() || genreTextfield.getText().isEmpty() || popularity == 0 ||
                setDurationTextfield.getText().isEmpty() || startingTimeTextfield.getText().isEmpty() || setDurationTextfield.getText().matches("[a-zA-Z]+")) {
            notificationPrompt(true, "Make sure to fill out all fields!");
            return;
        }

        amountOfArtistsAdded++;
        addArtistToList(artistNameTextfield.getText(), genreTextfield.getText(), popularity, startingTimeTextfield.getText(), setDurationTextfield.getText(), stagePickerChoicebox.getSelectionModel().getSelectedItem());


        for (Artist a : artists) {
            if (!artistsListView.getItems().contains(a.getName())) {
                artistsListView.getItems().add(a.getName());
            }
        }
        artistNameTextfield.clear();
        genreTextfield.clear();
        startingTimeTextfield.clear();
        setDurationTextfield.clear();
        podiumNameTextfield.clear();
        noStarsClicked();
    }

    @FXML
    public void onSaveFestivalButton() {
        if (amountOfVisitorsTextfield.getText().isEmpty() || festivalNameTextfield.getText().isEmpty()) {
            notificationPrompt(false, "Make sure to fill in all fields!");
            return;
        }
        try {
            visitorCount = Integer.parseInt(amountOfVisitorsTextfield.getText());
            if(visitorCount > 20) {
                notificationPrompt(false, "Can't add more than 20 visitors!");
                return;
            }
        } catch (Exception e) {
            notificationPrompt(true, "Value in box Visitor Count is supposed to be a number!");
            return;
        }
        for (int i = 0; i < visitorCount; i++) {
            visitors.add(new Visitor());            //create visitors based on user input and adds them to arraylist
        }
        festivalName = festivalNameTextfield.getText();
        notificationPrompt(false, "Successfully saved festival information :)");
    }

    @FXML
    public void onHoverOverFirstStar() {
        if (!popularitySelected) {
            firstStar.setStyle("-fx-fill: yellow");
            secondStar.setStyle("-fx-fill: white");
            thirdStar.setStyle("-fx-fill: white");
            fourthStar.setStyle("-fx-fill: white");
            fifthStar.setStyle("-fx-fill: white");
        }
    }

    @FXML
    public void onHoverOverSecondStar() {
        if (!popularitySelected) {
            firstStar.setStyle("-fx-fill: yellow");
            secondStar.setStyle("-fx-fill: yellow");
            thirdStar.setStyle("-fx-fill: white");
            fourthStar.setStyle("-fx-fill: white");
            fifthStar.setStyle("-fx-fill: white");
        }
    }

    @FXML
    public void onHoverOverThirdStar() {
        if (!popularitySelected) {
            firstStar.setStyle("-fx-fill: yellow");
            secondStar.setStyle("-fx-fill: yellow");
            thirdStar.setStyle("-fx-fill: yellow");
            fourthStar.setStyle("-fx-fill: white");
            fifthStar.setStyle("-fx-fill: white");
        }
    }

    @FXML
    public void onHoverOverFourthStar() {
        if (!popularitySelected) {
            firstStar.setStyle("-fx-fill: yellow");
            secondStar.setStyle("-fx-fill: yellow");
            thirdStar.setStyle("-fx-fill: yellow");
            fourthStar.setStyle("-fx-fill: yellow");
            fifthStar.setStyle("-fx-fill: white");
        }
    }

    @FXML
    public void onHoverOverFifthStar() {
        if (!popularitySelected) {
            firstStar.setStyle("-fx-fill: yellow");
            secondStar.setStyle("-fx-fill: yellow");
            thirdStar.setStyle("-fx-fill: yellow");
            fourthStar.setStyle("-fx-fill: yellow");
            fifthStar.setStyle("-fx-fill: yellow");
        }
    }

    @FXML
    public void onMouseNotOnStars() {
        if (!popularitySelected) {
            firstStar.setStyle("-fx-fill: white");
            secondStar.setStyle("-fx-fill: white");
            thirdStar.setStyle("-fx-fill: white");
            fourthStar.setStyle("-fx-fill: white");
            fifthStar.setStyle("-fx-fill: white");
        }
    }

    //OTHER
    public void notificationPrompt(boolean error, String message) {
        if (error) {
            Alert alert1 = new Alert(Alert.AlertType.ERROR, message);
            alert1.setTitle("Error");
            alert1.setHeaderText("Error");
            alert1.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, message);
            alert.setTitle("Information");
            alert.setHeaderText("Information");
            alert.showAndWait();
        }
    }
}

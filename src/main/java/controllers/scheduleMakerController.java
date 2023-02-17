package controllers;


import classes.Artist;
import classes.Festival;
import classes.Song;
import classes.Visitor;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.shape.SVGPath;
import org.jfree.fx.FXGraphics2D;

import java.io.IOException;
import java.util.ArrayList;

public class scheduleMakerController {

    @FXML
    public Button saveFestival;
    @FXML
    public Button addArtistButton;
    @FXML
    public TextField amountOfVisitorsTextfield;
    @FXML
    public TextField festivalNameTextfield;
    public ChoiceBox<String> startTimeChoicebox;
    public ChoiceBox<String> durationChoicebox;
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
    
    private ListView<String> artistsListView;

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
        startTimeChoicebox.getItems().addAll("10:00", "10:30", "11:00", "11:30", "12:00", "12:30", "13:00", "13:30", "14:00",
                "14:30", "15:00", "15:30", "16:00", "16:30", "17:00", "17:30", "18:00", "18:30", "19:00", "19:30", "20:00", "20:30",
                "21:00", "21:30", "22:00", "22:30", "23:00", "23:30", "00:00", "00:30", "01:00", "01:30", "02:00", "02:30");
        startTimeChoicebox.setValue("10:00");

        durationChoicebox.getItems().addAll("30 minutes", "60 minutes", "90 minutes", "120 minutes");
        durationChoicebox.setValue("30 minutes");


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
            artists.add(new Artist(name, genre, popularity, startingTime, Integer.parseInt(duration.substring(0, 2)), podiumName));
        } else {
            notificationPrompt(true, "Maximum amount of artists reached!");
        }
    }

    @FXML
    public void onAddArtistButton() {
        if (artistNameTextfield.getText().isEmpty() || genreTextfield.getText().isEmpty() || popularity == 0) {
            notificationPrompt(true, "Make sure to fill out all fields!");
            return;
        }

        amountOfArtistsAdded++;

        addArtistToList(artistNameTextfield.getText(), genreTextfield.getText(), popularity, startTimeChoicebox.getSelectionModel().getSelectedItem(),
                durationChoicebox.getSelectionModel().getSelectedItem(), stagePickerChoicebox.getSelectionModel().getSelectedItem());


        for (Artist a : artists) {
            if (!artistsListView.getItems().contains(a.getName())) {
                artistsListView.getItems().add(a.getName());
            }
        }
        artistNameTextfield.clear();
        genreTextfield.clear();
        startTimeChoicebox.setValue("10:00");
        durationChoicebox.setValue("30 minutes");
        stagePickerChoicebox.setValue("Main stage");
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

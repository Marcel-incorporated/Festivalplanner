package controllers;

import classes.Artist;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.shape.SVGPath;

import static controllers.ArtistArrayListController.artists;

public class EditArtistDialogController {
    //FXML attributen
    @FXML
    private TextField artistNameTextfield;
    @FXML
    private ChoiceBox<String> durationChoicebox;
    @FXML
    private SVGPath fifthStar;
    @FXML
    private SVGPath firstStar;
    @FXML
    private SVGPath fourthStar;
    @FXML
    private TextField genreTextfield;
    @FXML
    private SVGPath secondStar;
    @FXML
    private ChoiceBox<String> stagePickerChoicebox;
    @FXML
    private ChoiceBox<String> startTimeChoicebox;
    @FXML
    private SVGPath thirdStar;

    //Klasse attributen
    private Artist artist;
    private int popularity = 0;
    private boolean popularitySelected = false;

    @FXML
    void initialize() {
        startTimeChoicebox.getItems().addAll("13:00", "13:30", "14:00",
                "14:30", "15:00", "15:30", "16:00", "16:30", "17:00", "17:30", "18:00", "18:30", "19:00", "19:30", "20:00", "20:30",
                "21:00", "21:30", "22:00", "22:30", "23:00", "23:30", "00:00", "00:30", "01:00", "01:30", "02:00", "02:30");
        startTimeChoicebox.setValue("13:00");

        durationChoicebox.getItems().addAll("30 minutes", "60 minutes", "90 minutes", "120 minutes");
        durationChoicebox.setValue("30 minutes");


        stagePickerChoicebox.getItems().addAll("Main stage", "Stage 2", "Stage 3", "Stage 4");
        stagePickerChoicebox.setValue("Main stage");
    }

    void editArtist(Artist artist) {
        int duration;
        this.artist = artist;

//        artist.setGenre(genreTextfield.getText());
//        artist.setName(artistNameTextfield.getText());
//        artist.setPopularity(popularity);
//        artist.setPodium(stagePickerChoicebox.getSelectionModel().getSelectedItem());
        if (durationChoicebox.getSelectionModel().getSelectedItem().equals("120 minutes")) {
            duration = Integer.parseInt(durationChoicebox.getSelectionModel().getSelectedItem().substring(0, 3));
        } else {
            duration = Integer.parseInt(durationChoicebox.getSelectionModel().getSelectedItem().substring(0, 2));
        }
//        artist.setSetStartingTime(startTimeChoicebox.getSelectionModel().getSelectedItem());
        int artistPos = artists.indexOf(artist);
        artists.remove(artist);
        artists.add(artistPos, new Artist(artistNameTextfield.getText(), genreTextfield.getText(), popularity,
                startTimeChoicebox.getSelectionModel().getSelectedItem(),
                duration, stagePickerChoicebox.getSelectionModel().getSelectedItem()));
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

}

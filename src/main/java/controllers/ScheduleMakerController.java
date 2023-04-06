package controllers;


import classes.Artist;
import classes.Festival;
import classes.Song;
import classes.Visitor;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringBinding;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.shape.SVGPath;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

import static controllers.ArtistArrayListController.artists;
import static controllers.NotificationPromptController.notification;

public class ScheduleMakerController {
    //FXML attributen
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
    public ListView<Artist> artistsListView = new ListView<>();
    @FXML
    private ChoiceBox<String> stagePickerChoicebox;

    //Klasse attributen
    private int popularity = 0;
    private boolean popularitySelected = false;
    private int amountOfArtistsAdded = 0;
    public static int visitorCount;
    private String festivalName;
    private ArrayList<Visitor> visitors = new ArrayList<>();
    private ArrayList<Song> songs = new ArrayList<>();
    private String selectedStage;
    private ObservableList<Artist> artistsObservableList = artistsListView.getItems();

    @FXML

    public void initialize() {
        artistsListView.setItems(artistsObservableList);
        startTimeChoicebox.getItems().addAll("13:00", "13:30", "14:00",
                "14:30", "15:00", "15:30", "16:00", "16:30", "17:00", "17:30", "18:00", "18:30", "19:00", "19:30", "20:00", "20:30",
                "21:00", "21:30", "22:00", "22:30", "23:00", "23:30", "00:00", "00:30", "01:00", "01:30", "02:00", "02:30");
        startTimeChoicebox.setValue("13:00");

        durationChoicebox.getItems().addAll("30 minutes", "60 minutes", "90 minutes", "120 minutes");
        durationChoicebox.setValue("30 minutes");


        stagePickerChoicebox.getItems().addAll("Main stage", "Stage 2", "Stage 3", "Stage 4");
        stagePickerChoicebox.setValue("Main stage");

        //Code voor bewerken en verwijderen artiesten
        artistsListView.setCellFactory(lv -> {
            ListCell<Artist> cell = new ListCell<>();

            ContextMenu contextMenu = new ContextMenu();

            MenuItem editItem = new MenuItem();
            editItem.textProperty().bind(Bindings.format("Edit \"%s\"", cell.itemProperty()));
            editItem.setOnAction(event -> {
                Artist item = cell.getItem();
                //code om item te bewerken
                try {
                    openArtistEditDialog(item);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });

            MenuItem deleteItem = new MenuItem();
            deleteItem.textProperty().bind(Bindings.format("Delete \"%s\"", cell.itemProperty()));
            deleteItem.setOnAction(event -> deleteArtist(cell.getItem(), cell));


            contextMenu.getItems().addAll(editItem, deleteItem);

            cell.emptyProperty().addListener((obs, wasEmpty, isNowEmpty) -> {
                if (isNowEmpty) {
                    cell.setContextMenu(null);
                } else {
                    cell.setContextMenu(contextMenu);
                }
            });

            cell.textProperty().bind(new StringBinding() {
                {
                    bind(cell.itemProperty());
                }

                @Override
                protected String computeValue() {
                    Artist artist = cell.getItem();
                    return artist == null ? "" : artist.toString();
                }
            });

            return cell;
        });

    }

    private void deleteArtist(Artist artist, Cell<Artist> cell) {
        artistsObservableList.remove(cell.getItem());
        artists.remove(artist);
    }

    private void openArtistEditDialog(Artist item) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/fxml/editArtistDialog.fxml"));
        DialogPane editArtistDialogPane = fxmlLoader.load();

        EditArtistDialogController editArtistDialogController = fxmlLoader.getController();

        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setDialogPane(editArtistDialogPane);
        dialog.setTitle("Edit artist");

        Optional<ButtonType> clickedButton = dialog.showAndWait();
        if (clickedButton.get() == ButtonType.OK) {
            editArtistDialogController.editArtist(item);
            artistsObservableList.remove(item);
            onRefreshListButton();
        }
    }

    @FXML
    public void onRefreshListButton() {
        artistsObservableList.clear();
        for (Artist a : artists) {
            if (!artistsObservableList.contains(a)) {
                artistsObservableList.add(a);
            }
        }
    }

    @FXML
    void onExportButton() {
        Festival festival = new Festival(visitors, festivalName, artists);      //create festival object with all saved information from user

        try {                                               //try serializing all data into .txt file, showing error when unsuccessfull
            Serializer.Serialize(festival);
            notification(false, "Successfully exported festival file :)");
        } catch (IOException e) {
            notification(true, "Unable to import festival file :(");
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
            if (duration.equals("120 minutes")) {
                artists.add(new Artist(name, genre, popularity, startingTime, Integer.parseInt(duration.substring(0, 3)), podiumName));
            } else {
                artists.add(new Artist(name, genre, popularity, startingTime, Integer.parseInt(duration.substring(0, 2)), podiumName));
            }

        } else {
            notification(true, "Maximum amount of artists reached!");
        }
    }

    @FXML
    public void onAddArtistButton() {
        if (artistNameTextfield.getText().isEmpty() || genreTextfield.getText().isEmpty() || popularity == 0) {
            notification(true, "Make sure to fill out all fields!");
            return;
        }

        amountOfArtistsAdded++;

        addArtistToList(artistNameTextfield.getText(), genreTextfield.getText(), popularity, startTimeChoicebox.getSelectionModel().getSelectedItem(),
                durationChoicebox.getSelectionModel().getSelectedItem(), stagePickerChoicebox.getSelectionModel().getSelectedItem());


        for (Artist a : artists) {
            if (!artistsObservableList.contains(a)) {
                artistsObservableList.add(a);
            }
        }
        artistNameTextfield.clear();
        genreTextfield.clear();
        startTimeChoicebox.setValue("12:00");
        durationChoicebox.setValue("30 minutes");
        stagePickerChoicebox.setValue("Main stage");
        noStarsClicked();
    }

    @FXML
    public void onSaveFestivalButton() {
        if (amountOfVisitorsTextfield.getText().isEmpty() || festivalNameTextfield.getText().isEmpty()) {
            notification(true, "Make sure to fill in all fields!");
            return;
        }
        try {
            visitorCount = Integer.parseInt(amountOfVisitorsTextfield.getText());
            SimulatorController.visitorCount = visitorCount;
            SimulatorController.saveAITypes();
            if (visitorCount > 10000) {
                notification(true, "Can't add more than 10000 visitors!");
                return;
            }
        } catch (Exception e) {
            notification(true, "Value in box Visitor Count is supposed to be a number!");
            return;
        }
        for (int i = 0; i < visitorCount; i++) {
            visitors.add(new Visitor());            //create visitors based on user input and adds them to arraylist
        }
        festivalName = festivalNameTextfield.getText();
        notification(false, "Successfully saved festival information :)");
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
}

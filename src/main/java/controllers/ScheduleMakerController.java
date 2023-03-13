package controllers;


import classes.Artist;
import classes.Festival;
import classes.Song;
import classes.Visitor;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringBinding;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.shape.SVGPath;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Optional;

public class ScheduleMakerController {

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
    public ListView<Artist> artistsListView;
    @FXML
    private ChoiceBox<String> stagePickerChoicebox;

    //Actual attributes to save data

    private int popularity = 0;
    private boolean popularitySelected = false;
    private int amountOfArtistsAdded = 0;
    private int visitorCount;
    private String festivalName;
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

        //Code for editing and deleting artists
        artistsListView.setCellFactory(lv -> {
            ListCell<Artist> cell = new ListCell<>();

            ContextMenu contextMenu = new ContextMenu();

            MenuItem editItem = new MenuItem();
            editItem.textProperty().bind(Bindings.format("Edit \"%s\"", cell.itemProperty()));
            editItem.setOnAction(event -> {
                Artist item = cell.getItem();
                // code to edit item...
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
        artistsListView.getItems().remove(cell.getItem());
        ArtistArrayListController.artists.remove(artist);
    }

    private void openArtistEditDialog(Artist item) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/GUI/editArtistDialog.fxml"));
        DialogPane editArtistDialogPane = fxmlLoader.load();

        editArtistDialogController editArtistDialogController = fxmlLoader.getController();

        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setDialogPane(editArtistDialogPane);
        dialog.setTitle("Edit artist");

        Optional<ButtonType> clickedButton = dialog.showAndWait();
        if (clickedButton.get() == ButtonType.OK) {
            editArtistDialogController.editArtist(item);
            artistsListView.getItems().remove(item);
            onRefreshListButton();
        }
    }

    @FXML
    public void onRefreshListButton() {
//        System.out.println(ArtistArrayListController.artists.size());
        for (Artist a : ArtistArrayListController.artists) {
            if (!artistsListView.getItems().contains(a)) {
                artistsListView.getItems().add(a);
            }
        }
    }

    @FXML
    void onExportButton() {
        Festival festival = new Festival(visitors, festivalName, ArtistArrayListController.artists);      //create festival object with all saved information from user

        try {                                               //try serializing all data into .txt file, showing error when unsuccessfull
            Serializer.Serialize(festival);
            NotificationPromptController.notification(false, "Successfully exported festival file :)");
        } catch (IOException e) {
            NotificationPromptController.notification(true, "Unable to import festival file :(");
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
                ArtistArrayListController.artists.add(new Artist(name, genre, popularity, startingTime, Integer.parseInt(duration.substring(0, 3)), podiumName));
            } else {
                ArtistArrayListController.artists.add(new Artist(name, genre, popularity, startingTime, Integer.parseInt(duration.substring(0, 2)), podiumName));
            }

        } else {
            NotificationPromptController.notification(true, "Maximum amount of artists reached!");
        }
    }

    @FXML
    public void onAddArtistButton() {
        if (artistNameTextfield.getText().isEmpty() || genreTextfield.getText().isEmpty() || popularity == 0) {
            NotificationPromptController.notification(true, "Make sure to fill out all fields!");
            return;
        }

        amountOfArtistsAdded++;

        addArtistToList(artistNameTextfield.getText(), genreTextfield.getText(), popularity, startTimeChoicebox.getSelectionModel().getSelectedItem(),
                durationChoicebox.getSelectionModel().getSelectedItem(), stagePickerChoicebox.getSelectionModel().getSelectedItem());


        for (Artist a : ArtistArrayListController.artists) {
            if (!artistsListView.getItems().contains(a)) {
                artistsListView.getItems().add(a);
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
            NotificationPromptController.notification(true, "Make sure to fill in all fields!");
            return;
        }
        try {
            visitorCount = Integer.parseInt(amountOfVisitorsTextfield.getText());
            if (visitorCount > 20) {
                NotificationPromptController.notification(true, "Can't add more than 20 visitors!");
                return;
            }
        } catch (Exception e) {
            NotificationPromptController.notification(true, "Value in box Visitor Count is supposed to be a number!");
            return;
        }
        for (int i = 0; i < visitorCount; i++) {
            visitors.add(new Visitor());            //create visitors based on user input and adds them to arraylist
        }
        festivalName = festivalNameTextfield.getText();
        NotificationPromptController.notification(false, "Successfully saved festival information :)");
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

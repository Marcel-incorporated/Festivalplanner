package controllers;

import classes.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.SVGPath;
import org.jfree.fx.FXGraphics2D;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;


public class FestivalplannerController {
    //FXML Components
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
    private TabPane tabPane;
    @FXML
    private Button exportButton;
    @FXML
    private Button importButton;
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
    private Label artistLabel1 = new Label();
    @FXML
    private Label artistLabel2 = new Label();
    @FXML
    private Label artistLabel3 = new Label();
    @FXML
    private Label artistLabel4 = new Label();
    @FXML
    private Label artistLabel5 = new Label();
    @FXML
    private Label artistLabel6 = new Label();
    @FXML
    private Label artistLabel7 = new Label();
    @FXML
    private Label artistLabel8 = new Label();
    @FXML
    private Label artistLabel9 = new Label();
    @FXML
    private Label artistLabel10 = new Label();
    @FXML
    private Label artistLabel11 = new Label();
    @FXML
    private Label artistLabel12 = new Label();
    @FXML
    private Label artistLabel13 = new Label();
    @FXML
    private Label artistLabel14 = new Label();
    @FXML
    private Label artistLabel15 = new Label();
    @FXML
    private Label artistLabel16 = new Label();
    @FXML
    private ListView artistsListView;

    //Actual attributes to save data
    private boolean mapIsClicked = false;
    private boolean mapMakerIsClicked = false;
    private Color blockColors[] = {Color.BLUE, Color.RED, Color.YELLOW, Color.LIGHT_GRAY};
    private ArrayList<Block> blocks = new ArrayList<>();
    private Block lastBlockChanged = null;
    private int blockColorCounter = 0;
    private int popularity = 0;
    private boolean popularitySelected = false;
    private int amountOfArtistsAdded = 0;
    private int visitorCount;
    private String festivalName;
    private ArrayList<Artist> artists = new ArrayList<>();
    private ArrayList<Visitor> visitors = new ArrayList<>();
    private ArrayList<Song> songs = new ArrayList<>();


    // Schedule/main tab controller
    @FXML
    void onAddEditEventButton(ActionEvent event) throws IOException {
        tabPane.getSelectionModel().select(1);
    }

    // File editor/generator controller


    @FXML
    void onExportButton(ActionEvent event) {
//        System.out.println("exporting");      //create song generator

        for (int i = 0; i < visitorCount; i++) {
            visitors.add(new Visitor());            //create visitors based on user input and adds them to arraylist
        }

        Festival festival = new Festival(visitors, festivalName, artists);

        try {
            Serializer.Serialize(festival);
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Successfully converted data to .txt file :)");
            alert.showAndWait();
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Unable to convert data to .txt file :(");
            alert.showAndWait();
        }
    }

    @FXML
    void onImportButton(ActionEvent event) {
//        System.out.println("importing");
        try {
            Serializer.Deserialize();
        } catch (Exception e) {

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

    private void addArtistToList(String name, String genre, int popularity, String startingTime, String duration, String podiumName) {
        if (amountOfArtistsAdded <= 16) {
            artists.add(new Artist(name, genre, popularity, startingTime, Integer.parseInt(duration), podiumNameTextfield.getText()));

            switch (amountOfArtistsAdded) {
                case 1 -> {
                    artistLabel1.setText(name);
                    artistLabel1.setOpacity(1);
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
            notificationPopup(1, "Maximum amount of artists reached!");
        }
    }

    @FXML
    public void mapTabClicked() {
        FXGraphics2D graphics2DMap = new FXGraphics2D(mapCanvas.getGraphicsContext2D());
        if (!mapIsClicked) {
            new FXGraphics2D(mapCanvas.getGraphicsContext2D()).drawLine(200, 200, 100, 100);
            mapIsClicked = true;
        } else {
            mapIsClicked = false;
        }
    }

    private void mousePressed(MouseEvent event) {
        for (Block block : blocks) {
            if (block.contains(event.getX(), event.getY())) {
                //System.out.println(block.toString());

                if (block != lastBlockChanged) {
                    blockColorCounter = 0;
                    block.setColor(blockColors[blockColorCounter]);
                    updateBlock(new FXGraphics2D(mapCanvasMaker.getGraphicsContext2D()), block);
                    lastBlockChanged = block;
                } else {
                    blockColorCounter++;
                    if (blockColorCounter < 4) {
                        block.setColor(blockColors[blockColorCounter]);
                        updateBlock(new FXGraphics2D(mapCanvasMaker.getGraphicsContext2D()), block);
                    } else {
                        blockColorCounter = 0;
                        block.setColor(blockColors[blockColorCounter]);
                        updateBlock(new FXGraphics2D(mapCanvasMaker.getGraphicsContext2D()), block);
                    }
                }
                //System.out.println(block.toString());
            }
        }
    }

    public void updateBlock(FXGraphics2D graphics2DMapMaker, Block block) {
        block.changeSizeOfBlock(19, 19);
        graphics2DMapMaker.setColor(block.getColor());
        graphics2DMapMaker.fill(block);
    }

    public void updateBlocks(FXGraphics2D graphics2DMapMaker, ArrayList<Block> blocks) {
        for (Block block : blocks) {
            graphics2DMapMaker.setColor(Color.LIGHT_GRAY);
            graphics2DMapMaker.fill(block);
            graphics2DMapMaker.setColor(Color.BLACK);
            graphics2DMapMaker.draw(block);
        }
    }

    @FXML
    public void btnExportMapMaker() {
        System.out.println("Exporting");
    }

    @FXML
    public void btnImportMapMaker() {
        System.out.println("Importing");
    }

    @FXML
    public void mapMakerTabClicked() {
        FXGraphics2D graphics2DMapMaker = new FXGraphics2D(mapCanvasMaker.getGraphicsContext2D());
        if (!mapMakerIsClicked) {
            int y = 1;
            for (int j = 1; j + 20 < mapCanvasMaker.getHeight(); j = j + 20) {
                for (int i = 1; i + 20 < mapCanvasMaker.getWidth(); i = i + 20) {
                    blocks.add(new Block(20, 20, i, j, Color.LIGHT_GRAY));
                }
            }

            updateBlocks(new FXGraphics2D(mapCanvasMaker.getGraphicsContext2D()), blocks);

            mapCanvasMaker.setOnMousePressed(e -> mousePressed(e));

            mapMakerIsClicked = true;
        } else {
            mapMakerIsClicked = false;
        }
    }

    @FXML
    public void onAddArtistButton(ActionEvent actionEvent) {
        if (artistNameTextfield.getText().isEmpty() || genreTextfield.getText().isEmpty() || popularity == 0 ||
                setDurationTextfield.getText().isEmpty() || startingTimeTextfield.getText().isEmpty() || setDurationTextfield.getText().matches("[a-zA-Z]+")) {
            notificationPopup(1, "Make sure to fill out all fields!");
            return;
        }

        amountOfArtistsAdded++;
        addArtistToList(artistNameTextfield.getText(), genreTextfield.getText(), popularity, startingTimeTextfield.getText(), setDurationTextfield.getText(), podiumNameTextfield.getText());
        for (Artist a : artists) {
            if(!artistsListView.getItems().contains(a)) {
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
    public void onSaveFestivalButton(ActionEvent actionEvent) {
        if (amountOfVisitorsTextfield.getText().isEmpty() || festivalNameTextfield.getText().isEmpty()) {
            notificationPopup(0, "Make sure to fill in all fields!");
            return;
        }
        try {
            visitorCount = Integer.parseInt(amountOfVisitorsTextfield.getText());
        } catch (Exception e) {
            notificationPopup(1, "Value in box Visitor Count is supposed to be a number!");
            return;
        }
        festivalName = festivalNameTextfield.getText();
        notificationPopup(0, "Festival information saved :)");
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


    public void notificationPopup(int type, String message) {
        //type 0 = information, 1 = error
        switch (type) {
            case 0:
                Alert alert = new Alert(Alert.AlertType.INFORMATION, message);
                alert.setTitle("Error");
                alert.setHeaderText("Error");
                alert.showAndWait();
                break;
            case 1:
                Alert alert1 = new Alert(Alert.AlertType.ERROR, message);
                alert1.setTitle("Information");
                alert1.setHeaderText("Information");
                alert1.showAndWait();
                break;
        }
    }
}
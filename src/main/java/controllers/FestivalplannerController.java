package controllers;

import classes.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
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


    //SCHEDULE MAKER
    @FXML
    void onExportButton() {
        Festival festival = new Festival(visitors, festivalName, artists);      //create festival object with all saved information from user

        try {                                               //try serializing all data into .txt file, showing error when unsuccessfull
            Serializer.Serialize(festival);
            notificationPrompt(false, "Successfully converted data to .txt file :)");
        } catch (IOException e) {
            notificationPrompt(true, "Unable to convert data to .txt file :(");
        }
    }

    @FXML
    void onImportButton() {
        try {                                           //try importing file, showing error when unsuccessfull
            Serializer.Deserialize();
            notificationPrompt(false, "Imported .txt file successfully :)");
        } catch (Exception e) {
            notificationPrompt(true,"Unable to import .txt file :(" );
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
        addArtistToList(artistNameTextfield.getText(), genreTextfield.getText(), popularity, startingTimeTextfield.getText(), setDurationTextfield.getText(), podiumNameTextfield.getText());

        for (Artist a : artists) {
            if(!artistsListView.getItems().contains(a.getName())) {
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
        } catch (Exception e) {
            notificationPrompt(true, "Value in box Visitor Count is supposed to be a number!");
            return;
        }
        for (int i = 0; i < visitorCount; i++) {
            visitors.add(new Visitor());            //create visitors based on user input and adds them to arraylist
        }
        festivalName = festivalNameTextfield.getText();
        notificationPrompt(false, "Festival information saved :)");
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

    //MAP MAKER
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

    //OTHER
    public void notificationPrompt(boolean error, String message) {
        if(error) {
            Alert alert1 = new Alert(Alert.AlertType.ERROR, message);
            alert1.setTitle("Information");
            alert1.setHeaderText("Information");
            alert1.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, message);
            alert.setTitle("Error");
            alert.setHeaderText("Error");
            alert.showAndWait();
        }
    }
}
package controllers;

import classes.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import org.jfree.fx.FXGraphics2D;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;

import javafx.scene.control.Label;

import javax.imageio.ImageIO;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

public class SimulatorController extends Thread implements Runnable {
    //FXML attributen
    @FXML
    public Canvas simMap;
    @FXML
    public Canvas timerCanvas;
    @FXML
    private Label statusLabel = new Label();
    @FXML
    private Label timeLabel;
    @FXML
    public Canvas bottom;
    @FXML
    public Canvas pathFinding;

    //Klasse attributen
    private MyAnimationTimer animationTimer;
    private Map map;
    private int minutes = 00;
    private int hours = 10;
    private Map bottomMap;
    private Map pathFindingMap;
    private static int width;
    private static int height;
    private static int tileHeight;
    private static int tileWidth;
    private static Matrix orangeShopPath;
    private static Matrix blueShopPath;
    private static Matrix mainStagePath;
    private static Matrix leftTinyStagePath;
    private static Matrix middleTinyStagePath;
    private static Matrix rightTinyStagePath;
    private static Matrix toiletPath;

    private boolean pastMidnight = false;
    private static ArrayList<BufferedImage> aisImage = new ArrayList<>();
    private static BufferedImage image;
    private static ArrayList<newAi> ais = new ArrayList<>();
    private static ArrayList<BufferedImage> greenAI = new ArrayList<>();
    private static ArrayList<BufferedImage> blueAI = new ArrayList<>();
    private static ArrayList<BufferedImage> purpleAI = new ArrayList<>();
    private static ArrayList<BufferedImage> goldAI = new ArrayList<>();
    public static int visitorCount;

    @FXML
    public void initialize() throws FileNotFoundException {
        makeOrangeShopPath();
        makeBlueShopPath();
        makeMainStagePath();
        makeLeftTinyStagePath();
        makeMiddleTinyStagePath();
        makeRightTinyStagePath();
        makeToiletPath();
        saveAITypes();
        map = new Map("map.json");
        bottomMap = new Map("bottom.json");
        pathFindingMap = new Map("pathFinding.json");

        FXGraphics2D g2d = new FXGraphics2D(simMap.getGraphicsContext2D());
        FXGraphics2D bottomDrawer = new FXGraphics2D(bottom.getGraphicsContext2D());
        FXGraphics2D pathFinderDrawer = new FXGraphics2D(pathFinding.getGraphicsContext2D());

        drawMap(g2d);
        drawBottom(bottomDrawer);
        drawPathFinding(pathFinderDrawer);

        animationTimer = new MyAnimationTimer(timeLabel, ais, simMap) {
            long last = -1;
        };
    }

    public static void saveAITypes() throws FileNotFoundException {
        JsonReader reader = null;

        File file = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\" + "map.json");
        reader = Json.createReader(new FileInputStream(file));
        JsonObject root = reader.readObject();

        width = root.getInt("width");
        height = root.getInt("height");

        //Laad de tegelmap
        try {
            //Haal de tegelmap JSON object op
            JsonArray tilesets = root.getJsonArray("tilesets");
            JsonObject tileset = tilesets.getJsonObject(0);

            //Haal de bestandsnaam van de tegelmap op
            String fileNameTileMap = tileset.getString("image");

            //Laad de tegelmap afbeelding
            InputStream inputStream = SimulatorController.class.getClassLoader().getResourceAsStream(fileNameTileMap);
            BufferedImage tilemapImage = ImageIO.read(inputStream);

            tileHeight = tileset.getInt("tileheight");
            tileWidth = tileset.getInt("tilewidth");

            for (int y = 0; y < tilemapImage.getHeight(); y += tileHeight) {
                for (int x = 0; x < tilemapImage.getWidth(); x += tileWidth) {
                    aisImage.add(tilemapImage.getSubimage(x, y, tileWidth, tileHeight));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 16; i <= 25; i++) {
            greenAI.add(image = aisImage.get(i));
        }

        for (int i = 32; i <= 41; i++) {
            blueAI.add(image = aisImage.get(i));
        }

        for (int i = 48; i <= 57; i++) {
            purpleAI.add(image = aisImage.get(i));
        }

        for (int i = 64; i <= 73; i++) {
            goldAI.add(image = aisImage.get(i));
        }

        ArrayList<Integer> collisionMapArray = new ArrayList<>();

        collisionMapArray = makeCollisionMap();



        for (int i = 0; i < ScheduleMakerController.visitorCount; i++) {
            int value = getRandom7();

            switch (value) {
                case 1:
                    ais.add(new newAi(goldAI, collisionMapArray, aisImage, i, rightTinyStagePath));
                    break;
                case 2:
                    ais.add(new newAi(blueAI, collisionMapArray, aisImage, i, rightTinyStagePath));
                    break;
                case 3:
                    ais.add(new newAi(greenAI, collisionMapArray, aisImage, i, rightTinyStagePath));
                    break;
                case 4:
                    ais.add(new newAi(purpleAI, collisionMapArray, aisImage, i, rightTinyStagePath));
                    break;
                case 5:
                    ais.add(new newAi(purpleAI, collisionMapArray, aisImage, i, rightTinyStagePath));

                    break;
                case 6:
                    ais.add(new newAi(purpleAI, collisionMapArray, aisImage, i, rightTinyStagePath));

                    break;
                case 7:
                    ais.add(new newAi(purpleAI, collisionMapArray, aisImage, i, rightTinyStagePath));

                    break;
                default:
                    System.out.println("generating ai error");
                    break;
            }
        }
    }

    public static ArrayList<Integer> makeCollisionMap() throws FileNotFoundException {
        JsonReader reader = null;

        File file = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\" + "collision.json");
        reader = Json.createReader(new FileInputStream(file));
        JsonObject root = reader.readObject();

        JsonArray tilesets = root.getJsonArray("tilesets");
        JsonObject tileset = tilesets.getJsonObject(0);

        JsonArray layers = root.getJsonArray("layers");
        JsonObject layer = layers.getJsonObject(0);

        JsonArray jsonArray = layer.getJsonArray("data");

        ArrayList<Integer> collisionMapArray = new ArrayList<>();

        collisionMapArray = getIntArray(jsonArray);

        return collisionMapArray;
    }

    public void drawMap(Graphics2D g) {
        map.draw(g);
//        Graphics2D timerDrawer = new FXGraphics2D(timerCanvas.getGraphicsContext2D());
    }

    public static ArrayList<Integer> getIntArray(JsonArray jsonArray) {
        ArrayList<Integer> intArray = new ArrayList<>();
        for (int i = 0; i < jsonArray.size(); i++) {
            intArray.add(jsonArray.getInt(i));
        }
        return intArray;
    }

    public static int getRandom4() {
        Random rand = new Random();
        int randomNumber = rand.nextInt(4) + 1;
        return randomNumber;
    }

    public static int getRandom7() {
        Random rand = new Random();
        int randomNumber = rand.nextInt(7) + 1;
        return randomNumber;
    }

    public void setStatusLabel(String text) {
        statusLabel.setText(text);
    }

    @FXML
    public void onStartButton() {
        animationTimer.start();
        statusLabel.setText("Status: started");
    }


    @FXML
    public void onStopButton() {
        animationTimer.stop();
        statusLabel.setText("Status: stopped");
    }

    public void drawBottom(Graphics2D g) {
        bottomMap.draw(g);
    }

    public void drawPathFinding(Graphics2D g) {
        pathFindingMap.drawMatrix(g, rightTinyStagePath);   //for debugging
    }

    public void makeOrangeShopPath() {
        orangeShopPath = new Matrix(35, 56);
        orangeShopPath.updateAround(5, 3, 0);
    }

    public void makeBlueShopPath() {
        blueShopPath = new Matrix(35, 56);
        blueShopPath.updateAround(15, 3, 0);
    }

    public void makeMainStagePath() {
        mainStagePath = new Matrix(35, 56);
        mainStagePath.updateAround(5, 24, 0);
    }

    public void makeLeftTinyStagePath() {
        leftTinyStagePath = new Matrix(35, 56);
        leftTinyStagePath.updateAround(30, 5, 0);
    }

    public void makeMiddleTinyStagePath() {
        middleTinyStagePath = new Matrix(35, 56);
        middleTinyStagePath.updateAround(28, 26, 0);
    }

    public void makeRightTinyStagePath() {
        rightTinyStagePath = new Matrix(35, 56);
        rightTinyStagePath.updateAround(4, 49, 0);
    }


    public void makeToiletPath() {
        toiletPath = new Matrix(35, 56);
        toiletPath.updateAround(1,8, 0);
    }


}

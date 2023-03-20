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
    private int width;
    private int height;
    private int tileHeight;
    private int tileWidth;
    private Matrix orangeShopPath;
    private boolean pastMidnight = false;
    private ArrayList<BufferedImage> aisImage = new ArrayList<>();
    private BufferedImage image;
    private ArrayList<AI> ais = new ArrayList<>();
    private ArrayList<BufferedImage> greenAI = new ArrayList<>();
    private ArrayList<BufferedImage> blueAI = new ArrayList<>();
    private ArrayList<BufferedImage> purpleAI = new ArrayList<>();
    private ArrayList<BufferedImage> goldAI = new ArrayList<>();

    private Festival festival = new Festival();

    @FXML
    public void initialize() throws FileNotFoundException {
        saveAITypes(); //slaat alle tegels op van tegelmap per AI type

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



    public void LoadAIs() throws FileNotFoundException
    {
        ais.add(new AI(new Point2D.Double(664, 552), purpleAI));
        ais.add(new AI(new Point2D.Double(664, 552), goldAI));
        //ai's laden based on hoeveel visitors er zijn: maar werkt nog niet want andere klasses gay
//        for (Visitor v : festival.getVisitorList())
//        {
//            int number = (int)((Math.random() * 4) - 1) + 1;
//            switch(number) {
//                case 1:
//                    ais.add(new AI(new Point2D.Double(664, 552), goldAI));
//                    break;
//                case 2:
//                    ais.add(new AI(new Point2D.Double(664, 552), purpleAI));
//                    break;
//                case 3:
//                    ais.add(new AI(new Point2D.Double(664, 552), greenAI));
//                    break;
//                case 4:
//                    ais.add(new AI(new Point2D.Double(664, 552), blueAI));
//                    break;
//            }
//        }
    }

    public void drawMap(Graphics2D g) {
        map.draw(g);
    }

    public void setStatusLabel(String text) {
        statusLabel.setText(text);
    }

    @FXML
    public void onStartButton() throws FileNotFoundException
    {
        try {
            LoadAIs();
            animationTimer.start();
            statusLabel.setText("Status: started");
        } catch (Exception e) {
            NotificationPromptController.notification(true, "Geen bezoekers gevonden om te laden :(");
        }
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
        makeOrangeShopPath();
//        pathFindingMap.drawMatrix(g, orangeShopPath);
    }

    public void makeOrangeShopPath(){
        orangeShopPath = new Matrix(35, 56);
        orangeShopPath.updateAround(5, 3, 0);
    }

    public void saveAITypes() throws FileNotFoundException {
        JsonReader reader = null;

        File file = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\" + "map.json");
        reader = Json.createReader(new FileInputStream(file));
        JsonObject root = reader.readObject();

        this.width = root.getInt("width");
        this.height = root.getInt("height");

        //Laad de tegelmap
        try {
            //Haal de tegelmap JSON object op
            JsonArray tilesets = root.getJsonArray("tilesets");
            JsonObject tileset = tilesets.getJsonObject(0);

            //Haal de bestandsnaam van de tegelmap op
            String fileNameTileMap = tileset.getString("image");

            //Laad de tegelmap afbeelding
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileNameTileMap);
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

    }
}

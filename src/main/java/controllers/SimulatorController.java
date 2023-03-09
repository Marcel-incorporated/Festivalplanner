package controllers;

import classes.AI;
import classes.Map;
import javafx.animation.AnimationTimer;
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

    @FXML
    public Canvas simMap;
    @FXML
    public Canvas timerCanvas;
    @FXML
    private Label statusLabel = new Label();
    @FXML
    private Label timeLabel;
    private AnimationTimer animationTimer;
    private double timer;
    private Map map;
    private int minutes = 00;
    private int hours = 10;
    public Canvas bottom;
    private Map bottomMap;
    private int width;
    private int height;
    private int tileHeight;
    private int tileWidth;
    private ArrayList<BufferedImage> aisImage = new ArrayList<>();
    private BufferedImage image;
    private ArrayList<AI> ais = new ArrayList<>();
    private ArrayList<BufferedImage> greenAI = new ArrayList<>();
    private ArrayList<BufferedImage> blueAI = new ArrayList<>();
    private ArrayList<BufferedImage> purpleAI = new ArrayList<>();
    private ArrayList<BufferedImage> goldAI = new ArrayList<>();


    @FXML
    public void initialize() throws FileNotFoundException {
        saveAITypes();
        map = new Map("map.json");
        bottomMap = new Map("bottom.json");
        ais.add(new AI(new Point2D.Double(664, 553)));
        FXGraphics2D g2d = new FXGraphics2D(simMap.getGraphicsContext2D());
        FXGraphics2D bottomDrawer = new FXGraphics2D(bottom.getGraphicsContext2D());
        drawMap(g2d);
        drawBottom(bottomDrawer);

        animationTimer = new AnimationTimer() {
            long last = -1;

            @Override
            public void handle(long now) {
                if (last == -1)
                    last = now;
                double deltaTime = (now - last) / 1000000000.0;
                update(deltaTime);
                last = now;
            }
        };
    }

    public void saveAITypes() throws FileNotFoundException {
        JsonReader reader = null;

        File file = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\" + "map.json");
        reader = Json.createReader(new FileInputStream(file));
        JsonObject root = reader.readObject();

        this.width = root.getInt("width");
        this.height = root.getInt("height");

        //load the tilemap
        try {
            // Get the tileset JSON object
            JsonArray tilesets = root.getJsonArray("tilesets");
            JsonObject tileset = tilesets.getJsonObject(0);

            // Get the file name of the tilemap
            String fileNameTileMap = tileset.getString("image");

            // Load the tilemap image
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

    public void update(double deltaTime) {
        timer += deltaTime;
//        System.out.println(timer);

        if (timer > 1.0) {
            for (AI ai : ais) {
                ai.draw(new FXGraphics2D(simMap.getGraphicsContext2D()));
                ai.update(ais);
            }
            timer = 0;
            updateTime();
        }
    }


    public void drawMap(Graphics2D g) {
        map.draw(g);
        Graphics2D timerDrawer = new FXGraphics2D(timerCanvas.getGraphicsContext2D());
    }

    private void updateTime() {
        if (minutes == 59) {
            minutes = 00;
            if (hours == 23) {
                hours = 00;
            } else {
                hours++;
            }
            if (hours == 3) {
                animationTimer.stop();
                statusLabel.setText("Status: stopped");
                hours = 10;
                minutes = 0;
            } else {
            }
        } else {
            minutes++;
        }
        if (minutes < 10) {
            timeLabel.setText("" + hours + ":0" + minutes);
        } else {
            timeLabel.setText("" + hours + ":" + minutes);
        }
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
}

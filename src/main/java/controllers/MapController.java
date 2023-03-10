package controllers;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;

import org.jfree.fx.FXGraphics2D;

import classes.Map;

import java.awt.*;
import java.io.FileNotFoundException;

public class MapController {
    @FXML
    public Canvas canvasMap;

    @FXML
    public Canvas bottom;

    private Map map;
    private Map bottomMap;
    @FXML
    public void initialize() throws FileNotFoundException {
        map = new Map("map.json");
        bottomMap = new Map("bottom.json");

        FXGraphics2D g2d = new FXGraphics2D(canvasMap.getGraphicsContext2D());
        FXGraphics2D bottomDrawer = new FXGraphics2D(bottom.getGraphicsContext2D());

        drawMap(g2d);
        drawBottom(bottomDrawer);

        new AnimationTimer() {
            long last = -1;
            @Override
            public void handle(long now) {
                if(last == -1)
                    last = now;
                update((now - last) / 1000000000.0);
                last = now;
            }
        }.start();
    }

    public void update(double deltaTime) {
        // do nothing 'cause it's only a map view
    }

    public void drawMap(Graphics2D g) {
        map.draw(g);
    }
    public void drawBottom(Graphics2D g) {
        bottomMap.draw(g);
    }
}

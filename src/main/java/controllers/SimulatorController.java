package controllers;

import classes.Map;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import org.jfree.fx.FXGraphics2D;

import java.awt.*;
import java.io.FileNotFoundException;

public class SimulatorController {

    @FXML
    public Canvas simMap;

    private Map map;

    @FXML
    public void initialize() throws FileNotFoundException {
        map = new Map("map.json");

        FXGraphics2D g2d = new FXGraphics2D(simMap.getGraphicsContext2D());
        new AnimationTimer() {
            long last = -1;
            @Override
            public void handle(long now) {
                if(last == -1)
                    last = now;
                update((now - last) / 1000000000.0);
                last = now;
                draw(g2d);
            }
        }.start();
    }

    public void update(double deltaTime) {
        System.out.println("update sim");
    }

    public void draw(Graphics2D g) {
        map.draw(g);
    }
}

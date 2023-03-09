package controllers;

import classes.Map;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import org.jfree.fx.FXGraphics2D;

import java.awt.*;
import java.io.FileNotFoundException;
import javafx.scene.control.Label;

public class SimulatorController {

    @FXML
    public Canvas simMap;
    @FXML
    public Canvas timerCanvas;
    @FXML
    private Label statusLabel;

    private Map map;
    private int minutes;
    private int hours;
    private boolean run;

    @FXML
    public void initialize() throws FileNotFoundException {
        statusLabel.setText("Status: stopped");
        map = new Map("map.json");

        FXGraphics2D g2d = new FXGraphics2D(simMap.getGraphicsContext2D());
        draw(g2d);
        new AnimationTimer() {
            long last = -1;
            @Override
            public void handle(long now) {
                while(run) {
                    if(last == -1)
                        last = now;
                    update((now - last) / 1000000000.0);
                    last = now;
//                    draw(g2d);
                }

            }
        }.start();

    }

    public void update(double deltaTime) {
        System.out.println("update sim");
        if(run)
            statusLabel.setText("Status: started");
        else
            statusLabel.setText("Status: stopped");
    }

    public void draw(Graphics2D g) {
        map.draw(g);
        Graphics2D timerDrawer = new FXGraphics2D(timerCanvas.getGraphicsContext2D());
        timerDrawer.drawLine(0, 0, 100, 100);

    }

    @FXML
    public void onStartButton() {
        run = true;
    }

    @FXML
    public void onStopButton() {
        run = false;
    }
}

package controllers;

import classes.Map;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import org.jfree.fx.FXGraphics2D;

import java.awt.*;
import java.io.FileNotFoundException;
import javafx.scene.control.Label;

public class SimulatorController extends Thread implements Runnable  {

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
    private boolean execute = false;

    @FXML
    public void initialize() throws FileNotFoundException {
        map = new Map("map.json");

        FXGraphics2D g2d = new FXGraphics2D(simMap.getGraphicsContext2D());
        draw(g2d);
        animationTimer = new AnimationTimer() {
            long last = -1;
            @Override
            public void handle(long now) {
                if(last == -1)
                    last = now;
                double deltaTime = (now-last) /1000000000.0;
                update(deltaTime);
                last = now;
            }
        };
    }

    public void update(double deltaTime) {
        timer += deltaTime;
        if(timer > 1) {
            timer = 0;
            updateTime();
        }
    }

    public void draw(Graphics2D g) {
        map.draw(g);
        Graphics2D timerDrawer = new FXGraphics2D(timerCanvas.getGraphicsContext2D());
    }

    private void updateTime() {
            if(minutes == 59) {
                minutes = 00;
                if(hours == 23) {
                    hours = 00;
                } else {
                    hours++;
                }
                if(hours == 3) {
                    animationTimer.stop();
                    statusLabel.setText("Status: stopped");
                    hours = 10;
                    minutes = 0;
                } else {
                }
            } else {
                minutes++;
            }
            timeLabel.setText("" + hours + ":" + minutes);
        }


    @FXML
    public void onStartButton() {
        execute = true;
        animationTimer.start();
        statusLabel.setText("Status: started");
    }

    @FXML
    public void onStopButton() {
        execute = false;
        animationTimer.stop();
        statusLabel.setText("Status: stopped");
    }
}

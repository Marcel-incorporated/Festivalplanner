package classes;

import controllers.SimulatorController;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import org.jfree.fx.FXGraphics2D;

import java.awt.*;
import java.util.ArrayList;

public class MyAnimationTimer extends AnimationTimer {

    private long lastTime = 0;
    private long lastTimeForTimer = 0;
    private final SimulatorController simulatorController = new SimulatorController();
    private Label timerLabel;
    private boolean pastMidnight = false;
    private int minutes = 0;
    private int hours = 0;
    private ArrayList<AI> ais;
    private Canvas simMap;

    public MyAnimationTimer(Label timerLabel, ArrayList<AI> ais, Canvas simMap) {
        this.timerLabel = timerLabel;
        this.ais = ais;
        this.simMap = simMap;
    }
    @Override
    public void handle(long currentTime) {

//        System.out.println("handle method called");
        if (lastTime == 0) {
            lastTime = currentTime;
            return;
        }
        // Calculate elapsed time since last execution
        long elapsed = currentTime - lastTime;
        long elapsedForTimer = currentTime - lastTimeForTimer;

        if (elapsed >= 125_000_000) { // 125 milliseconds
            // Execute the method here
            myMethod();

            // Schedule the next execution 125 milliseconds later
            lastTime = currentTime;
        }
        if (elapsedForTimer >= 1_000_000_000) {
            Platform.runLater(this::addMinute);
            Platform.runLater(() -> {
                for (AI ai : ais) {
                    ai.draw(new FXGraphics2D(simMap.getGraphicsContext2D()));
                    ai.update(ais);
                }
            });

            lastTimeForTimer = currentTime;
        }

        if (isPastMidnight() && getHours() == 3) {
            stop();
            resetTimer();
        }
    }

    public boolean isPastMidnight() {
        return pastMidnight;
    }

    public void resetTimer() {
        simulatorController.setStatusLabel("Status: finished");
        minutes = 0;
        hours = 0;
    }

    public void addHour() {
        if (hours == 23) {
            hours = 0;
            pastMidnight = true;
        } else {
            hours++;
        }
        minutes = 0;
    }

    public int getHours() {
        return hours;
    }

    public void addMinute() {
        if (minutes <= 59) {
            minutes++;
        } else {
            addHour();
        }
        if (minutes < 10) {
            timerLabel.setText("" + hours + ":0" + minutes);
        } else {
            timerLabel.setText("" + hours + ":" + minutes);
        }
    }

    private void myMethod() {
        // code to execute 8 times per second
//        System.out.println("method");
    }
}

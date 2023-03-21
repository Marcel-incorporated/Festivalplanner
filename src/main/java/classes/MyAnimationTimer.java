package classes;

import controllers.SimulatorController;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import org.jfree.fx.FXGraphics2D;

import java.awt.geom.Point2D;
import java.util.ArrayList;

public class MyAnimationTimer extends AnimationTimer {

    private long lastTime = 0;
    private long lastTimeForTimer = 0;
    private final SimulatorController simulatorController = new SimulatorController();
    private Label timerLabel;
    private boolean pastMidnight = false;
    private int minutes = 0;
    private int hours = 10;
    private ArrayList<AI> ais;
    public static ArrayList<AI> realAis = new ArrayList<>();
    private Canvas simMap;
    private int index;
    private int counter = 10;

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
        //Bereken de verstreken tijd sinds de laatste keer
        long elapsed = currentTime - lastTime;
        long elapsedForTimer = currentTime - lastTimeForTimer;

        if (elapsed >= 125_000_000) { // 125 milliseconden
            //Roep methode aan
            myMethod();

            //Plan de volgende keer runnen 125 milliseconde later
            lastTime = currentTime;
        }
        if (elapsedForTimer >= 1_000_000_000) {
            counter++;
            if (counter > 10 && isSpawn() == true){
                if (index != ais.size()){
                    realAis.add(ais.get(index));
                    index++;
                }
                counter = 0;
            }
            if (isPastMidnight() && getHours() == 3) {
                stop();
                resetTimer();
            }
            Platform.runLater(this::addMinute);
            Platform.runLater(() -> {
                for (AI ai : realAis) {
                    ai.draw(new FXGraphics2D(simMap.getGraphicsContext2D()));
                    ai.update();
                }
            });

            lastTimeForTimer = currentTime;
        }
    }

    public boolean isSpawn() {
        for (AI ai : MyAnimationTimer.realAis) {
            if (ai.getPosition().getX() == 664 && ai.getPosition().getY() == 552) {
                return false;
            }
        }
        return true;
    }

    public boolean isPastMidnight() {
        return pastMidnight;
    }

    public void resetTimer() {
        simulatorController.setStatusLabel("Status: finished");
        minutes = 0;
        hours = 10;
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
        //Code die 8 keer per seconde wordt uitgevoerd
//        System.out.println("method");
    }

//    private void checkrealai() {
//        for(AI a : realAis) {
//            System.out.println(a.getPosition());
//            if() {
//
//            }
//        }
//    }
}

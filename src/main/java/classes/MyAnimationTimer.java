package classes;

import controllers.ScheduleController;
import controllers.Serializer;
import controllers.SimulatorController;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import org.jfree.fx.FXGraphics2D;

import java.awt.*;
import java.awt.geom.Point2D;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * De MyAnimationTimer klasse breidt de de animationtimer klasse uit. Deze klasse houdt de tijd bij en zorgt er voor
 * dat er acties worden uitgevoerd wanneer bepaalde voorwaarden voldaan zijn.
 */

public class MyAnimationTimer extends AnimationTimer {

    private long lastTime = 0;
    private long lastTimeForTimer = 0;
    private final SimulatorController simulatorController = new SimulatorController();
    private Label timerLabel;
    private boolean pastMidnight = false;
    private ArrayList<newAi> ais;
    public static ArrayList<newAi> realAis = new ArrayList<>();
    private Canvas simMap;
    private int index;
    private int counter = 10;
    private Pos newPos;
    private Festival festival;
    public static ArrayList<Artist> artists;
    private Timer timer;

    public MyAnimationTimer(Label timerLabel, ArrayList<newAi> ais, Canvas simMap, Timer timer) {
        this.timerLabel = timerLabel;
        this.ais = ais;
        this.simMap = simMap;
        this.timer = timer;
        artists = new ArrayList<>();
    }

    @Override
    public void handle(long currentTime) {

        this.festival = ScheduleController.getFestival();

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

            //Plan de volgende keer runnen 125 milliseconde later
            lastTime = currentTime;
        }
        // 1_000_000_000
        if (elapsedForTimer >= 1_000_000_00) {
            counter++;
            if (counter > 10 && isSpawn() == true) {
                if (index != ais.size()) {
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
            Platform.runLater(() ->
            {
                for (newAi ai : realAis) {

                    for (Artist artist : this.festival.getArtists())
                    {
                        String time = artist.getSetStartingTime();
                        String[] parts = time.split(":");

                        int hours = Integer.parseInt(parts[0]);
                        int minutes = Integer.parseInt(parts[1]);

                        int allMinutesArtist = (hours * 60) + minutes;
                        int allMinutesTimer = (timer.getHours() * 60) + timer.getMinutes();

                        if (allMinutesArtist + artist.getSetDurationInMinutes() == allMinutesTimer){
                            if (ai.getStatus().equals("mainStage")){
                                ai.setMatrixes(ai.getBackFromMainStageMatrixes());
                                ai.setFest(false);
                                ai.setStatus("mainStageBack");
                            }
                            if (ai.getStatus().equals("leftTinyStage")){
                                ai.setMatrixes(ai.getBackFromLeftTinyStage());
                                ai.setFest(false);
                                ai.setStatus("leftTinyStageBack");
                            }
                            if (ai.getStatus().equals("middleTinyStage")){
                                ai.setMatrixes(ai.getBackFromMiddleTinyStage());
                                ai.setFest(false);
                                ai.setStatus("middleTinyStageBack");
                            }
                            if (ai.getStatus().equals("rightTinyStage")){
                                ai.setMatrixes(ai.getBackFromRightTinyStage());
                                ai.setFest(false);
                                ai.setStatus("rightTinyStageBack");
                            }
                        }

                        if (allMinutesArtist - 30 == allMinutesTimer && !ai.isFest()){
                            switch (artist.getPopularity()) {
                                case 1 -> {
                                    if (Math.random() <= 0.20) {
                                        if (artist.getPodium().equals("Main stage")){
                                            if (!ai.isJustSpawned()){
                                                ai.setGoToPodium(1);
                                            }
                                        }
                                        if (artist.getPodium().equals("Stage 2")){
                                            if (!ai.isJustSpawned()){
                                                ai.setGoToPodium(2);
                                            }
                                        }
                                        if (artist.getPodium().equals("Stage 3")){
                                            if (!ai.isJustSpawned()){
                                                ai.setGoToPodium(3);
                                            }
                                        }
                                        if (artist.getPodium().equals("Stage 4")){
                                            if (!ai.isJustSpawned()){
                                                ai.setGoToPodium(4);
                                            }
                                        }
                                    }
                                }
                                case 2 -> {
                                    if (Math.random() <= 0.40) {
                                        if (artist.getPodium().equals("Main stage")){
                                            if (!ai.isJustSpawned()){
                                                ai.setGoToPodium(1);
                                            }
                                        }
                                        if (artist.getPodium().equals("Stage 2")){
                                            if (!ai.isJustSpawned()){
                                                ai.setGoToPodium(2);
                                            }
                                        }
                                        if (artist.getPodium().equals("Stage 3")){
                                            if (!ai.isJustSpawned()){
                                                ai.setGoToPodium(3);
                                            }
                                        }
                                        if (artist.getPodium().equals("Stage 4")){
                                            if (!ai.isJustSpawned()){
                                                ai.setGoToPodium(4);
                                            }
                                        }
                                    }
                                }
                                case 3 -> {
                                    if (Math.random() <= 0.60) {
                                        if (artist.getPodium().equals("Main stage")){
                                            if (!ai.isJustSpawned()){
                                                ai.setGoToPodium(1);
                                            }
                                        }
                                        if (artist.getPodium().equals("Stage 2")){
                                            if (!ai.isJustSpawned()){
                                                ai.setGoToPodium(2);
                                            }
                                        }
                                        if (artist.getPodium().equals("Stage 3")){
                                            if (!ai.isJustSpawned()){
                                                ai.setGoToPodium(3);
                                            }
                                        }
                                        if (artist.getPodium().equals("Stage 4")){
                                            if (!ai.isJustSpawned()){
                                                ai.setGoToPodium(4);
                                            }
                                        }
                                    }
                                }
                                case 4 -> {
                                    if (Math.random() <= 0.80) {
                                        if (artist.getPodium().equals("Main stage")){
                                            if (!ai.isJustSpawned()){
                                                ai.setGoToPodium(1);
                                            }
                                        }
                                        if (artist.getPodium().equals("Stage 2")){
                                            if (!ai.isJustSpawned()){
                                                ai.setGoToPodium(2);
                                            }
                                        }
                                        if (artist.getPodium().equals("Stage 3")){
                                            if (!ai.isJustSpawned()){
                                                ai.setGoToPodium(3);
                                            }
                                        }
                                        if (artist.getPodium().equals("Stage 4")){
                                            if (!ai.isJustSpawned()){
                                                ai.setGoToPodium(4);
                                            }
                                        }
                                    }
                                }
                                case 5 -> {
                                    if (Math.random() <= 0.90) {
                                        if (artist.getPodium().equals("Main stage")){
                                            if (!ai.isJustSpawned()){
                                                ai.setGoToPodium(1);
                                            }
                                        }
                                        if (artist.getPodium().equals("Stage 2")){
                                            if (!ai.isJustSpawned()){
                                                ai.setGoToPodium(2);
                                            }
                                        }
                                        if (artist.getPodium().equals("Stage 3")){
                                            if (!ai.isJustSpawned()){
                                                ai.setGoToPodium(3);
                                            }
                                        }
                                        if (artist.getPodium().equals("Stage 4")){
                                            if (!ai.isJustSpawned()){
                                                ai.setGoToPodium(4);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }

                    ai.update();
                    ai.draw(new FXGraphics2D(simMap.getGraphicsContext2D()));

//                    if (ai.getTicker() == 250) {
//                        ai.setFest(false);
//                        ai.setTicker(0);
//                        if (ai.getStatus().equals("mainStage")) {
//                            ai.setMatrixes(ai.getBackFromMainStageMatrixes());
//                            ai.setStatus("mainStageBack");
//                        }
//                        if (ai.getStatus().equals("leftTinyStage")) {
//                            ai.setMatrixes(ai.getBackFromLeftTinyStage());
//                            ai.setStatus("leftTinyStageBack");
//                        }
//                        if (ai.getStatus().equals("rightTinyStage")) {
//                            ai.setMatrixes(ai.getBackFromRightTinyStage());
//                            ai.setStatus("rightTinyStageBack");
//                        }
//                        if (ai.getStatus().equals("middleTinyStage")) {
//                            ai.setMatrixes(ai.getBackFromMiddleTinyStage());
//                            ai.setStatus("middleTinyStageBack");
//                        }
//                    }
//                    if (ai.getStatus() != null) {
//                        if (ai.getStatus().equals("mainStage")) {
//                            ai.setTicker(ai.getTicker() + 1);
//                        }
//                        if (ai.getStatus().equals("leftTinyStage")) {
//                            ai.setTicker(ai.getTicker() + 1);
//                        }
//                        if (ai.getStatus().equals("middleTinyStage")) {
//                            ai.setTicker(ai.getTicker() + 1);
//                        }
//                        if (ai.getStatus().equals("rightTinyStage")) {
//                            ai.setTicker(ai.getTicker() + 1);
//                        }
//                    }
                }
            });
            lastTimeForTimer = currentTime;
        }

//        for (Artist artist : artists) {
//            LocalTime startingTime = LocalTime.parse(artist.getSetStartingTime());
//            Duration duration = Duration.between(LocalTime.MIDNIGHT, startingTime);
//            //might wanna change the next line to duration.toNanos() - whatever is equal to 30 minutes cuz idk xdd
//            if (duration.toNanos() == currentTime) {
//                for (newAi ai : realAis) {
//
//                    }
//                }
//            }
//        }
    }

    //    public void checkPos() {
//        List<Integer> keysToRemove = new ArrayList<>();
//        for (Map.Entry<Integer, Pos> e : this.positions.entrySet()) {
//            System.out.println(this.positions.size());
//            for (Map.Entry<Integer, Pos> f : this.positions.entrySet()) {
//                if (f.getValue().getX() == e.getValue().getX() && f.getValue().getY() == e.getValue().getY() && f.getKey() != e.getKey()) {
//                    keysToRemove.add(f.getKey());
//                }
//            }
//        }
//        for (Integer key : keysToRemove) {
//            this.positions.remove(key);
//        }
//    }
    public boolean isSpawn() {
        for (newAi ai : MyAnimationTimer.realAis) {
            if (ai.getX() == 664 && ai.getY() == 552) {
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
        timer.setMinutes(0);
        timer.setHours(10);
    }

    public void addHour() {
        if (timer.getHours() == 23) {
            timer.setHours(0);
            pastMidnight = true;
        } else {
            timer.setHours(timer.getHours()+1);
        }
        timer.setMinutes(0);
    }

    public int getHours() {
        return timer.getHours();
    }

    public void addMinute() {
        if (timer.getMinutes() < 59) {
            timer.setMinutes(timer.getMinutes()+1);
        } else {
            addHour();
        }
        if (timer.getMinutes() < 10) {
            timerLabel.setText("" + timer.getHours() + ":0" + timer.getMinutes());
        } else {
            timerLabel.setText("" + timer.getHours() + ":" + timer.getMinutes());
        }
    }


}

package classes;

import controllers.ScheduleController;
import controllers.SimulatorController;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import org.jfree.fx.FXGraphics2D;

import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

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
    private ArrayList<AI> ais;
    public static ArrayList<AI> realAis = new ArrayList<>();
    private Canvas simMap;
    private int index;
    public static ArrayList<Artist> artists;
    private ArrayList<BufferedImage> artistPlayerViewModels = new ArrayList<>();
    private Timer timer;
    private Festival festival;

    public MyAnimationTimer(Label timerLabel, ArrayList<AI> ais, Canvas simMap, Timer timer) throws IOException, ClassNotFoundException {
        this.timerLabel = timerLabel;
        this.ais = ais;
        this.simMap = simMap;
        this.timer = timer;
        artistPlayerViewModels.add(SimulatorController.greenAI.get(1));
        artistPlayerViewModels.add(SimulatorController.blueAI.get(1));
        artistPlayerViewModels.add(SimulatorController.purpleAI.get(1));
        artistPlayerViewModels.add(SimulatorController.goldAI.get(1));

        artists = new ArrayList<>();
        this.festival = ScheduleController.getFestival();
    }

    public static int getRandom4() {
        Random rand = new Random();
        return rand.nextInt(4);
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
            if (isSpawn()) {
                if (index != ais.size()) {
                    realAis.add(ais.get(index));
                    index++;
                }
            }
            if (isPastMidnight() && getHours() == 3) {
                stop();
                resetTimer();
            }
            Platform.runLater(this::addMinute);
            Platform.runLater(() ->
            {
                for (AI ai : realAis) {
                    //stuurt visitors op tijd naar podiums
                    for (Artist artist : this.festival.getArtists()) {
                        String time = artist.getSetStartingTime();
                        String[] parts = time.split(":");

                        int hours = Integer.parseInt(parts[0]);
                        int minutes = Integer.parseInt(parts[1]);

                        int allMinutesArtist = (hours * 60) + minutes;
                        int allMinutesTimer = (timer.getHours() * 60) + timer.getMinutes();

                        if (allMinutesArtist + artist.getSetDurationInMinutes() == allMinutesTimer) {
                            if (ai.getStatus().equals("mainStage")) {
                                ai.setMatrixes(ai.getBackFromMainStageMatrixes());
//                                ai.setFest(false);
                                ai.setStatus("mainStageBack");
                            }
                            if (ai.getStatus().equals("leftTinyStage")) {
                                ai.setMatrixes(ai.getBackFromLeftTinyStage());
//                                ai.setFest(false);
                                ai.setStatus("leftTinyStageBack");
                            }
                            if (ai.getStatus().equals("middleTinyStage")) {
                                ai.setMatrixes(ai.getBackFromMiddleTinyStage());
//                                ai.setFest(false);
                                ai.setStatus("middleTinyStageBack");
                            }
                            if (ai.getStatus().equals("rightTinyStage")) {
                                ai.setMatrixes(ai.getBackFromRightTinyStage());
//                                ai.setFest(false);
                                ai.setStatus("rightTinyStageBack");
                            }
                        }

                        if (allMinutesArtist - 90 == allMinutesTimer && !ai.isFest() && ai.getStatus().equals("")) {
                            switch (artist.getPopularity()) {
                                case 1 -> {
                                    if (Math.random() <= 0.20) {
                                        if (artist.getPodium().equals("Main stage")) {
                                            if (!ai.isJustSpawned()) {
                                                ai.setGoToPodium(1);
                                            }
                                        }
                                        if (artist.getPodium().equals("Stage 2")) {
                                            if (!ai.isJustSpawned()) {
                                                ai.setGoToPodium(2);
                                            }
                                        }
                                        if (artist.getPodium().equals("Stage 3")) {
                                            if (!ai.isJustSpawned()) {
                                                ai.setGoToPodium(3);
                                            }
                                        }
                                        if (artist.getPodium().equals("Stage 4")) {
                                            if (!ai.isJustSpawned()) {
                                                ai.setGoToPodium(4);
                                            }
                                        }
                                    }
                                }
                                case 2 -> {
                                    if (Math.random() <= 0.40) {
                                        if (artist.getPodium().equals("Main stage")) {
                                            if (!ai.isJustSpawned()) {
                                                ai.setGoToPodium(1);
                                            }
                                        }
                                        if (artist.getPodium().equals("Stage 2")) {
                                            if (!ai.isJustSpawned()) {
                                                ai.setGoToPodium(2);
                                            }
                                        }
                                        if (artist.getPodium().equals("Stage 3")) {
                                            if (!ai.isJustSpawned()) {
                                                ai.setGoToPodium(3);
                                            }
                                        }
                                        if (artist.getPodium().equals("Stage 4")) {
                                            if (!ai.isJustSpawned()) {
                                                ai.setGoToPodium(4);
                                            }
                                        }
                                    }
                                }
                                case 3 -> {
                                    if (Math.random() <= 0.60) {
                                        if (artist.getPodium().equals("Main stage")) {
                                            if (!ai.isJustSpawned()) {
                                                ai.setGoToPodium(1);
                                            }
                                        }
                                        if (artist.getPodium().equals("Stage 2")) {
                                            if (!ai.isJustSpawned()) {
                                                ai.setGoToPodium(2);
                                            }
                                        }
                                        if (artist.getPodium().equals("Stage 3")) {
                                            if (!ai.isJustSpawned()) {
                                                ai.setGoToPodium(3);
                                            }
                                        }
                                        if (artist.getPodium().equals("Stage 4")) {
                                            if (!ai.isJustSpawned()) {
                                                ai.setGoToPodium(4);
                                            }
                                        }
                                    }
                                }
                                case 4 -> {
                                    if (Math.random() <= 0.80) {
                                        if (artist.getPodium().equals("Main stage")) {
                                            if (!ai.isJustSpawned()) {
                                                ai.setGoToPodium(1);
                                            }
                                        }
                                        if (artist.getPodium().equals("Stage 2")) {
                                            if (!ai.isJustSpawned()) {
                                                ai.setGoToPodium(2);
                                            }
                                        }
                                        if (artist.getPodium().equals("Stage 3")) {
                                            if (!ai.isJustSpawned()) {
                                                ai.setGoToPodium(3);
                                            }
                                        }
                                        if (artist.getPodium().equals("Stage 4")) {
                                            if (!ai.isJustSpawned()) {
                                                ai.setGoToPodium(4);
                                            }
                                        }
                                    }
                                }
                                case 5 -> {
                                    if (Math.random() <= 0.90) {
                                        if (artist.getPodium().equals("Main stage")) {
                                            if (!ai.isJustSpawned()) {
                                                ai.setGoToPodium(1);
                                            }
                                        }
                                        if (artist.getPodium().equals("Stage 2")) {
                                            if (!ai.isJustSpawned()) {
                                                ai.setGoToPodium(2);
                                            }
                                        }
                                        if (artist.getPodium().equals("Stage 3")) {
                                            if (!ai.isJustSpawned()) {
                                                ai.setGoToPodium(3);
                                            }
                                        }
                                        if (artist.getPodium().equals("Stage 4")) {
                                            if (!ai.isJustSpawned()) {
                                                ai.setGoToPodium(4);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        String time1 = artist.getSetStartingTime();
                        String[] parts2 = time1.split(":");

                        int hours1 = Integer.parseInt(parts2[0]);
                        int minutes1 = Integer.parseInt(parts2[1]);

                        int allMinutesArtist1 = (hours1 * 60) + minutes1;
                        int allMinutesTimer1 = (timer.getHours() * 60) + timer.getMinutes();

                        if (allMinutesArtist1 + artist.getSetDurationInMinutes() < allMinutesTimer1) {
                            if (artist.getPodium().equals("Main stage")) {
                                BufferedImage image = SimulatorController.aisImage.get(90);
                                AffineTransform tx = new AffineTransform();
                                tx.translate((24 * 16) + 8 - (image.getWidth() / 2.0), (2 * 16) + 8 - (image.getHeight() / 2.0));
                                new FXGraphics2D(simMap.getGraphicsContext2D()).drawImage(image, tx, null);
                                this.festival.getArtists().remove(artist);

                            }
                            if (artist.getPodium().equals("Stage 2")) {
                                BufferedImage image = SimulatorController.aisImage.get(90);
                                AffineTransform tx = new AffineTransform();
                                tx.translate((2 * 16) + 8 - image.getWidth() / 2.0, (27 * 16) + 8 - image.getHeight() / 2.0);
                                new FXGraphics2D(simMap.getGraphicsContext2D()).drawImage(image, tx, null);
                                this.festival.getArtists().remove(artist);

                            }
                            if (artist.getPodium().equals("Stage 3")) {
                                BufferedImage image = SimulatorController.aisImage.get(90);
                                AffineTransform tx = new AffineTransform();
                                tx.translate((29 * 16) + 8 - image.getWidth() / 2.0, (26 * 16) + 8 - image.getHeight() / 2.0);
                                new FXGraphics2D(simMap.getGraphicsContext2D()).drawImage(image, tx, null);
                                this.festival.getArtists().remove(artist);

                            }
                            if (artist.getPodium().equals("Stage 4")) {
                                BufferedImage image = SimulatorController.aisImage.get(90);
                                AffineTransform tx = new AffineTransform();
                                tx.translate((51 * 16) + 8 - image.getWidth() / 2.0, (1 * 16) + 8 - image.getHeight() / 2.0);
                                new FXGraphics2D(simMap.getGraphicsContext2D()).drawImage(image, tx, null);
                                this.festival.getArtists().remove(artist);

                            }
                        }
                    }
                    //tekent artiesten op podiums als het tijd is
                    for (Artist artist : this.festival.getArtists()) {

                        int number = getRandom4();

                        String time = artist.getSetStartingTime();
                        String[] parts = time.split(":");

                        int hours = Integer.parseInt(parts[0]);
                        int minutes = Integer.parseInt(parts[1]);

                        int allMinutesArtist = (hours * 60) + minutes;
                        int allMinutesTimer = (timer.getHours() * 60) + timer.getMinutes();


                        if (allMinutesArtist == allMinutesTimer) {
                            if (artist.getPodium().equals("Main stage")) {
                                BufferedImage image = artistPlayerViewModels.get(number);
                                AffineTransform tx = new AffineTransform();
                                tx.translate((24 * 16) + 8 - (image.getWidth() / 2.0), (2 * 16) + 8 - (image.getHeight() / 2.0));
                                new FXGraphics2D(simMap.getGraphicsContext2D()).drawImage(image, tx, null);
                            }
                            if (artist.getPodium().equals("Stage 2")) {
                                BufferedImage image = artistPlayerViewModels.get(number);
                                AffineTransform tx = new AffineTransform();
                                tx.translate((2 * 16) + 8 - image.getWidth() / 2.0, (27 * 16) + 8 - image.getHeight() / 2.0);
                                new FXGraphics2D(simMap.getGraphicsContext2D()).drawImage(image, tx, null);
                            }
                            if (artist.getPodium().equals("Stage 3")) {
                                BufferedImage image = artistPlayerViewModels.get(number);
                                AffineTransform tx = new AffineTransform();
                                tx.translate((29 * 16) + 8 - image.getWidth() / 2.0, (26 * 16) + 8 - image.getHeight() / 2.0);
                                new FXGraphics2D(simMap.getGraphicsContext2D()).drawImage(image, tx, null);
                            }
                            if (artist.getPodium().equals("Stage 4")) {
                                BufferedImage image = artistPlayerViewModels.get(number);
                                AffineTransform tx = new AffineTransform();
                                tx.translate((51 * 16) + 8 - image.getWidth() / 2.0, (1 * 16) + 8 - image.getHeight() / 2.0);
                                new FXGraphics2D(simMap.getGraphicsContext2D()).drawImage(image, tx, null);
                            }
                        }


                    }
                    ai.update();
                    ai.draw(new FXGraphics2D(simMap.getGraphicsContext2D()));
                }
            });
            lastTimeForTimer = currentTime;
        }
    }

    public boolean isSpawn() {
        for (AI ai : MyAnimationTimer.realAis) {
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
            timer.setHours(timer.getHours() + 1);
        }
        timer.setMinutes(0);
    }

    public int getHours() {
        return timer.getHours();
    }

    public void addMinute() {
        if (timer.getMinutes() < 59) {
            timer.setMinutes(timer.getMinutes() + 1);
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

package controllers;

import classes.Artist;
import classes.Festival;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import org.jfree.fx.FXGraphics2D;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class ScheduleController {

    private ScheduleMakerController scheduleMakerController = new ScheduleMakerController();

    private ArrayList<Rectangle2D> blocksToDraw = new ArrayList<>();
    private int xBlock = 0;
    private int yBlock = 0;

    @FXML
    public Canvas canvasSchedule;
    @FXML
    void onImportButton() {
        Festival festivalObject = null;

        try {                                           //try importing file, showing error when unsuccessfull
            festivalObject = Serializer.DeserializeFestival();
            NotificationPromptController.notification(false, "Successfully import festival file :)");
        } catch (Exception e) {
            NotificationPromptController.notification(true, "Unable to import festival file :(");
        }

        ArrayList<Artist> allArtists = festivalObject.getArtists();
//        System.out.println(allArtists.size());
        ArtistArrayListController.artists.addAll(allArtists);
//        scheduleMakerController.refreshList();
//        scheduleMakerController.setArtists(allArtists);
//        System.out.println(ArtistArrayListController.artists.size());
//        scheduleMakerController.artists.add
//        scheduleMakerController.updateListView();

        calculateBlockToDraw(allArtists);

        draw(new FXGraphics2D(canvasSchedule.getGraphicsContext2D()));
    }

    public void calculateBlockToDraw(ArrayList<Artist> allArtists) {

        String startTimeHours;
        String startTimeMinutes;

        for (Artist artist : allArtists) {

            if (artist.getPodium().equals("Main stage")) {
                xBlock = 0;
                yBlock = 0;

                startTimeHours = artist.getSetStartingTime();
                startTimeHours = startTimeHours.substring(0, 2);

                startTimeMinutes = artist.getSetStartingTime();
                startTimeMinutes = startTimeMinutes.substring(3,5);

                if (Integer.parseInt(startTimeHours) == 10) {
                    yBlock = 0;
                }
                else {
                    if (Integer.parseInt(startTimeHours) == 0 || Integer.parseInt(startTimeHours) == 1 || Integer.parseInt(startTimeHours) == 2 || Integer.parseInt(startTimeHours) == 3) {

                        if (Integer.parseInt(startTimeHours) == 0){
                            yBlock = 14*29;
                        }

                        if (Integer.parseInt(startTimeHours) == 1){
                            yBlock = 15*29;
                        }

                        if (Integer.parseInt(startTimeHours) == 2){
                            yBlock = 16*29;
                        }

                        if (Integer.parseInt(startTimeHours) == 3){
                            yBlock = 17*29;
                        }
                    }
                    else {
                        for (int i = 10; i < Integer.parseInt(startTimeHours); i++) {
                            yBlock += 29;
                        }
                    }
                }

                if (Integer.parseInt(startTimeMinutes) == 30){
                    yBlock += 14;
                }

                if (artist.getSetDurationInMinutes() == 30){
                    blocksToDraw.add(new Rectangle2D.Double(xBlock, yBlock, 100, yBlock+200));
                }

                if (artist.getSetDurationInMinutes() == 60){
                    blocksToDraw.add(new Rectangle2D.Double(xBlock, yBlock, 100, yBlock+28));
                }

                if (artist.getSetDurationInMinutes() == 90){
                    blocksToDraw.add(new Rectangle2D.Double(xBlock, yBlock, 100, yBlock+42));
                }

                if (artist.getSetDurationInMinutes() == 120){
                    blocksToDraw.add(new Rectangle2D.Double(xBlock, yBlock, 100, yBlock+56));
                }
            }

            if (artist.getPodium().equals("Stage 2")) {
                xBlock = 0;
                yBlock = 0;
                xBlock += 100;

                startTimeHours = artist.getSetStartingTime();
                startTimeHours = startTimeHours.substring(0, 2);

                startTimeMinutes = artist.getSetStartingTime();
                startTimeMinutes = startTimeMinutes.substring(3,5);

                if (Integer.parseInt(startTimeHours) == 10) {
                    yBlock = 0;
                }
                else {
                    if (Integer.parseInt(startTimeHours) == 0 || Integer.parseInt(startTimeHours) == 1 || Integer.parseInt(startTimeHours) == 2 || Integer.parseInt(startTimeHours) == 3) {

                        if (Integer.parseInt(startTimeHours) == 0){
                            yBlock = 14*29;
                        }

                        if (Integer.parseInt(startTimeHours) == 1){
                            yBlock = 15*29;
                        }

                        if (Integer.parseInt(startTimeHours) == 2){
                            yBlock = 16*29;
                        }

                        if (Integer.parseInt(startTimeHours) == 3){
                            yBlock = 17*29;
                        }
                    }
                    else {
                        for (int i = 10; i < Integer.parseInt(startTimeHours); i++) {
                            yBlock += 29;
                        }
                    }
                }

                if (Integer.parseInt(startTimeMinutes) == 30){
                    yBlock += 14;
                }

                if (artist.getSetDurationInMinutes() == 30){
                    blocksToDraw.add(new Rectangle2D.Double(xBlock, yBlock, 100, yBlock+14));
                }

                if (artist.getSetDurationInMinutes() == 60){
                    blocksToDraw.add(new Rectangle2D.Double(xBlock, yBlock, 100, yBlock+28));
                }

                if (artist.getSetDurationInMinutes() == 90){
                    blocksToDraw.add(new Rectangle2D.Double(xBlock, yBlock, 100, yBlock+42));
                }

                if (artist.getSetDurationInMinutes() == 120){
                    blocksToDraw.add(new Rectangle2D.Double(xBlock, yBlock, 100, yBlock+56));
                }
            }

            if (artist.getPodium().equals("Stage 3")) {
                xBlock = 0;
                yBlock = 0;
                xBlock += 200;

                startTimeHours = artist.getSetStartingTime();
                startTimeHours = startTimeHours.substring(0, 2);

                startTimeMinutes = artist.getSetStartingTime();
                startTimeMinutes = startTimeMinutes.substring(3,5);

                if (Integer.parseInt(startTimeHours) == 10) {
                    yBlock = 0;
                }
                else {
                    if (Integer.parseInt(startTimeHours) == 0 || Integer.parseInt(startTimeHours) == 1 || Integer.parseInt(startTimeHours) == 2 || Integer.parseInt(startTimeHours) == 3) {

                        if (Integer.parseInt(startTimeHours) == 0){
                            yBlock = 14*29;
                        }

                        if (Integer.parseInt(startTimeHours) == 1){
                            yBlock = 15*29;
                        }

                        if (Integer.parseInt(startTimeHours) == 2){
                            yBlock = 16*29;
                        }

                        if (Integer.parseInt(startTimeHours) == 3){
                            yBlock = 17*29;
                        }
                    }
                    else {
                        for (int i = 10; i < Integer.parseInt(startTimeHours); i++) {
                            yBlock += 29;
                        }
                    }
                }

                if (Integer.parseInt(startTimeMinutes) == 30){
                    yBlock += 14;
                }

                if (artist.getSetDurationInMinutes() == 30){
                    blocksToDraw.add(new Rectangle2D.Double(xBlock, yBlock, 100, yBlock+14));
                }

                if (artist.getSetDurationInMinutes() == 60){
                    blocksToDraw.add(new Rectangle2D.Double(xBlock, yBlock, 100, yBlock+28));
                }

                if (artist.getSetDurationInMinutes() == 90){
                    blocksToDraw.add(new Rectangle2D.Double(xBlock, yBlock, 100, yBlock+42));
                }

                if (artist.getSetDurationInMinutes() == 120){
                    blocksToDraw.add(new Rectangle2D.Double(xBlock, yBlock, 100, yBlock+56));
                }
            }

            if (artist.getPodium().equals("Stage 4")) {
                xBlock = 0;
                yBlock = 0;
                xBlock += 300;

                startTimeHours = artist.getSetStartingTime();
                startTimeHours = startTimeHours.substring(0, 2);

                startTimeMinutes = artist.getSetStartingTime();
                startTimeMinutes = startTimeMinutes.substring(3,5);

                if (Integer.parseInt(startTimeHours) == 10) {
                    yBlock = 0;
                }
                else {
                    if (Integer.parseInt(startTimeHours) == 0 || Integer.parseInt(startTimeHours) == 1 || Integer.parseInt(startTimeHours) == 2 || Integer.parseInt(startTimeHours) == 3) {

                        if (Integer.parseInt(startTimeHours) == 0){
                            yBlock = 14*29;
                        }

                        if (Integer.parseInt(startTimeHours) == 1){
                            yBlock = 15*29;
                        }

                        if (Integer.parseInt(startTimeHours) == 2){
                            yBlock = 16*29;
                        }

                        if (Integer.parseInt(startTimeHours) == 3){
                            yBlock = 17*29;
                        }
                    }
                    else {
                        for (int i = 10; i < Integer.parseInt(startTimeHours); i++) {
                            yBlock += 29;
                        }
                    }
                }

                if (Integer.parseInt(startTimeMinutes) == 30){
                    yBlock += 14;
                }

                if (artist.getSetDurationInMinutes() == 30){
                    blocksToDraw.add(new Rectangle2D.Double(xBlock, yBlock, 100, yBlock+14));
                }

                if (artist.getSetDurationInMinutes() == 60){
                    blocksToDraw.add(new Rectangle2D.Double(xBlock, yBlock, 100, yBlock+28));
                }

                if (artist.getSetDurationInMinutes() == 90){
                    blocksToDraw.add(new Rectangle2D.Double(xBlock, yBlock, 100, yBlock+42));
                }

                if (artist.getSetDurationInMinutes() == 120){
                    blocksToDraw.add(new Rectangle2D.Double(xBlock, yBlock, 100, yBlock+56));
                }
            }
        }
    }

    public void draw(FXGraphics2D graphics) {

        Color[] colors = {Color.red, Color.blue, Color.GREEN, Color.YELLOW};

        int i = 0;

        for (Rectangle2D block : blocksToDraw) {

            graphics.setPaint(colors[i]);
            graphics.fill(block);
            i++;

            if (i == 4){
                i = 0;
            }
        }
    }
}

package controllers;

import classes.Artist;
import classes.Festival;
import classes.MyAnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;
import org.jfree.fx.FXGraphics2D;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.HashMap;

import static controllers.ArtistArrayListController.artists;

public class ScheduleController {

    @FXML
    public Canvas canvasSchedule;

    private ScheduleMakerController scheduleMakerController = new ScheduleMakerController();
    private ArrayList<Rectangle2D> blocksToDraw = new ArrayList<>();
    private ArrayList<String> artistNames = new ArrayList<>();
    private int xBlock = 0;
    private int yBlock = 0;
    HashMap<Rectangle2D, Artist> blocksConnectedToArtist = new HashMap<Rectangle2D, Artist>();
    ArrayList<Artist> allArtists = new ArrayList<>();
    static Festival festivalObject;

    @FXML
    void onImportButton() {

        artists.clear();
        this.festivalObject = null;
        try {                                           //try importing file, showing error when unsuccessfull
            festivalObject = Serializer.DeserializeFestival();
            NotificationPromptController.notification(false, "Successfully import festival file :)");
            allArtists = festivalObject.getArtists();
            MyAnimationTimer.artists.addAll(allArtists);
            artists.addAll(allArtists);
            calculateBlockToDraw(allArtists);
            draw(new FXGraphics2D(canvasSchedule.getGraphicsContext2D()));
        } catch (Exception e) {
            NotificationPromptController.notification(true, "Unable to import festival file :(");
            System.out.println(e);
        }
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
                startTimeMinutes = startTimeMinutes.substring(3, 5);

                if (Integer.parseInt(startTimeHours) == 10) {
                    yBlock = 0;
                } else {
                    if (Integer.parseInt(startTimeHours) == 0 || Integer.parseInt(startTimeHours) == 1 || Integer.parseInt(startTimeHours) == 2 || Integer.parseInt(startTimeHours) == 3) {

                        if (Integer.parseInt(startTimeHours) == 0) {
                            yBlock = 14 * 29;
                        }

                        if (Integer.parseInt(startTimeHours) == 1) {
                            yBlock = 15 * 29;
                        }

                        if (Integer.parseInt(startTimeHours) == 2) {
                            yBlock = 16 * 29;
                        }

                        if (Integer.parseInt(startTimeHours) == 3) {
                            yBlock = 17 * 29;
                        }
                    } else {
                        for (int i = 10; i < Integer.parseInt(startTimeHours); i++) {
                            yBlock += 29;
                        }
                    }
                }

                if (Integer.parseInt(startTimeMinutes) == 30) {
                    yBlock += 14;
                }

                if (artist.getSetDurationInMinutes() == 30) {
                    Rectangle2D block = new Rectangle2D.Double(xBlock + 1, yBlock, 98, 15);

                    blocksConnectedToArtist.put(block, artist);
                    blocksToDraw.add(block);
                    artistNames.add(artist.getName());
                }

                if (artist.getSetDurationInMinutes() == 60) {
                    Rectangle2D block = new Rectangle2D.Double(xBlock + 1, yBlock, 98, 30);

                    blocksConnectedToArtist.put(block, artist);
                    blocksToDraw.add(block);
                    artistNames.add(artist.getName());
                }

                if (artist.getSetDurationInMinutes() == 90) {
                    Rectangle2D block = new Rectangle2D.Double(xBlock + 1, yBlock, 98, 45);

                    blocksConnectedToArtist.put(block, artist);
                    blocksToDraw.add(block);
                    artistNames.add(artist.getName());
                }

                if (artist.getSetDurationInMinutes() == 120) {
                    Rectangle2D block = new Rectangle2D.Double(xBlock + 1, yBlock, 98, 60);

                    blocksConnectedToArtist.put(block, artist);
                    blocksToDraw.add(block);
                    artistNames.add(artist.getName());
                }
            }

            if (artist.getPodium().equals("Stage 2")) {
                xBlock = 0;
                yBlock = 0;
                xBlock += 100;

                startTimeHours = artist.getSetStartingTime();
                startTimeHours = startTimeHours.substring(0, 2);

                startTimeMinutes = artist.getSetStartingTime();
                startTimeMinutes = startTimeMinutes.substring(3, 5);

                if (Integer.parseInt(startTimeHours) == 10) {
                    yBlock = 0;
                } else {
                    if (Integer.parseInt(startTimeHours) == 0 || Integer.parseInt(startTimeHours) == 1 || Integer.parseInt(startTimeHours) == 2 || Integer.parseInt(startTimeHours) == 3) {

                        if (Integer.parseInt(startTimeHours) == 0) {
                            yBlock = 14 * 29;
                        }

                        if (Integer.parseInt(startTimeHours) == 1) {
                            yBlock = 15 * 29;
                        }

                        if (Integer.parseInt(startTimeHours) == 2) {
                            yBlock = 16 * 29;
                        }

                        if (Integer.parseInt(startTimeHours) == 3) {
                            yBlock = 17 * 29;
                        }
                    } else {
                        for (int i = 10; i < Integer.parseInt(startTimeHours); i++) {
                            yBlock += 29;
                        }
                    }
                }

                if (Integer.parseInt(startTimeMinutes) == 30) {
                    yBlock += 14;
                }

                if (artist.getSetDurationInMinutes() == 30) {
                    Rectangle2D block = new Rectangle2D.Double(xBlock, yBlock, 100, 15);

                    blocksConnectedToArtist.put(block, artist);
                    blocksToDraw.add(block);
                }

                if (artist.getSetDurationInMinutes() == 60) {
                    Rectangle2D block = new Rectangle2D.Double(xBlock, yBlock, 100, 30);

                    blocksConnectedToArtist.put(block, artist);
                    blocksToDraw.add(block);
                }

                if (artist.getSetDurationInMinutes() == 90) {
                    Rectangle2D block = new Rectangle2D.Double(xBlock, yBlock, 100, 45);

                    blocksConnectedToArtist.put(block, artist);
                    blocksToDraw.add(block);
                }

                if (artist.getSetDurationInMinutes() == 120) {
                    Rectangle2D block = new Rectangle2D.Double(xBlock, yBlock, 100, 60);

                    blocksConnectedToArtist.put(block, artist);
                    blocksToDraw.add(block);
                }
            }

            if (artist.getPodium().equals("Stage 3")) {
                xBlock = 0;
                yBlock = 0;
                xBlock += 200;

                startTimeHours = artist.getSetStartingTime();
                startTimeHours = startTimeHours.substring(0, 2);

                startTimeMinutes = artist.getSetStartingTime();
                startTimeMinutes = startTimeMinutes.substring(3, 5);

                if (Integer.parseInt(startTimeHours) == 10) {
                    yBlock = 0;
                } else {
                    if (Integer.parseInt(startTimeHours) == 0 || Integer.parseInt(startTimeHours) == 1 || Integer.parseInt(startTimeHours) == 2 || Integer.parseInt(startTimeHours) == 3) {

                        if (Integer.parseInt(startTimeHours) == 0) {
                            yBlock = 14 * 29;
                        }

                        if (Integer.parseInt(startTimeHours) == 1) {
                            yBlock = 15 * 29;
                        }

                        if (Integer.parseInt(startTimeHours) == 2) {
                            yBlock = 16 * 29;
                        }

                        if (Integer.parseInt(startTimeHours) == 3) {
                            yBlock = 17 * 29;
                        }
                    } else {
                        for (int i = 10; i < Integer.parseInt(startTimeHours); i++) {
                            yBlock += 29;
                        }
                    }
                }

                if (Integer.parseInt(startTimeMinutes) == 30) {
                    yBlock += 14;
                }

                if (artist.getSetDurationInMinutes() == 30) {
                    Rectangle2D block = new Rectangle2D.Double(xBlock, yBlock, 100, 15);

                    blocksConnectedToArtist.put(block, artist);
                    blocksToDraw.add(block);
                }

                if (artist.getSetDurationInMinutes() == 60) {
                    Rectangle2D block = new Rectangle2D.Double(xBlock, yBlock, 100, 30);

                    blocksConnectedToArtist.put(block, artist);
                    blocksToDraw.add(block);
                }

                if (artist.getSetDurationInMinutes() == 90) {
                    Rectangle2D block = new Rectangle2D.Double(xBlock, yBlock, 100, 45);

                    blocksConnectedToArtist.put(block, artist);
                    blocksToDraw.add(block);
                }

                if (artist.getSetDurationInMinutes() == 120) {
                    Rectangle2D block = new Rectangle2D.Double(xBlock, yBlock, 100, 60);

                    blocksConnectedToArtist.put(block, artist);
                    blocksToDraw.add(block);
                }
            }

            if (artist.getPodium().equals("Stage 4")) {
                xBlock = 0;
                yBlock = 0;
                xBlock += 300;

                startTimeHours = artist.getSetStartingTime();
                startTimeHours = startTimeHours.substring(0, 2);

                startTimeMinutes = artist.getSetStartingTime();
                startTimeMinutes = startTimeMinutes.substring(3, 5);

                if (Integer.parseInt(startTimeHours) == 10) {
                    yBlock = 0;
                } else {
                    if (Integer.parseInt(startTimeHours) == 0 || Integer.parseInt(startTimeHours) == 1 || Integer.parseInt(startTimeHours) == 2 || Integer.parseInt(startTimeHours) == 3) {

                        if (Integer.parseInt(startTimeHours) == 0) {
                            yBlock = 14 * 29;
                        }

                        if (Integer.parseInt(startTimeHours) == 1) {
                            yBlock = 15 * 29;
                        }

                        if (Integer.parseInt(startTimeHours) == 2) {
                            yBlock = 16 * 29;
                        }

                        if (Integer.parseInt(startTimeHours) == 3) {
                            yBlock = 17 * 29;
                        }
                    } else {
                        for (int i = 10; i < Integer.parseInt(startTimeHours); i++) {
                            yBlock += 29;
                        }
                    }
                }

                if (Integer.parseInt(startTimeMinutes) == 30) {
                    yBlock += 14;
                }

                if (artist.getSetDurationInMinutes() == 30) {
                    Rectangle2D block = new Rectangle2D.Double(xBlock, yBlock, 98, 15);

                    blocksConnectedToArtist.put(block, artist);
                    blocksToDraw.add(block);
                }

                if (artist.getSetDurationInMinutes() == 60) {
                    Rectangle2D block = new Rectangle2D.Double(xBlock, yBlock, 98, 30);

                    blocksConnectedToArtist.put(block, artist);
                    blocksToDraw.add(block);
                }

                if (artist.getSetDurationInMinutes() == 90) {
                    Rectangle2D block = new Rectangle2D.Double(xBlock, yBlock, 98, 45);

                    blocksConnectedToArtist.put(block, artist);
                    blocksToDraw.add(block);
                }

                if (artist.getSetDurationInMinutes() == 120) {
                    Rectangle2D block = new Rectangle2D.Double(xBlock, yBlock, 98, 60);

                    blocksConnectedToArtist.put(block, artist);
                    blocksToDraw.add(block);
                }
            }
        }
    }

    public static Festival getFestival() {
        return festivalObject;
    }

    public void draw(FXGraphics2D graphics) {

        graphics.setStroke(new BasicStroke(2));

        Color[] colors = {Color.red, Color.blue, Color.GREEN, Color.YELLOW};

        canvasSchedule.setOnMousePressed(e -> mousePressed(e));

        int i = 0;

        for (Rectangle2D block : blocksToDraw) {
            graphics.setPaint(colors[i]);
            graphics.fill(block);

            i++;

            if (i == 4) {
                i = 0;
            }
        }
    }

    private void mousePressed(MouseEvent event) {
        for (Rectangle2D block : blocksToDraw) {
            if (block.contains(event.getX(), event.getY())) {
                Artist artist = blocksConnectedToArtist.get(block);
                NotificationPromptController.notification(false, "Name: " + artist.getName() + "\n"
                        + "Time: " + artist.getSetStartingTime() + " + " + artist.getSetDurationInMinutes() + " minuten" + "\n" +
                        "Genre: " + artist.getGenre() + "\n" + "Podium: " + artist.getPodium() + "\n" +
                        "Popularity: " + artist.getPopularity());
            }
        }
    }
}

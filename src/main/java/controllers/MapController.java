package controllers;

import classes.Map;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import org.jfree.fx.FXGraphics2D;

import java.awt.*;
import java.io.FileNotFoundException;

public class MapController {
    //FXML attributen
    @FXML
    public Canvas canvasMap;
    @FXML
    public Canvas bottom;

    //Klasse attributen
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
    }

    public void drawMap(Graphics2D g) {
        map.draw(g);
    }

    public void drawBottom(Graphics2D g) {
        bottomMap.draw(g);
    }
}

package controllers;

import classes.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.SVGPath;
import org.jfree.fx.FXGraphics2D;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

import java.util.ArrayList;

public class CalendarController {
    private ArrayList<Artist> artists;

    public CalendarController(ArrayList<Artist> artists) {
        this.artists = artists;
    }



    @Override
    public String toString() {
        return "CalendarController{" +
                "artists=" + artists +
                '}';
    }
}

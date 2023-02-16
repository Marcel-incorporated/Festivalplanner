package controllers;

import classes.Artist;

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

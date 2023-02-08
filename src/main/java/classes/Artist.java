package classes;

import java.io.Serializable;
import java.util.ArrayList;

public class Artist implements Serializable {
    private String name;
    private int popularity;
//    private ArrayList<Song> songs = new ArrayList<>();
    private String setStartingTime;
    private int setDurationInMinutes;
    private String genre;
    private Genre genreInEnum;


    public Artist(String name, String genre, int popularity, String setStartingTime, int setDurationInMinutes) {
        this.setStartingTime = setStartingTime;
        this.setDurationInMinutes = setDurationInMinutes;
        this.name = name;
        this.popularity = popularity;
//        this.songs = songs;
        this.genre = genre;
    }
    @Override
    public String toString() {
        return "Artist{" +
                "name='" + name + '\'' +
                ", popularity=" + popularity +
                '}';
    }
}

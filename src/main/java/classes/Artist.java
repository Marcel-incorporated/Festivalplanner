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
    private String podium;


    public Artist(String name, String genre, int popularity, String setStartingTime, int setDurationInMinutes, String podium) {
        this.podium = podium;
        this.setStartingTime = setStartingTime;
        this.setDurationInMinutes = setDurationInMinutes;
        this.name = name;
        this.popularity = popularity;
//        this.songs = songs;
        this.genre = genre;
    }

    public String getName() {
        return name;
    }

    public int getPopularity() {
        return popularity;
    }

    public String getSetStartingTime() {
        return setStartingTime;
    }

    public int getSetDurationInMinutes() {
        return setDurationInMinutes;
    }

    public String getGenre() {
        return genre;
    }

    public Genre getGenreInEnum() {
        return genreInEnum;
    }

    public String getPodium() {
        return podium;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    public void setSetStartingTime(String setStartingTime) {
        this.setStartingTime = setStartingTime;
    }

    public void setSetDurationInMinutes(int setDurationInMinutes) {
        this.setDurationInMinutes = setDurationInMinutes;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setGenreInEnum(Genre genreInEnum) {
        this.genreInEnum = genreInEnum;
    }

    public void setPodium(String podium) {
        this.podium = podium;
    }

    public String toString() {
        return name;
    }
}

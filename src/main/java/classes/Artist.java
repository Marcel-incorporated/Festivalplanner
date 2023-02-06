package classes;

import interfaces.PlanningImporter;

import java.io.Serializable;
import java.util.ArrayList;

public class Artist implements PlanningImporter, Serializable {
    private String name;
    private int popularity;
    private ArrayList<Song> songs = new ArrayList<>();

    private int id;

    public Artist(String name, int popularity, ArrayList<Song> songs, int id) {
        this.name = name;
        this.popularity = popularity;
        this.songs = songs;
        this.id = id;
    }
    @Override
    public String toString() {
        return "Artist{" +
                "name='" + name + '\'' +
                ", popularity=" + popularity +
                ", songs=" + songs +
                ", id=" + id +
                '}';
    }
}

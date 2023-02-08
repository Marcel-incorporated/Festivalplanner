package classes;

import java.io.Serializable;
import java.util.ArrayList;

public class Artist implements Serializable {
    private String name;
    private int popularity;
    private ArrayList<Song> songs = new ArrayList<>();

    private Genre genre;

    private int id;

    public Artist(String name, int popularity, ArrayList<Song> songs, int id, Genre genre) {

        this.name = name;
        this.popularity = popularity;
        this.songs = songs;
        this.id = id;
        this.genre = genre;
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

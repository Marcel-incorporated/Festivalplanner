package classes;

import java.io.Serializable;

/**
 * constructor voor een song.
 */
public class Song implements Serializable {
    private String name;
    private int length;
    private String genre;
    private int id;

    public Song(String name, int length, String genre, int id) {
        this.name = name;
        this.length = length;
        this.genre = genre;
        this.id = id;
    }

    @Override
    public String toString() {
        return "Song{" +
                "name='" + name + '\'' +
                ", length=" + length +
                ", genre='" + genre + '\'' +
                ", id=" + id +
                '}';
    }
}

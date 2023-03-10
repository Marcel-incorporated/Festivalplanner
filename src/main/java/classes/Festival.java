package classes;

import java.io.Serializable;
import java.util.ArrayList;

public class Festival implements Serializable {
    private ArrayList<Visitor> visitorList;
    private String name;
    private ArrayList<Artist> artists = new ArrayList<>();

    public Festival(ArrayList<Visitor> visitors, String name, ArrayList<Artist> artists) {
        this.visitorList = visitors;
        this.name = name;
        this.artists = artists;
    }

    @Override
    public String toString() {
        return "Festival{" +
                "visitorList=" + visitorList +
                ", name='" + name + '\'' +
                ", artists=" + artists +
                '}';
    }

    public ArrayList<Artist> getArtists() {
        return artists;
    }
}

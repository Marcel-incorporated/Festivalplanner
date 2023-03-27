package classes;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Constructor voor de festival
 */
public class Festival implements Serializable {
    private ArrayList<Visitor> visitors;
    private String name;
    private ArrayList<Artist> artists = new ArrayList<>();

    public Festival(ArrayList<Visitor> visitors, String name, ArrayList<Artist> artists) {
        this.visitors = visitors;
        this.name = name;
        this.artists = artists;
    }

    public Festival() {

    }

    @Override
    public String toString() {
        return "Festival{" +
                "visitorList=" + visitors +
                ", name='" + name + '\'' +
                ", artists=" + artists +
                '}';
    }

    public ArrayList<Artist> getArtists() {
        return artists;
    }

}

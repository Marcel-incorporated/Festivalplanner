package classes;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Festival implements Serializable {
    private ArrayList<Visitor> visitorList;
    private String name;
    private ArrayList<Artist> artists;

    public Festival(ArrayList<Visitor> visitors, String name, ArrayList<Artist> artists) {
        this.visitorList = visitors;
        this.name = name;
        this.artists = artists;
    }
//
//    public void addToVisitorList(Visitor visitor) {
//        visitorList.add(visitor);
//    }

    @Override
    public String toString() {
        return "Festival{" +
                "visitorList=" + visitorList +
                ", name='" + name + '\'' +
                '}';
    }
}

package classes;

import java.io.Serializable;
import java.util.ArrayList;

public class Festival implements Serializable {
    private ArrayList<Visitor> visitorList;
    private String name;
    private ArrayList<Performance> performances = new ArrayList<>();

    public Festival(ArrayList<Visitor> visitors, String name, ArrayList<Performance> performances) {
        this.visitorList = new ArrayList<>();
        this.visitorList = visitors;
        this.name = name;
        this.performances = performances;
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
                ", performances=" + performances +
                '}';
    }
}

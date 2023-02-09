package classes;

import java.io.Serializable;
import java.util.ArrayList;

public class Festival implements Serializable {
    private ArrayList<Visitor> visitorList;
    private String startTime;
    private String endTime;
    private String name;
    private ArrayList<Performance> performances = new ArrayList<>();
    private int numberOfVisitors;

    public Festival(int numberOfVisitors, String name, ArrayList<Performance> performances) {
        this.visitorList = new ArrayList<>();
        this.numberOfVisitors = numberOfVisitors;
        this.name = name;
        this.performances = performances;
    }

    public void addToVisitorList(Visitor visitor) {
        visitorList.add(visitor);
    }

    @Override
    public String toString() {
        return "Festival{" +
                "visitorList=" + visitorList +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", name='" + name + '\'' +
                ", performances=" + performances +
                ", numberOfVisitors=" + numberOfVisitors +
                '}';
    }
}

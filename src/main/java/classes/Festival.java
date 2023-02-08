package classes;

import java.io.Serializable;
import java.util.ArrayList;

public class Festival implements Serializable {
    private ArrayList<Visitor> visitorList;
    private String startTime;
    private String endTime;
    private String name;
    private ArrayList<Performance> performances = new ArrayList<>();
    private String date;
    private int numberOfVisitors;

    public Festival(int numberOfVisitors, String name, ArrayList<Performance> performances, String date) {
        this.visitorList = new ArrayList<>();
        this.numberOfVisitors = numberOfVisitors;
        this.name = name;
        this.performances = performances;
        this.date = date;
    }

    public void addToVisitorList(Visitor visitor) {
        visitorList.add(visitor);
    }

    @Override
    public String toString() {
        return "Festival{" +
                "visitors=" + visitorList +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", name='" + name + '\'' +
                ", performances=" + performances +
                ", date='" + date + '\'' +
                '}';
    }
}

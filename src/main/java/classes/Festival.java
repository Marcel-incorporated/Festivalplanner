package classes;

import java.io.Serializable;
import java.util.ArrayList;

public class Festival implements Serializable {
    private ArrayList<Visitor> visitors = new ArrayList<>();
    private String startTime;
    private String endTime;
    private String name;
    private ArrayList<Performance> performances = new ArrayList<>();
    private String date;

    public Festival(ArrayList<Visitor> visitors, String startTime, String endTime, String name, ArrayList<Performance> performances, String date) {
        this.visitors = visitors;
        this.startTime = startTime;
        this.endTime = endTime;
        this.name = name;
        this.performances = performances;
        this.date = date;
    }

    @Override
    public String toString() {
        return "Festival{" +
                "visitors=" + visitors +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", name='" + name + '\'' +
                ", performances=" + performances +
                ", date='" + date + '\'' +
                '}';
    }
}

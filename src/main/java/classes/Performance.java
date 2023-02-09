package classes;

import java.io.Serializable;

public class Performance implements Serializable {
    private Artist artist;
    private String startPerformance;
    private String endPerformance;
    private String podium;

    public Performance(Artist artist, String startPerformance, String endPerformance, String podium) {
        this.artist = artist;
        this.startPerformance = startPerformance;
        this.endPerformance = endPerformance;
        this.podium = podium;
    }

    @Override
    public String toString() {
        return "Performance{" +
                "artist=" + artist +
                ", startPerformance='" + startPerformance + '\'' +
                ", endPerformance='" + endPerformance + '\'' +
                ", podium='" + podium + '\'' +
                '}';
    }
}

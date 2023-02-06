package tests;

import classes.*;
import controllers.Serializer;

import java.io.IOException;
import java.util.ArrayList;

public class TestSerialzerOnMultiObjects {



    public static void main(String[] args) throws IOException, ClassNotFoundException {

        Song song1 = new Song("name", 30, "rock", 1);
        Song song2 = new Song("name", 30, "rock", 1);
        Song song3 = new Song("name", 30, "rock", 2);
        Song song4 = new Song("name", 30, "rock", 2);
        Song song5 = new Song("name", 30, "rock", 2);

        ArrayList<Song> JohnsSongs = new ArrayList<>();
        ArrayList<Song> KimsSongs = new ArrayList<>();

        JohnsSongs.add(song1);
        JohnsSongs.add(song2);

        KimsSongs.add(song3);
        KimsSongs.add(song4);
        KimsSongs.add(song5);

        Artist artist1 = new Artist("john", 3, JohnsSongs, 1);
        Artist artist2 = new Artist("kim", 1, KimsSongs, 2);

        ArrayList<Artist> artists = new ArrayList<>();

        artists.add(artist1);
        artists.add(artist2);

        Visitor visitor1 = new Visitor("joost", 22, "man", "joostDeKIP420@lol.com");
        Visitor visitor2 = new Visitor("jaap", 52, "vrouw", "jaap2009@serieus.com");

        ArrayList<Visitor> visitors = new ArrayList<>();

        visitors.add(visitor1);
        visitors.add(visitor2);

        Performance performance1 = new Performance(artist1, "10:00", "11:00", "A");
        Performance performance2 = new Performance(artist2, "13:00", "17:00", "C");

        ArrayList<Performance> performances = new ArrayList<>();

        performances.add(performance1);
        performances.add(performance2);

        Festival festival = new Festival(visitors, "00:00", "00:00", "HardKipStyle", performances, "20-20-2034");

        Serializer.Serialize(festival);

        Festival festival1 = Serializer.Deserialize();
        System.out.println(festival1.toString());
    }
}

package tests;

import java.io.*;

import controllers.Visitor;

import controllers.Serializer;

public class TestSerialize {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Visitor a = new Visitor("Peter", 69, "Vaatwasser", "peter@gmail.com");

        Serializer.Serialize(a);

        Visitor b = Serializer.Deserialize();

        System.out.println(b.toString());
    }
}
package controllers;

import classes.Festival;

import java.io.*;

public class Serializer {

    public static void Serialize(Festival festival) throws IOException {

        FileOutputStream fos = new FileOutputStream("planning.txt");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(festival);

        if (oos != null) {
            oos.close();
        }
    }

    public static Festival Deserialize() throws IOException, ClassNotFoundException {

        FileInputStream fis = new FileInputStream("planning.txt");
        ObjectInputStream ois = new ObjectInputStream(fis);
        Festival festival = (Festival) ois.readObject();

        if (ois != null) {
            ois.close();
        }

        return festival;
    }
}

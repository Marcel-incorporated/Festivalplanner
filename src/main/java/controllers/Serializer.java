package controllers;

import java.io.*;

public class Serializer {

    public static void Serialize(Visitor x) throws IOException {

        FileOutputStream fos = new FileOutputStream("Visitors.txt");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(x);

        if (oos != null) {
            oos.close();
        }
    }

    public static Visitor Deserialize() throws IOException, ClassNotFoundException {

        FileInputStream fis = new FileInputStream("Visitors.txt");
        ObjectInputStream ois = new ObjectInputStream(fis);
        Visitor visitor = (Visitor) ois.readObject();

        if (ois != null) {
            ois.close();
        }

        return visitor;
    }
}

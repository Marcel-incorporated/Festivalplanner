package controllers;

import classes.Visitor;
import interfaces.PlanningImporter;

import java.io.*;

public class Serializer {

    public static void Serialize(PlanningImporter x) throws IOException {

        FileOutputStream fos = new FileOutputStream("planning.txt");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(x);

        if (oos != null) {
            oos.close();
        }
    }

    public static PlanningImporter Deserialize() throws IOException, ClassNotFoundException {

        FileInputStream fis = new FileInputStream("planning.txt");
        ObjectInputStream ois = new ObjectInputStream(fis);
        PlanningImporter planningObject = (PlanningImporter) ois.readObject();

        if (ois != null) {
            ois.close();
        }

        return planningObject;
    }
}

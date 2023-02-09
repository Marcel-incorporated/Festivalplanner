package controllers;

import classes.Festival;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.*;

public class Serializer {

    public static void Serialize(Festival festival) throws IOException {
        Stage stage = new Stage();
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setInitialDirectory(new File("src"));
        File selectedDirectory = directoryChooser.showDialog(stage);
        File file = new File(selectedDirectory.getAbsolutePath());
        FileOutputStream fos = new FileOutputStream(file + "/planning.txt");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(festival);

        if (oos != null) {
            oos.close();
        }
    }

    public static Festival Deserialize() throws IOException, ClassNotFoundException {

        FileInputStream fis = new FileInputStream("planning. txt");
        ObjectInputStream ois = new ObjectInputStream(fis);
        Festival festival = (Festival) ois.readObject();

        if (ois != null) {
            ois.close();
        }

        return festival;
    }
}

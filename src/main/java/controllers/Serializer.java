package controllers;

import classes.Festival;
import classes.Map;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.*;
import java.io.*;

public class Serializer {
    private Desktop desktop = Desktop.getDesktop();

    public static void Serialize(Festival festival) throws IOException {
        Stage stage = new Stage();
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Choose a directory to save file");
        File selectedDirectory = directoryChooser.showDialog(stage);
        File dir = new File(selectedDirectory.getAbsolutePath());
        FileOutputStream fos = new FileOutputStream(dir + "/planning.txt");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(festival);

        if (oos != null) {
            oos.close();
        }
    }

    public static void Serialize(Map map) throws IOException {
        Stage stage = new Stage();
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Choose a directory to save file");
        File selectedDirectory = directoryChooser.showDialog(stage);
        File dir = new File(selectedDirectory.getAbsolutePath());
        FileOutputStream fos = new FileOutputStream(dir + "/map.txt");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(map);

        if (oos != null) {
            oos.close();
        }
    }

    public static Festival DeserializeFestival() throws IOException, ClassNotFoundException {
        Stage stage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select the file");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text file", "*.txt"));
        File selected = fileChooser.showOpenDialog(stage);
        File file = new File(selected.getAbsolutePath());
        System.out.println(file);
//        System.out.println("filechooser created");

//        System.out.println("handle method started");
//        List<File> list =
//                fileChooser.showOpenMultipleDialog(((Node) event.getTarget()).getScene().getWindow());
//        System.out.println("opened filechooser");
//        if (list != null) {
//            for (File file : list) {
//                try {
//                    desktop.open(file);
//                } catch (IOException ex) {
//                    Logger.getLogger(
//                            getClass().getName()).log(
//                            Level.SEVERE, null, ex
//                    );
//                }
//            }
//        }
        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fis);
        Festival festival = (Festival) ois.readObject();

        if (ois != null) {
            ois.close();
        }
        System.out.println(festival.toString());
        return festival;
    }

    public static Map DeserializeMap() throws IOException, ClassNotFoundException {
        Stage stage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select the file");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text file", "*.txt"));
        File selected = fileChooser.showOpenDialog(stage);
        File file = new File(selected.getAbsolutePath());
        System.out.println(file);

        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fis);
        Map map = (Map) ois.readObject();

        if (ois != null) {
            ois.close();
        }
        System.out.println(map.toString());
        return map;
    }
}

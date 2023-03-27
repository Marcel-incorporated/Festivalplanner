package controllers;

import classes.Festival;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.file.StandardCopyOption.*;


public class Serializer {
    private Desktop desktop = Desktop.getDesktop();

    public static void Serialize(Festival festival) throws IOException {
        Stage stage = new Stage();
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Choose a directory to save file");
        File selectedDirectory = directoryChooser.showDialog(stage);
        try {
            File dir = new File(selectedDirectory.getAbsolutePath());
            FileOutputStream fos = new FileOutputStream(dir + "/planning.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(festival);

            if (oos != null) {
                oos.close();
            }
        } catch (Exception e) {
            throw new IOException();
        }

    }

    public static Festival DeserializeFestival() throws IOException, ClassNotFoundException {
        Stage stage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select the file");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text file", "*.txt"));
        File selected = fileChooser.showOpenDialog(stage);
        File file = new File(selected.getAbsolutePath());
        FileInputStream fis = new FileInputStream(file);
//        String path = System.getProperty("user.dir") +"\\src\\main\\resources\\planning.txt";
//        Files.copy(file, path, REPLACE_EXISTING);
        File copy = new File(System.getProperty("user.dir") +"\\src\\main\\resources\\planning.txt");
        FileOutputStream fos = new FileOutputStream(copy);
//        int c;
//        while((c = fis.read()) != 0) {
//            fos.write(c);
//        }
        ObjectInputStream ois = new ObjectInputStream(fis);
        Festival festival = (Festival) ois.readObject();

        if (ois != null) {
            ois.close();
        }
        System.out.println(festival.toString());
        return festival;
    }
}

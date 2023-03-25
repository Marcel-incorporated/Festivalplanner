package tests;

import classes.Matrix;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class testMatrix {
    public static void main(String[] args) throws FileNotFoundException
    {
        Matrix matrix = new Matrix(5, 5);
        matrix.updateAround(2, 3, 0);
        System.out.println(matrix);
    }
}

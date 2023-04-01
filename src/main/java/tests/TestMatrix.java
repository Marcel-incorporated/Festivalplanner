package tests;

import classes.Matrix;

import java.io.FileNotFoundException;

public class TestMatrix
{
    public static void main(String[] args) throws FileNotFoundException
    {
        Matrix matrix = new Matrix(5, 5);
        matrix.updateAround(2, 3, 0);
        System.out.println(matrix);
    }
}

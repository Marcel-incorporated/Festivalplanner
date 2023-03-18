package tests;

import classes.Matrix;

public class testMatrix
{
    public static void main(String[] args) {
        Matrix matrix = new Matrix(35, 56);
        matrix.updateAround(5, 3, 0);
        System.out.println(matrix);
    }
}

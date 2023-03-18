package classes;

import java.util.Arrays;

public class Matrix {
    private int[][] matrix;
    private int numRows;
    private int numCols;

    public Matrix(int numRows, int numCols) {
        this.numRows = numRows;
        this.numCols = numCols;
        matrix = new int[numRows][numCols];
    }

    public void set(int row, int col, int value) {
        matrix[row][col] = value;
    }

    public int get(int row, int col) {
        return matrix[row][col];
    }

    public void updateAround(int row, int col, int value) {
        int centerRow = row;
        int centerCol = col;

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                int distance = Math.max(Math.abs(i - centerRow), Math.abs(j - centerCol));
                set(i, j, value + distance);
            }
        }
    }


    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int[] row : matrix) {
            sb.append(Arrays.toString(row)).append("\n");
        }
        return sb.toString();
    }
}
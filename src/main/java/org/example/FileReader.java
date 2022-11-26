package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileReader {

    private final List<String> valuesB = new ArrayList<>();
    private final List<String> valuesC = new ArrayList<>();
    private final List<String> valuesS = new ArrayList<>();

    protected int rows, cols;
    int countOfS = 0;
    File file;

    public FileReader(File file, int rows, int cols) {
        this.file = file;
        this.rows = rows;
        this.cols = cols;
    }

    private void fileToStringArray(File file, int rowSize, int columnSize) throws FileNotFoundException {
        rows = rowSize;
        cols = columnSize;
        Scanner scanner = new Scanner(file);

        for (int row = 0; scanner.hasNextLine() && row < rowSize; row++) {
            String[] values = scanner.nextLine().split("\\s+");
            if (row > 1  && row < rowSize - 2) {
                countOfS++;
            }
            for (String value : values) {
                if (row == 0) {
                    valuesC.add(value);
                } else if (row == rowSize - 1) {
                    valuesB.add(value);
                } else {
                    valuesS.add(value);
                }
            }
        }

        valuesS.remove(0);
        valuesS.remove(valuesS.size() - 1);
        scanner.close();
    }


    private double[][] transformToMatrix() {
        rows -= 3;
        cols = cols + countOfS + 1;
        int tempLengthOfCArray = 0;
        int tempLengthOfBArray = 0;
        int tempLengthOfSArray = 0;
        int tempCount = 0;

        double[][] result = new double[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (i == rows - 1 && j < cols - countOfS - 1) {
                    result[i][j] = Double.parseDouble(valuesC.get(tempLengthOfCArray)) * -1;
                    tempLengthOfCArray++;
                } else if (j == cols - 1) {
                    if(tempLengthOfBArray < valuesB.size()) {
                        result[i][j] = Double.parseDouble(valuesB.get(tempLengthOfBArray));
                        tempLengthOfBArray++;
                    }

                } else if (j < cols - countOfS - 1) {
                    result[i][j] = Double.parseDouble(valuesS.get(tempLengthOfSArray));
                    tempLengthOfSArray++;
                } else if (cols - countOfS - 1 + tempCount < cols - 1) {
                    result[tempCount][j] = 1;
                    tempCount++;
                }
            }
        }
        return result;
    }


    public double[][] fileToArray(File file, int rowSize, int columnSize) {
        try {
            fileToStringArray(file, rowSize, columnSize);
            return transformToMatrix();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}

package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        File input = new File("Input.txt");
        File output = new File("Result.txt");
        FileReader fileReader = new FileReader(input, 7, 3);
        double[][] array = fileReader.fileToArray(input, 7, 3);
        Calculation calculation = new Calculation(fileReader.rows, fileReader.cols);

        PrintWriter printWriter = new PrintWriter(output);
        for (double[] doubles : array) {
            for (double aDouble : doubles) {
                double temp = aDouble;
                temp = Double.parseDouble(new DecimalFormat("##.###").format(temp));
                printWriter.print(temp + "\t");
            }
            printWriter.println();
        }
        printWriter.println("\n");

        while (!calculation.isEnd(array)) {
            array = calculation.makeStep(array);
            for (double[] doubles : array) {
                for (double aDouble : doubles) {
                    double temp = aDouble;
                    temp = Double.parseDouble(new DecimalFormat("##.###").format(temp));
                    printWriter.print(temp + "\t");
                }
                printWriter.println();
            }
            printWriter.println("\n");
        }
        printWriter.close();
    }
}
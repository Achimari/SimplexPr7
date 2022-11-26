package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.Arrays;


public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        File input = new File("Input.txt");
        File output = new File("Result.txt");
        FileReader fileReader = new FileReader(input, 7, 2);
        double[][] array = fileReader.fileToArray(input, fileReader.rows, fileReader.cols);
        double[][] arrayForFinalLine = array;
        Calculation calculation = new Calculation(fileReader.rows, fileReader.cols);

        PrintWriter printWriter = new PrintWriter(output);
        save(array, printWriter);
        printWriter.println("\n" + "Pivot point is: " + calculation.findPivotPoint(array));
        printWriter.println("\n");

        do {
            array = calculation.makeStep(array);
            save(array, printWriter);
            if (!calculation.isEnd(array)) {
                printWriter.println("\n" + "Pivot point is: " + calculation.findPivotPoint(array));
            } else {

                printWriter.println("\n" + "Final line: " + Arrays.toString(calculation.getFinalLine(arrayForFinalLine)));
                printWriter.println("Answer is: " + array[fileReader.rows - 1][fileReader.cols - 1]);
            }
            printWriter.println("\n");
        } while (!calculation.isEnd(array));

        System.out.println(output.getAbsolutePath());
        printWriter.close();
    }

    private static void save(double[][] array, PrintWriter printWriter) {
        for (double[] doubles : array) {
            for (double aDouble : doubles) {
                double temp = aDouble;
                temp = Double.parseDouble(new DecimalFormat("##.###").format(temp));
                printWriter.print(temp + "\t");
            }
            printWriter.println();
        }
    }
}
package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.Arrays;

public class FinalFileWriter {
    int rows, cols;
    File input = new File("Input.txt");
    public static File result = new File("Result.txt");

    public FinalFileWriter(File file, int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.input = file;
    }
    FileReader fileReader = new FileReader(input, 7, 3);

    Calculation calculation = new Calculation(7, 3);
    double[][] matrix = fileReader.fileToArray(input, rows, cols);



    public File makeSteps() throws FileNotFoundException {
        PrintWriter printWriter = new PrintWriter(result);
        for (double[] doubles : matrix) {
            for (double aDouble : doubles) {
                double temp = aDouble;
                temp = Double.parseDouble(new DecimalFormat("##.###").format(temp));
                printWriter.print(temp + "\t");
            }
            printWriter.println();
        }

        while (!calculation.isEnd(matrix)) {
            matrix = calculation.makeStep(matrix);
            for (double[] doubles : matrix) {
                for (double aDouble : doubles) {
                    double temp = aDouble;
                    temp = Double.parseDouble(new DecimalFormat("##.###").format(temp));
                    printWriter.print(temp + "\t");
                }
                printWriter.println();
            }
        }
        printWriter.close();
        return result;
    }
}

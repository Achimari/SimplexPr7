//package org.example;
//
//public class trash {
//    public double[][] changeMatrix() {
//        double[][] arrayWithOutSpaces = new double[rows][cols];
//        for (int i = 0; i < rows; i++) {
//            for (int j = 0; j < cols; j++) {
//                if (i == 1) {
//                    arrayWithOutSpaces[i][j] = matrix[rows - 1][j];
//                } else {
//                    arrayWithOutSpaces[i][j] = matrix[i][j];
//                }
//            }
//        }
//
//        for (int i = 0; i < rows - 3; i++) {
//            for (int j = 0; j < cols; j++) {
//                double temp = arrayWithOutSpaces[i][j];
//                arrayWithOutSpaces[i][j] = arrayWithOutSpaces[i + 1][j];
//                arrayWithOutSpaces[i + 1][j] = temp;
//            }
//        }
//        for (int i = 0; i < rows - 3; i++) {
//            for (int j = 0; j < cols; j++) {
//                double temp = arrayWithOutSpaces[i][j];
//                arrayWithOutSpaces[i][j] = arrayWithOutSpaces[i + 1][j];
//                arrayWithOutSpaces[i + 1][j] = temp;
//            }
//        }
//
//
//        double[][] arrayWithOutArgs = new double[rows - 2][cols];
//        for (int i  = 0; i < rows - 2; i++) {
//            System.arraycopy(arrayWithOutSpaces[i], 0, arrayWithOutArgs[i], 0, cols);
//        }
//
//        for (int i = 0; i < arrayWithOutArgs.length; i++) {
//            for (int j = 0; j < arrayWithOutArgs[i].length; j++) {
//                arrayWithOutArgs[arrayWithOutArgs.length - 2][j] *= -1;
//            }
//        }
//
//        double[][] arrayWithChangedB = new double[rows - 3][cols + 1];
//        for (int i = 0; i < arrayWithOutArgs.length - 1; i++) {
//            for (int j = 0; j < cols + 1; j++) {
//                if (j == cols) {
//                    if (i < cols) {
//                        arrayWithChangedB[i][j] = arrayWithOutArgs[rows - 3][i];
//                    }
//                } else {
//                    arrayWithChangedB[i][j] = arrayWithOutArgs[i][j];
//                }
//            }
//        }
//
//        int count = 0;
//        double[][] result = new double[rows - 3][cols + cols + 1];
//        for (int i = 0; i < rows - 3; i++) {
//            for (int j = 0; j < cols + 3; j++) {
//                if (j < cols) {
//                    result[i][j] = arrayWithChangedB[i][j];
//                } else {
//                    if(j == cols) {
//                        result[i][cols + cols] = arrayWithChangedB[i][cols];
//                        result[i][j] = 0;
//                    }
//                    if (cols + count < result[i].length - 1) {
//                        result[i + count][cols + count] = 1;
//                        count++;
//                    }
//                }
//            }
//        }
//        return result;
//    }
//}

//
//package org.example;
//
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.text.DecimalFormat;
//import java.util.Arrays;
//
//public class FinalFileWriter {
//    int rows, cols;
//    File input = new File("Input.txt");
//    public static File result = new File("Result.txt");
//
//    public FinalFileWriter(File file, int rows, int cols) {
//        this.rows = rows;
//        this.cols = cols;
//        this.input = file;
//    }
//    FileReader fileReader = new FileReader(input, 7, 3);
//
//    Calculation calculation = new Calculation(7, 3);
//    double[][] matrix = fileReader.fileToArray(input, rows, cols);
//
//
//
//    public File makeSteps() throws FileNotFoundException {
//        PrintWriter printWriter = new PrintWriter(result);
//        for (double[] doubles : matrix) {
//            for (double aDouble : doubles) {
//                double temp = aDouble;
//                temp = Double.parseDouble(new DecimalFormat("##.###").format(temp));
//                printWriter.print(temp + "\t");
//            }
//            printWriter.println();
//        }
//
//        while (!calculation.isEnd(matrix)) {
//            matrix = calculation.makeStep(matrix);
//            for (double[] doubles : matrix) {
//                for (double aDouble : doubles) {
//                    double temp = aDouble;
//                    temp = Double.parseDouble(new DecimalFormat("##.###").format(temp));
//                    printWriter.print(temp + "\t");
//                }
//                printWriter.println();
//            }
//        }
//        printWriter.close();
//        return result;
//    }
//}


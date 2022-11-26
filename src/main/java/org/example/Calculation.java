package org.example;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Calculation {

    private int rows, cols;

    public Calculation(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
    }

    private int findPivotColumnIndex(double[][] matrix) {
        int location = 0;
        double min = matrix[rows - 1][0];
        for (double[] doubles : matrix) {
            for (int j = 0; j < doubles.length - 1; j++) {
                if (matrix[matrix.length - 1][j] != 0 && matrix[matrix.length - 1][j] < min) {
                    min = matrix[matrix.length - 1][j];
                    location = j;
                }
            }
        }
        return location;
    }

    private int findPivotRowIndex(double[][] matrix, int pivotColumIndex) {
        int location = 0;
        double max = 0;
        for (double[] doubles : matrix) {
            for (int j = 0; j < doubles.length; j++) {
                if (doubles[doubles.length - 1] > max) {
                    max = doubles[doubles.length - 1];
                    break;
                }
            }
        }
        double min = max;
        for (int i = 0; i < matrix.length - 1; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (j != pivotColumIndex || matrix[i][pivotColumIndex] <= 0) {
                    continue;
                }
                if (matrix[i][cols - 1] / matrix[i][pivotColumIndex] < min) {
                    min = matrix[i][cols - 1] / matrix[i][pivotColumIndex];
                    location = i;
                    break;
                }
            }
        }
        return location;
    }

    private double findPivotPoint(double[][] matrix) {
        int columnPivot = findPivotColumnIndex(matrix);
        int rowPivot = findPivotRowIndex(matrix, columnPivot);
        return matrix[rowPivot][columnPivot];
    }

    public double[][] makeStep(double[][] matrix) {
        int columnPivotIndex = findPivotColumnIndex(matrix);
        int rowPivotIndex = findPivotRowIndex(matrix, columnPivotIndex);
        double pivotPoint = findPivotPoint(matrix);

        double[][] result = new double[rows][cols];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (j != columnPivotIndex && i != rowPivotIndex) {
                    double temp = matrix[i][j] - matrix[rowPivotIndex][j] * matrix[i][columnPivotIndex] / pivotPoint;
                    temp = Double.parseDouble(new DecimalFormat("##.###").format(temp));
                    result[i][j] = temp;
                }
            }
        }

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (i == rowPivotIndex) {
                    double temp = matrix[i][j] / pivotPoint;
                    temp = Double.parseDouble(new DecimalFormat("##.###").format(temp));
                    result[i][j] = temp;
                } else if (j == columnPivotIndex) {
                    if (result[i][j] != pivotPoint) {
                        result[i][j] = 0;
                    }
                }
            }
        }
        return result;
    }

    public boolean isEnd(double[][] matrix) {
        boolean result = false;
        double min = matrix[matrix.length - 1][0];
        for (double[] doubles : matrix) {
            for (int j = 0; j < doubles.length - 1; j++) {
                if (matrix[matrix.length - 1][j] < min) {
                    min = matrix[matrix.length - 1][j];
                }
            }
        }
        if (min >= 0) {
            result = true;
        }
        return result;
    }
}

package processor;

import java.util.Arrays;
import java.util.Collections;

public class MatrixOperations {

    public static double[][] addMatrices(double[][] matrix1, double[][] matrix2) {
        for (int i = 0; i < matrix1.length; i++) {
            for (int j = 0; j < matrix2[0].length; j++) {
                matrix1[i][j] += matrix2[i][j];
            }
        }

        return matrix1;
    }

    public static double[][] multiplyMatrices(double[][] matrix1, double[][] matrix2) {
        double[][] result = new double[matrix1.length][matrix2[0].length];

        for (int i = 0; i < matrix1.length; i++) {
            for (int j = 0; j < matrix2[0].length; j++) {
                for (int k = 0; k < matrix1[0].length; k++) {
                    result[i][j] += matrix1[i][k] * matrix2[k][j];
                }
            }
        }

        return result;
    }

    public static double[][] multiplyMatrixConst(double[][] matrix, double constant) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[i][j] *= constant;
            }
        }

        return matrix;
    }

    public static double[][] transposeMainDiagonal(double[][] matrix) {
        double[][] result = new double[matrix.length][matrix[0].length];

        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[0].length; j++) {
                result[j][i] = matrix[i][j];
            }
        }

        return result;
    }

    public static double[][] transposeSideDiagonal(double[][] matrix) {
        double[][] result = matrix;

        Collections.reverse(Arrays.asList(result));
        result = transposeMainDiagonal(result);
        Collections.reverse(Arrays.asList(result));

        return result;
    }

    public static double[][] transposeVerticalLine(double[][] matrix) {
        double[][] result = transposeMainDiagonal(matrix);

        Collections.reverse(Arrays.asList(result));
        result = transposeMainDiagonal(result);

        return result;
    }

    public static double[][] transposeHorizontalLine(double[][] matrix) {
        Collections.reverse(Arrays.asList(matrix));
        return matrix;
    }

    public static double calcDeterminant(double[][] matrix) {
        if (matrix.length == 1) {
            return matrix[0][0];
        } else if (matrix.length == 2) {
            return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
        }

        double total = 0;
        boolean coefficient = false;

        for (int i = 0; i < matrix[0].length; i++) {
            double[][] subMatrix = sub(matrix, 0, i);

            if (coefficient) {
                total -= matrix[0][i] * calcDeterminant(subMatrix);
            } else {
                total += matrix[0][i] * calcDeterminant(subMatrix);
            }

            coefficient = !coefficient;
        }

        return total;
    }

    // only works on 3x3 matrix
    public static double[][] performInverse(double[][] matrix) {
        double det = calcDeterminant(matrix);

        double[][] newMatrix = new double[matrix.length][matrix[0].length];

        boolean coefficient = false;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (coefficient) {
                    newMatrix[i][j] -= calcDeterminant(sub(matrix, i, j));
                } else {
                    newMatrix[i][j] += calcDeterminant(sub(matrix, i, j));
                }

                coefficient = !coefficient;
            }
        }


        transposeMainDiagonal(newMatrix);

        for (int i = 0; i < newMatrix.length; i++) {
           for (int j = 0; j < newMatrix[0].length; j++) {
               newMatrix[i][j] /= det;
           }
       }

       return newMatrix;
    }

    private static double[][] sub(double[][] matrix, int row, int col) {
        double[][] newMatrix = new double[matrix.length - 1][matrix[0].length - 1];

        boolean rowOffset = false;
        for (int i = 0; i < matrix.length; i++) {
            if (i == row) {
                rowOffset = true;
                continue;
            }

            boolean colOffset = false;
            for (int j = 0; j < matrix[0].length; j++) {
                if (j == col) {
                    colOffset = true;
                    continue;
                }

                newMatrix[rowOffset ? i - 1 : i][colOffset ? j - 1 : j] = matrix[i][j];
            }
        }

        return newMatrix;
    }
}

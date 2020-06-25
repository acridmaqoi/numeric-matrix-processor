package processor;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Scanner;

public class MatrixUtils {

    public static double[][][] input(boolean multipleMatrices) {
        Scanner scanner = new Scanner(System.in);
        double[][][] matrices = new double[2][][];

        for (int i = 0; i < (multipleMatrices ? 2 : 1); i++) {
            System.out.println("Enter size of " + (i == 0 ? "first" : "second") + " matrix:");
            double[][] array = new double[scanner.nextInt()][scanner.nextInt()];

            System.out.println("Enter " + (i == 0 ? "first" : "second") + " matrix:");
            matrices[i] = fill(array, scanner);
        }

        return matrices;
    }

    public static double[][] fill(double[][] matrix, Scanner scanner) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[i][j] = scanner.nextDouble();
            }
        }

        scanner.nextLine();
        return matrix;
    }

    public static void print(double[][] matrix) {
        for (double[] row : matrix) {
            for (double element : row) {
                System.out.printf("%.2f ", element);
            }
            System.out.println();
        }
    }

    public static void print2(double[][] matrix) {
        DecimalFormat df = new DecimalFormat("0.##");
        df.setRoundingMode(RoundingMode.DOWN);

        for (double[] row : matrix) {
            for (double element : row) {
                System.out.print((df.format(element).equals("-0") ? "0" : df.format(element)) + " ");
            }
            System.out.println();
        }
    }
}

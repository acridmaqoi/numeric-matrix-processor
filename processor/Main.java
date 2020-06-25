package processor;

import java.util.Scanner;

import static processor.MatrixUtils.*;
import static processor.MatrixOperations.*;

public class Main {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("1. Add matrices\n" +
                "2. Multiply matrix to a constant\n" +
                "3. Multiply matrices\n" +
                "4. Transpose matrix\n" +
                "5. Calculate a determinant\n" +
                "6. Inverse matrix\n" +
                "0. Exit");

        while (true) {
            switch (scanner.nextLine()) {
                case "1":
                    add();
                    break;
                case "2":
                    multiplyConst();
                    break;
                case "3":
                    multiply();
                    break;
                case "4":
                    transpose();
                    break;
                case "5":
                    determinant();
                    break;
                case "6":
                    inverse();
                    break;
                default:
                    return;
            }
        }
    }

    public static void add() {
        double[][][] matrices = input(true);

        System.out.println("The result is:");
        print(addMatrices(matrices[0], matrices[1]));
    }

    public static void multiplyConst() {
        double[][] matrix = input(false)[0];

        System.out.println("Enter constant:");
        double constant = scanner.nextInt();

        System.out.println("The multiplication result is:");
        print(multiplyMatrixConst(matrix, constant));
    }

    public static void multiply() {
        double[][][] matrices = input(true);

        System.out.println("The multiplication result is:");
        print(multiplyMatrices(matrices[0], matrices[1]));
    }

    public static void transpose() {
        System.out.println("1. Main diagonal\n" +
                "2. Side diagonal\n" +
                "3. Vertical line\n" +
                "4. Horizontal line");

        System.out.println("Your choice:");
        String choice = scanner.nextLine();

        double[][] matrix = input(false)[0];
        double[][] result = new double[matrix.length][matrix[0].length];

        switch (choice) {
            case "1":
                result = transposeMainDiagonal(matrix);
                break;
            case "2":
                result = transposeSideDiagonal(matrix);
                break;
            case "3":
                result = transposeVerticalLine(matrix);
                break;
            case "4":
                result = transposeHorizontalLine(matrix);
                break;
            default:
                break;
        }

        System.out.println("The result is:");
        print(result);
    }

    public static void determinant() {
        double[][] matrix = input(false)[0];

        System.out.println("The result is:");
        System.out.println(calcDeterminant(matrix));
    }

    public static void inverse() {
        double[][] matrix = input(false)[0];

        System.out.println("The result is: ");
        print2(performInverse(matrix));
    }
}

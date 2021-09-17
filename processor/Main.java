package processor;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        new Main().run();
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        int choice = -1;
        while (choice != 0) {
            showMenu();
            switch (choice = sc.nextInt()) {
                case 0:
                    break;
                case 1:
                    addMatrices();
                    break;
                case 2:
                    multiplyMatricesByAConstant();
                    break;
                case 3:
                    multiplyMatrices();
                    break;
                case 4:
                    transposeMatrixMenu();
                    break;
                case 5:
                    calculateDeterminant();
                    break;
                case 6:
                    inverseMatrix();
                    break;
                default:
            }
        }
    }

    void showMenu() {
        System.out.print("1. Add matrices\n" +
                "2. Multiply matrix by a constant\n" +
                "3. Multiply matrices\n" +
                "4. Transpose matrix\n" +
                "5. Calculate a determinant\n" +
                "6. Inverse matrix\n" +
                "0. Exit\n" +
                "Your choice: ");
    }

    public void addMatrices() {
        Matrix first = new Matrix();
        Matrix second = new Matrix();
        Matrix.fill(first, second);
        if (first.add(second.getArr()) == 1) {
            System.out.println("The result is:");
            first.show();
            System.out.println();
        }
    }

    public void multiplyMatricesByAConstant() {
        Scanner sc = new Scanner(System.in);
        Matrix matrix = new Matrix();
        matrix.fill();
        System.out.print("Enter constant: ");
        double cons = sc.nextDouble();
        matrix.multiplyByAConstant(cons);
        System.out.println("The result is:");
        matrix.show();
        System.out.println();
    }

    public void multiplyMatrices() {
        Matrix first = new Matrix();
        Matrix second = new Matrix();
        Matrix.fill(first, second);
        Matrix multiplied = Matrix.multiplyMatrices(first, second);
        if (multiplied != null) {
            System.out.println("The result is:");
            multiplied.show();
            System.out.println();
        }
    }

    void transposeMatrixMenu() {
        Scanner sc = new Scanner(System.in);
        System.out.print("1. Main diagonal\n" +
                "2. Side diagonal\n" +
                "3. Vertical line\n" +
                "4. Horizontal line\n" +
                "Your choice: ");
        int choice = sc.nextInt();
        Matrix matrix = new Matrix();
        matrix.fill();
        switch (choice) {
            case 1:
                matrix = Matrix.transpose(matrix);
                break;
            case 2:
                matrix = Matrix.transposeAlongSideDiagonal(matrix);
                break;
            case 3:
                matrix = Matrix.transposeAlongVerticalLine(matrix);
                break;
            case 4:
                matrix = Matrix.transposeAlongHorizontalLine(matrix);
                break;
            default:
        }
        System.out.println("The result is:");
        matrix.show();
        System.out.println();
    }

    void calculateDeterminant() {
        Matrix matrix = new Matrix();
        matrix.fill();
        if (matrix.getArr().length != matrix.getArr()[0].length) {
            System.out.println("A matrix should be square!");
            return;
        }
        System.out.println("The result is:");
        System.out.println(Matrix.calculateDeterminant(matrix, 0));
        System.out.println();
    }

    void inverseMatrix() {
        Matrix matrix = new Matrix();
        matrix.fill();
        if (matrix.getArr().length != matrix.getArr()[0].length) {
            System.out.println("A matrix should be square!");
            return;
        }

        if (Matrix.calculateDeterminant(matrix, 0) != 0) {
            System.out.println("The result is: ");
            Matrix.inverseMatrix(matrix).show();
        } else {
            System.out.println("This matrix doesn't have an inverse.");
        }
        System.out.println();

    }
}

class Matrix implements Cloneable {
    private double[][] arr;

    public double[][] getArr() {
        return arr;
    }

    public void fill() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter size of matrix: ");
        int n = sc.nextInt();
        int m = sc.nextInt();
        arr = new double[n][m];

        System.out.println("Enter matrix:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                arr[i][j] = sc.nextDouble();
            }
        }
    }

    public static void fill(Matrix matrix1, Matrix matrix2) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter size of first matrix: ");
        int n1 = sc.nextInt();
        int m1 = sc.nextInt();
        matrix1.arr = new double[n1][m1];

        System.out.println("Enter first matrix:");
        for (int i = 0; i < n1; i++) {
            for (int j = 0; j < m1; j++) {
                matrix1.arr[i][j] = sc.nextDouble();
            }
        }

        System.out.print("Enter size of second matrix: ");
        int n2 = sc.nextInt();
        int m2 = sc.nextInt();
        matrix2.arr = new double[n2][m2];

        System.out.println("Enter second matrix:");
        for (int i = 0; i < n2; i++) {
            for (int j = 0; j < m2; j++) {
                matrix2.arr[i][j] = sc.nextDouble();
            }
        }

    }

    public int add(double[][] arr2) {
        if (arr.length != arr2.length || arr[0].length != arr2[0].length) {
            System.out.println("The operation cannot be performed.");
            System.out.println();
            return -1;
        }

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                arr[i][j] += arr2[i][j];
            }
        }
        return 1;
    }

    public void show() {
        for (double[] doubles : arr) {
            for (int j = 0; j < arr[0].length; j++) {
                System.out.print(doubles[j] + " ");
            }
            System.out.println();
        }
    }

    public void multiplyByAConstant(double num) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                arr[i][j] *= num;
            }
        }
    }

    public static Matrix multiplyMatrices(Matrix m1, Matrix m2) {
        if (m1.arr[0].length != m2.arr.length) {
            System.out.println("The operation cannot be performed.");
            System.out.println();
            return null;
        }

        Matrix matrix = new Matrix();
        int n = m1.arr.length;
        int m = m2.arr[0].length;
        matrix.arr = new double[n][m];

        for (int i = 0; i < matrix.arr.length; i++) {
            for (int j = 0; j < matrix.arr[0].length; j++) {
                for (int k = 0; k < m1.arr[0].length; k++) {
                    matrix.arr[i][j] += m1.arr[i][k] * m2.arr[k][j];
                }
            }
        }

        return matrix;
    }

    public static Matrix transpose(Matrix org) {
        Matrix transposed = new Matrix();
        transposed.arr = new double[org.arr[0].length][org.arr.length];
        for (int i = 0; i < transposed.arr.length; i++) {
            for (int j = 0; j < transposed.arr[0].length; j++) {
                transposed.arr[i][j] = org.arr[j][i];
            }
        }
        return transposed;
    }

    public static Matrix transposeAlongSideDiagonal(Matrix org) {
        Matrix transposed = new Matrix();
        transposed.arr = new double[org.arr[0].length][org.arr.length];
        for (int i = 0; i < transposed.arr.length; i++) {
            for (int j = 0; j < transposed.arr[0].length; j++) {
                transposed.arr[i][j] = org.arr[org.arr.length - 1 - j][org.arr[0].length - 1 - i];
            }
        }
        return transposed;
    }

    public static Matrix transposeAlongVerticalLine(Matrix org) {
        Matrix transposed = new Matrix();
        transposed.arr = new double[org.arr[0].length][org.arr.length];
        for (int i = 0; i < transposed.arr.length; i++) {
            for (int j = 0; j < transposed.arr[0].length; j++) {
                transposed.arr[i][j] = org.arr[i][org.arr[0].length - 1 - j];
            }
        }
        return transposed;
    }

    public static Matrix transposeAlongHorizontalLine(Matrix org) {
        Matrix transposed = new Matrix();
        transposed.arr = new double[org.arr[0].length][org.arr.length];
        for (int i = 0; i < transposed.arr.length; i++) {
            System.arraycopy(org.arr[org.arr.length - 1 - i], 0, transposed.arr[i], 0, transposed.arr[0].length);
        }
        return transposed;
    }

    public static double calculateDeterminant(Matrix m1, double det) {
        if (m1.arr.length == 2) {
            return m1.arr[0][0] * m1.arr[1][1] - (m1.arr[0][1] * m1.arr[1][0]);
        }

        for (int i = 0; i < m1.arr.length; i++) {
            Matrix matrix = new Matrix();
            matrix.arr = new double[m1.arr.length - 1][m1.arr.length - 1];
            for (int j = 0; j < m1.arr.length; j++) {
                for (int k = 1; k < m1.arr.length; k++) {
                    if (i < j) {
                        matrix.arr[j - 1][k - 1] = m1.arr[j][k];
                    } else if (i > j) {
                        matrix.arr[j][k - 1] = m1.arr[j][k];
                    }
                }
            }
            det += m1.arr[i][0] * Math.pow(-1, i) * Matrix.calculateDeterminant(matrix, 0);
        }

        return det;
    }

    public static Matrix inverseMatrix(Matrix m1) {
        Matrix matrix = new Matrix();
        matrix.arr = new double[m1.arr.length][m1.arr.length];
        for (int i = 0; i < m1.arr.length; i++) {
            System.arraycopy(m1.arr[i], 0, matrix.arr[i], 0, m1.arr.length);
        }

        for (int i = 0; i < m1.arr.length; i++) {
            for (int j = 0; j < m1.arr.length; j++) {
                Matrix buf = new Matrix();
                buf.arr = new double[matrix.arr.length - 1][matrix.arr.length - 1];
                for (int k = 0; k < m1.arr.length; k++) {
                    for (int l = 0; l < m1.arr.length; l++) {
                        if (i > k && j > l) {
                            buf.arr[k][l] = m1.arr[k][l];
                        } else if (i < k && j > l) {
                            buf.arr[k - 1][l] = m1.arr[k][l];
                        } else if (i > k && j < l) {
                            buf.arr[k][l - 1] = m1.arr[k][l];
                        } else if (i < k && j < l) {
                            buf.arr[k - 1][l - 1] = m1.arr[k][l];
                        }
                    }
                }
                matrix.arr[i][j] = Math.pow(-1, i + j) * Matrix.calculateDeterminant(buf, 0);
            }
        }

        matrix = Matrix.transpose(matrix);
        double det = Matrix.calculateDeterminant(m1, 0);

        for (int i = 0; i < matrix.arr.length; i++) {
            for (int j = 0; j < matrix.arr.length; j++) {
                matrix.arr[i][j] *= 1 / det;
            }
        }

        return matrix;
    }


    @Override
    public Matrix clone() {
        try {
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            return (Matrix) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}

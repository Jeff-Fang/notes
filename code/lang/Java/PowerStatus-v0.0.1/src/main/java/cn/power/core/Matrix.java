package cn.power.core;

import java.util.Random;

/**
 * Created by ray on 2017/1/8.
 */
public class Matrix implements Cloneable {

    /**
     * Array for internal storage of elements.
     *
     * @serial internal array storage.
     */
    private int[][] A;

    /**
     * Row and column dimensions.
     *
     * @serial row dimension.
     * @serial column dimension.
     */
    private int m, n;

    /**
     * Construct an m-by-n matrix of zeros.
     *
     * @param m Number of rows.
     * @param n Number of colums.
     */

    public Matrix(int m, int n) {
        this.m = m;
        this.n = n;
        A = new int[m][n];
    }

    /**
     * Construct a matrix quickly without checking arguments.
     *
     * @param A Two-dimensional array of doubles.
     * @param m Number of rows.
     * @param n Number of colums.
     */

    public Matrix(int[][] A, int m, int n) {
        this.A = A;
        this.m = m;
        this.n = n;
    }

    public Matrix(int m, int n, int defaultValue) {
        this.m = m;
        this.n = n;
        A = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                A[i][j] = defaultValue;
            }
        }
    }

    /**
     * Generate identity matrix
     *
     * @param m Number of rows.
     * @param n Number of colums.
     * @return An m-by-n matrix with ones on the diagonal and zeros elsewhere.
     */

    public static Matrix identity(int m, int n) {
        Matrix A = new Matrix(m, n);
        int[][] X = A.getArray();
        for (int i = 0; i < Math.min(m, n); i++) {
            X[i][i] = 1;
        }
        return A;
    }

    /**
     * 随机矩阵，parent控制1元素百分比
     *
     * @param n
     * @param parent
     * @return
     */
    public static Matrix random(int n, float parent) {
        Matrix A = identity(n, n);
        int[][] X = A.getArray();
        Random random = new Random(System.currentTimeMillis());
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (random.nextInt(10000) < parent * 10000) {
                    X[i][j] = 1;
                    X[j][i] = 1;
                }
            }
        }
        return A;
    }

    public int get(int i, int j) {
        return A[i][j];
    }

    public void set(int i, int j, int s) {
        A[i][j] = s;
    }


    /**
     * Linear algebraic matrix multiplication, A * B
     *
     * @param B another matrix
     * @return Matrix product, A * B
     * @throws IllegalArgumentException Matrix inner dimensions must agree.
     */

    public Matrix times(Matrix B) {
        if (B.m != n) {
            throw new IllegalArgumentException("Matrix inner dimensions must agree.");
        }
        Matrix X = new Matrix(m, B.n);
        int[][] C = X.getArray();
        int[] Bcolj = new int[n];
        for (int j = 0; j < B.n; j++) {
            for (int k = 0; k < n; k++) {
                Bcolj[k] = B.A[k][j];
            }
            for (int i = 0; i < m; i++) {
                int[] Arowi = A[i];
                int s = 0;
                for (int k = 0; k < n; k++) {
                    s += Arowi[k] * Bcolj[k];
                }
                C[i][j] = s;
            }
        }
        return X;
    }

    /**
     * Access the internal two-dimensional array.
     *
     * @return Pointer to the two-dimensional array of matrix elements.
     */

    public int[][] getArray() {
        return A;
    }

    /**
     * Get row dimension.
     *
     * @return m, the number of rows.
     */

    public int getRowDimension() {
        return m;
    }

    /**
     * Get column dimension.
     *
     * @return n, the number of columns.
     */

    public int getColumnDimension() {
        return n;
    }

    public boolean equal(Matrix other) {
        if (n != other.n || m != other.m) {
            return false;
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (A[i][j] != other.get(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Clone the Matrix object.
     */

    public Matrix clone() {
        return this.copy();
    }

    /**
     * Make a deep copy of a matrix
     */

    public Matrix copy() {
        Matrix X = new Matrix(m, n);
        int[][] C = X.getArray();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                C[i][j] = A[i][j];
            }
        }
        return X;
    }
}

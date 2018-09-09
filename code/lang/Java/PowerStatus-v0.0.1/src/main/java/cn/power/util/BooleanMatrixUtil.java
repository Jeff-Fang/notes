package cn.power.util;

import cn.power.core.BooleanMatrix;
import cn.power.core.Matrix;

/**
 * Created by ray on 2017/1/6.
 */
public class BooleanMatrixUtil {
    /**
     * 判断两个矩阵是否相等
     *
     * @param a
     * @param b
     * @return
     */
    public static boolean matrixEquals(Matrix a, Matrix b) {
        if (!checkMatrixDimensions(a, b)) {
            return false;
        }

        for (int i = 0; i < a.getRowDimension(); i++) {
            for (int j = 0; j < a.getColumnDimension(); j++) {
                int aV = (int) a.get(i, j);
                int bV = (int) b.get(i, j);
                if ((aV > 0 && bV == 0) || (aV == 0 && bV > 0)) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * 判断两个矩阵行和列是否相同
     *
     * @param a
     * @param b
     * @return
     */
    public static boolean checkMatrixDimensions(Matrix a, Matrix b) {
        if (a.getRowDimension() != b.getRowDimension() || a.getColumnDimension() != b.getColumnDimension()) {
            return false;
        }
        return true;
    }

    /**
     * 设置 x,y以及y,x点的值
     *
     * @param matrix
     * @param value
     * @param indexes 允许传多个坐标
     */
    public static void setMatrixValue(BooleanMatrix matrix, boolean value, int... indexes) {
        int size = indexes.length;
        if (size == 1) {
            matrix.set(indexes[0], indexes[0], value);
            return;
        }
        for (int i = 0; i < indexes.length; i++) {
            for (int j = i + 1; j < indexes.length; j++) {
                int x = indexes[i];
                int y = indexes[j];
                matrix.set(x, y, value);
                matrix.set(y, x, value);
            }
        }
    }
}

package cn.power.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ray on 2017/1/8.
 */
public class Core {

    public static Map<Integer, List<Integer>> getCoreData(Matrix orgMatrix) {
        return getCoreData(orgMatrix, 0.4f);
    }

    /**
     * 获取点集的矩阵下标
     *
     * @param orgMatrix
     * @param minXSL
     * @return
     */
    public static Map<Integer, List<Integer>> getCoreData(Matrix orgMatrix, float minXSL) {
        Matrix coreMatrix = getCoreMatrix(orgMatrix, minXSL);

        int n = coreMatrix.getRowDimension();
        int[] useIndex = new int[n];
        Map<Integer, List<Integer>> res = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (useIndex[j] == 0) {
                    if (coreMatrix.get(i, j) == 1) {
                        useIndex[j] = 1;
                        if (res.get(i) == null) {
                            res.put(i, new ArrayList<Integer>());
                        }
                        res.get(i).add(j);
                    }
                }
            }
        }
        return res;
    }

    public static Matrix getCoreMatrix(Matrix orgMatrix, float minXSL) {

        float xsl = 0.0f;
        int n = orgMatrix.getColumnDimension();
        float nSquare = n * n;

        int[] AR = new int[n + 1];//矩阵orgMatrix中每行第一个1元素对应的AC数组中的下标

        boolean change = true;

        while (change) {
            change = false;
            int[] AC = new int[get1Count(orgMatrix)];//矩阵orgMatrix中每行1元素的位置
            if (xsl < minXSL) {
                //稀疏率小于 minXSL 时，形成稀疏矩阵
                int k = 0;//多少个1元素
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        if (orgMatrix.get(i, j) == 1) {
                            AC[k] = j;//计算AC， 即T中1元素的列号
                            k += 1;
                        }
                    }
                    AR[i + 1] = k;//下一行1元素在AC中的位置
                }
                //(AR[n] - 1) 其实 == k
                xsl = k / nSquare;//矩阵的稀疏率
            }

            //进入稀疏矩阵的乘法运算
            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    if (orgMatrix.get(i, j) == 0) {
                        int line = AR[j];   //第j行首个1元素在AC中的位置
                        while (line < AR[j + 1]) {
                            int m = AC[line];//1元素的列标
                            if (orgMatrix.get(i, m) == 1) {
                                orgMatrix.set(i, j, 1);
                                orgMatrix.set(j, i, 1);
                                change = true;
                            }
                            line += 1;
                        }
                    }
                }
            }
        }

        return orgMatrix;
    }

    public static int get1Count(Matrix org) {
        int count = 0;
        for (int i = 0; i < org.getRowDimension(); i++) {
            for (int j = 0; j < org.getColumnDimension(); j++) {
                count += org.get(i, j);
            }
        }
        return count;
    }
}

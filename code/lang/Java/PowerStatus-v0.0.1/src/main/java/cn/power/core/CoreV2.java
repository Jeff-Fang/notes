package cn.power.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ray on 2017/1/12.
 */
public class CoreV2 {

    public static List<List<Integer>> getCoreData(BooleanMatrix orgMatrix) {
        BooleanMatrix[] dataMatrix = getCoreMatrix(orgMatrix);

        BooleanMatrix finalMatrix = dataMatrix[0];
        BooleanMatrix exMatrix = dataMatrix[1];

        int count = exMatrix.getRowDimension();
        List<List<Integer>> res = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            if (exMatrix.get(i, 0) == true) {
                List<Integer> indexList = new ArrayList<>();
                for (int j = 0; j < count; j++) {
                    if (finalMatrix.get(i, j) == true) {
                        indexList.add(j);
                    }
                }
                res.add(indexList);
            }
        }

        return res;
    }

    public static BooleanMatrix[] getCoreMatrix(BooleanMatrix orgMatrix) {
        BooleanMatrix[] result = new BooleanMatrix[2];

        int n = orgMatrix.getRowDimension();

        BooleanMatrix exMatrix = new BooleanMatrix(n, 1, true);

        boolean change = true;
        while (change) {
            change = false;
            for (int i = 0; i < n; i++) {
                if (exMatrix.get(i, 0) == true) {
                    for (int j = 0; j < n; j++) {
                        if (j != i) {
                            if (exMatrix.get(j, 0) == true) {
                                if (orgMatrix.get(i, j) == true) {
                                    for (int k = 0; k < n; k++) {
                                        if (orgMatrix.get(i, k) == false) {
                                            if (orgMatrix.get(j, k) == true) {
                                                orgMatrix.set(i, k, true);
                                            }
                                        }
                                    }
                                    exMatrix.set(j, 0, false);
                                    change = true;
                                }
                            }
                        }
                    }
                }
            }

        }
        result[0] = orgMatrix;
        result[1] = exMatrix;
        return result;
    }
}

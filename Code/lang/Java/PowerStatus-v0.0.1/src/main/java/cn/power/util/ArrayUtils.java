package cn.power.util;

import java.util.List;

/**
 * Created by ray on 2017/1/6.
 */
public class ArrayUtils {
    public static int[] toPrimitive(List<Integer> data) {
        int[] res = new int[data.size()];
        for (int i = 0; i < data.size(); i++) {
            res[i] = data.get(i);
        }
        return res;
    }
}

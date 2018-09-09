package cn.power;

import cn.power.beans.base.Element;
import cn.power.beans.base.ElementStatus;
import cn.power.beans.base.Point;
import cn.power.beans.base.PowerStatus;
import cn.power.core.Core;
import cn.power.core.Matrix;
import cn.power.interfaces.DataProvider;
import cn.power.util.MatrixUtilV1;
import cn.power.util.MatrixUtilV2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Created by ray on 2017/1/8.
 */
public class AppV2 {
    public static DataProvider dataProvider;
    static int[][] A = new int[138][138];
    static int[][] B = new int[138][138];

    public static void main(String[] args) {

        readFile("/Users/ray/IdeaProjects/PowerStatus/src/main/res/org", A);
        readFile("/Users/ray/IdeaProjects/PowerStatus/src/main/res/res", B);
        long time = System.currentTimeMillis();
        Matrix org = new Matrix(A, 138, 138);
        Matrix result = new Matrix(B, 138, 138);

        Matrix myFinal = Core.getCoreMatrix(org, 0.4f);
        if (myFinal.equal(result)) {
            System.out.println("you got it!!! cast time = " + (System.currentTimeMillis() - time));
        } else {
            System.out.println("something wrong man!");
        }
    }

    public static void readFile(String fileName, int[][] T) {

        File file = new File(fileName);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line = 0;
            while ((tempString = reader.readLine()) != null) {
                String[] vs = tempString.split(",");
                for (int i = 0; i < vs.length; i++) {
                    T[line][i] = Integer.valueOf(vs[i]);
                }
                line++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
    }

    public static void init(List<Element> elements) {
        dataProvider.saveElements(elements);

        //生成端点库
        Map<Long, Point> pointMap = genPoints(elements);


        //生成端点，下标对照map
        Map<Long, Integer> orgPointIndex = new HashMap<>();
        Map<Integer, Long> orgIndexPoint = new HashMap<>();
        Set set = pointMap.keySet();
        int index = 0;
        Iterator iter = set.iterator();
        while (iter.hasNext()) {
            long id = (long) iter.next();
            orgPointIndex.put(id, index);
            orgIndexPoint.put(index, id);
            index++;
        }

        Map<Integer, List<Integer>> points = Core.getCoreData(genPointMatrix(elements, orgPointIndex));



    }


    /**
     * 根据元件库，遍历生成端点库
     * 端点库可以查询端点连接的所有元件
     *
     * @param elements
     */
    public static Map<Long, Point> genPoints(List<Element> elements) {
        Map<Long, Point> pointMap = new HashMap<>();

        for (Element element : elements) {
            for (long pointId : element.getPoints()) {
                Point point = pointMap.get(pointId);
                if (point == null) {
                    point = new Point();
                    point.addElement(element);
                }
            }
        }
        return pointMap;
    }

    public static Matrix genPointMatrix(List<Element> elements, Map<Long, Integer> pointIndex) {

        int pointSize = pointIndex.size();
        Matrix pointMatrix = Matrix.identity(pointSize, pointSize);//对角线设置为1的矩阵

        for (Element element : elements) {
            if (element.getElementStatus() == ElementStatus.OFF || element.getElementStatus() == ElementStatus.YOU_CAN_CONTROL) {
                continue;
            }

            int[] indexes = new int[element.getPoints().size()];
            for (int i = 0; i < element.getPoints().size(); i++) {
                indexes[i] = pointIndex.get(element.getPoints().get(i));
            }
            MatrixUtilV2.setMatrixValue(pointMatrix, PowerStatus.ON.getValue()?1:0, indexes);
        }
        return pointMatrix;
    }
}

package cn.power;

import Jama.JamaMatrix;
import cn.power.beans.base.*;
import cn.power.interfaces.DataProvider;
import cn.power.util.MatrixUtilV1;
import cn.power.util.ArrayUtils;

import java.util.*;

/**
 * power status app
 * <p>
 * Created by Ray on 16/11/4.
 */
public class AppV1 {
    public static DataProvider dataProvider;

    public static void main(String[] args) {
        System.out.println("this is an object for power switches.");
    }

    /**
     * 1.	获取elements，生成元件库
     * 2.	根据元件库，生成端点库
     * 3.	创建临时端点—元件（导通矩阵）矩阵，通过调用setSource，遍历所有端点，生成节点库（生成节点的元件库）
     * 4.	根据element的通断状态创建节点—元件（导通矩阵）矩阵，通过调用setSource，遍历所有节点，生成节点集
     * 5.	根据element 的source状态，通过调用setSource，返回所有元件的无压状态
     *
     * @param elements
     */
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
        //生成初始的导通矩阵
        JamaMatrix orgPointMatrix = genPointMatrix(elements, orgPointIndex);

        //生成节点表
        int findPointCount = 0;
        JamaMatrix subPointMatrix = orgPointMatrix;
        while (findPointCount < pointMap.size()) {
            JamaMatrix dotMatrix = getFinalTimeMatrix(subPointMatrix);
            Map<Integer, List<Integer>> dotPointMap = getMatrixIndexStatusMap(dotMatrix);
            List<Integer> outDotIndex = dotPointMap.get(0);
            List<Integer> inDotIndex = dotPointMap.get(1);
            List<Long> youGotDotPointIds = getDotPointId(orgIndexPoint, inDotIndex);

            Dot newDot = new Dot(elements, youGotDotPointIds);
            //保存节点信息
            dataProvider.saveDot(newDot);
            //更新保存端点信息
            for (long pointId : youGotDotPointIds) {
                Point p = dataProvider.getPointFromId(pointId);
                p.setDotId(newDot.getId());
                dataProvider.savePoint(p);
            }

            subPointMatrix = getSubMatrix(orgPointMatrix, ArrayUtils.toPrimitive(outDotIndex));
            findPointCount += inDotIndex.size();
        }

        //获取所有节点
        List<Dot> dots = dataProvider.getAllDots();

        //生成节点的导通矩阵
        //1.首先完善Element元素的属性
        //  element的dot属性，如果只有一个，那么该元素属于某节点内部，如果有多个，则被多个节点夹住
        fillElementDot(elements, dots);
        //2.遍历elements，生成节点的导通矩阵
        Map<Long, Integer> orgDotIndex = new HashMap<>();
        Map<Integer, Long> orgIndexDot = new HashMap<>();
        index = 0;
        for (Dot dot : dots) {
            orgDotIndex.put(dot.getId(), index);
            orgIndexDot.put(index, dot.getId());
            index++;
        }
        JamaMatrix orgDotMatrix = genDotMatrix(elements, orgDotIndex);

        //生成该运行方式下节点集表
        int findDotCount = 0;
        JamaMatrix subDotMatrix = orgDotMatrix;
        while (findDotCount < dots.size()) {
            JamaMatrix dotSetMatrix = getFinalTimeMatrix(subDotMatrix);
            Map<Integer, List<Integer>> dotsetPointMap = getMatrixIndexStatusMap(dotSetMatrix);
            List<Integer> outDotSetIndex = dotsetPointMap.get(0);
            List<Integer> inDotSetIndex = dotsetPointMap.get(1);
            List<Long> youGotDotSetDotIds = getDotPointId(orgIndexDot, inDotSetIndex);

            dataProvider.saveDotSet(new DotSet(elements, youGotDotSetDotIds));

            subDotMatrix = getSubMatrix(orgPointMatrix, ArrayUtils.toPrimitive(outDotSetIndex));
            findDotCount += inDotSetIndex.size();
        }
    }

    public static Map<Source, List<Long>> setSource(Map<Source, List<Long>> src) {
        List<Long> s = src.get(Source.S);
        List<Long> e = src.get(Source.E);

        List<DotSet> dotSets = dataProvider.getAllDotSets();


        return null;

    }


    public static JamaMatrix genDotMatrix(List<Element> elements, Map<Long, Integer> dotIndex) {
        int dotSize = dotIndex.size();
        JamaMatrix dotMatrix = JamaMatrix.identity(dotSize, dotSize);

        for (Element element : elements) {
            if (element.getDots().size() <= 1) {
                continue;
            }

            if (element.getStatus() != PowerStatus.ON) {
                continue;
            }

            int[] indexes = new int[element.getDots().size()];
            for (int i = 0; i < element.getDots().size(); i++) {
                indexes[i] = dotIndex.get(element.getDots().get(i));
            }

            MatrixUtilV1.setMatrixValue(dotMatrix, element.getStatus().getValue() ? 1 : 0, indexes);
        }
        return dotMatrix;
    }

    /**
     * 填充element表中的dot属性字段
     *
     * @param elements 元件表
     * @param dots     节点表
     */
    private static void fillElementDot(List<Element> elements, List<Dot> dots) {
        for (Element element : elements) {
            element.clearDots();
            for (long pointId : element.getPoints()) {
                Dot findDot = null;
                for (Dot dot : dots) {
                    if (dot.inDot(pointId)) {
                        findDot = dot;
                        break;
                    }
                }
                if (findDot != null) {
                    element.addDot(findDot);
                }
            }

        }
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

    /**
     * @param orgMatrix
     * @param orgIndexPoint
     * @param excludeIndexes
     * @return
     */
    public static int[] getSubMatrixIndexes(JamaMatrix orgMatrix, Map<Integer, Long> orgIndexPoint, List<Integer> excludeIndexes) {
        for (int excludeIndex : excludeIndexes) {
            orgIndexPoint.remove(excludeIndex);
        }

        int[] res = new int[orgIndexPoint.size()];
        int index = 0;
        Iterator iter = orgIndexPoint.keySet().iterator();
        while (iter.hasNext()) {
            res[index] = (Integer) iter.next();
            index++;
        }
        return res;
    }

    public static JamaMatrix getSubMatrix(JamaMatrix orgMatrix, int[] indexes) {
        return orgMatrix.getMatrix(indexes, indexes);
    }

    /**
     * 获取属于某节点的所有端点id
     *
     * @param orgIndexPoint
     * @param indexes
     * @return
     */
    public static List<Long> getDotPointId(Map<Integer, Long> orgIndexPoint, List<Integer> indexes) {
        List<Long> res = new ArrayList<>();
        for (Integer index : indexes) {
            res.add(orgIndexPoint.get(index));
        }
        return res;
    }

    /**
     * 获取值>0的集合以及值==0的集合
     *
     * @param matrix 只有1列的矩阵
     * @return map 0 值==0 的集合  1 值>0的集合
     */
    public static Map<Integer, List<Integer>> getMatrixIndexStatusMap(JamaMatrix matrix) {
        Map<Integer, List<Integer>> resMap = new HashMap<>();
        List<Integer> greater0 = new ArrayList<>();
        List<Integer> res0 = new LinkedList<>();
        for (int i = 0; i < matrix.getRowDimension(); i++) {
            if (matrix.get(i, 0) > 0) {
                greater0.add(i);
            } else {
                res0.add(i);
            }
        }
        resMap.put(0, res0);
        resMap.put(1, greater0);
        return resMap;
    }

    /**
     * 获取设置某一点为源后，最终的导通序列矩阵
     *
     * @param matrix
     * @return
     */
    public static JamaMatrix getFinalTimeMatrix(JamaMatrix matrix) {
        JamaMatrix timeMatrix = new JamaMatrix(matrix.getRowDimension(), 1);
        timeMatrix.set(0, 0, 1);
        JamaMatrix res = timeMatrix;
        do {
            timeMatrix = res;
            res = matrix.times(timeMatrix);
        } while (!MatrixUtilV1.matrixEquals(timeMatrix, res));
        return res;
    }

    public static JamaMatrix genPointMatrix(List<Element> elements, Map<Long, Integer> pointIndex) {

        int pointSize = pointIndex.size();
        JamaMatrix pointMatrix = JamaMatrix.identity(pointSize, pointSize);//对角线设置为1的矩阵

        for (Element element : elements) {
            if (element.getElementStatus() == ElementStatus.OFF || element.getElementStatus() == ElementStatus.YOU_CAN_CONTROL) {
                continue;
            }

            int[] indexes = new int[element.getPoints().size()];
            for (int i = 0; i < element.getPoints().size(); i++) {
                indexes[i] = pointIndex.get(element.getPoints().get(i));
            }
            MatrixUtilV1.setMatrixValue(pointMatrix, PowerStatus.ON.getValue() ? 1 : 0, indexes);
        }
        return pointMatrix;
    }
}

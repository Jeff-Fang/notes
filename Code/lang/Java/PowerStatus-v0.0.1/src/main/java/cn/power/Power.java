package cn.power;

import Jama.JamaMatrix;
import cn.power.beans.base.*;
import cn.power.core.BooleanMatrix;
import cn.power.core.Core;
import cn.power.core.CoreV2;
import cn.power.interfaces.DataProvider;
import cn.power.util.BooleanMatrixUtil;
import cn.power.util.MatrixUtilV1;
import cn.power.util.MatrixUtilV2;

import java.util.*;

/**
 * Created by ray on 2017/1/14.
 */
public class Power {
    public static DataProvider dataProvider;

    public static Map<Source, List<Long>> source = new HashMap<>();

    public static void init(DataProvider dp, List<Element> elements) {
        dataProvider = dp;
        init(elements);
    }

    private static void init(List<Element> elements) {
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

        List<List<Integer>> points = CoreV2.getCoreData(genPointMatrix(elements, orgPointIndex));

        for (List<Integer> indexes : points) {
            List<Long> youGotDotPointIds = getIdFromIndex(indexes, orgIndexPoint);
            Dot newDot = new Dot(elements, youGotDotPointIds);

            //保存节点信息
            dataProvider.saveDot(newDot);
            //更新保存端点信息
            for (long pointId : youGotDotPointIds) {
                Point p = dataProvider.getPointFromId(pointId);
                p.setDotId(newDot.getId());
                dataProvider.savePoint(p);
            }
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
        List<List<Integer>> dotsets = CoreV2.getCoreData(genDotMatrix(elements, orgDotIndex));

        for (List<Integer> indexes : dotsets) {
            List<Long> youGotDotSetDotIds = getIdFromIndex(indexes, orgIndexDot);
            DotSet ds = new DotSet(elements, youGotDotSetDotIds);
            dataProvider.saveDotSet(ds);

            for (long dotid : youGotDotSetDotIds) {
                List<Point> pointSet = dataProvider.getPointFromDotId(dotid);
                for (Point p : pointSet) {
                    p.setDotsetId(ds.getId());
                    dataProvider.savePoint(p);
                }
            }

        }
    }

    public static List<Long> getIdFromIndex(List<Integer> indexes, Map<Integer, Long> indexPointMap) {
        List<Long> res = new ArrayList<>();
        for (int index : indexes) {
            res.add(indexPointMap.get(index));
        }
        return res;
    }

    public static Map<Source, List<Long>> setSource(Map<Source, List<Long>> src) {
        source = src;
        return getPointsWithSource(source);
    }

    public static Map<Source, List<Long>> addSource(Map<Source, List<Long>> src) {
        List<Long> addP = src.get(Source.S);
        List<Long> targetP = source.get(Source.S);
        for (long p : addP) {
            if (!targetP.contains(p)) {
                targetP.add(p);
            }
        }
        return getPointsWithSource(source);
    }

    public static Map<Source, List<Long>> getPointsWithSource(Map<Source, List<Long>> src) {
        List<Long> points = src.get(Source.S);
        List<Long> result = new ArrayList<>();

        List<Long> dotsets = new ArrayList<>();

        for (long pID : points) {
            long dsId = dataProvider.getPointFromId(pID).getDotsetId();
            if (!dotsets.contains(dsId)) {
                dotsets.add(dsId);
                List<Point> ps = dataProvider.getPointFromDotSetId(dsId);
                for (Point p : ps) {
                    result.add(p.getId());
                }
            }
        }
        Map<Source, List<Long>> map = new HashMap<>();
        map.put(Source.S, result);
        return map;
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

    public static BooleanMatrix genPointMatrix(List<Element> elements, Map<Long, Integer> pointIndex) {

        int pointSize = pointIndex.size();
        BooleanMatrix pointMatrix = BooleanMatrix.identity(pointSize, pointSize);//对角线设置为1的矩阵

        for (Element element : elements) {
            if (element.getElementStatus() == ElementStatus.OFF || element.getElementStatus() == ElementStatus.YOU_CAN_CONTROL) {
                continue;
            }

            int[] indexes = new int[element.getPoints().size()];
            for (int i = 0; i < element.getPoints().size(); i++) {
                indexes[i] = pointIndex.get(element.getPoints().get(i));
            }
            BooleanMatrixUtil.setMatrixValue(pointMatrix, PowerStatus.ON.getValue(), indexes);
        }
        return pointMatrix;
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


    public static BooleanMatrix genDotMatrix(List<Element> elements, Map<Long, Integer> dotIndex) {
        int dotSize = dotIndex.size();
        BooleanMatrix dotMatrix = BooleanMatrix.identity(dotSize, dotSize);

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

            BooleanMatrixUtil.setMatrixValue(dotMatrix, element.getStatus().getValue(), indexes);
        }
        return dotMatrix;
    }

}

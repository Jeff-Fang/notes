package cn.power.interfaces;

import cn.power.beans.base.Dot;
import cn.power.beans.base.DotSet;
import cn.power.beans.base.Element;
import cn.power.beans.base.Point;

import java.util.List;

/**
 * Created by ray on 2017/1/2.
 */
public interface DataProvider {
    /**
     * 保存元素
     *
     * @param elements
     */
    void saveElements(List<Element> elements);

    Element getElementFromId(long id);

    /**
     * 保存端点信息
     *
     * @param points
     */
    void savePoints(List<Point> points);

    void savePoint(Point point);

    Point getPointFromId(long id);

    List<Point> getPointFromDotId(long id);

    List<Point> getPointFromDotSetId(long id);


    /**
     * 保存节点
     *
     * @param dots
     */
    void saveDots(List<Dot> dots);

    void saveDot(Dot dot);

    Dot getDotFromId(long id);

    List<Dot> getAllDots();

    void saveDotSet(DotSet dotset);

    List<DotSet> getAllDotSets();

    DotSet getDotSetFromId(long id);
}

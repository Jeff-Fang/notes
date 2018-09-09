package cn.power.beans.base;

import java.util.List;

/**
 * Created by ray on 2017/1/1.
 */
public class Dot {
    long id;
    List<Long> points;
    String name;
    List<Long>  outElements;
    List<Long>  inElements;

    public Dot(List<Element> elements,List<Long> points) {
        this.points = points;
        // FIXME: 2017/1/6 通过elements 查找 inelements 以及 outelements
        this.id = System.currentTimeMillis();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Long> getPoints() {
        return points;
    }

    public void setPoints(List<Long> points) {
        this.points = points;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Long> getOutElements() {
        return outElements;
    }

    public void setOutElements(List<Long> outElements) {
        this.outElements = outElements;
    }

    public List<Long> getInElements() {
        return inElements;
    }

    public void setInElements(List<Long> inElements) {
        this.inElements = inElements;
    }

    public boolean inDot(long pointId) {
        for (long point : points) {
            if (point == pointId) {
                return true;
            }
        }
        return false;
    }
}

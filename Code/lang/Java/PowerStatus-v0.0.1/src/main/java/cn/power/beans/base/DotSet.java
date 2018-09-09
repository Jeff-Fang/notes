package cn.power.beans.base;

import java.util.List;

/**
 * Created by ray on 2017/1/1.
 */
public class DotSet {
    long id;
    List<Long> dots;
    String name;
    List<Long> outElements;
    List<Long> inElements;

    public DotSet(List<Element> elements, List<Long> dots) {
        this.dots = dots;
        // FIXME: 2017/1/7 通过elements 查找 inelements 以及 outelements
        this.id = System.currentTimeMillis();
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Long> getDots() {
        return dots;
    }

    public void setDots(List<Long> dots) {
        this.dots = dots;
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

    public boolean inDotSet(long dotId) {
        for (long dot : dots) {
            if (dot == dotId) {
                return true;
            }
        }
        return false;
    }
}

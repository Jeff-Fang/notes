package cn.power.beans.base;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ray on 2017/1/1.
 */
public class Point {
    long id;
    List<Long> elements;
    long dotId;
    long dotsetId;

    public long getDotId() {
        return dotId;
    }

    public void setDotId(long dotId) {
        this.dotId = dotId;
    }

    public long getDotsetId() {
        return dotsetId;
    }

    public void setDotsetId(long dotsetId) {
        this.dotsetId = dotsetId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Long> getElements() {
        return elements;
    }

    public void setElements(List<Long> elements) {
        this.elements = elements;
    }

    public void addElement(Element element) {
        if (elements == null) {
            elements = new ArrayList<>();
        }
        elements.add(element.getId());
    }

}

package cn.power.beans.base;

import java.util.List;

/**
 * 各开关元件
 * <p>
 * Created by JieGuo on 16/12/10.
 */
public abstract class Element {
    long id;
    String name;
    String nickName;
    int type;
    int subType;
    List<Long> points;
    int status;
    String desc;
    List<Long> dots;//如果只有一个，那么该元素属于某节点内部，如果有多个，则被多个节点夹住
    List<Long> dotsets;//如果只有一个，那么该元素属于某节点集内部，如果有多个，则被多个节点集夹住

    public abstract String getName();

    public abstract PowerStatus getStatus();

    public abstract ElementStatus getElementStatus();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getSubType() {
        return subType;
    }

    public void setSubType(int subType) {
        this.subType = subType;
    }

    public List<Long> getPoints() {
        return points;
    }

    public void setPoints(List<Long> points) {
        this.points = points;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<Long> getDots() {
        return dots;
    }

    public void setDots(List<Long> dots) {
        this.dots = dots;
    }

    public List<Long> getDotsets() {
        return dotsets;
    }

    public void setDotsets(List<Long> dotsets) {
        this.dotsets = dotsets;
    }

    public boolean addDot(Dot dot) {
        for (long dotId : dots) {
            if (dotId == dot.getId()) {
                return false;
            }
        }
        dots.add(dot.getId());
        return true;
    }

    public void clearDots() {
        dots.clear();
    }

    public boolean addDotset(DotSet dotSet) {
        for (long dotsetId : dotsets) {
            if (dotsetId == dotSet.getId()) {
                return false;
            }
        }
        dotsets.add(dotSet.getId());
        return true;
    }

    public void clearDotsets() {
        dotsets.clear();
    }
}

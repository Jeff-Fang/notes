package cn.power.beans.base;

import java.util.List;

/**
 * Created by ray on 2017/1/1.
 */
public class Interval {
    long id;
    String Name;
    String nickName;
    List<Long> elements;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public List<Long> getElements() {
        return elements;
    }

    public void setElements(List<Long> elements) {
        this.elements = elements;
    }
}

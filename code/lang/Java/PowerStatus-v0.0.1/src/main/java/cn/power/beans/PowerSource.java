package cn.power.beans;

import cn.power.beans.elements.other.MasterWire;

import java.util.LinkedList;

/**
 * 电源
 * 这个电源下面接的线叫母线。 母线下面接的是子线
 * <p/>
 * Created by JieGuo on 16/11/4.
 */
public class PowerSource {

    LinkedList<MasterWire> masterWires = new LinkedList<MasterWire>();

    public void addItem(MasterWire item) {
        masterWires.add(item);
    }

    public LinkedList<MasterWire> getMasterWires() {
        return masterWires;
    }
}

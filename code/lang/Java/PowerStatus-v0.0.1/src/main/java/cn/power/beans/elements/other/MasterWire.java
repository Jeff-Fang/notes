package cn.power.beans.elements.other;

import cn.power.beans.base.Element;
import cn.power.beans.base.ElementStatus;
import cn.power.beans.base.Wire;
import cn.power.beans.base.PowerStatus;
import cn.power.beans.elements.transformers.StationTransformer;

import java.util.LinkedList;

/**
 * 母线
 *
 * Created by JieGuo on 16/12/10.
 */
public class MasterWire extends Element implements Wire {

    private String name = "母线";

    LinkedList<StationTransformer> subLines = new LinkedList<StationTransformer>();

    public MasterWire() {
    }

    public MasterWire(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public PowerStatus getStatus() {
        return PowerStatus.ON;
    }

    @Override
    public ElementStatus getElementStatus() {
        return ElementStatus.ON;
    }

    public void addSubLine(StationTransformer subLine){

        this.subLines.add(subLine);
    }

    public LinkedList<StationTransformer> getSubLines() {
        return subLines;
    }
}

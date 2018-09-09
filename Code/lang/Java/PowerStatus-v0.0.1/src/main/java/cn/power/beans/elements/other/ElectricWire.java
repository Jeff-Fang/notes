package cn.power.beans.elements.other;

import cn.power.beans.base.Element;
import cn.power.beans.base.ElementStatus;
import cn.power.beans.base.PowerStatus;
import cn.power.beans.base.Wire;

/**
 * 导线
 * <p/>
 * 线分两端,头和尾
 * <p/>
 * Created by JieGuo on 16/12/10.
 */
public class ElectricWire extends Element implements Wire {


    private Element head;

    private Element tail;

    @Override
    public String getName() {
        return "连接线";
    }

    @Override
    public PowerStatus getStatus() {
        return PowerStatus.ON;
    }

    @Override
    public ElementStatus getElementStatus() {
        return ElementStatus.ON;
    }


}

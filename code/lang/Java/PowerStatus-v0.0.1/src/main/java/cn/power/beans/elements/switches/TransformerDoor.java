package cn.power.beans.elements.switches;

import cn.power.beans.base.Element;
import cn.power.beans.base.ElementStatus;
import cn.power.beans.base.PowerStatus;
import cn.power.beans.base.Switch;

/**
 * 变压器 网门
 *
 * Created by JieGuo on 16/12/19.
 */
public class TransformerDoor extends Element implements Switch {

    private PowerStatus status = PowerStatus.OFF;

    @Override
    public void turnOn() {
        status = PowerStatus.ON;
    }

    @Override
    public void turnOff() {
        status = PowerStatus.OFF;
    }

    @Override
    public String getName() {
        return "网门";
    }

    @Override
    public PowerStatus getStatus() {
        return status;
    }

    @Override
    public ElementStatus getElementStatus() {
        return ElementStatus.YOU_CAN_CONTROL;
    }
}

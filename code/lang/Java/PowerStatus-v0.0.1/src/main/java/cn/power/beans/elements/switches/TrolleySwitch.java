package cn.power.beans.elements.switches;

import cn.power.beans.base.Element;
import cn.power.beans.base.ElementStatus;
import cn.power.beans.base.PowerStatus;
import cn.power.beans.base.Switch;

/**
 * 小车开关
 *
 * Created by JieGuo on 16/12/10.
 */
public class TrolleySwitch extends Element implements Switch {

    private PowerStatus status = PowerStatus.OFF;

    @Override
    public String getName() {
        return "小车开关";
    }

    @Override
    public PowerStatus getStatus() {
        return status;
    }

    @Override
    public ElementStatus getElementStatus() {
        return ElementStatus.YOU_CAN_CONTROL;
    }

    @Override
    public void turnOn() {
        status = PowerStatus.ON;
    }

    @Override
    public void turnOff() {
        status = PowerStatus.OFF;
    }
}

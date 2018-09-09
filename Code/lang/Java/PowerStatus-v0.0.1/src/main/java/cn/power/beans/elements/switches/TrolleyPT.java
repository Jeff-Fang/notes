package cn.power.beans.elements.switches;

import cn.power.beans.base.Element;
import cn.power.beans.base.ElementStatus;
import cn.power.beans.base.PowerStatus;

/**
 * 小车PT
 *
 * Created by JieGuo on 16/12/10.
 */
public class TrolleyPT extends Element {


    @Override
    public String getName() {
        return "小车PT";
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

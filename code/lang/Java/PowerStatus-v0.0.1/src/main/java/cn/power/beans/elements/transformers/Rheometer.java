package cn.power.beans.elements.transformers;

import cn.power.beans.base.Element;
import cn.power.beans.base.ElementStatus;
import cn.power.beans.base.PowerStatus;

/**
 * 流变器
 *
 * Created by JieGuo on 16/12/10.
 */
public class Rheometer extends Element {

    @Override
    public String getName() {
        return "流变器（CT）";
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

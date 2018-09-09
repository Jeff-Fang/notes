package cn.power.beans.elements.other;

import cn.power.beans.base.Element;
import cn.power.beans.base.ElementStatus;
import cn.power.beans.base.PowerStatus;

/**
 * 中性点星型连接
 * @author YW.SUN
 * @from 2016/12/19
 */
public class NeutralStarConnection extends Element{

    @Override
    public String getName() {
        return "中性点星型连接";
    }

    @Override
    public PowerStatus getStatus() {
        return PowerStatus.OFF;
    }

    @Override
    public ElementStatus getElementStatus() {
        return ElementStatus.OFF;
    }
}

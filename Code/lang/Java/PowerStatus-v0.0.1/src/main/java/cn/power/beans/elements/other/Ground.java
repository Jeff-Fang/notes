package cn.power.beans.elements.other;

import cn.power.beans.base.Element;
import cn.power.beans.base.ElementStatus;
import cn.power.beans.base.PowerStatus;
import cn.power.beans.base.Wire;

/**
 * 地线
 * <p/>
 * Created by JieGuo on 16/12/10.
 */
public class Ground extends Element implements Wire {

    @Override
    public String getName() {
        return "接地线";
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

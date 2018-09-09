package cn.power.beans.elements.transformers;

import cn.power.beans.base.Element;
import cn.power.beans.base.ElementStatus;
import cn.power.beans.base.PowerStatus;

/**
 * 接地变
 *
 * Created by JieGuo on 16/12/10.
 */
public class GroundTransformer extends Element {

    @Override
    public String getName() {
        return "接地变";
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

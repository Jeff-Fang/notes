package cn.power.beans.elements.transformers;

import cn.power.beans.base.Element;
import cn.power.beans.base.ElementStatus;
import cn.power.beans.base.PowerStatus;

/**
 * 小车所变
 *
 * Created by JieGuo on 16/12/10.
 */
public class TrolleyTransformer extends Element {

    @Override
    public String getName() {
        return "小车所变";
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

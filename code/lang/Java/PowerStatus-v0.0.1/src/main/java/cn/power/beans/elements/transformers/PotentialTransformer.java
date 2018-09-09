package cn.power.beans.elements.transformers;

import cn.power.beans.base.Element;
import cn.power.beans.base.ElementStatus;
import cn.power.beans.base.PowerStatus;

/**
 * 压变（PT）
 *
 * Created by JieGuo on 16/12/10.
 */
public class PotentialTransformer extends Element {

    @Override
    public String getName() {
        return "压变（PT）";
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

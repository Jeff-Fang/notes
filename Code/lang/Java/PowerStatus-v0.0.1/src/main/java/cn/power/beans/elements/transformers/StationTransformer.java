package cn.power.beans.elements.transformers;

import cn.power.beans.base.Element;
import cn.power.beans.base.ElementStatus;
import cn.power.beans.base.PowerStatus;

/**
 * 站用变
 * 始终连通
 *
 * Created by JieGuo on 16/12/10.
 */
public class StationTransformer extends Element {

    private String name = "站用变";

    public StationTransformer() {
    }

    public StationTransformer(String name) {
        this.name = "站用变" + " : " + name;
    }

    @Override
    public String getName() {
        return name;
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

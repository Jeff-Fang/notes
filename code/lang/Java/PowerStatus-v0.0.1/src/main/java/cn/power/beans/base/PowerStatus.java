package cn.power.beans.base;

/**
 * the power status
 * <p>
 * Created by JieGuo on 16/11/4.
 */
public enum PowerStatus {


    /**
     * 非连通状态
     */
    OFF(false),

    /**
     * 连通状态
     */
    ON(true);


    private boolean value;

    private PowerStatus(boolean value) {
        this.value = value;
    }

    public boolean getValue() {
        return value;
    }
}

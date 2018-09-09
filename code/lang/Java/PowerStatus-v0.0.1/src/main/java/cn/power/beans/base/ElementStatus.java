package cn.power.beans.base;

/**
 * Created by ray on 2017/1/2.
 */
public enum ElementStatus {

    /**
     * 永断元件
     */
    OFF(0),

    /**
     * 永通元件
     */
    ON(1),

    /**
     * 可操作元件
     */
    YOU_CAN_CONTROL(2);

    private int value;

    private ElementStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

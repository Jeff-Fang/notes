package cn.power.beans.base;

/**
 * Created by ray on 2017/1/7.
 */
public enum Source {

    S(0),//Source 有压电源
    E(1),//Earth 接地
    N(2),//No 悬空源
    L(3),//Load 负荷源
    C(4);//Capacitance 电容源

    private int value;

    private Source(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

package com.bw.movie.bean;

/**
 * Author:user
 * Date:2018/9/12 16:19
 * Description:This is EventBusBean_1
 */
public class EventBusBean_1 {
    private boolean flag;

    public EventBusBean_1(boolean flag) {
        this.flag = flag;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "EventBusBean_1{" +
                "flag=" + flag +
                '}';
    }
}

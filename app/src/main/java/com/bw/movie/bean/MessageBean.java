package com.bw.movie.bean;

public class MessageBean {
    private int can1;
    private int can2;
    private String can3;
    private String can4;
    private String can5;
    private String can6;
    private String can7;
    private String can8;
    private String can9;

    public int getCan1() {
        return can1;
    }

    public void setCan1(int can1) {
        this.can1 = can1;
    }

    public int getCan2() {
        return can2;
    }

    public void setCan2(int can2) {
        this.can2 = can2;
    }

    public String getCan3() {
        return can3;
    }

    public void setCan3(String can3) {
        this.can3 = can3;
    }

    public String getCan4() {
        return can4;
    }

    public void setCan4(String can4) {
        this.can4 = can4;
    }

    public String getCan5() {
        return can5;
    }

    public void setCan5(String can5) {
        this.can5 = can5;
    }

    public String getCan6() {
        return can6;
    }

    public void setCan6(String can6) {
        this.can6 = can6;
    }

    public String getCan7() {
        return can7;
    }

    public void setCan7(String can7) {
        this.can7 = can7;
    }

    public String getCan8() {
        return can8;
    }

    public void setCan8(String can8) {
        this.can8 = can8;
    }

    public MessageBean(int can1, int can2, String can3, String can4, String can5, String can6, String can7, String can8, String can9) {
        this.can1 = can1;
        this.can2 = can2;
        this.can3 = can3;
        this.can4 = can4;
        this.can5 = can5;
        this.can6 = can6;
        this.can7 = can7;
        this.can8 = can8;
        this.can9 = can9;
    }

    public String getCan9() {
        return can9;
    }

    public void setCan9(String can9) {
        this.can9 = can9;
    }

    @Override
    public String toString() {
        return "MessageBean{" +
                "can1=" + can1 +
                ", can2=" + can2 +
                ", can3='" + can3 + '\'' +
                ", can4='" + can4 + '\'' +
                ", can5='" + can5 + '\'' +
                ", can6='" + can6 + '\'' +
                '}';
    }
}

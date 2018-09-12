package com.bw.movie.bean;

public class LocationBean {
    private double jingdu;
    private double weidu;

    public LocationBean(double jingdu, double weidu) {
        this.jingdu = jingdu;
        this.weidu = weidu;
    }

    public double getJingdu() {
        return jingdu;
    }

    public void setJingdu(double jingdu) {
        this.jingdu = jingdu;
    }

    public double getWeidu() {
        return weidu;
    }

    public void setWeidu(double weidu) {
        this.weidu = weidu;
    }
}

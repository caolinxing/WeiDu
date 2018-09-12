package com.bw.movie.bean;

import java.util.List;

public class PicMessageBean {
    private List<String> list;
    private int postion;


    public PicMessageBean(List<String> list, int postion) {
        this.list = list;
        this.postion = postion;
    }

    public int getPostion() {
        return postion;
    }

    public void setPostion(int postion) {
        this.postion = postion;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "PicMessageBean{" +
                "list=" + list +
                '}';
    }
}

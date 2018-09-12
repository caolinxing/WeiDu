package com.bw.movie.bean;

public class CanShuBean {
    private String id;
    private int page;
    private int num;

    public CanShuBean(String id,int page, int num) {
        this.id = id;
        this.page = page;
        this.num = num;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}

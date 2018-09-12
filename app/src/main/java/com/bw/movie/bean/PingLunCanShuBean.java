package com.bw.movie.bean;

public class PingLunCanShuBean {
    private int id;
    private int page;
    private int count;

    public PingLunCanShuBean(int id, int page, int count) {
        this.id = id;
        this.page = page;
        this.count = count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

}

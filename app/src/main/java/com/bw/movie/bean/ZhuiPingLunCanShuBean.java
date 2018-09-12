package com.bw.movie.bean;

public class ZhuiPingLunCanShuBean {
    private int id;
    private int page;
    private int count;
    private int flag;
    private String api;


    public ZhuiPingLunCanShuBean(int id, int page, int count, int flag, String api) {
        this.id = id;
        this.page = page;
        this.count = count;
        this.flag = flag;
        this.api = api;
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

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public String getApi() {
        return api;
    }

    public void setApi(String api) {
        this.api = api;
    }
}

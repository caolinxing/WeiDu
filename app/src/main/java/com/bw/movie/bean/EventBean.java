package com.bw.movie.bean;

public class EventBean {
    public int movidId;
    public int cinemaId;
    public int page;
    public int count;

    public EventBean(int movidId, int cinemaId, int page, int count) {
        this.movidId = movidId;
        this.cinemaId = cinemaId;
        this.page = page;
        this.count = count;
    }

    public int getMovidId() {
        return movidId;
    }

    public void setMovidId(int movidId) {
        this.movidId = movidId;
    }

    public int getCinemaId() {
        return cinemaId;
    }

    public void setCinemaId(int cinemaId) {
        this.cinemaId = cinemaId;
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

package com.bw.movie.bean;

public class MessageEvent {

    private int tag;

    public MessageEvent(int tag) {
        this.tag = tag;
    }

    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }
}

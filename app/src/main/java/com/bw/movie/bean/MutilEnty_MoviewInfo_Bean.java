package com.bw.movie.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

public class MutilEnty_MoviewInfo_Bean implements MultiItemEntity{
    public static final int TYPE_ONE = 1;
    public static final int TYPE_TWO = 2;
    public static final int TYPE_THREE = 3;
    public static final int TYPE_FOUR = 4;
    public static final int TYPE_FIVE = 5;
    public static final int TYPE_SIX = 6;

    private int itemType;
    private MovieInfoBean movieInfo;
    private MoviePingLunListBean getComment;

    public MutilEnty_MoviewInfo_Bean(int itemType, MovieInfoBean movieInfo, MoviePingLunListBean getComment) {
        this.itemType = itemType;
        this.movieInfo = movieInfo;
        this.getComment = getComment;
    }

    public MovieInfoBean getMovieInfo() {
        return movieInfo;
    }

    public MoviePingLunListBean getGetComment() {
        return getComment;
    }

    @Override
    public int getItemType() {
        return itemType;
    }
}

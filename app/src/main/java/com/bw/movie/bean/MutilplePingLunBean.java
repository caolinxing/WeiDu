package com.bw.movie.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

public class MutilplePingLunBean implements MultiItemEntity {
    public static final int TYPE_ONE = 1;
    public static final int TYPE_TWO = 2;

    private ZhuiPingListBean.ResultBean zhuiPinglistBean;
    private MoviePingLunListBean.ResultBean pingLunListBean;
    private int itemType;

    public MutilplePingLunBean(ZhuiPingListBean.ResultBean zhuiPinglistBean, MoviePingLunListBean.ResultBean lunListBean, int itemType) {
        this.zhuiPinglistBean = zhuiPinglistBean;
        this.pingLunListBean = lunListBean;
        this.itemType = itemType;
    }

    @Override
    public int getItemType() {
        return itemType;
    }

    public ZhuiPingListBean.ResultBean getZhuiPinglistBean() {
        return zhuiPinglistBean;
    }

    public MoviePingLunListBean.ResultBean getPingLunListBean() {
        return pingLunListBean;
    }
}

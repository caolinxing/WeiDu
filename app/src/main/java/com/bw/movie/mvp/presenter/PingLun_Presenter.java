package com.bw.movie.mvp.presenter;

import com.bw.movie.bean.MoviePingLunListBean;
import com.bw.movie.bean.ZhuiPingListBean;
import com.bw.movie.mvp.contract.PingLun_Contract;
import com.bw.movie.mvp.model.PingLun_Model;

import java.util.List;

public class PingLun_Presenter implements PingLun_Contract.Presenter, PingLun_Contract.GetDataState, PingLun_Contract.GetDataStateZhui {
    private PingLun_Contract.View view;
    private PingLun_Contract.Model model;

    public PingLun_Presenter(PingLun_Contract.View view) {
        this.view = view;
        model = new PingLun_Model();
    }

    @Override
    public void setData() {
        model.setData(view.setPingLunCan(),this);
    }

    @Override
    public void setDataZhui() {
        model.setDataZhui(view.setPingLunCan(),this);
    }

    @Override
    public void onDestory() {
        if (view!=null){
            view=null;
        }
    }

    @Override
    public void onSuccessfully(MoviePingLunListBean list) {
        view.onSuccessfully(list);
    }

    @Override
    public void onSuccessfully_zhui(ZhuiPingListBean zhuiPingListBean) {
        view.onSuccessfully_zhui(zhuiPingListBean);
    }

    @Override
    public void onFaild(String e) {
        view.onFaild(e);
    }
}

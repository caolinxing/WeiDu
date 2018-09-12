package com.bw.movie.mvp.presenter;

import com.bw.movie.base.BaseModel;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.bean.AllCinemaBean;
import com.bw.movie.bean.NearCinemaBean;
import com.bw.movie.mvp.contract.CinemaContract;
import com.bw.movie.mvp.model.CinemaModel;

import java.lang.ref.WeakReference;
import java.util.List;

public class CinemaPresenter extends BasePresenter implements CinemaContract.Presenter {

    private CinemaContract.View view;
    private CinemaContract.Model model;

    public CinemaPresenter(Object view) {
        super(view);
        this.view = (CinemaContract.View) view;
        model = new CinemaModel();
    }

    @Override
    protected WeakReference getWeak() {
        return null;
    }

    @Override
    protected BaseModel initModel() {
        return null;
    }

    @Override
    public void loadFJCinema() {
        String longitude = view.getLongitude();
        String latitude = view.getLatitude();
        String page = view.getPage();
        String count = view.getCount();
        model.loadFJCinema("0","0",longitude,latitude,page,count,this);
    }

    @Override
    public void onSuccess2FjCinema(List<NearCinemaBean.ResultBean.NearbyCinemaListBean> list) {
        view.setFJCinema(list);
    }

    @Override
    public void loadAllCinema() {
        String page = view.getPage();
        String count = view.getCount();
        model.loadAllCinema("0","0",page,count,this);
    }

    @Override
    public void onSuccess2AllCinema(List<AllCinemaBean.ResultBean> list) {
        view.setAllCinema(list);
    }

}

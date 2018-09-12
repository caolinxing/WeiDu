package com.bw.movie.mvp.presenter;

import com.bw.movie.bean.MovieInfoBean;
import com.bw.movie.mvp.contract.MovieInfo_Contract;
import com.bw.movie.mvp.model.MovieInfo_Model;
import com.bw.movie.utils.OnHttpCallBack;

public class MovieInfo_Presenter implements MovieInfo_Contract.Presenter, OnHttpCallBack<MovieInfoBean> {
    MovieInfo_Contract.View view;
    MovieInfo_Contract.Model model;

    public MovieInfo_Presenter(MovieInfo_Contract.View view) {
        this.view = view;
        model = new MovieInfo_Model();
    }

    @Override
    public void setData() {
        model.setData(view.setCan().getId(),this);
    }

    @Override
    public void setData1() {
        model.setData1(view.setCan().getId(),this);
    }

    @Override
    public void onSuccessful(MovieInfoBean movieInfoBean) {
        view.onSuccessful(movieInfoBean);
    }

    @Override
    public void onFaild(String errorMsg) {
        view.onFaild(errorMsg);
    }
}

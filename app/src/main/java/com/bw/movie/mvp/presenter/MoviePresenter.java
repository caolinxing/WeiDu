package com.bw.movie.mvp.presenter;

import com.bw.movie.base.BaseModel;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.bean.MovieBean;
import com.bw.movie.mvp.contract.MovieContract;
import com.bw.movie.mvp.model.MovieModel;

import java.lang.ref.WeakReference;
import java.util.List;

public class MoviePresenter extends BasePresenter implements MovieContract.Presenter, MovieContract.GetDataState {
    private MovieContract.View view;
    private MovieContract.Model model;
    public MoviePresenter(Object view) {
        //noinspection unchecked
        super(view);
        this.view = (MovieContract.View) view;
        model = new MovieModel();
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
    public void loadHotMovie() {
        String page = view.getPage();
        String count = view.getCount();
        model.loadHotMovie(page,count,this);
    }

    @Override
    public void loadNowMoview() {
        String page = view.getPage();
        String count = view.getCount();
        model.loadNowMoview(page,count,this);
        model.loadNowMoview(page,count,this);
    }

    @Override
    public void commingSoonMoview() {
        String page = view.getPage();
        String count = view.getCount();
        model.commingSoonMoview(page,count,this);
    }

    @Override
    public void onSuccess2HotMovie(List<MovieBean.ResultBean> list) {
        view.onSuccess2HotMovie(list);
    }

    @Override
    public void onFaild(String e) {
        view.onFaild(e);
    }
}

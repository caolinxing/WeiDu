package com.bw.movie.mvp.presenter;

import com.bw.movie.base.BaseModel;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.bean.CinemaCommentBean;
import com.bw.movie.mvp.contract.AllCinemaCommentContract;
import com.bw.movie.mvp.model.AllCinemaCommentModel;

import java.lang.ref.WeakReference;

public class AllCinemaCommentPresenter extends BasePresenter implements AllCinemaCommentContract.Presenter {

    private AllCinemaCommentContract.View view;
    private AllCinemaCommentContract.Model model;

    public AllCinemaCommentPresenter(Object view) {
        super(view);
        this.view = (AllCinemaCommentContract.View) view;
        model = new AllCinemaCommentModel();
    }

    @Override
    public void loadAllCinemaComment() {
        String cinemaId = view.getCinemaId();
        String page = view.getPage();
        String count = view.getCount();
        model.loadAllCinemaComment("0","0",cinemaId,page,count,this);
    }

    @Override
    public void onSuccessToAllCinemaComment(CinemaCommentBean cinemaComment) {
        view.setAllCinemaComment(cinemaComment);
    }

    @Override
    protected WeakReference getWeak() {
        return null;
    }

    @Override
    protected BaseModel initModel() {
        return null;
    }
}

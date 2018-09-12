package com.bw.movie.mvp.presenter;

import com.bw.movie.base.BaseModel;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.bean.CinemaCommentBean;
import com.bw.movie.mvp.contract.CinemaInfoContract;
import com.bw.movie.mvp.model.CinemaInfoModel;

import java.lang.ref.WeakReference;

public class CinemaInfoPresenter extends BasePresenter implements CinemaInfoContract.Presenter {

    private CinemaInfoContract.View view;
    private CinemaInfoContract.Model model;

    public CinemaInfoPresenter(Object view) {
        super(view);
        this.view = (CinemaInfoContract.View) view;
        model = new CinemaInfoModel();
    }

    @Override
    public void loadComment() {
        String userId = view.getUserId();
        String sessionId = view.getSessionId();
        String cinemaId = view.getCinemaId();
        String count = view.getCount();
        String page = view.getPage();
        model.loadComment(userId,sessionId,cinemaId,page,count,this);
    }

    @Override
    public void onSuccessToComment(CinemaCommentBean cinemaCommentBean) {
        view.setComment(cinemaCommentBean);
    }

    @Override
    public void pingLun() {
        String userId = view.getUserId();
        String sessionId = view.getSessionId();
        String cinemaId = view.getCinemaId();
        String content = view.getContent();
        model.pingLun(userId,sessionId,cinemaId,content,this);
    }

    @Override
    public void onSuccessToPingLun(CinemaCommentBean cinemaCommentBean) {
        view.setPingLun(cinemaCommentBean.getMessage());
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

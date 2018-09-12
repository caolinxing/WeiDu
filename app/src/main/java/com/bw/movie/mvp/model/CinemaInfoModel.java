package com.bw.movie.mvp.model;


import android.util.Log;

import com.bw.movie.bean.CinemaCommentBean;
import com.bw.movie.mvp.contract.CinemaInfoContract;
import com.bw.movie.utils.Api;
import com.bw.movie.utils.MyServer;
import com.bw.movie.utils.RetrofitUtils;

import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class CinemaInfoModel implements CinemaInfoContract.Model {
    @Override
    public void loadComment(String userId,String sessionId,final String cinemaId, final String page, final String count, final CinemaInfoContract.Presenter presenter) {
        RetrofitUtils.getInstance(Api.BASE_API)
                .getRetrofit()
                .create(MyServer.class)
                .loadCinemaComment(userId,sessionId,cinemaId,page,count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CinemaCommentBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(CinemaCommentBean cinemaCommentBean) {
                        presenter.onSuccessToComment(cinemaCommentBean);
                    }
                });
    }

    @Override
    public void pingLun(String userId, String sessionId, String cinemaId, String content, final CinemaInfoContract.Presenter presenter) {
        RetrofitUtils.getInstance(Api.BASE_API)
                .getRetrofit()
                .create(MyServer.class)
                .pingLun(userId,sessionId,cinemaId,content)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<CinemaCommentBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("-----------------", "onError: "+e );
                    }

                    @Override
                    public void onNext(CinemaCommentBean cinemaCommentBean) {
                        presenter.onSuccessToPingLun(cinemaCommentBean);
                    }
                });
    }
}

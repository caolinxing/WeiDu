package com.bw.movie.mvp.model;

import android.util.Log;

import com.bw.movie.bean.CinemaCommentBean;
import com.bw.movie.mvp.contract.AllCinemaCommentContract;
import com.bw.movie.utils.Api;
import com.bw.movie.utils.MyServer;
import com.bw.movie.utils.RetrofitUtils;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class AllCinemaCommentModel implements AllCinemaCommentContract.Model {
    @Override
    public void loadAllCinemaComment(String userId,String sessionId,String cinemaId, String page, String count, final AllCinemaCommentContract.Presenter presenter) {
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
                        Log.e("---------------", "onError: "+e );
                    }

                    @Override
                    public void onNext(CinemaCommentBean cinemaCommentBean) {
                        presenter.onSuccessToAllCinemaComment(cinemaCommentBean);
                    }
                });
    }
}

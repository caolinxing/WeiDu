package com.bw.movie.mvp.model;

import com.bw.movie.bean.MemberAttentionCinema;
import com.bw.movie.mvp.contract.MemberAttentionCinemaContract;
import com.bw.movie.utils.Api;
import com.bw.movie.utils.MyServer;
import com.bw.movie.utils.RetrofitUtils;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MemberAttentionCinemaModel implements MemberAttentionCinemaContract.Model {
    @Override
    public void requestAttentionCinema(String page, String count, final GetAttentionCinemaCallBack callBack) {
        MyServer myServer = RetrofitUtils.getInstance(Api.BASE_API).getRetrofit().create(MyServer.class);

        Observable<MemberAttentionCinema> attentionCinema =
                myServer.getAttentionCinema("309", "1536153092704309", page, count);

        attentionCinema.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<MemberAttentionCinema>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                         callBack.onError(e.getMessage());
                    }

                    @Override
                    public void onNext(MemberAttentionCinema memberAttentionCinema) {
                        callBack.onSuccess(memberAttentionCinema.getResult());
                    }
                });

    }
}

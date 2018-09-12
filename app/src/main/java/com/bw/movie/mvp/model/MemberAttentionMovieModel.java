package com.bw.movie.mvp.model;

import com.bw.movie.bean.MemberAttentionMovies;
import com.bw.movie.mvp.contract.MemberAttentionMovieContract;
import com.bw.movie.utils.Api;
import com.bw.movie.utils.MyServer;
import com.bw.movie.utils.RetrofitUtils;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class MemberAttentionMovieModel implements MemberAttentionMovieContract.Model {

    @Override
    public void requestAttentionMovie(int userId,String session,String page, String count, final getAttentionMoviesCallBack callBack) {

        MyServer myServer = RetrofitUtils.getInstance(Api.BASE_API).getRetrofit().create(MyServer.class);

        Observable<MemberAttentionMovies> attentionMovies = myServer.getAttentionMovies(Integer.valueOf(userId), session, page, count);

        attentionMovies.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<MemberAttentionMovies>() {
                    @Override
                    public void onCompleted() {

                    }
                    @Override
                    public void onError(Throwable e) {
                         callBack.onError();
                    }

                    @Override
                    public void onNext(MemberAttentionMovies memberAttentionMovies) {
                         callBack.onSuccess(memberAttentionMovies.getResult());
                    }
                });

    }
}

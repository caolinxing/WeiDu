package com.bw.movie.mvp.model;

import android.util.Log;

import com.bw.movie.bean.AllCinemaBean;
import com.bw.movie.bean.NearCinemaBean;
import com.bw.movie.mvp.contract.CinemaContract;
import com.bw.movie.utils.Api;
import com.bw.movie.utils.RetrofitUtils;
import com.bw.movie.utils.MyServer;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class CinemaModel implements CinemaContract.Model {
    @Override
    public void loadFJCinema(String userId,String sessionId,String longitude, String latitude, final String page, String count, final CinemaContract.Presenter presenter) {
        RetrofitUtils.getInstance(Api.BASE_API)
                .getRetrofit()
                .create(MyServer.class)
                .loadFJCineam(userId,sessionId,longitude,latitude,page,count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<NearCinemaBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(NearCinemaBean fjCinemaBean) {
                        presenter.onSuccess2FjCinema(fjCinemaBean.getResult().getNearbyCinemaList());
                    }
                });
    }

    @Override
    public void loadAllCinema(String userId,String sessionId,String page, String count, final CinemaContract.Presenter presenter) {
        RetrofitUtils.getInstance(Api.BASE_API)
                .getRetrofit()
                .create(MyServer.class)
                .loadAllCineam(userId,sessionId,page,count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AllCinemaBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("------------------", "onError: "+e );
                    }

                    @Override
                    public void onNext(AllCinemaBean allCinemaBean) {
                        presenter.onSuccess2AllCinema(allCinemaBean.getResult());
                    }
                });
    }
}

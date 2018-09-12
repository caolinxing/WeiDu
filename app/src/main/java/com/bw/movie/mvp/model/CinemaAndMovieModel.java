package com.bw.movie.mvp.model;

import android.util.Log;

import com.bw.movie.bean.CinemaCommentBean;
import com.bw.movie.bean.CinemaInfoBean;
import com.bw.movie.bean.CinemaToMovieBean;
import com.bw.movie.bean.MovieScheduleBean;
import com.bw.movie.mvp.contract.CinemaAndMovieContract;
import com.bw.movie.utils.Api;
import com.bw.movie.utils.MyServer;
import com.bw.movie.utils.RetrofitUtils;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class CinemaAndMovieModel implements CinemaAndMovieContract.Model {
    @Override
    public void followCinema(final String userId, String sessionId, final String cinemaId, final CinemaAndMovieContract.Presenter presenter) {
        RetrofitUtils.getInstance(Api.BASE_API)
                .getRetrofit()
                .create(MyServer.class)
                .followCinema(userId,sessionId,cinemaId)
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
                    public void onNext(CinemaCommentBean cinemaInfoBean) {
                        presenter.onSuccessToFollowCinema(cinemaInfoBean);
                    }
                });
    }

    @Override
    public void cancleFollowCinema(String userId, String sessionId, String cinemaId, final CinemaAndMovieContract.Presenter presenter) {
        RetrofitUtils.getInstance(Api.BASE_API)
                .getRetrofit()
                .create(MyServer.class)
                .cancleFollowCinema(userId,sessionId,cinemaId)
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
                    public void onNext(CinemaCommentBean cinemaInfoBean) {
                        Log.e("-------------------", "onNext: "+cinemaInfoBean.getMessage() );
                        presenter.onSuccessToCancleFollowCinema(cinemaInfoBean);
                    }
                });
    }

    @Override
    public void loadCinemaInfo(String userId, String sessionId,String cinemaId, final CinemaAndMovieContract.Presenter presenter) {
        RetrofitUtils.getInstance(Api.BASE_API)
                .getRetrofit()
                .create(MyServer.class)
                .loadCinemaInfo(userId,sessionId,cinemaId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CinemaInfoBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(CinemaInfoBean cinemaInfoBean) {
                        presenter.onSuccessToCinemaInfo(cinemaInfoBean);
                    }
                });
    }

    @Override
    public void loadCinemaMovie(String userId, String sessionId,String cinemaId, final CinemaAndMovieContract.Presenter presenter) {
        RetrofitUtils.getInstance(Api.BASE_API)
                .getRetrofit()
                .create(MyServer.class)
                .loadMovieToCinema(userId,sessionId,cinemaId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CinemaToMovieBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(CinemaToMovieBean cinemaInfoBean) {
                        presenter.onSuccessToCinemaToMovie(cinemaInfoBean);
                    }
                });
    }

    @Override
    public void loadMovieSchedule(String userId, String sessionId,String cinemasId, String movieId, final CinemaAndMovieContract.Presenter presenter) {
        RetrofitUtils.getInstance(Api.BASE_API)
                .getRetrofit()
                .create(MyServer.class)
                .loadMovieTimeAndAddress(userId,sessionId,cinemasId,movieId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MovieScheduleBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(MovieScheduleBean cinemaInfoBean) {
                        presenter.onSuccessToMovieTimeAndAddress(cinemaInfoBean);
                    }
                });
    }
}

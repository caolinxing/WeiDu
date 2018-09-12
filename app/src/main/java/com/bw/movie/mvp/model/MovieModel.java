package com.bw.movie.mvp.model;

import android.graphics.Bitmap;
import android.util.Log;

import com.bw.movie.bean.MovieBean;
import com.bw.movie.mvp.contract.MovieContract;
import com.bw.movie.utils.Api;
import com.bw.movie.utils.BitMap;
import com.bw.movie.utils.RetrofitUtils;
import com.bw.movie.utils.MyServer;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.adapter.rxjava.HttpException;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MovieModel implements MovieContract.Model {
    private static final String TAG = MovieModel.class.getSimpleName();
    private List<Bitmap> bitmaps = new ArrayList<>();

    @Override
    public void loadHotMovie(String page, String count, final MovieContract.GetDataState callBack) {
        RetrofitUtils.getInstance(Api.BASE_API)
                .getRetrofit()
                .create(MyServer.class)
                .loadHotMovie(page, count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MovieBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e instanceof HttpException) {
                            HttpException httpException = (HttpException) e;
                            //httpException.response().errorBody().string()
                            int code = httpException.code();
                            if (code == 500 || code == 404) {
                                callBack.onFaild("服务器出错");
                            }
                        } else if (e instanceof ConnectException) {
                            callBack.onFaild("网络断开,请打开网络!");
                        } else if (e instanceof SocketTimeoutException) {
                            callBack.onFaild("网络连接超时!!");
                        } else {
                            callBack.onFaild("发生未知错误" + e.getMessage());
                        }
                    }

                    @Override
                    public void onNext(MovieBean movieListBean) {
                        Log.i(TAG, "onNext: " + movieListBean.getResult().size());
                        callBack.onSuccess2HotMovie(movieListBean.getResult());
                    }
                });
    }

    @Override
    public void loadNowMoview(String page, String count, final MovieContract.GetDataState callBack) {
        RetrofitUtils.getInstance(Api.BASE_API)
                .getRetrofit()
                .create(MyServer.class)
                .rx_zhengzaimovie(page, count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MovieBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e instanceof HttpException) {
                            HttpException httpException = (HttpException) e;
                            //httpException.response().errorBody().string()
                            int code = httpException.code();
                            if (code == 500 || code == 404) {
                                callBack.onFaild("服务器出错");
                            }
                        } else if (e instanceof ConnectException) {
                            callBack.onFaild("网络断开,请打开网络!");
                        } else if (e instanceof SocketTimeoutException) {
                            callBack.onFaild("网络连接超时!!");
                        } else {
                            callBack.onFaild("发生未知错误" + e.getMessage());
                        }
                    }

                    @Override
                    public void onNext(final MovieBean movieListBean) {
                        Log.i(TAG, "onNext: " + movieListBean.getResult().size());
                        callBack.onSuccess2HotMovie(movieListBean.getResult());
                    }
                });
    }

    @Override
    public void commingSoonMoview(String page, String count, final MovieContract.GetDataState callBack) {
        RetrofitUtils.getInstance(Api.BASE_API)
                .getRetrofit()
                .create(MyServer.class)
                .rx_jijiangmovie(page, count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MovieBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e instanceof HttpException) {
                            HttpException httpException = (HttpException) e;
                            //httpException.response().errorBody().string()
                            int code = httpException.code();
                            if (code == 500 || code == 404) {
                                callBack.onFaild("服务器出错");
                            }
                        } else if (e instanceof ConnectException) {
                            callBack.onFaild("网络断开,请打开网络!");
                        } else if (e instanceof SocketTimeoutException) {
                            callBack.onFaild("网络连接超时!!");
                        } else {
                            callBack.onFaild("发生未知错误" + e.getMessage());
                        }
                    }

                    @Override
                    public void onNext(MovieBean movieListBean) {
                        Log.i(TAG, "onNext: " + movieListBean.getResult().size());
                        callBack.onSuccess2HotMovie(movieListBean.getResult());
                    }
                });
    }

}

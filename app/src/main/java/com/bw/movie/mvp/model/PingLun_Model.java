package com.bw.movie.mvp.model;

import android.util.Log;

import com.bw.movie.bean.PingLunCanShuBean;
import com.bw.movie.bean.MoviePingLunListBean;
import com.bw.movie.bean.ZhuiPingListBean;
import com.bw.movie.bean.ZhuiPingLunCanShuBean;
import com.bw.movie.mvp.contract.PingLun_Contract;
import com.bw.movie.utils.Api;
import com.bw.movie.utils.MyServer;
import com.bw.movie.utils.RetrofitUtils;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import retrofit2.adapter.rxjava.HttpException;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class PingLun_Model implements PingLun_Contract.Model {
    private final int ZHUIPINGLIST = 0;

    @Override
    public void setData(ZhuiPingLunCanShuBean canShuBean, final PingLun_Contract.GetDataState callBack) {
        RetrofitUtils.getInstance(canShuBean.getApi())
                .getRetrofit()
                .create(MyServer.class)
                .rx_pinglunList(canShuBean.getId(), canShuBean.getPage(), canShuBean.getCount())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MoviePingLunListBean>() {
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
                    public void onNext(MoviePingLunListBean pingLunListBean) {

                        Log.i("TAG", "onNext: " + pingLunListBean.getResult().size());
                        callBack.onSuccessfully(pingLunListBean);
                    }
                });
    }

    @Override
    public void setDataZhui(ZhuiPingLunCanShuBean canShuBean, final PingLun_Contract.GetDataStateZhui callBack) {
        RetrofitUtils.getInstance(canShuBean.getApi())
                .getRetrofit()
                .create(MyServer.class)
                .rx_zhuipingList(canShuBean.getId(),canShuBean.getPage(),canShuBean.getCount())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ZhuiPingListBean>() {
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
                    public void onNext(ZhuiPingListBean zhuiPingListBean) {
                        Log.i("TAG", "onNext: " + zhuiPingListBean.getMessage());
                        callBack.onSuccessfully_zhui(zhuiPingListBean);
                    }
                });
    }

}

package com.bw.movie.mvp.model;

import com.bw.movie.bean.ResultBean_Can;
import com.bw.movie.bean.Result_pinglunBean;
import com.bw.movie.mvp.contract.SendMoviePingLun_Contract;
import com.bw.movie.utils.Api;
import com.bw.movie.utils.MyServer;
import com.bw.movie.utils.RetrofitUtils;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import retrofit2.adapter.rxjava.HttpException;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class SendMoviePingLun_Model implements SendMoviePingLun_Contract.Model {
    @Override
    public void setData_mping(ResultBean_Can can, final SendMoviePingLun_Contract.GetPingLunState callBack) {
        RetrofitUtils.getInstance(Api.BASE_API)
                .getRetrofit()
                .create(MyServer.class)
                .sendMoviePing(can.getUserId(),can.getSessionId(),can.getMovieId(),can.getCommentContent())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Result_pinglunBean>() {
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
                                callBack.Error("服务器出错");
                            }
                        } else if (e instanceof ConnectException) {
                            callBack.Error("网络断开,请打开网络!");
                        } else if (e instanceof SocketTimeoutException) {
                            callBack.Error("网络连接超时!!");
                        } else {
                            callBack.Error("发生未知错误" + e.getMessage());
                        }
                    }

                    @Override
                    public void onNext(Result_pinglunBean result_pinglunBean) {
                        callBack.Success(result_pinglunBean);
                    }
                });
    }

    @Override
    public void setData_zping(ResultBean_Can can, final SendMoviePingLun_Contract.GetPingLunState callBack) {
        RetrofitUtils.getInstance(Api.BASE_API)
                .getRetrofit()
                .create(MyServer.class)
                .sendZhuiPing(can.getUserId(),can.getSessionId(),can.getCommentId(),can.getReplyContent())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Result_pinglunBean>() {
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
                                callBack.Error("服务器出错");
                            }
                        } else if (e instanceof ConnectException) {
                            callBack.Error("网络断开,请打开网络!");
                        } else if (e instanceof SocketTimeoutException) {
                            callBack.Error("网络连接超时!!");
                        } else {
                            callBack.Error("发生未知错误" + e.getMessage());
                        }
                    }

                    @Override
                    public void onNext(Result_pinglunBean result_pinglunBean) {
                        callBack.Success(result_pinglunBean);
                    }
                });
    }

    @Override
    public void setData_dzan(ResultBean_Can can, final SendMoviePingLun_Contract.GetPingLunState callBack) {
        RetrofitUtils.getInstance(Api.BASE_API)
                .getRetrofit()
                .create(MyServer.class)
                .sendZan(can.getUserId(),can.getSessionId(),can.getCommentId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Result_pinglunBean>() {
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
                                callBack.Error("服务器出错");
                            }
                        } else if (e instanceof ConnectException) {
                            callBack.Error("网络断开,请打开网络!");
                        } else if (e instanceof SocketTimeoutException) {
                            callBack.Error("网络连接超时!!");
                        } else {
                            callBack.Error("发生未知错误" + e.getMessage());
                        }
                    }

                    @Override
                    public void onNext(Result_pinglunBean result_pinglunBean) {
                        callBack.Success(result_pinglunBean);
                    }
                });
    }

    @Override
    public void setData_gzhu(ResultBean_Can can, final SendMoviePingLun_Contract.GetPingLunState callBack) {
        RetrofitUtils.getInstance(Api.BASE_API)
                .getRetrofit()
                .create(MyServer.class)
                .rx_guanzhu(can.getUserId(),can.getSessionId(),can.getMovieId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Result_pinglunBean>() {
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
                                callBack.Error("服务器出错");
                            }
                        } else if (e instanceof ConnectException) {
                            callBack.Error("网络断开,请打开网络!");
                        } else if (e instanceof SocketTimeoutException) {
                            callBack.Error("网络连接超时!!");
                        } else {
                            callBack.Error("发生未知错误" + e.getMessage());
                        }
                    }

                    @Override
                    public void onNext(Result_pinglunBean result_pinglunBean) {
                        callBack.Success(result_pinglunBean);
                    }
                });
    }
}

package com.bw.movie.mvp.model;

import android.util.Log;

import com.bw.movie.bean.TicketRecordBean;
import com.bw.movie.mvp.contract.TicketRecordContract;
import com.bw.movie.utils.Api;
import com.bw.movie.utils.MyServer;
import com.bw.movie.utils.RetrofitUtils;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class TicketRecordModel implements TicketRecordContract.Model {
    private static final String TAG = "TicketRecordModel";

    @Override
    public void requestTicketRecord(String page, String count, Integer userId, String sessionId, final GetRecordCallback callback) {

        MyServer myServer = RetrofitUtils.getInstance(Api.BASE_API).getRetrofit().create(MyServer.class);

        final Observable<TicketRecordBean> ticketRecord = myServer.getTicketRecord(userId, sessionId, page, count);

        ticketRecord.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<TicketRecordBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("----------------", "onError: " + e);
                        callback.onError(e.getMessage());
                    }

                    @Override
                    public void onNext(TicketRecordBean ticketRecordBean) {
                        Log.i("----------------", "onNext: " + ticketRecordBean.getMessage());
                        callback.onSuccess(ticketRecordBean.getResult());
                    }
                });

    }
}

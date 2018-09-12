package com.bw.movie.mvp.presenter;

import android.os.Handler;

import com.bw.movie.bean.TicketRecordBean;
import com.bw.movie.mvp.contract.TicketRecordContract;
import com.bw.movie.mvp.model.TicketRecordModel;
import com.bw.movie.ui.fragment.memberfragment.PaidFragment;

import java.util.List;

public class TicketRecordPresenter implements TicketRecordContract.Presenter {


    private TicketRecordContract.View mView;
    private TicketRecordModel recordModel;
    private Handler mHandler = new Handler();
    public TicketRecordPresenter(TicketRecordContract.View fragment) {
        this.mView = fragment;
        this.recordModel = new TicketRecordModel();
    }

    @Override
    public void requestTicketRecord() {

         //请求用户购票相关记录
        recordModel.requestTicketRecord(mView.getPage(), mView.getCount(),mView.getUserId(),mView.getSessionId(), new TicketRecordContract.Model.GetRecordCallback() {
            @Override
            public void onSuccess(final List<TicketRecordBean.ResultBean> ticketRecordBean) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mView.showProgress();
                        mView.onSuccess(ticketRecordBean);
                    }
                });

            }

            @Override
            public void onError(final String s) {
                  mHandler.post(new Runnable() {
                      @Override
                      public void run() {
                          mView.onError(s);
                          mView.hideProgress();
                      }
                  });
            }
        });



    }
}

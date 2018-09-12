package com.bw.movie.mvp.contract;

import com.bw.movie.bean.TicketRecordBean;

import java.util.List;

public interface TicketRecordContract {
    interface Model {

        interface GetRecordCallback{
            void onSuccess(List<TicketRecordBean.ResultBean> ticketRecordBean);
            void onError(String s);
        }
        void requestTicketRecord(String page, String count, Integer userId, String sessionId, GetRecordCallback callback);

    }

    interface View {

        void onSuccess(List<TicketRecordBean.ResultBean> list);
        void onError(String s);
        Integer getUserId();
        String getSessionId();
        String getPage();
        String getCount();
        void showProgress();
        void hideProgress();

    }

    interface Presenter {
        void requestTicketRecord();

    }
}

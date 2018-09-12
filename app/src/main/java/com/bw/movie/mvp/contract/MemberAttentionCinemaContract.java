package com.bw.movie.mvp.contract;

import com.bw.movie.bean.MemberAttentionCinema;

import java.util.List;

public interface MemberAttentionCinemaContract {
    interface Model {


        interface GetAttentionCinemaCallBack{
            void onSuccess(List<MemberAttentionCinema.ResultBean> resultBeans);
            void onError(String s);
        }
        void requestAttentionCinema(String page,String count,GetAttentionCinemaCallBack callBack);

    }

    interface View {
        void onSuccess(List<MemberAttentionCinema.ResultBean>resultBeans);
        void onError(String s);
    }

    interface Presenter {

        void requestAttentionCinema(String page,String count);
    }
}

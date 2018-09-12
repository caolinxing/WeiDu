package com.bw.movie.mvp.presenter;

import com.bw.movie.bean.MemberAttentionCinema;
import com.bw.movie.mvp.contract.MemberAttentionCinemaContract;
import com.bw.movie.mvp.model.MemberAttentionCinemaModel;
import com.bw.movie.ui.activity.AttentionCinemaActivity;

import java.util.List;

public class MemberAttentionCinemaPresenter implements MemberAttentionCinemaContract.Presenter {


    private MemberAttentionCinemaContract.View mView;
    private MemberAttentionCinemaModel cinemaModel;

    public MemberAttentionCinemaPresenter(MemberAttentionCinemaContract.View activity) {
        this.mView = activity;
        this.cinemaModel = new MemberAttentionCinemaModel();
    }

    @Override
    public void requestAttentionCinema(String page, String count) {
            cinemaModel.requestAttentionCinema(page, count, new MemberAttentionCinemaContract.Model.GetAttentionCinemaCallBack() {
                @Override
                public void onSuccess(List<MemberAttentionCinema.ResultBean> resultBeans) {
                    mView.onSuccess(resultBeans);
                }

                @Override
                public void onError(String s) {
                    mView.onError(s);
                }
            });
    }
}

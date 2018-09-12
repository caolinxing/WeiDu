package com.bw.movie.mvp.presenter;

import com.bw.movie.bean.MemberAttentionMovies;
import com.bw.movie.mvp.contract.MemberAttentionMovieContract;
import com.bw.movie.mvp.model.MemberAttentionMovieModel;
import com.bw.movie.ui.activity.AttentionMovieActivity;

import java.util.List;

public class MemberAttentionMoviePresenter implements MemberAttentionMovieContract.Presenter {

    private MemberAttentionMovieContract.View mView;
    private MemberAttentionMovieModel movieModel;

    public MemberAttentionMoviePresenter(MemberAttentionMovieContract.View view) {

        this.movieModel = new MemberAttentionMovieModel();
        this.mView = view;
    }

    @Override
    public void requestAttentionMovies(int userId,String session,String page, String count) {

           movieModel.requestAttentionMovie(userId,session,page, count, new MemberAttentionMovieContract.Model.getAttentionMoviesCallBack() {
               @Override
               public void onSuccess(List<MemberAttentionMovies.ResultBean> resultBeans) {
                     mView.onSuccess(resultBeans);
               }

               @Override
               public void onError() {
                    mView.onError("数据请求失败");
               }
           });

    }
}

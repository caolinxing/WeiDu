package com.bw.movie.mvp.contract;

import com.bw.movie.bean.MemberAttentionMovies;

import java.util.List;

public interface MemberAttentionMovieContract {
    interface Model {

         interface getAttentionMoviesCallBack{
             void onSuccess(List<MemberAttentionMovies.ResultBean>resultBeans);
             void onError();
         }
         void requestAttentionMovie(int userId,String session,String page,String count,getAttentionMoviesCallBack callBack);
    }

    interface View {
           void onSuccess(List<MemberAttentionMovies.ResultBean>resultBeans);
           void onError(String s);
    }

    interface Presenter {

        void requestAttentionMovies(int userId,String session,String page,String count);
    }
}

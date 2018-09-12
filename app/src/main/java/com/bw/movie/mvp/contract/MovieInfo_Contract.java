package com.bw.movie.mvp.contract;

import com.bw.movie.bean.CanShuBean;
import com.bw.movie.bean.MovieInfoBean;
import com.bw.movie.utils.OnHttpCallBack;

public interface MovieInfo_Contract {
    interface Model {
        void setData(String id, OnHttpCallBack<MovieInfoBean> callBack);
        void setData1(String id, OnHttpCallBack<MovieInfoBean> callBack);
    }

    interface View extends OnHttpCallBack<MovieInfoBean> {
        @Override
        void onSuccessful(MovieInfoBean movieInfoBean);

        @Override
        void onFaild(String errorMsg);

        CanShuBean setCan();
    }

    interface Presenter {
        void setData();
        void setData1();
    }
}

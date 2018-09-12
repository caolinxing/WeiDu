package com.bw.movie.mvp.contract;

import android.graphics.Bitmap;

import com.bw.movie.bean.MovieBean;

import java.util.List;

public interface MovieContract {
    interface Model {
        void loadHotMovie(String page, String count, GetDataState getDataState);
        void loadNowMoview(String page, String count, GetDataState getDataState);
        void commingSoonMoview(String page, String count, GetDataState getDataState);
    }

    interface GetDataState {
        void onSuccess2HotMovie(List<MovieBean.ResultBean> list);
        void onFaild(String e);
    }


    interface View extends GetDataState {
        String getPage();
        String getCount();

        @Override
        void onSuccess2HotMovie(List<MovieBean.ResultBean> list);

        @Override
        void onFaild(String e);
    }

    interface Presenter {
        void loadHotMovie();
        void loadNowMoview();
        void commingSoonMoview();
    }

}

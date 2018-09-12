package com.bw.movie.mvp.contract;

import com.bw.movie.bean.AllCinemaBean;
import com.bw.movie.bean.NearCinemaBean;

import java.util.List;

public interface CinemaContract {
    interface Model {
        void loadFJCinema(String userId,String sessionId,String longitude,String latitude,String page,String count,Presenter presenter);
        void loadAllCinema(String userId,String sessionId,String page,String count,Presenter presenter);
    }

    interface View {
        String getLongitude();
        String getLatitude();
        String getPage();
        String getCount();
        String getUserId();
        String getSessionId();
        void setFJCinema(List<NearCinemaBean.ResultBean.NearbyCinemaListBean> listBeans);
        void setAllCinema(List<AllCinemaBean.ResultBean> listBeans);
    }

    interface Presenter {
        void loadFJCinema();
        void onSuccess2FjCinema(List<NearCinemaBean.ResultBean.NearbyCinemaListBean> list);
        void loadAllCinema();
        void onSuccess2AllCinema(List<AllCinemaBean.ResultBean> list);
    }
}

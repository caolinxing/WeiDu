package com.bw.movie.mvp.contract;

import com.bw.movie.bean.CanShuBean;
import com.bw.movie.bean.ChooseCinemaBean;

import java.util.List;

public interface ChooseCinema_Contract {
    interface Model {
        void loadData(String movieId, GetDataState getDataState);
    }

    interface GetDataState {
        void onSuccessfully(List<ChooseCinemaBean.ResultBean> list);
        void onFail(String e);
    }


    interface View extends GetDataState {
        CanShuBean setCan();
        @Override
        void onSuccessfully(List<ChooseCinemaBean.ResultBean> list);

        @Override
        void onFail(String e);
    }

    interface Presenter {
        void loadData();
    }
}

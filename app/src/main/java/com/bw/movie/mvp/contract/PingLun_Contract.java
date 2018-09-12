package com.bw.movie.mvp.contract;

import com.bw.movie.bean.PingLunCanShuBean;
import com.bw.movie.bean.MoviePingLunListBean;
import com.bw.movie.bean.ZhuiPingListBean;
import com.bw.movie.bean.ZhuiPingLunCanShuBean;

import java.util.List;

public interface PingLun_Contract {
    interface Model {
       void  setData(ZhuiPingLunCanShuBean canShuBean, GetDataState getDataState);
       void  setDataZhui(ZhuiPingLunCanShuBean canShuBean, GetDataStateZhui getDataStateZhui);
    }
    interface GetDataState {
        void onSuccessfully(MoviePingLunListBean pingLunList);
        void onFaild(String e);
    }
    interface GetDataStateZhui {
        void onSuccessfully_zhui(ZhuiPingListBean zhuiPingListBean);
        void onFaild(String e);
    }

    interface View extends GetDataState ,GetDataStateZhui{

        ZhuiPingLunCanShuBean setPingLunCan();

        @Override
        void onSuccessfully(MoviePingLunListBean pingLunList);

        @Override
        void onFaild(String e);

        @Override
        void onSuccessfully_zhui(ZhuiPingListBean zhuiPingListBean);
    }

    interface Presenter {

        void setData();

        void setDataZhui();

        void onDestory();
    }
}

package com.bw.movie.mvp.contract;

import com.bw.movie.bean.ResultBean_Can;
import com.bw.movie.bean.Result_pinglunBean;

public interface SendMoviePingLun_Contract {
    interface Model {
        void setData_mping(ResultBean_Can can, GetPingLunState getPingLunState);
        void setData_zping(ResultBean_Can can, GetPingLunState getPingLunState);
        void setData_dzan(ResultBean_Can can, GetPingLunState getPingLunState);
        void setData_gzhu(ResultBean_Can can, GetPingLunState getPingLunState);
    }

    interface GetPingLunState{
        void Success(Result_pinglunBean result_pinglunBean);
        void Error(String e);
    }
    interface View extends GetPingLunState{
        @Override
        void Success(Result_pinglunBean result_pinglunBean);

        @Override
        void Error(String e);

        ResultBean_Can setResultBeanCan();
    }

    interface Presenter {
        void setData_mping();
        void setData_zping();
        void setData_dzan();
        void setData_gzhu();
        void onDestory();
    }
}

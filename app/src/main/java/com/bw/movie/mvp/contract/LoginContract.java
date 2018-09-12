package com.bw.movie.mvp.contract;

import com.bw.movie.bean.LoginBean;
import com.bw.movie.bean.UserBean;

public interface LoginContract {
    interface Model {

        interface LoginCallBack {
            void onSuccess(LoginBean login);
            void onError(String s);
        }

        void login(UserBean user, LoginCallBack callBack);

    }

    interface View {

        void SignUpSuccess(LoginBean loginBean);

        void SignUpError(String s);

        void showProgress();

        void hidProgress();
    }

    interface Presenter {

        void RequestLogin(UserBean user);
    }
}

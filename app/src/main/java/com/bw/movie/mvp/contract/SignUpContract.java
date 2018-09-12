package com.bw.movie.mvp.contract;

import com.bw.movie.bean.SignUpBean;
import com.bw.movie.bean.UserBean;

public interface SignUpContract {
    interface Model {

        interface SignUpCallBack{
            void onSuccess(SignUpBean sing);
            void onError(SignUpBean s);
        }
        void signUp(UserBean user, SignUpCallBack callBack);

    }

    interface View {

        void SignUpSuccess();
        void SignUpError(String s);
        void showProgress();
        void hidProgress();
    }

    interface Presenter {

        void RequestSignUp(UserBean user);
    }
}

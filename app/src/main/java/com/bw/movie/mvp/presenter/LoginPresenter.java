package com.bw.movie.mvp.presenter;

import android.os.Handler;
import android.util.Log;

import com.bw.movie.bean.LoginBean;
import com.bw.movie.bean.UserBean;
import com.bw.movie.mvp.contract.LoginContract;
import com.bw.movie.mvp.model.LoginModel;



public class LoginPresenter implements LoginContract.Presenter {
    private static final String TAG = LoginPresenter.class.getSimpleName();
    private Handler mHandler = new Handler();

    private LoginContract.View mView;
    private LoginModel loginModel;

    public LoginPresenter(LoginContract.View activity) {
        this.mView = activity;
        this.loginModel = new LoginModel();
    }

    @Override
    public void RequestLogin(UserBean user) {
        //请求并回调
        loginModel.login(user, new LoginContract.Model.LoginCallBack() {
            @Override
            public void onSuccess(final LoginBean login) {
                 mHandler.post(new Runnable() {
                     @Override
                     public void run() {
                         Log.i(TAG, "run: 获取传来的值"+login.getStatus());
                         if(login.getStatus().contains("0000")){
                             mView.SignUpSuccess(login);
                             mView.hidProgress();
                         }else {
                             Log.i(TAG, "run: 调用了错误方法");
                             mView.SignUpError(login.getMessage());
                             mView.hidProgress();
                         }
                     }
                 });


            }

            @Override
            public void onError(String s) {
                Log.i(TAG, "onError: "+s);
                mView.SignUpError(s);
                mView.hidProgress();
            }
        });

    }
}

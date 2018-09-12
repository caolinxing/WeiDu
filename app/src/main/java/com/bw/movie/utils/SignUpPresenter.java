package com.bw.movie.utils;

import android.os.Handler;

import com.bw.movie.bean.SignUpBean;
import com.bw.movie.bean.UserBean;
import com.bw.movie.mvp.contract.SignUpContract;
import com.bw.movie.mvp.model.SignUpModel;

public class SignUpPresenter implements SignUpContract.Presenter {


    private SignUpContract.View mView;
    private SignUpModel signUpModel;
    private Handler mHandler = new Handler();
    public SignUpPresenter(SignUpContract.View activity) {

        this.mView = activity;
        this.signUpModel = new SignUpModel();
    }

    @Override
    public void RequestSignUp(UserBean user) {
            signUpModel.signUp(user, new SignUpContract.Model.SignUpCallBack() {
                @Override
                public void onSuccess(final SignUpBean sing) {

                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            if(sing.getMessage().contains("注册成功")){
                                mView.SignUpSuccess();
                                mView.hidProgress();
                            }else {
                                mView.SignUpError(sing.getMessage());
                                mView.hidProgress();
                            }


                        }
                    });



                }

                @Override
                public void onError(final SignUpBean s) {

                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            mView.SignUpError(s.getMessage());
                            mView.hidProgress();

                        }
                    });
                }
            });
    }
}

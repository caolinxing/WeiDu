package com.bw.movie.mvp.model;

import com.bw.movie.bean.SignUpBean;
import com.bw.movie.bean.UserBean;
import com.bw.movie.mvp.contract.SignUpContract;
import com.bw.movie.utils.Api;
import com.bw.movie.utils.MyServer;
import com.bw.movie.utils.RetrofitUtils;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class SignUpModel implements SignUpContract.Model {
    @Override
    public void signUp(UserBean user, final SignUpCallBack callBack) {
        Observable<SignUpBean> userBeanObservable = RetrofitUtils.getInstance(Api.BASE_API).getRetrofit().create(MyServer.class)
                .signUp(user.getNickName(), user.getPhone(), user.getPwd(),
                        user.getPwd2(), user.getSex(), user.getBirthday(),
                        user.getImei(), user.getUa(), user.getScreenSize(),
                        user.getOs(), user.getEmail());
        userBeanObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<SignUpBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(SignUpBean signupBean) {
                       callBack.onSuccess(signupBean);
                    }
                });

    }
}

package com.bw.movie.mvp.model;

import com.bw.movie.bean.LoginBean;
import com.bw.movie.bean.UserBean;
import com.bw.movie.mvp.contract.LoginContract;
import com.bw.movie.utils.Api;
import com.bw.movie.utils.MyServer;
import com.bw.movie.utils.RetrofitUtils;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class LoginModel implements LoginContract.Model {


    @Override
    public void login(UserBean user, final LoginCallBack callBack) {
        MyServer myServer = RetrofitUtils.getInstance(Api.BASE_API).getRetrofit().create(MyServer.class);

        Observable<LoginBean> login = myServer.login(user.getPhone(), user.getPwd());
        login.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<LoginBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                         callBack.onError(e.getMessage());
                    }

                    @Override
                    public void onNext(LoginBean loginBean) {
                         callBack.onSuccess(loginBean);
                    }
                });

    }
}

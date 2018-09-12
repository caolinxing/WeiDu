package com.bw.movie.mvp.presenter;

import com.bw.movie.base.BaseModel;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.bean.Result_pinglunBean;
import com.bw.movie.mvp.contract.SendMoviePingLun_Contract;
import com.bw.movie.mvp.model.SendMoviePingLun_Model;

import java.lang.ref.WeakReference;

public class SendMoviePingLun_Presenter extends BasePresenter implements SendMoviePingLun_Contract.Presenter, SendMoviePingLun_Contract.GetPingLunState {
    SendMoviePingLun_Contract.View view;
    SendMoviePingLun_Contract.Model model;

    public SendMoviePingLun_Presenter(Object view) {
        super(view);
        this.view = (SendMoviePingLun_Contract.View) view;
        model = new SendMoviePingLun_Model();
    }

    @Override
    public void setData_mping() {
        model.setData_mping(view.setResultBeanCan(),this);
    }

    @Override
    public void setData_zping() {
        model.setData_zping(view.setResultBeanCan(),this);
    }

    @Override
    public void setData_dzan() {
        model.setData_dzan(view.setResultBeanCan(),this);
    }

    @Override
    public void setData_gzhu() {
        model.setData_gzhu(view.setResultBeanCan(),this);
    }

    @Override
    public void onDestory() {
        if (view!=null){
            view=null;
        }
    }

    @Override
    public void Success(Result_pinglunBean result_pinglunBean) {
        view.Success(result_pinglunBean);
    }

    @Override
    public void Error(String e) {
        view.Error(e);
    }

    @Override
    protected WeakReference getWeak() {
        return null;
    }

    @Override
    protected BaseModel initModel() {
        return null;
    }
}

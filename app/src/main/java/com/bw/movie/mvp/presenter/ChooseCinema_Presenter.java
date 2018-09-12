package com.bw.movie.mvp.presenter;

import com.bw.movie.bean.ChooseCinemaBean;
import com.bw.movie.mvp.contract.ChooseCinema_Contract;
import com.bw.movie.mvp.model.ChooseCinema_Model;

import java.util.List;

public class ChooseCinema_Presenter implements ChooseCinema_Contract.Presenter, ChooseCinema_Contract.GetDataState {
    ChooseCinema_Contract.View view;
    ChooseCinema_Contract.Model model;

    public ChooseCinema_Presenter(ChooseCinema_Contract.View view) {
        this.view = view;
        this.model = new ChooseCinema_Model();
    }

    @Override
    public void loadData() {
        model.loadData(view.setCan().getId(),this);
    }

    @Override
    public void onSuccessfully(List<ChooseCinemaBean.ResultBean> list) {
        view.onSuccessfully(list);
    }

    @Override
    public void onFail(String e) {
        view.onFail(e);
    }
}

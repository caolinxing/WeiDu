package com.bw.movie.ui.fragment.memberfragment;


import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bw.movie.R;
import com.bw.movie.adapter.PaidAdapter;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.bean.TicketRecordBean;
import com.bw.movie.mvp.contract.TicketRecordContract;
import com.bw.movie.mvp.presenter.TicketRecordPresenter;
import com.bw.movie.utils.SharedPreferenceUtil;
import com.bw.movie.utils.SpaceItemDecoration;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class PaidFragment extends BaseFragment implements TicketRecordContract.View {


    @BindView(R.id.paid_rv)
    RecyclerView paidRv;
    @BindView(R.id.paid_refresh_layout)
    SmartRefreshLayout paidRefreshLayout;
    @BindView(R.id.paid_avl)
    AVLoadingIndicatorView paidAvl;
    Unbinder unbinder;
    private String page = "1";
    private String count= "10";
    List<TicketRecordBean.ResultBean> myList = new ArrayList<>();
    private TicketRecordPresenter ticketRecordPresenter;

    public PaidFragment() {
        // Required empty public constructor
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_paid;
    }

    @Override
    public Unbinder unbinder() {
        return unbinder;
    }

    @Override
    protected void initData() {

        //获取引用
        ticketRecordPresenter = new TicketRecordPresenter(this);
        //发送请求
        ticketRecordPresenter.requestTicketRecord();

        //请求完之后进行RecyclerView的初始化操作
        initRecyclerView();

        initRefresh();

    }

    private void initRefresh() {


        paidRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page = "1";
                ticketRecordPresenter.requestTicketRecord();
            }
        });
        paidRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = "1";
                ticketRecordPresenter.requestTicketRecord();
            }
        });



    }

    private void initRecyclerView() {

        paidRv.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        int spacingInPixels = 14;
        paidRv.addItemDecoration(new SpaceItemDecoration(spacingInPixels));

    }

    @Override
    protected void initView(View view) {
        unbinder = ButterKnife.bind(this, view);
    }

    @Override
    public void onSuccess(List<TicketRecordBean.ResultBean> list) {

               if(list!=null){
                   myList.clear();
               }
               myList.addAll(list);
        PaidAdapter paidAdapter = new PaidAdapter(getActivity(),myList);
        paidRv.setAdapter(paidAdapter);
        paidAvl.hide();
        paidRefreshLayout.finishLoadMore();
        paidRefreshLayout.finishRefresh();

    }

    @Override
    public void onError(String s) {

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public Integer getUserId() {
        return (Integer) SharedPreferenceUtil.get(Objects.requireNonNull(getActivity()),"userId",0);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public String getSessionId() {
        return (String) SharedPreferenceUtil.get(Objects.requireNonNull(getActivity()),"sessionId","0");
    }

    @Override
    public String getPage() {
        return page;
    }

    @Override
    public String getCount() {
        return count;
    }

    @Override
    public void showProgress() {
        paidAvl.show();
    }

    @Override
    public void hideProgress() {
         paidAvl.hide();
    }


}

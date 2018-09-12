package com.bw.movie.ui.fragment.memberfragment;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bw.movie.R;
import com.bw.movie.adapter.ObligationAdapter;
import com.bw.movie.adapter.PaidAdapter;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.bean.TicketRecordBean;
import com.bw.movie.mvp.contract.TicketRecordContract;
import com.bw.movie.mvp.presenter.TicketRecordPresenter;
import com.bw.movie.ui.activity.TicketDetailsActivity;
import com.bw.movie.ui.activity.TicketRecordActivity;
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
public class ObligationFragment extends BaseFragment implements TicketRecordContract.View {


    private static final String TAG = ObligationFragment.class.getSimpleName();
    @BindView(R.id.obligation_rv)
    RecyclerView obligationRv;
    @BindView(R.id.obligation_refresh_layout)
    SmartRefreshLayout obligationRefreshLayout;
    @BindView(R.id.obligation_avl)
    AVLoadingIndicatorView obligationAvl;
    Unbinder unbinder;
    List<TicketRecordBean.ResultBean> myList = new ArrayList<>();

    private String page = "1";
    private TicketRecordPresenter ticketRecordPresenter;
    private ObligationAdapter obligationAdapter;

    public ObligationFragment() {
        // Required empty public constructor
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_obligation;
    }


    @Override
    public Unbinder unbinder() {
        return unbinder;
    }

    @Override
    protected void initData() {

        //获取引用
        ticketRecordPresenter = new TicketRecordPresenter(this);
        //进行RecyclerView的初始化操作
        initRecyclerView();
        //发送请求
        ticketRecordPresenter.requestTicketRecord();
        //初始化刷新事件
        initRefresh();


    }

    private void initClick() {

        obligationAdapter.setClickListener(new ObligationAdapter.MyItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getActivity(), TicketDetailsActivity.class);
                intent.putExtra("position",position);
                startActivity(intent);
            }
        });
    }

    private void initRefresh() {


        obligationRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                 page = "1";
                 ticketRecordPresenter.requestTicketRecord();
            }
        });
        obligationRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                  page = "1";
                  ticketRecordPresenter.requestTicketRecord();
            }
        });

    }

    private void initRecyclerView() {
        obligationRv.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        int spacingInPixels = 14;
        obligationRv.addItemDecoration(new SpaceItemDecoration(spacingInPixels));

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

        obligationAdapter = new ObligationAdapter(getActivity(),myList);
        Log.i(TAG, "initRecyclerView: 此时数据的长度"+myList.size());
        Log.i(TAG, "initRecyclerView: "+"设置适配器");
        obligationRv.setAdapter(obligationAdapter);
        obligationAvl.hide();
        obligationRefreshLayout.finishLoadMore();
        obligationRefreshLayout.finishRefresh();
        //可以处理条目点击事件
        initClick();
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
        String count = "10";
        return count;
    }

    @Override
    public void showProgress() {
        obligationAvl.show();
    }

    @Override
    public void hideProgress() {
        obligationAvl.hide();
    }
}

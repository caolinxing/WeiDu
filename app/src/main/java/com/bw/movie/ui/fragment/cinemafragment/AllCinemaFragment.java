package com.bw.movie.ui.fragment.cinemafragment;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.bw.movie.R;
import com.bw.movie.adapter.AllCinemaListAdapter;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.bean.AllCinemaBean;
import com.bw.movie.bean.EventBean;
import com.bw.movie.bean.NearCinemaBean;
import com.bw.movie.mvp.contract.CinemaContract;
import com.bw.movie.mvp.presenter.CinemaPresenter;
import com.bw.movie.ui.activity.CinemaAndMovieActivity;
import com.bw.movie.utils.SpaceItemDecoration;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.wang.avi.AVLoadingIndicatorView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class AllCinemaFragment extends BaseFragment implements CinemaContract.View{


    @BindView(R.id.rv_all_cinema)
    RecyclerView rvAllCinema;
    @BindView(R.id.mh_all_cinema)
    SmartRefreshLayout mhAllCinema;
    @BindView(R.id.avl_all_cinema)
    AVLoadingIndicatorView avl_all_cinema;
    Unbinder unbinder;
    private int page = 1;
    @SuppressWarnings("FieldCanBeLocal")
    private int count = 10;
    private CinemaContract.Presenter presenter;
    private List<AllCinemaBean.ResultBean> listBeans;
    private AllCinemaListAdapter adapter;
    private int userId;
    private String sessionId;

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_all_cinema;
    }

    @Override
    public Unbinder unbinder() {
        return unbinder;
    }

    @Override
    protected void initData() {

        SharedPreferences userinfo = getActivity().getSharedPreferences("userinfo", Context.MODE_PRIVATE);
        userId = userinfo.getInt("userId",0);
        sessionId = userinfo.getString("sessionId","");

        avl_all_cinema.show();
        listBeans = new ArrayList<>();
        presenter = new CinemaPresenter(this);
        rvAllCinema.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new AllCinemaListAdapter(getContext(), listBeans);
        rvAllCinema.setAdapter(adapter);
        presenter.loadAllCinema();
        int spacingInPixels = 14;
        rvAllCinema.addItemDecoration(new SpaceItemDecoration(spacingInPixels));
        mhAllCinema.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                listBeans.clear();
                page = 1;
                presenter.loadAllCinema();

            }
        });
        mhAllCinema.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                presenter.loadAllCinema();

            }
        });
        adapter.setCallback(new AllCinemaListAdapter.Callback() {
            @Override
            public void onItemClick(int postion) {
                EventBus.getDefault().postSticky(new EventBean(0,listBeans.get(postion).getId(),0,0));
                getActivity().startActivity(new Intent(getActivity(), CinemaAndMovieActivity.class));
            }
        });
    }

    @Override
    protected void initView(View view) {
        unbinder = ButterKnife.bind(this, view);
    }

    @Override
    public String getLongitude() {
        return null;
    }

    @Override
    public String getLatitude() {
        return null;
    }

    @Override
    public String getPage() {
        return String.valueOf(page);
    }

    @Override
    public String getCount() {
        return String.valueOf(count);
    }

    @Override
    public String getUserId() {
        return String.valueOf(userId);
    }

    @Override
    public String getSessionId() {
        return sessionId;
    }

    @Override
    public void setFJCinema(List<NearCinemaBean.ResultBean.NearbyCinemaListBean> listBeans) {

    }

    @Override
    public void setAllCinema(List<AllCinemaBean.ResultBean> listBeans) {
        if (listBeans.size() < page) {
            mhAllCinema.finishLoadMoreWithNoMoreData();
        }
        this.listBeans.addAll(listBeans);
        adapter.notifyDataSetChanged();
        avl_all_cinema.hide();
        mhAllCinema.finishLoadMore();
        mhAllCinema.finishRefresh();
    }
}

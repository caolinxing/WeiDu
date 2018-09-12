package com.bw.movie.ui.fragment.cinemafragment;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bw.movie.R;
import com.bw.movie.adapter.NearCinemaListAdapter;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.bean.AllCinemaBean;
import com.bw.movie.bean.EventBean;
import com.bw.movie.bean.NearCinemaBean;
import com.bw.movie.mvp.contract.CinemaContract;
import com.bw.movie.mvp.presenter.CinemaPresenter;
import com.bw.movie.ui.activity.CinemaAndMovieActivity;
import com.bw.movie.ui.activity.LoginActivity;
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
public class NearCinemaFragment extends BaseFragment implements CinemaContract.View {

    Unbinder unbinder;
    @BindView(R.id.rv_fj_cinema)
    RecyclerView recy_fj_cinema;
    @BindView(R.id.mh_fj_cinema)
    SmartRefreshLayout material_fj_cinema;
    @BindView(R.id.avl_near_cinema)
    AVLoadingIndicatorView avl_near_cinema;
    private CinemaContract.Presenter presenter;
    private List<NearCinemaBean.ResultBean.NearbyCinemaListBean> listBeans;
    private NearCinemaListAdapter adapter;
    @SuppressWarnings("FieldCanBeLocal")
    private String longitude = "116.30551391385724";
    @SuppressWarnings("FieldCanBeLocal")
    private String latitude = "40.04571807462411";
    private int page = 1;
    @SuppressWarnings("FieldCanBeLocal")
    private int count = 10;
    private int userId;
    private String sessionId;

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_near_cinema;
    }

    @Override
    public Unbinder unbinder() {
        return unbinder;
    }

    @Override
    protected void initData() {
        SharedPreferences userinfo = getActivity().getSharedPreferences("userinfo", Context.MODE_PRIVATE);
        userId = userinfo.getInt("userId", 0);
        sessionId = userinfo.getString("sessionId", "");

        //显示进度条
        avl_near_cinema.show();

        //初始化
        listBeans = new ArrayList<>();
        presenter = new CinemaPresenter(this);

        //设置适配器
        recy_fj_cinema.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new NearCinemaListAdapter(getContext(), listBeans);
        recy_fj_cinema.setAdapter(adapter);

        //加载附近影院
        presenter.loadFJCinema();

        //加间距
        int spacingInPixels = 10;
        recy_fj_cinema.addItemDecoration(new SpaceItemDecoration(spacingInPixels));

        //刷新
        material_fj_cinema.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                listBeans.clear();
                page = 1;
                presenter.loadFJCinema();

            }
        });

        //加载更多
        material_fj_cinema.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                presenter.loadFJCinema();

            }
        });

        adapter.setCallback(new NearCinemaListAdapter.Callback() {
            @Override
            public void onItemClick(int postion) {
                if (userId == 0 || sessionId.equals("")) {
                    getActivity().startActivity(new Intent(getActivity(), LoginActivity.class));
                } else {
                    EventBus.getDefault().postSticky(new EventBean(0, listBeans.get(postion).getId(), 0, 0));
                    getActivity().startActivity(new Intent(getActivity(), CinemaAndMovieActivity.class));
                }
            }
        });
    }

    @Override
    protected void initView(View view) {
        unbinder = ButterKnife.bind(this, view);
    }

    @Override
    public String getLongitude() {
        return longitude;
    }

    @Override
    public String getLatitude() {
        return latitude;
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
        return null;
    }

    @Override
    public String getSessionId() {
        return null;
    }

    @Override
    public void setFJCinema(List<NearCinemaBean.ResultBean.NearbyCinemaListBean> listBeans) {
        if (listBeans.size() < count) {
            material_fj_cinema.finishLoadMoreWithNoMoreData();
        }
        this.listBeans.addAll(listBeans);
        adapter.notifyDataSetChanged();
        avl_near_cinema.hide();
        material_fj_cinema.finishRefresh();
        material_fj_cinema.finishLoadMore();
    }

    @Override
    public void setAllCinema(List<AllCinemaBean.ResultBean> listBeans) {

    }
}

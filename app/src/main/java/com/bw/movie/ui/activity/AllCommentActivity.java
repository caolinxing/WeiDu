package com.bw.movie.ui.activity;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.bw.movie.R;
import com.bw.movie.adapter.CinemaCommentAdapter;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.bean.CinemaCommentBean;
import com.bw.movie.bean.EventBean;
import com.bw.movie.mvp.contract.AllCinemaCommentContract;
import com.bw.movie.mvp.presenter.AllCinemaCommentPresenter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.wang.avi.AVLoadingIndicatorView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class AllCommentActivity extends BaseActivity implements AllCinemaCommentContract.View{


    @BindView(R.id.rv_all_comment)
    RecyclerView rvAllComment;
    @BindView(R.id.srl_all_comment)
    SmartRefreshLayout srlAllComment;
    @BindView(R.id.avl_all_comment)
    AVLoadingIndicatorView avlAllComment;
    private int cinemaId;
    private int page = 1;
    private int count = 15;
    private AllCinemaCommentContract.Presenter presenter;
    private List<CinemaCommentBean.ResultBean> cinemaCommentBean;
    private CinemaCommentAdapter adapter;
    private boolean f = true;

    @Override
    public int bindLayout() {
        return R.layout.activity_all_comment;
    }

    @Override
    public void initData() {
        avlAllComment.show();
        cinemaCommentBean = new ArrayList<>();
        presenter = new AllCinemaCommentPresenter(this);
        EventBus.getDefault().register(this);
        rvAllComment.setLayoutManager(new LinearLayoutManager(this));
        presenter.loadAllCinemaComment();
        srlAllComment.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 1;
                count = 15;
                cinemaCommentBean.clear();
                presenter.loadAllCinemaComment();
            }
        });
        srlAllComment.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                count = 5;
                presenter.loadAllCinemaComment();
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void getEvent(EventBean cinemaId){
        Log.e("---------------", "getEvent: "+cinemaId.getCinemaId() );
        this.cinemaId = cinemaId.getCinemaId();
    }

    @Override
    public BasePresenter providePresenter() {
        return null;
    }

    @Override
    public void setAllCinemaComment(CinemaCommentBean cinemaComment) {


        cinemaCommentBean.addAll(cinemaComment.getResult());
        if(f){
            adapter = new CinemaCommentAdapter(this, cinemaCommentBean);
            rvAllComment.setAdapter(adapter);
            f = false;
        }
        adapter.notifyDataSetChanged();
        avlAllComment.hide();
        srlAllComment.finishLoadMore();
        srlAllComment.finishRefresh();
        if ((cinemaComment.getResult().size())<5){
            srlAllComment.finishLoadMoreWithNoMoreData();
        }
    }

    @Override
    public String getCinemaId() {
        return String.valueOf(cinemaId);
    }

    @Override
    public String getPage() {
        return String.valueOf(page);
    }

    @Override
    public String getCount() {
        return String.valueOf(count);
    }
}

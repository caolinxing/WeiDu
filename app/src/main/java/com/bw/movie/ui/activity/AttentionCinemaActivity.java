package com.bw.movie.ui.activity;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import com.bw.movie.R;
import com.bw.movie.adapter.MemberAttentionCinemaAdapter;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.bean.MemberAttentionCinema;
import com.bw.movie.mvp.contract.MemberAttentionCinemaContract;
import com.bw.movie.mvp.presenter.MemberAttentionCinemaPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class AttentionCinemaActivity extends BaseActivity<BasePresenter> implements MemberAttentionCinemaContract.View {


    @BindView(R.id.member_attention_cinema_back)
    ImageView memberAttentionCinemaBack;
    @BindView(R.id.remeber_attentioncinema_rv)
    RecyclerView remeberAttentioncinemaRv;
    private MemberAttentionCinemaPresenter memberAttentionCinemaPresenter;
    private String page = "1";
    private String count = "10";
    List<MemberAttentionCinema.ResultBean> myResultBeans = new ArrayList<>();
    private MemberAttentionCinemaAdapter memberAttentionCinemaAdapter;

    @Override
    public int bindLayout() {
        return R.layout.activity_attention_cinema;
    }

    @Override
    public void initData() {
        //获取引用
         memberAttentionCinemaPresenter  = new MemberAttentionCinemaPresenter(AttentionCinemaActivity.this);
         //请求数据
         memberAttentionCinemaPresenter.requestAttentionCinema(page,count);

    }




    @Override
    public BasePresenter providePresenter() {
        return null;
    }


    @OnClick(R.id.member_attention_cinema_back)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void onSuccess(List<MemberAttentionCinema.ResultBean> resultBeans) {
              if(resultBeans!=null){
                    myResultBeans.clear();
              }
              myResultBeans.addAll(resultBeans);
              //进行Recycler的操作
              initRecyclerView();

    }

    private void initRecyclerView() {
        //设置RecyclerView的属性
        remeberAttentioncinemaRv.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        //创建适配
        memberAttentionCinemaAdapter = new MemberAttentionCinemaAdapter(this,myResultBeans);
        remeberAttentioncinemaRv.setAdapter(memberAttentionCinemaAdapter);


    }

    @Override
    public void onError(String s) {

    }
}

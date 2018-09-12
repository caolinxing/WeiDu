package com.bw.movie.ui.activity;
import android.content.SharedPreferences;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.Toast;

import android.graphics.drawable.Drawable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bw.movie.R;
import com.bw.movie.adapter.MemberAttentionMovieAdapter;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.base.BasePresenter;
import com.squareup.picasso.Picasso;
import com.bw.movie.bean.MemberAttentionMovies;
import com.bw.movie.mvp.contract.MemberAttentionMovieContract;
import com.bw.movie.mvp.presenter.MemberAttentionMoviePresenter;
import com.bw.movie.view.RecycleViewDivider;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class AttentionMovieActivity extends BaseActivity<BasePresenter> implements MemberAttentionMovieContract.View {

    @BindView(R.id.vip_attention_movie_back)
    ImageView vipAttentionMovieBack;
    @BindView(R.id.remeber_attentionmovie_rv)
    RecyclerView remeberAttentionmovieRv;
    private MemberAttentionMoviePresenter mPresenter;
    private String page = "1";
    private String count = "10";

    List<MemberAttentionMovies.ResultBean> myResultBeans = new ArrayList<>();
    private MemberAttentionMovieAdapter memberAttentionMovieAdapter;

    @Override
    public int bindLayout() {
        return R.layout.activity_attention_movie;
    }

    @Override
    public void initData() {
        /**
         * 获取sp
         */
        SharedPreferences sp = getSharedPreferences("user_info", MODE_PRIVATE);
        int userId = sp.getInt("userId", 0);
        String sessionId = sp.getString("sessionId", "");
        //获取引用
        mPresenter = new MemberAttentionMoviePresenter(AttentionMovieActivity.this);
        //进行请求
        mPresenter.requestAttentionMovies(userId,sessionId,page, count);

    }

    @Override
    public BasePresenter providePresenter() {
        return null;
    }

    @Override
    public void onSuccess(List<MemberAttentionMovies.ResultBean> resultBeans) {
        if (resultBeans.size() > 0) {
            myResultBeans.clear();
        }
        myResultBeans.addAll(resultBeans);
        //初始化RecyclerView的操作
        initRecyclerView();

    }

    private void initRecyclerView() {

        remeberAttentionmovieRv.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        //添加分割线
        remeberAttentionmovieRv.addItemDecoration(new RecycleViewDivider(this, LinearLayoutManager.VERTICAL,15,getResources().getColor(R.color.colorGray)));
        //设置适配器
        memberAttentionMovieAdapter = new MemberAttentionMovieAdapter(this, myResultBeans);
        remeberAttentionmovieRv.setAdapter(memberAttentionMovieAdapter);
    }

    @Override
    public void onError(String s) {
        Toast.makeText(this,s,Toast.LENGTH_SHORT).show();
    }


    @OnClick(R.id.vip_attention_movie_back)
    public void onViewClicked() {
        finish();
    }
}

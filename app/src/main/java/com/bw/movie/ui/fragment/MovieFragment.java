package com.bw.movie.ui.fragment;


import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.bw.movie.R;
import com.bw.movie.adapter.MovieAdapter;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.bean.EventBusBean_1;
import com.bw.movie.ui.fragment.moviefragment.ComingMovieFragment;
import com.bw.movie.ui.fragment.moviefragment.HotMovieFragment;
import com.bw.movie.ui.fragment.moviefragment.NowMovieFragment;
import com.flyco.tablayout.SlidingTabLayout;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFragment extends BaseFragment {


    @BindView(R.id.stl_home)
    SlidingTabLayout stlHome;
    @BindView(R.id.vp_home)
    ViewPager vpHome;
    Unbinder unbinder;
    @BindView(R.id.iv_qiehuan)
    ImageView ivQiehuan;
    Unbinder unbinder1;
    @SuppressWarnings("FieldCanBeLocal")
    private List<Fragment> fragments;
    @SuppressWarnings("FieldCanBeLocal")
    private HotMovieFragment e;
    @SuppressWarnings("FieldCanBeLocal")
    private NowMovieFragment e1;
    @SuppressWarnings("FieldCanBeLocal")
    private ComingMovieFragment e2;
    private boolean flag = true;

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_movie;
    }

    @Override
    public Unbinder unbinder() {
        return unbinder;
    }

    @Override
    protected void initData() {
        fragments = new ArrayList<>();
        e = new HotMovieFragment();
        fragments.add(e);
        e1 = new NowMovieFragment();
        fragments.add(e1);
        e2 = new ComingMovieFragment();
        fragments.add(e2);
        vpHome.setAdapter(new MovieAdapter(getFragmentManager(), fragments));
        vpHome.setOffscreenPageLimit(2);
        stlHome.setViewPager(vpHome);
    }

    @Override
    protected void initView(View view) {
        unbinder = ButterKnife.bind(this, view);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder1.unbind();
    }

    @OnClick(R.id.iv_qiehuan)
    public void onViewClicked() {
        if (flag){
            EventBus.getDefault().postSticky(new EventBusBean_1(flag));
            flag = false;
        }else {
            EventBus.getDefault().postSticky(new EventBusBean_1(flag));
            flag = true;
        }
    }
}

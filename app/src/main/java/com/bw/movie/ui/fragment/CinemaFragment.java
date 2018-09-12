package com.bw.movie.ui.fragment;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bw.movie.R;
import com.bw.movie.adapter.CinemaAdapter;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.ui.activity.LocationActivity;
import com.bw.movie.ui.fragment.cinemafragment.NearCinemaFragment;
import com.bw.movie.ui.fragment.cinemafragment.AllCinemaFragment;
import com.flyco.tablayout.SlidingTabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class CinemaFragment extends BaseFragment {

    @BindView(R.id.address_movie)
    LinearLayout addressMovie;
    @BindView(R.id.stl_movie)
    SlidingTabLayout stlMovie;
    @BindView(R.id.vp_movie)
    ViewPager vpMovie;
    @BindView(R.id.search_movie)
    ImageView searchMovie;
    private List<Fragment> fragments;
    private Unbinder unbinder;

    public CinemaFragment() {
        // Required empty public constructor
    }


    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_cinema;

    }

    @Override
    public Unbinder unbinder() {
        return unbinder;
    }

    @Override
    protected void initData() {
        fragments = new ArrayList<>();
        Fragment reYingFragment = new NearCinemaFragment();
        Fragment shangYingFragment = new AllCinemaFragment();
        fragments.add(reYingFragment);
        fragments.add(shangYingFragment);
        vpMovie.setAdapter(new CinemaAdapter(getFragmentManager(), fragments));
        stlMovie.setViewPager(vpMovie);
        addressMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), LocationActivity.class));
            }
        });
    }

    @Override
    protected void initView(View view) {
        unbinder = ButterKnife.bind(this, view);
    }

}

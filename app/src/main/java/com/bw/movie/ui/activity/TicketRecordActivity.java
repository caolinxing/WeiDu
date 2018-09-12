package com.bw.movie.ui.activity;


import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;

import com.bw.movie.R;
import com.bw.movie.adapter.TicketRecordAdapter;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.ui.fragment.memberfragment.ObligationFragment;
import com.bw.movie.ui.fragment.memberfragment.PaidFragment;
import com.flyco.tablayout.SlidingTabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TicketRecordActivity extends BaseActivity {


    @BindView(R.id.ticket_record_back)
    ImageView ticketRecordBack;
    @BindView(R.id.record_tab)
    SlidingTabLayout recordTab;
    @BindView(R.id.record_vp)
    ViewPager recordVp;
    private List<Fragment> fragmentList;
    private TicketRecordAdapter ticketRecordAdapter;

    @Override
    public int bindLayout() {
        return R.layout.activity_ticket_record;
    }

    @Override
    public void initData() {

         //新建Fragment集合
         fragmentList = new ArrayList<>();
         //将fragment放入
        fragmentList.add(new ObligationFragment());
        fragmentList.add(new PaidFragment());

        //创建fragment容器 viewPager的适配器
        ticketRecordAdapter = new TicketRecordAdapter(getSupportFragmentManager(),fragmentList);
        //设置适配器
        //缓存
        recordVp.setOffscreenPageLimit(2);
        recordVp.setAdapter(ticketRecordAdapter);

        //Tab 与 ViewPager 进行绑定  ViewPager 需要先设置适配器
        recordTab.setViewPager(recordVp);




    }

    @Override
    public BasePresenter providePresenter() {
        return null;
    }

    //返回按钮
    @OnClick(R.id.ticket_record_back)
    public void onViewClicked() {
        finish();
    }
}

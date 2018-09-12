package com.bw.movie.ui.fragment;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.bean.MemberAttentionCinema;
import com.bw.movie.bean.MessageEvent;
import com.bw.movie.ui.activity.AttentionCinemaActivity;
import com.bw.movie.ui.activity.AttentionMovieActivity;
import com.bw.movie.ui.activity.MainActivity;
import com.bw.movie.ui.activity.TicketRecordActivity;
import com.bw.movie.view.MyScrollView;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class MemberFragment extends BaseFragment implements MyScrollView.TranslucentListener{

    Unbinder unbinder;
    @BindView(R.id.scrollView)
    MyScrollView scrollView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    Unbinder unbinder1;
    @BindView(R.id.h_back)
    ImageView hBack;
    @BindView(R.id.h_head)
    ImageView hHead;
    @BindView(R.id.vip_username)
    TextView vipUsername;
    @BindView(R.id.vip_ticket_record)
    TextView vipTicketRecord;
    @BindView(R.id.vip_attention_movie)
    TextView vipAttentionMovie;
    @BindView(R.id.vip_attention_cinema)
    TextView vipAttentionCinema;
    @BindView(R.id.vip_recommend)
    TextView vipRecommend;

    public MemberFragment() {
        // Required empty public constructor
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_member;
    }

    @Override
    public Unbinder unbinder() {
        return unbinder;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View view) {
        unbinder = ButterKnife.bind(this, view);
        setHasOptionsMenu(true);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        scrollView.setTranslucentListener(this);
    }

    @Override
    public void onTranlucent(float alpha) {
        toolbar.setAlpha(alpha);
    }
    @OnClick({R.id.h_head, R.id.vip_ticket_record, R.id.vip_attention_movie, R.id.vip_attention_cinema, R.id.vip_recommend})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.h_head:
                break;
            case R.id.vip_ticket_record:
                SharedPreferences user_info = getActivity().getSharedPreferences("user_info", Context.MODE_PRIVATE);
                if (!user_info.getAll().isEmpty())
                startActivity(new Intent(getActivity(),TicketRecordActivity.class));
                break;
            case R.id.vip_attention_movie:
               startActivity(new Intent(getActivity(),AttentionMovieActivity.class));
                break;
            case R.id.vip_attention_cinema:
                startActivity(new Intent(getActivity(),AttentionCinemaActivity.class));
                break;
            case R.id.vip_recommend:
                EventBus.getDefault().postSticky(new MessageEvent(0));
                break;
        }
    }
}

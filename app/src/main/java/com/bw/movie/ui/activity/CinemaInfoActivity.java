package com.bw.movie.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.adapter.CinemaCommentAdapter;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.bean.CinemaCommentBean;
import com.bw.movie.bean.EventBean;
import com.bw.movie.bean.MyCinemaInfo;
import com.bw.movie.mvp.contract.CinemaInfoContract;
import com.bw.movie.mvp.presenter.CinemaInfoPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CinemaInfoActivity extends BaseActivity implements CinemaInfoContract.View {
    @SuppressWarnings("all")
    @BindView(R.id.iv_back_cinema_info)
    ImageView iv_back_cinema_info;
    @BindView(R.id.tv_name_cinema_info)
    TextView tv_name_cinema_info;
    @BindView(R.id.iv_cinema_info)
    ImageView ivCinemaInfo;
    @BindView(R.id.tv_address_cinema_info)
    TextView tvAddressCinemaInfo;
    @BindView(R.id.tv_time_cinema_info)
    TextView tvTimeCinemaInfo;
    @BindView(R.id.tv_phone_cinema_info)
    TextView tvPhoneCinemaInfo;
    @BindView(R.id.tv_type_cinema_info)
    TextView tvTypeCinemaInfo;
    @BindView(R.id.rv_comment_cinema_info)
    RecyclerView rvCommentCinemaInfo;
    @BindView(R.id.tv_more_comment)
    TextView tv_more_comment;
    @BindView(R.id.et_content_cinema)
    EditText etContentCinema;
    @BindView(R.id.tv_send_cinema)
    TextView tvSendCinema;
    @SuppressWarnings("FieldCanBeLocal")
    private CinemaInfoContract.Presenter presenter;
    private List<CinemaCommentBean.ResultBean> list;
    private int cinemaId = 1;
    @SuppressWarnings("FieldCanBeLocal")
    private int page = 1;
    @SuppressWarnings("FieldCanBeLocal")
    private int count = 5;
    private int userId;
    private String sessionId;
    private String content;

    @Override
    public int bindLayout() {
        return R.layout.activity_cinema_info;
    }

    @Override
    public void initData() {
        SharedPreferences userinfo = getSharedPreferences("userinfo", MODE_PRIVATE);
        userId = userinfo.getInt("userId", 306);
        sessionId = userinfo.getString("sessionId","1536490602029306");
        list = new ArrayList<>();
        presenter = new CinemaInfoPresenter(this);
        EventBus.getDefault().register(this);
        rvCommentCinemaInfo.setLayoutManager(new LinearLayoutManager(this));
        presenter.loadComment();
        iv_back_cinema_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tv_more_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().postSticky(new EventBean(0, cinemaId, 0, 0));
                startActivity(new Intent(CinemaInfoActivity.this, AllCommentActivity.class));
            }
        });
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void getEvent(MyCinemaInfo myCinemaInfo) {
        Glide.with(this).load(myCinemaInfo.getLogo()).into(ivCinemaInfo);
        cinemaId = myCinemaInfo.getId();
        tvAddressCinemaInfo.setText(myCinemaInfo.getAddress());
        tvPhoneCinemaInfo.setText(myCinemaInfo.getPhone());
        tvTimeCinemaInfo.setText(myCinemaInfo.getBusinessHoursContent());
        tvTypeCinemaInfo.setText(myCinemaInfo.getVehicleRoute());
        tv_name_cinema_info.setText(myCinemaInfo.getName());
    }

    @Override
    public BasePresenter providePresenter() {
        return null;
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

    @Override
    public String getUserId() {
        return String.valueOf(userId);
    }

    @Override
    public String getSessionId() {
        return sessionId;
    }

    @Override
    public String getContent() {
        return content;
    }

    @Override
    public void setComment(CinemaCommentBean cinemaCommentBean) {
        this.list.clear();
        this.list.addAll(cinemaCommentBean.getResult());
        rvCommentCinemaInfo.setAdapter(new CinemaCommentAdapter(this, this.list));
        rvCommentCinemaInfo.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }

    @Override
    public void setPingLun(String content) {
        Toast.makeText(this,content,Toast.LENGTH_SHORT).show();
        presenter.loadComment();

    }


    @OnClick(R.id.tv_send_cinema)
    public void onViewClicked() {
        if(userId == 0 || sessionId.equals("")){
            Toast.makeText(this,"请先登录",Toast.LENGTH_SHORT).show();
        }else {
            content = etContentCinema.getText().toString();
            etContentCinema.setText("");
            presenter.pingLun();
        }
    }
}

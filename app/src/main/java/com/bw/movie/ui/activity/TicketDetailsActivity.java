package com.bw.movie.ui.activity;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.bean.TicketRecordBean;
import com.bw.movie.mvp.contract.TicketRecordContract;
import com.bw.movie.mvp.presenter.TicketRecordPresenter;
import com.bw.movie.utils.DateFormatUtil;
import com.bw.movie.utils.SharedPreferenceUtil;

import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TicketDetailsActivity extends BaseActivity implements TicketRecordContract.View {


    @BindView(R.id.ticket_details_back)
    ImageView ticketDetailsBack;
    @BindView(R.id.ticket_details_movieName)
    TextView ticketDetailsMovieName;
    @BindView(R.id.ticket_details_cinemaName)
    TextView ticketDetailsCinemaName;
    @BindView(R.id.beginTimeAndEndTime)
    TextView beginTimeAndEndTime;
    @BindView(R.id.ticket_details_continuetoPay)
    TextView ticketDetailsContinuetoPay;
    @BindView(R.id.ticket_details_movie_down_name)
    TextView ticketDetailsMovieDownName;
    @BindView(R.id.ticket_details_cinema_down_name)
    TextView ticketDetailsCinemaDownName;
    @BindView(R.id.ticket_details_amount)
    TextView ticketDetailsAmount;
    @BindView(R.id.ticket_details_price)
    TextView ticketDetailsPrice;
    @BindView(R.id.ticket_details_creatTime)
    TextView ticketDetailsCreatTime;
    @BindView(R.id.ticket_details_orderId)
    TextView ticketDetailsOrderId;
    private int listPosition;

    private String page = "1";
    private TicketRecordPresenter ticketRecordPresenter;
    private TicketRecordBean.ResultBean resultBean;

    @Override
    public int bindLayout() {
        return R.layout.activity_ticket_details;
    }

    @Override
    public void initData() {

        //获取传来的集合的下标
        Intent intent = getIntent();
        listPosition = intent.getIntExtra("position", 0);
        //获取presenter引用
        ticketRecordPresenter = new TicketRecordPresenter(TicketDetailsActivity.this);
        //请求数据
        ticketRecordPresenter.requestTicketRecord();


    }

    @Override
    public BasePresenter providePresenter() {
        return null;
    }

    @Override
    public void onSuccess(List<TicketRecordBean.ResultBean> list) {
        if (list != null) {
            resultBean = list.get(listPosition);
        }
        //将得到的值渲染到UI上
        runOnUiThread(new Runnable() {
            @SuppressLint("SetTextI18n")
            @Override
            public void run() {

                ticketDetailsMovieName.setText(resultBean.getMovieName());
                ticketDetailsCinemaName.setText(resultBean.getCinemaName());
                String beginTime = resultBean.getBeginTime();
                String endTime = resultBean.getEndTime();
                beginTimeAndEndTime.setText(beginTime + "--" + endTime);

                ticketDetailsMovieDownName.setText(resultBean.getMovieName());
                ticketDetailsCinemaDownName.setText(resultBean.getCinemaName());
                ticketDetailsAmount.setText(resultBean.getAmount() + "");
                ticketDetailsPrice.setText(resultBean.getPrice() + "");
                long createTime = resultBean.getCreateTime();
                String s = DateFormatUtil.longToString(createTime, "yyyy-MM-dd HH-ss");
                ticketDetailsCreatTime.setText(s);
                ticketDetailsOrderId.setText(resultBean.getOrderId());

            }
        });


    }

    @Override
    public void onError(String s) {
        Toast.makeText(this, "未获取到数据", Toast.LENGTH_SHORT).show();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public Integer getUserId() {
        return (Integer) SharedPreferenceUtil.get(Objects.requireNonNull(TicketDetailsActivity.this), "userId", 0);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public String getSessionId() {
        return (String) SharedPreferenceUtil.get(Objects.requireNonNull(TicketDetailsActivity.this), "sessionId", "0");
    }

    @Override
    public String getPage() {
        return page;
    }

    @Override
    public String getCount() {
        String count = "10";
        return count;
    }

    @Override
    public void showProgress() {
    }

    @Override
    public void hideProgress() {
    }


    @OnClick({R.id.ticket_details_back, R.id.ticket_details_continuetoPay})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ticket_details_back:
                finish();
                break;
            case R.id.ticket_details_continuetoPay:
                startActivity(new Intent(this,CheckoutCounterActivity.class));
                break;
        }
    }
}

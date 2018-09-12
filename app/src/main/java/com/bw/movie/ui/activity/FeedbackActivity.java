package com.bw.movie.ui.activity;

import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bw.movie.R;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.base.BasePresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
//意见反馈
public class FeedbackActivity extends BaseActivity {
    private static final String TAG = "Act_options_recordFeedB";
    @BindView(R.id.fh)
    ImageView fh;
    @BindView(R.id.my_toolbar)
    Toolbar myToolbar;
    @BindView(R.id.edyj)
    EditText edyj;
    @BindView(R.id.yjbtn)
    Button yjbtn;

    @Override
    public int bindLayout() {
        return R.layout.activity_feedback;
    }

    @Override
    public void initData() {

    }

    @Override
    public BasePresenter providePresenter() {
        return null;
    }

    @OnClick(R.id.fh)
    public void onViewClicked() {
        finish();
    }
}

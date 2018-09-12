package com.bw.movie.ui.activity;

import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.base.BasePresenter;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.OnClick;
//个人信息
public class InformationActivity extends BaseActivity {

    @BindView(R.id.fh)
    ImageView fh;
    @BindView(R.id.my_toolbar)
    Toolbar myToolbar;
    @BindView(R.id.head_portrait_simple)
    SimpleDraweeView headPortraitSimple;
    @BindView(R.id.nickname_text)
    TextView nicknameText;
    @BindView(R.id.nickname_edit)
    EditText nicknameEdit;
    @BindView(R.id.region_text)
    TextView regionText;
    @BindView(R.id.region_edit)
    EditText regionEdit;
    @BindView(R.id.email_edit)
    EditText emailEdit;
    @BindView(R.id.email_text)
    TextView emailText;
    @BindView(R.id.gender_text)
    TextView genderText;
    @BindView(R.id.gender_edit)
    EditText genderEdit;
    @BindView(R.id.Last_login_time_text)
    TextView LastLoginTimeText;
    @BindView(R.id.person_message)
    RelativeLayout personMessage;

    @OnClick(R.id.fh)
    public void onViewClicked() {
        finish();
    }


    @Override
    public int bindLayout() {
        return R.layout.activity_information;
    }

    @Override
    public void initData() {

    }

    @Override
    public BasePresenter providePresenter() {
        return null;
    }
}

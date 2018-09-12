package com.bw.movie.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;

import com.bw.movie.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

//变更密码
public class ChangeActivity extends AppCompatActivity {

    @BindView(R.id.fh)
    ImageView fh;
    @BindView(R.id.my_changetoolbar)
    Toolbar myToolbar;
    @BindView(R.id.original_pwd)
    EditText originalPwd;
    @BindView(R.id.change_pwd)
    EditText changePwd;
    @BindView(R.id.repetition_pwd)
    EditText repetitionPwd;
    @BindView(R.id.settings_synch_delet)
    CheckBox settingsSynchDelet;
    @BindView(R.id.update_password_submit)
    Button updatePasswordSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.fh)
    public void onViewClicked() {
        finish();
    }
}

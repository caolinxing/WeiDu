package com.bw.movie.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;

import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.bean.LoginBean;
import com.bw.movie.bean.UserBean;
import com.bw.movie.mvp.contract.LoginContract;
import com.bw.movie.mvp.presenter.LoginPresenter;
import com.bw.movie.utils.DateFormatUtil;
import com.bw.movie.utils.EncryptUtil;
import com.bw.movie.utils.SharedPreferenceUtil;
import com.bw.movie.utils.Validator;
import com.wang.avi.AVLoadingIndicatorView;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends BaseActivity implements LoginContract.View {

    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;
    @BindView(R.id.avlview_login)
    AVLoadingIndicatorView avlviewLogin;
    @BindView(R.id.login_et_phone)
    EditText loginEtPhone;
    @BindView(R.id.login_et_password)
    EditText loginEtPassword;
    @BindView(R.id.btn_login)
    AppCompatButton btnLogin;
    @BindView(R.id.link_signup)
    TextView linkSignup;


    @Override
    public int bindLayout() {
        return R.layout.activity_login;
    }

    @Override
    public void initData() {
        //暂时将progress隐藏
        avlviewLogin.hide();
        avlviewLogin.bringToFront();
        //登录的监听
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
        linkSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the Signup activity
                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                startActivityForResult(intent, REQUEST_SIGNUP);
            }
        });

    }

    private void login() {
        //验证是否合格
        if (!validate()) {
            //有一项不合格就会返回false
            onLOginFailed();
        }
        //合格操作
        //显示加载
        avlviewLogin.show();
        //获取输入框内容
        String phone = loginEtPhone.getText().toString();
        String password = loginEtPassword.getText().toString();
        //加密密码
        String pwd = EncryptUtil.encrypt(password);
        //获取引用
        LoginPresenter loginPresenter = new LoginPresenter(LoginActivity.this);
        //请求
        UserBean userBean = new UserBean();
        userBean.setPhone(phone);
        userBean.setPwd(pwd);
        loginPresenter.RequestLogin(userBean);

        //当按钮为灰色时,按钮变灰不可按压,EditText同样如此
        //btnLogin.setEnabled(false);

    }

    //表单验证失败
    private void onLOginFailed() {
     //   btnLogin.setEnabled(true);
        Toast.makeText(getBaseContext(), "Login Failed", Toast.LENGTH_SHORT).show();

    }

    //表单验证
    private boolean validate() {
        //先设置为真,一项满足条件就不合法
        boolean valid = true;
        //获取输入框内容
        String phone = loginEtPhone.getText().toString();
        String pwd = loginEtPassword.getText().toString();

        //手机号的验证
        if (phone.isEmpty() || !Validator.isMobile(phone)) {
            loginEtPhone.setError("Please enter a valid phone number");
            valid = false;
        } else {
            loginEtPhone.setError(null);
        }
        //密码的验证
        if (pwd.isEmpty() || !Validator.isPassword(pwd)) {
            loginEtPassword.setError("Please enter legal characters");
            valid = false;
        } else {
            loginEtPassword.setError(null);
        }

        return valid;
    }


    //请求成功的回调
    @Override
    public void SignUpSuccess(LoginBean loginBean) {
        //将数据储存在ShaedPreference中
        LoginBean.ResultBean result = loginBean.getResult();

        String sessionId = result.getSessionId();
        int userId = result.getUserId();
        LoginBean.ResultBean.UserInfoBean userInfo = result.getUserInfo();

        long longBirthday = userInfo.getBirthday();
        Date date = DateFormatUtil.longToDate(longBirthday);
        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-DD");
        String birthday = simpleDateFormat.format(date);

        String headPic = userInfo.getHeadPic();
        int id = userInfo.getId();

        //转换最后登录时间
        long longlastLoginTime = userInfo.getLastLoginTime();
        Date date1 = DateFormatUtil.longToDate(longlastLoginTime);
        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String lastLoginTime = simpleDateFormat1.format(date1);

        //昵称
        String nickName = userInfo.getNickName();
        //手机号
        String phone = userInfo.getPhone();
        //性别
        int sex = userInfo.getSex();

        //开始存放
        SharedPreferenceUtil.put(this,"sessionId",sessionId);
        SharedPreferenceUtil.put(this,"userId",userId);
        SharedPreferenceUtil.put(this,"birthday",birthday);
        SharedPreferenceUtil.put(this,"headPic",headPic);
        SharedPreferenceUtil.put(this,"id",id);
        SharedPreferenceUtil.put(this,"lastLoginTime",lastLoginTime);
        SharedPreferenceUtil.put(this,"nickName",nickName);
        SharedPreferenceUtil.put(this,"phone",phone);
        SharedPreferenceUtil.put(this,"sex",sex);
        startActivity(new Intent(LoginActivity.this, MainActivity.class));

    }

    @Override
    public void SignUpError(String s) {
        Toast.makeText(LoginActivity.this, s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgress() {
        avlviewLogin.show();
    }

    @Override
    public void hidProgress() {
        avlviewLogin.hide();
    }

    @Override
    public BasePresenter providePresenter() {
        return null;
    }

}

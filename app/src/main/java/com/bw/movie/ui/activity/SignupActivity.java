package com.bw.movie.ui.activity;

import android.app.ProgressDialog;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.bw.movie.R;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.base.BasePresenter;
import butterknife.BindView;

public class SignupActivity extends BaseActivity {

    private static final String TAG = "SignupActivity";
    @BindView(R.id.input_name)
    EditText inputName;
    @BindView(R.id.input_password)
    EditText inputPassword;
    @BindView(R.id.btn_signup)
    AppCompatButton btnSignup;
    @BindView(R.id.link_login)
    TextView linkLogin;
    @BindView(R.id.input_phone)
    EditText inputPhone;
    @BindView(R.id.input_twopassword)
    EditText inputTwopassword;
    @BindView(R.id.input_email)
    EditText inputEmail;

    @Override
    public int bindLayout() {
        return R.layout.activity_signup;
    }

    @Override
    public void initData() {

        //进行登录按钮的监听
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                singup();
            }
        });
        //跳转登录链接的监听
        linkLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void singup() {
        Log.d(TAG, "singup");
        if (!validate()) {
            onSignupFailed();
            return;
        }
        String name = inputName.getText().toString();
        String password = inputPassword.getText().toString();
        String email = inputEmail.getText().toString();
        String twicePassword = inputTwopassword.getText().toString();
        String phone = inputPhone.getText().toString();



        btnSignup.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(SignupActivity.this,
                R.style.AppTheme);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Creating Account...");
        progressDialog.show();

        // TODO: Implement your own signup logic here.

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onSignupSuccess or onSignupFailed
                        // depending on success
                        onSignupSuccess();
                        // onSignupFailed();
                        progressDialog.dismiss();
                    }
                }, 3000);
    }

    public void onSignupSuccess() {
        btnSignup.setEnabled(true);

        setResult(RESULT_OK, null);
        finish();
    }
    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();
        btnSignup.setEnabled(true);
    }
    public boolean validate() {
        boolean valid = true;
        String name = inputName.getText().toString();
        String password = inputPassword.getText().toString();
        String email = inputEmail.getText().toString();
        String twicePassword = inputTwopassword.getText().toString();
        String phone = inputPhone.getText().toString();
        //昵称
        if (name.isEmpty() || name.length() < 5) {
            inputName.setError("at least 5 numbers");
            valid = false;
        } else {
            inputName.setError(null);
        }
        //手机号
        if(phone.isEmpty() || phone.length() != 11){
            inputPhone.setError("The telephone calls for eleven");
            valid = false;
        }else {
            inputPhone.setError(null);
        }
        //Email
        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            inputEmail.setError("enter a valid email address");
            valid = false;
        } else {
            inputEmail.setError(null);
        }
        //密码
        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            inputPassword.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            inputPassword.setError(null);
        }
        //二次密码
        if(!twicePassword.equals(password)){
            inputTwopassword.setError("Passwords need to be equal");
            valid = false;
        }else {
            inputEmail.setError(null);
        }

        return valid;
    }

    @Override
    public BasePresenter providePresenter() {
        return null;
    }
}

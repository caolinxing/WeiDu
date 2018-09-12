package com.bw.movie.base;

import android.Manifest;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.bw.movie.utils.NavigationBarUtil;
import com.bw.movie.utils.StatusBarUtil;
import com.tandong.swichlayout.SwitchLayout;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity {

    protected P presenter;
    private Unbinder bind;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{Manifest.permission.CALL_PHONE},200);
        }
        //沉浸式
        StatusBarUtil.setImage(this);
        NavigationBarUtil.assistActivity(findViewById(android.R.id.content));
        setContentView(bindLayout());

        bind = ButterKnife.bind(this);
        presenter = providePresenter();
        // 设置进入Activity的Activity特效动画，同理可拓展为布局动画
        SwitchLayout.getFadingIn(this);
        initData();

    }
    //绑定视图
    public abstract int bindLayout();
    //初始化数据
    public abstract void initData();
    //实例化presenter
    public abstract P providePresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(presenter != null){
            presenter.onDestroy();
        }

        bind.unbind();
    }
}

package com.bw.movie.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bw.movie.utils.NavigationBarUtil;
import com.bw.movie.utils.StatusBarUtil;

import butterknife.Unbinder;

/**
 * author:Created by YangYong on 2018/8/31 0031.
 */
public abstract class BaseFragment<P extends BasePresenter> extends Fragment {
    protected P presenter;
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(getLayout(), container, false);
        presenter = getPresenter();
        StatusBarUtil.setImage(getActivity());
        NavigationBarUtil.assistActivity(getActivity().findViewById(android.R.id.content));
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView(view);
        initData();
    }

    protected abstract P getPresenter();

    protected abstract int getLayout();

    public abstract Unbinder unbinder();

    protected abstract void initData();

    protected abstract void initView(View view);

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //presenter.onDestroy();
        presenter = null;
        unbinder().unbind();
    }
}

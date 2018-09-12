package com.bw.movie.ui.fragment;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.bw.movie.R;
import com.bw.movie.adapter.SettingAdapter;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.ui.activity.ChangeActivity;
import com.bw.movie.ui.activity.FeedbackActivity;
import com.bw.movie.ui.activity.InformationActivity;
import com.bw.movie.ui.activity.WeiduActivity;
import com.bw.movie.utils.DataCleanManager;

import java.util.ArrayList;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingFragment extends BaseFragment {


    @BindView(R.id.my_toolbar)
    Toolbar myToolbar;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.btn)
    Button btn;
    Unbinder unbinder;
    Unbinder unbinder1;
    private ArrayList<String> titles = new ArrayList<>();

    public SettingFragment() {
        // Required empty public constructor
    }



    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_setting;
    }

    @Override
    public Unbinder unbinder() {
        return unbinder;
    }

    @Override
    protected void initData() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        titles.add("个人信息");
        titles.add("变更密码");
        titles.add("清空缓存");
        titles.add("软件升级");
        titles.add("意见反馈");
        titles.add("关于维度");
        titles.add("客服电话:10086");
       // System.out.println(titles);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(getActivity())
                        .setTitle("退出登录")
                        .setMessage("是否确认退出")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                DataCleanManager.clearAllCache(getActivity());
                                // settingNumber.setText("0M");
                                Toast.makeText(getActivity(), "退出成功", Toast.LENGTH_LONG).show();
                            }
                        })
                        .setNegativeButton("取消", null)
                        .show();
            }
        });
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        SettingAdapter adapter = new SettingAdapter(getActivity(), titles);
        recyclerView.setAdapter(adapter);

        adapter.setmListener(new SettingAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int postion) {
                switch (postion) {
                    case 0:
                        if (true) {
                            //                查询个人信息
                            startActivity(new Intent(getContext(), InformationActivity.class));
                        } else {
                         //   startActivity(UserLoginActivity.class, null);
                        }
                        break;
                    case 1:
                        if (true)
                            startActivity(new Intent(getContext(), ChangeActivity.class));
//                        else
                          //  startActivity(UserLoginActivity.class, null);
                        break;
                    case 2:
                        String totalCacheSize = null;
                        try {
                            totalCacheSize = DataCleanManager.getTotalCacheSize(getActivity());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        new AlertDialog.Builder(getActivity())
                                .setTitle("清除缓存")
                                .setMessage("是否确认清除缓存")
                                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        DataCleanManager.clearAllCache(getActivity());
                                        // settingNumber.setText("0M");
                                        Toast.makeText(getActivity(), "清除成功", Toast.LENGTH_LONG).show();
                                    }
                                })
                                .setNegativeButton("取消", null)
                                .show();
                        break;
                    case 3:
                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                        builder.setTitle("下载新版本")
                                .setMessage("检查到有更新")
                                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Uri uri = Uri.parse("http://172.17.8.100/media/movie.apk");
                                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                                        startActivity(intent);
                                    }
                                })
                                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        Toast.makeText(getActivity(), "取消下载", Toast.LENGTH_SHORT).show();
                                    }
                                }).show();

                        break;
                    case 4:
                        if (true) {
                            startActivity(new Intent(getContext(), FeedbackActivity.class));
                        } else {
                            Toast.makeText(getActivity(), "请先登录", Toast.LENGTH_LONG).show();
                        }
                        break;
                    case 5:
                        Intent intent3 = new Intent(getActivity(), WeiduActivity.class);
                        startActivity(intent3);
                        break;
                    case 6:
                        startActivity(new Intent(Intent.ACTION_CALL).setData(Uri.parse("tel:" + "10086")));
                        break;


                }
            }
        });
    }



    @Override
    protected void initView(View view) {
        unbinder = ButterKnife.bind(this, view);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder1 = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder1.unbind();
    }



}

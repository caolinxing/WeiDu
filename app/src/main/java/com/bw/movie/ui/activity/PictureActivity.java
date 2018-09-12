package com.bw.movie.ui.activity;

import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.bean.PicMessageBean;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;
import cn.bingoogolapple.bgabanner.BGABanner;

public class PictureActivity extends BaseActivity {


    @BindView(R.id.banner_guide_content)
    BGABanner mContentBanner;
    private PicMessageBean list;


    @Override
    public int bindLayout() {
        return R.layout.activity_picture;
    }

    @Override
    public void initData() {
        EventBus.getDefault().register(this);
        Log.i("TAG", "initData: " + list.toString());
        mContentBanner.setAdapter(new BGABanner.Adapter<ImageView, String>() {
            @Override
            public void fillBannerItem(BGABanner banner, ImageView itemView, String model, int position) {
                Glide.with(PictureActivity.this)
                        .load(model)
                        .into(itemView);
            }
        });
        ArrayList<String> strings = new ArrayList<>();
        for (int i = 0; i < list.getList().size(); i++) {
            i++;
            strings.add("剧照："+i);
        }
        mContentBanner.setData(list.getList(), strings);
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void getMessage(PicMessageBean messageBean) {
        list = messageBean;
    }

    @Override
    public BasePresenter providePresenter() {
        return null;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

}

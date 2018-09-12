package com.bw.movie.adapter;

import android.content.Intent;
import android.graphics.Picture;
import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.bw.movie.R;
import com.bw.movie.bean.MessageBean;
import com.bw.movie.bean.PicMessageBean;
import com.bw.movie.ui.activity.PictureActivity;
import com.bw.movie.ui.activity.VideoActivity;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.fresco.helper.Phoenix;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

class MovieInfoBillAdapter extends BaseQuickAdapter<String,BaseViewHolder>{
    private final List<String> list;

    public MovieInfoBillAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
        list = data;
    }


    @Override
    protected void convert(BaseViewHolder helper, String item) {
        SimpleDraweeView sdv = helper.getView(R.id.bill_sdv);
        Phoenix.with(sdv).load(item);
        helper.addOnClickListener(R.id.bill_sdv);
        final int position = helper.getPosition();
        sdv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().postSticky(new PicMessageBean(list,position));
                mContext.startActivity(new Intent(mContext,PictureActivity.class));
            }
        });
    }
}

package com.bw.movie.adapter;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.bw.movie.R;
import com.bw.movie.bean.MessageBean;
import com.bw.movie.bean.MovieInfoBean;
import com.bw.movie.ui.activity.VideoActivity;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.fresco.helper.Phoenix;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

class MovieInfoTrailerdapter extends BaseQuickAdapter<MovieInfoBean.ResultBean.ShortFilmListBean,BaseViewHolder>{
    public MovieInfoTrailerdapter(int layoutResId, @Nullable List<MovieInfoBean.ResultBean.ShortFilmListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final MovieInfoBean.ResultBean.ShortFilmListBean item) {
        SimpleDraweeView sdv = helper.getView(R.id.trailer_sdv);
        Phoenix.with(sdv).load(item.getImageUrl());
        helper.addOnClickListener(R.id.trailer_sdv);
        sdv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().postSticky(new MessageBean(0,0,item.getImageUrl(),item.getVideoUrl(),null,null,null,null,null));
                mContext.startActivity(new Intent(mContext,VideoActivity.class));
            }
        });
    }
}

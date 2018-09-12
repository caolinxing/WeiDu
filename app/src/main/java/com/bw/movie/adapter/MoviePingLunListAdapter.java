package com.bw.movie.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.blankj.utilcode.util.TimeUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.bw.movie.R;
import com.bw.movie.bean.MoviePingLunListBean;
import com.bw.movie.utils.ToastUtils;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.fresco.helper.Phoenix;

import java.util.List;

import me.zhanghai.android.materialratingbar.MaterialRatingBar;

public class MoviePingLunListAdapter extends BaseQuickAdapter<MoviePingLunListBean.ResultBean,BaseViewHolder> implements View.OnClickListener {

    private int commentId;

    public MoviePingLunListAdapter(int layoutResId, @Nullable List<MoviePingLunListBean.ResultBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MoviePingLunListBean.ResultBean item) {
        SimpleDraweeView sdv2 = helper.getView(R.id.usercomment_icon_r);
        commentId = item.getCommentId();
        String string = TimeUtils.millis2String(item.getCommentTime());
        MaterialRatingBar materialRatingBar = helper.getView(R.id.usercomment_ratingbar_r);
        materialRatingBar.setProgress(item.getGreatNum());
        Phoenix.with(sdv2).load(item.getCommentHeadPic());
        helper.setText(R.id.usercomment_tv_date_r, string)
                .setText(R.id.userconmment_uname_r,item.getCommentUserName())
                .setText(R.id.usercomment_tv_pinglun_r,item.getCommentContent())
                .setText(R.id.usercomment_tv_pingnum_r,item.getReplyNum()+"")
                .setText(R.id.usercomment_tv_zan_r,item.getGreatNum()+"")
                .addOnClickListener(R.id.usrecomment_dianzan_r);
        LinearLayout zhuiPing = helper.getView(R.id.usrecomment_zhuiping_r);
        zhuiPing.setOnClickListener(this);
        RelativeLayout itemview = helper.getView(R.id.item_view_r);
        itemview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showToast(mContext,"稍后再做+");
            }
        });
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.usrecomment_zhuiping_r:
                ToastUtils.showToast(mContext,"追评");
                break;
        }
    }
}

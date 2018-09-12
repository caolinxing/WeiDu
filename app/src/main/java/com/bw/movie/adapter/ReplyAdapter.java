package com.bw.movie.adapter;

import android.support.annotation.Nullable;
import android.widget.LinearLayout;

import com.blankj.utilcode.util.TimeUtils;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.bw.movie.R;
import com.bw.movie.bean.MutilplePingLunBean;
import com.bw.movie.bean.ZhuiPingListBean;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.fresco.helper.Phoenix;

import java.util.List;

import me.zhanghai.android.materialratingbar.MaterialRatingBar;

public class ReplyAdapter extends BaseQuickAdapter<ZhuiPingListBean.ResultBean,BaseViewHolder> {

    public ReplyAdapter(int layoutResId, @Nullable List<ZhuiPingListBean.ResultBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ZhuiPingListBean.ResultBean item) {
        SimpleDraweeView sdv1 = helper.getView(R.id.usercomment_icon_r);
        String string1 = TimeUtils.millis2String(item.getReplyTime());
        MaterialRatingBar materialRatingBar1 = helper.getView(R.id.usercomment_ratingbar_r);
        materialRatingBar1.setProgress(5);
        Phoenix.with(sdv1).load(item.getReplyHeadPic());
        helper.setText(R.id.usercomment_tv_date_r, string1)
                .setText(R.id.userconmment_uname_r,item.getReplyUserName())
                .setText(R.id.usercomment_tv_pinglun_r,item.getReplyContent());
    }
}

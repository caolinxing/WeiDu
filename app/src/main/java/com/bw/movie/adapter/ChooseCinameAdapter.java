package com.bw.movie.adapter;

import android.support.annotation.Nullable;

import com.bw.movie.R;
import com.bw.movie.bean.ChooseCinemaBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class ChooseCinameAdapter extends BaseQuickAdapter<ChooseCinemaBean.ResultBean,BaseViewHolder> {
    private final List<ChooseCinemaBean.ResultBean> list;

    public ChooseCinameAdapter(int layoutResId, @Nullable List<ChooseCinemaBean.ResultBean> data) {
        super(layoutResId, data);
        list = data;
    }

    @Override
    protected void convert(BaseViewHolder helper, ChooseCinemaBean.ResultBean item) {
        helper.setText(R.id.item_bottom_tv_title,item.getName())
                .setText(R.id.item_bottom_tv_address,item.getAddress());
    }

}

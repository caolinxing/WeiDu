package com.bw.movie.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.bean.MovieBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Author:user
 * Date:2018/9/12 16:43
 * Description:This is MyRecyclerMovieListAdapter
 */
public class MyRecyclerMovieListAdapter extends BaseQuickAdapter<MovieBean.ResultBean,BaseViewHolder> {

    public MyRecyclerMovieListAdapter(int layoutResId, @Nullable List<MovieBean.ResultBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MovieBean.ResultBean item) {
        ImageView sdv = helper.getView(R.id.movielist_sdv_movie_icon);
        Glide.with(mContext).load(item.getImageUrl()).into(sdv);
        helper.setText(R.id.movielist_tv_moviecontent,item.getSummary())
                .setText(R.id.movielist_tv_moviename,item.getName());
    }
}

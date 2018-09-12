package com.bw.movie.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.bean.MemberAttentionMovies;
import com.bw.movie.utils.DateFormatUtil;
import com.facebook.drawee.view.SimpleDraweeView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MemberAttentionMovieAdapter extends RecyclerView.Adapter<MemberAttentionMovieAdapter.ViewHolder> {

    // private Context context;
    private List<MemberAttentionMovies.ResultBean> mResultBeans;
    private LayoutInflater inflater;

    public MemberAttentionMovieAdapter(Context activity, List<MemberAttentionMovies.ResultBean> myResultBeans) {
        //  this.context = activity;
        this.inflater = LayoutInflater.from(activity);
        this.mResultBeans = myResultBeans;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        @SuppressLint("InflateParams") View inflate = inflater.inflate(R.layout.memberattentionmovie_item, parent, false);

        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
          holder.imageView.setImageURI(mResultBeans.get(position).getImageUrl());
          holder.attentionMovieName.setText(mResultBeans.get(position).getName());
          //请求long类型时间
          long releaseTime = mResultBeans.get(position).getReleaseTime();
          //转换为Date\
          Date date = DateFormatUtil.longToDate(releaseTime);
          //将Date格式转换
          @SuppressLint("SimpleDateFormat") DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
          //得出格式化后的String类型值
          String myReleaseTime = df.format(date);
          holder.attentionMovieTime.setText(myReleaseTime);
          holder.attentionMovieContent.setText(mResultBeans.get(position).getSummary());
    }

    @Override
    public int getItemCount() {
        return mResultBeans == null ? 0 : mResultBeans.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imageView)
        SimpleDraweeView imageView;
        @BindView(R.id.attention_movie_name)
        TextView attentionMovieName;
        @BindView(R.id.attention_movie_content)
        TextView attentionMovieContent;
        @BindView(R.id.attention_movie_time)
        TextView attentionMovieTime;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}

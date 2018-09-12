package com.bw.movie.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.bean.MovieScheduleBean;

import java.util.List;

public class CinemaAndMovieBottomAdapter extends RecyclerView.Adapter<CinemaAndMovieBottomAdapter.Holder> {

    private Context context;
    private List<MovieScheduleBean.ResultBean> list;
    private String price;

    public CinemaAndMovieBottomAdapter(Context context, List<MovieScheduleBean.ResultBean> list, String price) {
        this.context = context;
        this.list = list;
        this.price = price;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(context).inflate(R.layout.cinema_movie_botton_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.tv_beginTime.setText(list.get(position).getBeginTime());
        holder.tv_endTime.setText(list.get(position).getEndTime());
        holder.tv_duration.setText(list.get(position).getDuration());
        holder.tv_screeningHall.setText(list.get(position).getScreeningHall());
        holder.tv_price.setText("¥"+price);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class Holder extends RecyclerView.ViewHolder{
        TextView tv_beginTime;
        TextView tv_endTime;
        TextView tv_screeningHall;
        TextView tv_duration;
        TextView tv_price;
        TextView tv_buy;
        Holder(View itemView) {
            super(itemView);
            tv_beginTime = itemView.findViewById(R.id.tv_begintime);
            tv_endTime = itemView.findViewById(R.id.tv_endtime);
            tv_screeningHall = itemView.findViewById(R.id.tv_screeningHall);
            tv_duration = itemView.findViewById(R.id.tv_duration);
            tv_price = itemView.findViewById(R.id.tv_price);
            tv_buy = itemView.findViewById(R.id.tv_buy);
        }
    }
}

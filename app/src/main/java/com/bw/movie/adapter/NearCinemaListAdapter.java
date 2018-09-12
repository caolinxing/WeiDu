package com.bw.movie.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.bean.EventBean;
import com.bw.movie.bean.NearCinemaBean;
import com.bw.movie.ui.activity.CinemaAndMovieActivity;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.fresco.helper.Phoenix;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

public class NearCinemaListAdapter extends RecyclerView.Adapter<NearCinemaListAdapter.MyHolder>{

    private Context context;
    private List<NearCinemaBean.ResultBean.NearbyCinemaListBean> listBeans;

    public NearCinemaListAdapter(Context context, List<NearCinemaBean.ResultBean.NearbyCinemaListBean> listBeans) {
        this.context = context;
        this.listBeans = listBeans;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.cinema_item, parent, false);
        return new MyHolder(inflate);
    }

    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, final int position) {
        holder.tv_name.setText(listBeans.get(position).getName());
        holder.tv_address.setText(listBeans.get(position).getAddress());
        holder.tv_long.setText(String .format("%.1f",listBeans.get(position).getDistance()/1000.0)+"km");
        Phoenix.with(holder.sdv).load(listBeans.get(position).getLogo());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listBeans.size();
    }

    class MyHolder extends RecyclerView.ViewHolder{
        SimpleDraweeView sdv;
        TextView tv_name;
        TextView tv_address;
        TextView tv_long;
        MyHolder(View itemView) {
            super(itemView);
            sdv = itemView.findViewById(R.id.sdv_cinema_item);
            tv_name = itemView.findViewById(R.id.tv_name_cinema_item);
            tv_address = itemView.findViewById(R.id.tv_address_cinema_item);
            tv_long = itemView.findViewById(R.id.tv_long_cinema_item);
        }
    }
    public Callback callback;
    public interface Callback{
        void onItemClick(int postion);
    }
    public void setCallback(Callback callback){
        this.callback = callback;
    }

}

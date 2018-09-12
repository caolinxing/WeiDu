package com.bw.movie.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ConvertUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.bw.movie.R;
import com.bw.movie.bean.CinemaCommentBean;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.fresco.helper.Phoenix;

import java.util.Date;
import java.util.List;


public class CinemaCommentAdapter extends RecyclerView.Adapter<CinemaCommentAdapter.Holder> {


    private Context context;
    private List<CinemaCommentBean.ResultBean> list;

    public CinemaCommentAdapter(Context context, List<CinemaCommentBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(context).inflate(R.layout.item_cinema_pinglun, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        Phoenix.with(holder.ivIconCinemaPinglunItem).load(list.get(position).getCommentHeadPic());
        holder.tvNameCinemaPinglunItem.setText(list.get(position).getCommentUserName());
        holder.tvContentCinemaPinglunItem.setText(list.get(position).getCommentContent());
        String date = TimeUtils.millis2String(list.get(position).getCommentTime());
        holder.tvTimeCinemaPinglunItem.setText(date+"");
        holder.tvNumCinemaPinglunItem.setText(list.get(position).getGreatNum()+"");
        if (list.get(position).getIsGreat() == 1){
            holder.ivLikeCinemaPinglunItem.setImageResource(R.drawable.like_blue);
        }else {
            holder.ivLikeCinemaPinglunItem.setImageResource(R.drawable.like_gray);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class Holder extends RecyclerView.ViewHolder {
        SimpleDraweeView ivIconCinemaPinglunItem;
        TextView tvNameCinemaPinglunItem;
        TextView tvTimeCinemaPinglunItem;
        TextView tvContentCinemaPinglunItem;
        TextView tvNumCinemaPinglunItem;
        ImageView ivLikeCinemaPinglunItem;
        public Holder(View itemView) {
            super(itemView);
            ivIconCinemaPinglunItem = itemView.findViewById(R.id.iv_icon_cinema_pinglun_item);
            tvNameCinemaPinglunItem = itemView.findViewById(R.id.tv_name_cinema_pinglun_item);
            tvTimeCinemaPinglunItem = itemView.findViewById(R.id.tv_time_cinema_pinglun_item);
            tvContentCinemaPinglunItem = itemView.findViewById(R.id.tv_content_cinema_pinglun_item);
            tvNumCinemaPinglunItem = itemView.findViewById(R.id.tv_num_cinema_pinglun_item);
            ivLikeCinemaPinglunItem = itemView.findViewById(R.id.iv_like_cinema_pinglun_item);
        }
    }
}

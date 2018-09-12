package com.bw.movie.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bw.movie.R;
import com.bw.movie.bean.MovieBean;
import com.bw.movie.ui.activity.MovieInfoActivity;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.fresco.helper.Phoenix;
import com.view.jameson.library.CardAdapterHelper;

import java.util.List;

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.MyHolder>{

    private Context context;
    private List<MovieBean.ResultBean> list;
    private CardAdapterHelper cardAdapterHelper = new CardAdapterHelper();

    public GalleryAdapter(Context context, List<MovieBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.gallery_item,parent,false);
        cardAdapterHelper.onCreateViewHolder(parent,inflate);
        return new MyHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, final int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MovieInfoActivity.class);
                intent.putExtra("mid",list.get(position).getId()+"");
                context.startActivity(intent);
            }
        });
        Phoenix.with(holder.imageView).load(list.get(position).getImageUrl());
        cardAdapterHelper.onBindViewHolder(holder.itemView, position, getItemCount());
    }

    @Override
    public int getItemCount() {

        return list.size();
    }

    class MyHolder extends RecyclerView.ViewHolder{

        SimpleDraweeView imageView;
        @SuppressWarnings("WeakerAccess")
        public MyHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.iv_banner_item);
        }

    }

}

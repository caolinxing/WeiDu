package com.bw.movie.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.bean.CinemaToMovieBean;
import com.bw.movie.bean.MovieBean;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.fresco.helper.Phoenix;
import com.view.jameson.library.CardAdapterHelper;

import java.util.List;

public class CinemaAndMovieGalleryAdapter extends RecyclerView.Adapter<CinemaAndMovieGalleryAdapter.MyHolder>{

    private Context context;
    private List<CinemaToMovieBean.ResultBean> list;
    private CardAdapterHelper cardAdapterHelper = new CardAdapterHelper();

    @SuppressWarnings("WeakerAccess")
    public CinemaAndMovieGalleryAdapter(Context context, List<CinemaToMovieBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.cinema_movie_gallery_item,parent,false);
        cardAdapterHelper.onCreateViewHolder(parent,inflate);
        return new MyHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        cardAdapterHelper.onBindViewHolder(holder.itemView, position, getItemCount());
        Glide.with(context).load(list.get(position).getImageUrl()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {

        return list.size();
    }

    class MyHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        @SuppressWarnings("WeakerAccess")
        public MyHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.iv_cinema_movie_gallery);
        }
    }

}

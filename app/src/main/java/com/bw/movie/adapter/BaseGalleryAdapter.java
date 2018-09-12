package com.bw.movie.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bw.movie.R;
import com.bw.movie.bean.CinemaToMovieBean;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.fresco.helper.Phoenix;

import java.util.List;

public class BaseGalleryAdapter extends BaseAdapter{

    private Context context;
    private List<CinemaToMovieBean.ResultBean> list;

    public BaseGalleryAdapter(Context context, List<CinemaToMovieBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null){
            convertView = View.inflate(context, R.layout.gallery_item,null);
            viewHolder = new ViewHolder();
            viewHolder.imageView = convertView.findViewById(R.id.iv_banner_item);

            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Phoenix.with(viewHolder.imageView).load(list.get(position).getImageUrl());
        return convertView;
    }

    class ViewHolder{
        SimpleDraweeView imageView;
    }
}

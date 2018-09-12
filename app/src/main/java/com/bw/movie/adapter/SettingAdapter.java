package com.bw.movie.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bw.movie.R;

import java.util.ArrayList;

public class SettingAdapter extends RecyclerView.Adapter<SettingAdapter.ViewHolder> {
    private Context context;
    private ArrayList<String> datas;

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivIcon;
        TextView tvTitle;
        View view;
        public ViewHolder(View itemView) {
            super(itemView);
            this.view = itemView;
            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.onItemClick(view,getPosition());
                }
            });
        }
    }

    public SettingAdapter(Context context, ArrayList<String> datas) {
        this.context = context;
        this.datas = datas;
    }

    /**
     * 相当于getView方法中View和ViewHolder
     *
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = View.inflate(context, R.layout.setting_item, null);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        itemView.setLayoutParams(lp);
        return new ViewHolder(itemView);
    }

    /**
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //根据position得到对应的数据
        String data = datas.get(position);
        holder.tvTitle.setText(data);
    }

    /**
     * 得到总条数
     *
     * @return
     */
    @Override
    public int getItemCount() {
        return datas.size();
    }
    //接口
    public interface OnItemClickListener {
         void onItemClick(View view, int postion);
    }
    private OnItemClickListener mListener;// 声明自定义的接口

    public void setmListener(OnItemClickListener mListener) {
        this.mListener = mListener;

    }
}

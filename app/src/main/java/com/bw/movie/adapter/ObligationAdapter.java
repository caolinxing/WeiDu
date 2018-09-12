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
import com.bw.movie.bean.TicketRecordBean;
import com.bw.movie.utils.DateFormatUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ObligationAdapter extends RecyclerView.Adapter<ObligationAdapter.ViewHolder> {


    private List<TicketRecordBean.ResultBean> list;
    private LayoutInflater inflater;

    public ObligationAdapter(Context activity, List<TicketRecordBean.ResultBean> myList) {
        this.inflater = LayoutInflater.from(activity);
        this.list = myList;
    }

    //接口回调
    //定义接口
    public interface MyItemClickListener{
        void onItemClick(View view, int position);
    }
    //创建实例对象
    public MyItemClickListener clickListener;

    //set方法

    public void setClickListener(MyItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View inflate = inflater.inflate(R.layout.obligation_fragment_item, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") final int position) {

        TicketRecordBean.ResultBean resultBean = list.get(position);
        int status = resultBean.getStatus();
        if(status == 1){
            holder.obligationfragmentTvAddress.setText(list.get(position).getCinemaName());

            long createTime = list.get(position).getCreateTime();
            Date date = DateFormatUtil.longToDate(createTime);
            @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String format = simpleDateFormat.format(date);
            holder.obligationfragmentTvDate.setText(format);
            holder.obligationfragmentTvName.setText(list.get(position).getMovieName());

            holder.myView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onItemClick(v,position);
                }
            });
        }


    }


    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.obligationfragment_tv_name)
        TextView obligationfragmentTvName;
        @BindView(R.id.obligationfragment_tv_address)
        TextView obligationfragmentTvAddress;
        @BindView(R.id.obligationfragment_tv_date)
        TextView obligationfragmentTvDate;

        View myView;
        ViewHolder(View view) {
            super(view);
            this.myView = view;
            ButterKnife.bind(this, view);
        }
    }
}

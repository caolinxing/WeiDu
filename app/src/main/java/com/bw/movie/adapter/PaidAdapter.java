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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PaidAdapter extends RecyclerView.Adapter<PaidAdapter.ViewHolder> {

    private List<TicketRecordBean.ResultBean> list;
    private LayoutInflater inflater;

    public PaidAdapter(Context activity, List<TicketRecordBean.ResultBean> myList) {

        this.inflater = LayoutInflater.from(activity);
        this.list = myList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View inflate = inflater.inflate(R.layout.paid_fragment_item, parent, false);

        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TicketRecordBean.ResultBean resultBean = list.get(position);
        int status = resultBean.getStatus();
        if(status == 2){
           holder.paidfragmentTvAddress.setText(list.get(position).getCinemaName());

            long createTime = list.get(position).getCreateTime();
            Date date = DateFormatUtil.longToDate(createTime);
            @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm-DD");
            String format = simpleDateFormat.format(date);
            holder.paidfragmentTvDate.setText(format);
            holder.paidfragmentTvName.setText(list.get(position).getMovieName());
        }


    }

    @Override
    public int getItemCount() {

        List<TicketRecordBean.ResultBean> myStatus = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getStatus() == 2){
                myStatus.add(list.get(i));
            }
        }
        return myStatus.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.paidfragment_tv_name)
        TextView paidfragmentTvName;
        @BindView(R.id.paidfragment_tv_address)
        TextView paidfragmentTvAddress;
        @BindView(R.id.paidfragment_tv_date)
        TextView paidfragmentTvDate;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}

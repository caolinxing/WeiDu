package com.bw.movie.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.bean.MemberAttentionCinema;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MemberAttentionCinemaAdapter extends RecyclerView.Adapter<MemberAttentionCinemaAdapter.ViewHolder> {

    // private Context context;
    private List<MemberAttentionCinema.ResultBean> mResultBeans;
    private LayoutInflater inflater;

    public MemberAttentionCinemaAdapter(Context activity, List<MemberAttentionCinema.ResultBean> myResultBeans) {
        //  this.context = activity;
        this.inflater = LayoutInflater.from(activity);
        this.mResultBeans = myResultBeans;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View inflate = inflater.inflate(R.layout.memberattentioncinema_item, parent, false);

        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MemberAttentionCinema.ResultBean resultBean = mResultBeans.get(position);
        holder.attentionCinemaItemSdv.setImageURI(resultBean.getLogo());
        holder.attentionCinemaItemTvAddress.setText(resultBean.getAddress());
        holder.attentionCinemaItemTvName.setText(resultBean.getName());

    }

    @Override
    public int getItemCount() {
        return mResultBeans == null ? 0 : mResultBeans.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.attention_cinema_item_sdv)
        SimpleDraweeView attentionCinemaItemSdv;
        @BindView(R.id.attention_cinema_item_tv_name)
        TextView attentionCinemaItemTvName;
        @BindView(R.id.attention_cinema_item_tv_address)
        TextView attentionCinemaItemTvAddress;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}

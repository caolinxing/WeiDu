package com.bw.movie.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.bean.CinemaInfoBean;
import com.bw.movie.bean.CinemaToMovieBean;
import com.bw.movie.bean.MovieScheduleBean;
import com.bw.movie.bean.MyCinemaInfo;
import com.bw.movie.ui.activity.CinemaInfoActivity;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.fresco.helper.Phoenix;
import com.view.jameson.library.CardScaleHelper;
import com.yarolegovich.discretescrollview.DiscreteScrollView;
import com.yarolegovich.discretescrollview.transform.Pivot;
import com.yarolegovich.discretescrollview.transform.ScaleTransformer;

import org.greenrobot.eventbus.EventBus;

import it.moondroid.coverflow.components.ui.containers.FeatureCoverFlow;


public class CinemaAndMovieAdapter extends RecyclerView.Adapter {

    private Context context;
    private CinemaInfoBean cinemaInfoBean;
    private CinemaToMovieBean cinemaToMovieBean;
    private MovieScheduleBean movieTimeAndAddress;
    private CardScaleHelper mCardScaleHelper;
    private int currentItemPos;
    private CinemaAndMovieBottomAdapter adapter;
    private int currentItemPos1;

    public CinemaAndMovieAdapter(Context context, CinemaInfoBean cinemaInfoBean, CinemaToMovieBean cinemaToMovieBean, MovieScheduleBean movieTimeAndAddress) {
        this.context = context;
        this.cinemaInfoBean = cinemaInfoBean;
        this.cinemaToMovieBean = cinemaToMovieBean;
        this.movieTimeAndAddress = movieTimeAndAddress;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 1){
            return new MyHolder1(LayoutInflater.from(context).inflate(R.layout.cinema_info, parent, false));
        } else if(viewType == 2){
            return new MyHolder2(LayoutInflater.from(context).inflate(R.layout.cinema_movie_gallery, parent, false));
        }else if (viewType == 3){
            return new MyHolder3(LayoutInflater.from(context).inflate(R.layout.cinema_movie_item3, parent, false));
        }else
            return null;

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyHolder1) {
            MyHolder1 holder1 = (MyHolder1) holder;
            holder1.tv_name.setText(cinemaInfoBean.getResult().getName());
            holder1.tv_address.setText(cinemaInfoBean.getResult().getAddress());
            holder1.tv_phone.setText("联系电话"+cinemaInfoBean.getResult().getPhone());
            Phoenix.with(holder1.sdv).load(cinemaInfoBean.getResult().getLogo());
            holder1.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EventBus.getDefault().postSticky(new MyCinemaInfo(
                            cinemaInfoBean.getResult().getAddress(),
                            cinemaInfoBean.getResult().getBusinessHoursContent(),
                            cinemaInfoBean.getResult().getCommentTotal(),
                            cinemaInfoBean.getResult().getDistance(),
                            cinemaInfoBean.getResult().isFollowCinema(),
                            cinemaInfoBean.getResult().getId(),
                            cinemaInfoBean.getResult().getLogo(),
                            cinemaInfoBean.getResult().getName(),
                            cinemaInfoBean.getResult().getPhone(),
                            cinemaInfoBean.getResult().getVehicleRoute()));
                    context.startActivity(new Intent(context,CinemaInfoActivity.class));
                }
            });
        }

        if (holder instanceof MyHolder2) {
            final MyHolder2 holder2 = (MyHolder2) holder;
            holder2.featureCoverFlow.setAdapter(new BaseGalleryAdapter(context,cinemaToMovieBean.getResult()));
            holder2.tv_name_gallery.setText(cinemaToMovieBean.getResult().get(0).getName());
            holder2.tv_time_gallery.setText(cinemaToMovieBean.getResult().get(0).getDuration()+cinemaToMovieBean.getResult().get(0).getFare());
            holder2.rv_gallery.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
            mCardScaleHelper = new CardScaleHelper();
            holder2.rv_gallery.setAdapter(new CinemaAndMovieGalleryAdapter(context,cinemaToMovieBean.getResult()));
            mCardScaleHelper.attachToRecyclerView(holder2.rv_gallery);
            currentItemPos1 = mCardScaleHelper.getCurrentItemPos();
            holder2.rv_gallery.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                    super.onScrollStateChanged(recyclerView, newState);
                    if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                        currentItemPos = mCardScaleHelper.getCurrentItemPos();
                        holder2.tv_name_gallery.setText(cinemaToMovieBean.getResult().get(currentItemPos).getName());
                        holder2.tv_time_gallery.setText(cinemaToMovieBean.getResult().get(currentItemPos).getDuration()+cinemaToMovieBean.getResult().get(currentItemPos).getFare());
                        callback.onScrollToGallery(String.valueOf(cinemaInfoBean.getResult().getId()),
                                String.valueOf(cinemaToMovieBean.getResult().get(currentItemPos).getId()));

                    }
                }
            });
        }

        if (holder instanceof MyHolder3) {
            MyHolder3 holder3 = (MyHolder3) holder;
            holder3.rv_gallery.setLayoutManager(new LinearLayoutManager(context));
            ((DefaultItemAnimator)holder3.rv_gallery.getItemAnimator()).setSupportsChangeAnimations(false);
            adapter = new CinemaAndMovieBottomAdapter(context, movieTimeAndAddress.getResult(), String.valueOf(cinemaToMovieBean.getResult().get(currentItemPos).getFare()));
            holder3.rv_gallery.setAdapter(adapter);


        }
    }

    private void changeData() {

    }

    @Override
    public int getItemCount() {
        return 3;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return 1;
        } else if (position == 1) {
            return 2;
        } else {
            return 3;
        }
    }

    class MyHolder1 extends RecyclerView.ViewHolder {
        SimpleDraweeView sdv;
        TextView tv_name;
        TextView tv_address;
        TextView tv_phone;

        MyHolder1(View itemView) {
            super(itemView);
            sdv = itemView.findViewById(R.id.sdv_cinema_item);
            tv_name = itemView.findViewById(R.id.tv_name_cinema_item);
            tv_address = itemView.findViewById(R.id.tv_address_cinema_item);
            tv_phone = itemView.findViewById(R.id.tv_phone_cinema);
        }
    }

    class MyHolder2 extends RecyclerView.ViewHolder {
        RecyclerView rv_gallery;
        TextView tv_name_gallery;
        TextView tv_time_gallery;
        TextView tv_date_gallery;
        FeatureCoverFlow featureCoverFlow;
        //DiscreteScrollView discreteScrollView;
        MyHolder2(View itemView) {
            super(itemView);
            //discreteScrollView = itemView.findViewById(R.id.picker);
            featureCoverFlow = itemView.findViewById(R.id.coverflow);
            tv_name_gallery = itemView.findViewById(R.id.tv_name_ciname_movie_gallery);
            tv_time_gallery = itemView.findViewById(R.id.tv_time_ciname_movie_gallery);
            tv_date_gallery = itemView.findViewById(R.id.tv_date_ciname_movie_gallery);
            rv_gallery = itemView.findViewById(R.id.rv_cinema_movie_gallery);
        }
    }

    class MyHolder3 extends RecyclerView.ViewHolder {
        RecyclerView rv_gallery;

        MyHolder3(View itemView) {
            super(itemView);
            rv_gallery = itemView.findViewById(R.id.rv_cinema_movie_item3);
            rv_gallery.addItemDecoration(new DividerItemDecoration(context,DividerItemDecoration.VERTICAL));
        }
    }
    public Callback callback;

    public void setCallback(Callback callback){
        this.callback = callback;
    }

    public interface Callback{
        void onScrollToGallery(String cinemaId,String movieId);
    }

}

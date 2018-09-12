package com.bw.movie.adapter;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.TimeUtils;
import com.bw.movie.R;
import com.bw.movie.bean.MessageBean;
import com.bw.movie.bean.MutilEnty_MoviewInfo_Bean;
import com.bw.movie.ui.activity.PingLunActivity;
import com.bw.movie.utils.SpaceItemDecoration_h;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.fresco.helper.Phoenix;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import me.zhanghai.android.materialratingbar.MaterialRatingBar;

public class MovieInfoAdapter extends BaseMultiItemQuickAdapter<MutilEnty_MoviewInfo_Bean, BaseViewHolder> implements View.OnClickListener {

    private Bitmap bitmap1;
    private int commentUserId;
    private String name;
    private int id;
    public final String ZHUIPING="0";
    public final String PINGLUNLIST="1";
    private int commentId;
    private String string;

    public MovieInfoAdapter(List<MutilEnty_MoviewInfo_Bean> data) {
        super(data);
        addItemType(MutilEnty_MoviewInfo_Bean.TYPE_ONE, R.layout.item_movieinfo_heade);
        addItemType(MutilEnty_MoviewInfo_Bean.TYPE_TWO, R.layout.item_movieinfo_preson);
        addItemType(MutilEnty_MoviewInfo_Bean.TYPE_THREE, R.layout.item_movieinfo_bill);
        addItemType(MutilEnty_MoviewInfo_Bean.TYPE_FOUR, R.layout.item_movieinfo_trailer);
        addItemType(MutilEnty_MoviewInfo_Bean.TYPE_FIVE, R.layout.item_movie_info_brief_layout);
        addItemType(MutilEnty_MoviewInfo_Bean.TYPE_SIX, R.layout.item_movieinfo_usercomment);
    }

    @Override
    protected void convert(BaseViewHolder helper, final MutilEnty_MoviewInfo_Bean item) {
        switch (helper.getItemViewType()) {
            case MutilEnty_MoviewInfo_Bean.TYPE_ONE:
                SimpleDraweeView sdv = helper.getView(R.id.movie_info_sdv);
                final SimpleDraweeView sdv_bg = helper.getView(R.id.movie_info_img_bg);
                Phoenix.with(sdv_bg).setNeedBlur(true).load(item.getMovieInfo().getResult().getImageUrl());

                /*Phoenix.with(mContext)
                        .setUrl(item.getMovieInfo().getResult().getImageUrl())
                        .setResult(new IResult<Bitmap>() {
                            @Override
                            public void onResult(Bitmap result) {
                                // 在主线程
                                Bitmap bitmap = BlurBitmapUtils.getBlurBitmap(mContext,result);
                                sdv_bg.setImageBitmap(bitmap);
                            }
                        }).load();*/

                name = item.getMovieInfo().getResult().getName();
                id = item.getMovieInfo().getResult().getId();

                Phoenix.with(sdv).load(item.getMovieInfo().getResult().getImageUrl());
                helper.setText(R.id.movie_info_country, "国家:" + item.getMovieInfo().getResult().getPlaceOrigin())
                        .setText(R.id.movie_info_tv_timer, "片长:" + item.getMovieInfo().getResult().getDuration())
                        .setText(R.id.movie_info_tv_title, item.getMovieInfo().getResult().getName())
                        .setText(R.id.movie_info_type, item.getMovieInfo().getResult().getMovieTypes())
                        .addOnClickListener(R.id.movie_info_btn_guanzhu)
                        .addOnClickListener(R.id.movie_info_btn_share);
                break;
            case MutilEnty_MoviewInfo_Bean.TYPE_TWO:
                String[] persons = item.getMovieInfo().getResult().getStarring().split(",");
                String person = "";
                for (int i = 0; i <persons.length ; i++) {
                    person += persons[i]+" ";
                }
                Log.i("TAG", "convert: "+person);
                helper.setText(R.id.item_moviewinfo_tv_director, item.getMovieInfo().getResult().getDirector())
                        .setText(R.id.item_moviewinfo_tv_starring1, person);
                break;
            case MutilEnty_MoviewInfo_Bean.TYPE_THREE:
                RecyclerView recyclerView = helper.getView(R.id.item_moviewinfo_bill_recycler);
                recyclerView.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.HORIZONTAL,false));
                recyclerView.addItemDecoration(new SpaceItemDecoration_h(7));
                MovieInfoBillAdapter adapter = new MovieInfoBillAdapter(R.layout.item_bill_layout, item.getMovieInfo().getResult().getPosterList());
                recyclerView.setAdapter(adapter);
                break;
            case MutilEnty_MoviewInfo_Bean.TYPE_FOUR:
                RecyclerView recyclerView1 = helper.getView(R.id.item_moviewinfo_trailer_recycler);
                recyclerView1.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.HORIZONTAL,false));
                recyclerView1.addItemDecoration(new SpaceItemDecoration_h(7));
                MovieInfoTrailerdapter adapter1 = new MovieInfoTrailerdapter(R.layout.item_trailer_layout, item.getMovieInfo().getResult().getShortFilmList());
                recyclerView1.setAdapter(adapter1);
                break;
            case MutilEnty_MoviewInfo_Bean.TYPE_FIVE:
                helper.setText(R.id.biref_tv,"       "+item.getMovieInfo().getResult().getSummary());
                break;
            case MutilEnty_MoviewInfo_Bean.TYPE_SIX:
                //用户id
                commentUserId = item.getGetComment().getResult().get(0).getCommentUserId();
                commentId = item.getGetComment().getResult().get(0).getCommentId();
                SimpleDraweeView sdv2 = helper.getView(R.id.usercomment_icon);
                string = TimeUtils.millis2String(item.getGetComment().getResult().get(0).getCommentTime());
                MaterialRatingBar materialRatingBar = helper.getView(R.id.usercomment_ratingbar);
                materialRatingBar.setProgress(item.getGetComment().getResult().get(0).getGreatNum());
                Phoenix.with(sdv2).load(item.getGetComment().getResult().get(0).getCommentHeadPic());
                helper.setText(R.id.usercomment_count,"查看全部评论("+item.getGetComment().getResult().size()+")")
                        .addOnClickListener(R.id.usrecomment_dianzan)
                        .setText(R.id.usercomment_tv_date, string)
                        .setText(R.id.userconmment_uname,item.getGetComment().getResult().get(0).getCommentUserName())
                        .setText(R.id.usercomment_tv_pinglun,item.getGetComment().getResult().get(0).getCommentContent())
                        .setText(R.id.usercomment_tv_pingnum,item.getGetComment().getResult().get(0).getReplyNum()+"")
                        .setText(R.id.usercomment_tv_zan,item.getGetComment().getResult().get(0).getGreatNum()+"");
                LinearLayout zhuiPing = helper.getView(R.id.usrecomment_zhuiping);
                TextView pinglun = helper.getView(R.id.usercomment_count);
                zhuiPing.setOnClickListener(this);
                pinglun.setOnClickListener(this);
                RelativeLayout itemview = helper.getView(R.id.item_view);
                //追评
                itemview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.i("TAG", "onClick------: "+item.getGetComment().getResult().get(0).toString());

                        Log.i("TAG", "onClick: "+commentId);
                        EventBus.getDefault().postSticky(new MessageBean(id,commentId,name,ZHUIPING,"查看全部评论("+item.getGetComment().getResult().size()+")",item.getGetComment().getResult().get(0).getGreatNum()+"",item.getGetComment().getResult().get(0).getCommentUserName(),string,item.getGetComment().getResult().get(0).getCommentHeadPic()));
                        mContext.startActivity(new Intent(mContext,PingLunActivity.class));
                    }
                });
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.usercomment_count:
                EventBus.getDefault().postSticky(new MessageBean(id,commentId,name,PINGLUNLIST,null,null,null,null,null));
                mContext.startActivity(new Intent(mContext,PingLunActivity.class));
                break;
        }
    }
}

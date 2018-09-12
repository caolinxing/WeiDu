package com.bw.movie.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.bw.movie.R;
import com.bw.movie.adapter.MoviePingLunListAdapter;
import com.bw.movie.adapter.ReplyAdapter;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.bean.MessageBean;
import com.bw.movie.bean.MoviePingLunListBean;
import com.bw.movie.bean.MutilplePingLunBean;
import com.bw.movie.bean.ResultBean_Can;
import com.bw.movie.bean.Result_pinglunBean;
import com.bw.movie.bean.ZhuiPingListBean;
import com.bw.movie.bean.ZhuiPingLunCanShuBean;
import com.bw.movie.mvp.contract.PingLun_Contract;
import com.bw.movie.mvp.contract.SendMoviePingLun_Contract;
import com.bw.movie.mvp.presenter.PingLun_Presenter;
import com.bw.movie.mvp.presenter.SendMoviePingLun_Presenter;
import com.bw.movie.utils.Api;
import com.bw.movie.utils.Keybords;
import com.bw.movie.utils.ToastUtils;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.fresco.helper.Phoenix;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.wang.avi.AVLoadingIndicatorView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import me.zhanghai.android.materialratingbar.MaterialRatingBar;

public class PingLunActivity extends BaseActivity implements PingLun_Contract.View,SendMoviePingLun_Contract.View {

    @BindView(R.id.movie_info_iv_back)
    ImageView movieInfoIvBack;
    @BindView(R.id.titlebar_tv_title)
    TextView titlebarTvTitle;
    @BindView(R.id.zhuiping_recycler)
    RecyclerView zhuipingRecycler;
    @BindView(R.id.edi_pinglun_content)
    EditText ediPinglunContent;
    @BindView(R.id.tv_send_Pinglun)
    TextView tvSendPinglun;
    @BindView(R.id.smartRefresh)
    SmartRefreshLayout smartRefresh;
    @BindView(R.id.avl_near_cinema)
    AVLoadingIndicatorView avlNearCinema;
    private int movieid;
    private PingLun_Presenter pingLun_presenter;
    private List<MoviePingLunListBean.ResultBean> dataList = new ArrayList<>();
    private List<MutilplePingLunBean> dataList1 = new ArrayList<>();
    private MoviePingLunListAdapter adapter;
    private int size;

    /**
     * flag:判断请求
     *  0:追评列表
     *  1:总评论列表
     * flag1:判断请求
     *  false:追评列表
     *  true:总评论列表
     */
    private String flag;
    private int contentId;
    private MoviePingLunListBean list1;
    private ReplyAdapter adapter1;
    private boolean flag1 = true;
    private int userId;
    private String sessionId;
    private String replyCount="对用户的回复";
    private String commentCount = "对影片的评论";
    private SendMoviePingLun_Presenter sendMoviePingLun_presenter;
    private SharedPreferences sp ;
    private int commentId;
    private String pinglun;
    private String star;
    private String name;
    private String date;
    private String picurl;

    @Override
    public int bindLayout() {
        return R.layout.activity_ping_lun;
    }

    @Override
    public void initData() {
        sp = getSharedPreferences("user_info", MODE_PRIVATE);
        sendMoviePingLun_presenter = new SendMoviePingLun_Presenter(this);
        //显示加载动画
        avlNearCinema.show();
        /**
         * eventBus注册
         */
        EventBus.getDefault().register(this);
        /**
         * 加载数据
         */
        pingLun_presenter = new PingLun_Presenter(this);
        zhuipingRecycler.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MoviePingLunListAdapter(R.layout.item_recycler_usercomment, dataList);
        zhuipingRecycler.setAdapter(adapter);
        if (flag.equals("0")){
            /**
             * 回复主题
             */
            LinearLayout include = findViewById(R.id.include);
            include.setVisibility(View.VISIBLE);
            SimpleDraweeView sdv = findViewById(R.id.usercomment_icon_r);
            Phoenix.with(sdv).load(picurl);
            TextView tv_name = findViewById(R.id.userconmment_uname_r);
            tv_name.setText(name);
            MaterialRatingBar materialRatingBar1 = findViewById(R.id.usercomment_ratingbar_r);
            materialRatingBar1.setProgress(Integer.valueOf(star));
            TextView tv_pinglun = findViewById(R.id.usercomment_tv_pinglun_r);
            tv_pinglun.setText(pinglun);
            TextView tv_date = findViewById(R.id.usercomment_tv_date_r);
            tv_date.setText(date);

            pingLun_presenter.setDataZhui();
        }else {
            pingLun_presenter.setData();
        }
        /**
         * 下拉加载上拉刷新
         */
        smartRefresh.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                if (size == 5) {
                    dataList.clear();
                    pingLun_presenter.setData();
                    smartRefresh.finishLoadMore();
                } else {
                    ToastUtils.showToast(PingLunActivity.this, "已经到底了");
                    smartRefresh.finishLoadMoreWithNoMoreData();
                }
            }
        });
        smartRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                dataList.clear();
                pingLun_presenter.setData();
                Log.i("TAG", "onRefresh: "+dataList.size());
                smartRefresh.finishRefresh();
            }
        });
        /**
         * 发表评论
         */
        tvSendPinglun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag.equals("1")){
                    //总评论
                    if (sp.getAll().isEmpty()){
                        startActivity(new Intent(PingLunActivity.this,LoginActivity.class));
                        finish();
                    }else {
                        userId = sp.getInt("userId", 305);
                        sessionId = sp.getString("sessionId", "1536409181091305");
                        commentCount = ediPinglunContent.getText().toString();
                        sendMoviePingLun_presenter.setData_mping();
                        ediPinglunContent.setText("");
                        Keybords.closeKeybord(ediPinglunContent,PingLunActivity.this);
                        dataList.clear();
                        pingLun_presenter.setData();
                    }
                }else {
                    //追评
                    if (sp.getAll().isEmpty()) {
                        startActivity(new Intent(PingLunActivity.this, LoginActivity.class));
                        finish();
                    }else {
                        userId = sp.getInt("userId", 305);
                        Log.i("TAG", "contentId: " + contentId);
                        sessionId = sp.getString("sessionId", "1536409181091305");
                        replyCount = ediPinglunContent.getText().toString();
                        Log.i("TAG", "reply: " + replyCount);
                        sendMoviePingLun_presenter.setData_zping();
                        ediPinglunContent.setText("");
                        Keybords.closeKeybord(ediPinglunContent, PingLunActivity.this);
                        pingLun_presenter.setDataZhui();
                    }
                }
            }
        });
        /**
         * 点赞
         */

        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

                if (sp.getAll().isEmpty()){
                    ToastUtils.showToast(PingLunActivity.this,"请登入后点赞");
                }else {
                    userId = sp.getInt("userId",305);
                    sessionId = sp.getString("sessionId","1536409181091305");
                    contentId = dataList.get(position).getCommentId();
                    Log.i("TAG", "onItemChildClick: "+userId+ "---"+sessionId+"---"+contentId);
                    sendMoviePingLun_presenter.setData_dzan();
                    adapter.notifyItemChanged(position);
                }
            }
        });
    }

    @Override
    public BasePresenter providePresenter() {
        return null;
    }

    /**
     * 接收值
     */
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void EventBus(MessageBean messageBean) {
        titlebarTvTitle.setText("短评 - " + messageBean.getCan3());
        movieid = messageBean.getCan1();
        contentId = messageBean.getCan2();
        pinglun = messageBean.getCan5();
        star = messageBean.getCan6();
        name = messageBean.getCan7();
        date = messageBean.getCan8();
        picurl = messageBean.getCan9();
        flag = messageBean.getCan4();
        if (flag.equals("0")){
            flag1=false;
        }
    }

    @OnClick({R.id.movie_info_iv_back, R.id.tv_send_Pinglun})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.movie_info_iv_back:
                break;
            case R.id.tv_send_Pinglun:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        pingLun_presenter.onDestory();
        sendMoviePingLun_presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public ZhuiPingLunCanShuBean setPingLunCan() {
        if (flag.equals("0")){
            return new ZhuiPingLunCanShuBean(contentId, 1, 5,0,Api.MOVIE_ZHUIPINGLIST);
        }else if (flag.equals("3")){
            return new ZhuiPingLunCanShuBean(movieid, 1,1,1, Api.MOVIE_PING_LUN_LIST);
        }else {
            return new ZhuiPingLunCanShuBean(movieid, 1,10,1, Api.MOVIE_PING_LUN_LIST);
        }
    }

    @Override
    public void onSuccessfully(MoviePingLunListBean pingLunList) {
        if (flag1) {
            avlNearCinema.hide();
            size = pingLunList.getResult().size();
            dataList.addAll(pingLunList.getResult());
            adapter.notifyDataSetChanged();
        }

    }

    @Override
    public void onFaild(String e) {
        Log.i("TAG", "onFaild: " + e);
    }

    @Override
    public void onSuccessfully_zhui(ZhuiPingListBean zhuiPingListBean) {
        if (zhuiPingListBean.getMessage().equals("无数据")){
            avlNearCinema.hide();
            flag="3";
            ToastUtils.showToast(PingLunActivity.this,"还没有人评论哦！赶紧来评论吧！");
        }else {
            avlNearCinema.hide();
            adapter1 = new ReplyAdapter(R.layout.item_recycler_usercomment,zhuiPingListBean.getResult());
            zhuipingRecycler.setAdapter(adapter1);
            adapter1.notifyDataSetChanged();
        }
    }

    @OnClick(R.id.movie_info_iv_back)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void Success(Result_pinglunBean result_pinglunBean) {
        ToastUtils.showToast(PingLunActivity.this,result_pinglunBean.getMessage());
        if (result_pinglunBean.getMessage().equals("点赞成功")){
            dataList.clear();
            pingLun_presenter.setData();
            /**---------------分割线----------------------------分割线--------------------------------------分割线--------------------------------------------**/
        }
    }

    @Override
    public void Error(String e) {
        Log.i("TAG3", "Error: "+e);
    }

    @Override
    public ResultBean_Can setResultBeanCan() {
        return new ResultBean_Can(Integer.valueOf(userId),contentId,movieid,sessionId,replyCount,commentCount);
    }
}

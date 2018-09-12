package com.bw.movie.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.adapter.ChooseCinameAdapter;
import com.bw.movie.adapter.MovieInfoAdapter;
import com.bw.movie.bean.CanShuBean;
import com.bw.movie.bean.ChooseCinemaBean;
import com.bw.movie.bean.EventBean;
import com.bw.movie.bean.MovieInfoBean;
import com.bw.movie.bean.MoviePingLunListBean;
import com.bw.movie.bean.MutilEnty_MoviewInfo_Bean;
import com.bw.movie.bean.ResultBean_Can;
import com.bw.movie.bean.Result_pinglunBean;
import com.bw.movie.bean.ZhuiPingListBean;
import com.bw.movie.bean.ZhuiPingLunCanShuBean;
import com.bw.movie.mvp.contract.ChooseCinema_Contract;
import com.bw.movie.mvp.contract.MovieInfo_Contract;
import com.bw.movie.mvp.contract.PingLun_Contract;
import com.bw.movie.mvp.contract.SendMoviePingLun_Contract;
import com.bw.movie.mvp.presenter.ChooseCinema_Presenter;
import com.bw.movie.mvp.presenter.MovieInfo_Presenter;
import com.bw.movie.mvp.presenter.PingLun_Presenter;
import com.bw.movie.mvp.presenter.SendMoviePingLun_Presenter;
import com.bw.movie.utils.Api;
import com.bw.movie.utils.SpaceItemDecoration;
import com.bw.movie.utils.ToastUtils;
import com.bw.movie.utils.Utils;
import com.bw.movie.view.Constant;
import com.chad.library.adapter.base.BaseQuickAdapter;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.Unbinder;

public class MovieInfoActivity extends AppCompatActivity implements MovieInfo_Contract.View, PingLun_Contract.View, SendMoviePingLun_Contract.View, ChooseCinema_Contract.View, View.OnClickListener {

    private UpAndDownView upAndDownView;

    private String mid = "1";
    private MovieInfo_Contract.Presenter presenter;
    private List<MutilEnty_MoviewInfo_Bean> dataList = new ArrayList<>();
    private MovieInfoAdapter adapter;
    private int movieId = 1;
    private PingLun_Presenter pingLun_presenter;
    private SharedPreferences sp;
    private int userId;
    private String sessionId;
    private int contentId;
    private MoviePingLunListBean list;
    private SendMoviePingLun_Presenter sendMoviePingLun_presenter;
    private MovieInfoActivity mContext;
    private Unbinder bind;
    private LinearLayout mLin;
    private ImageView mMovieInfoIvBack;
    private RecyclerView mMovieInfoRecycler;
    private UpAndDownView mUpDownView;
    private RecyclerView bottm_recycler;
    private ChooseCinema_Presenter chooseCinema_presenter;
    private ArrayList<ChooseCinemaBean.ResultBean> chooseCinema = new ArrayList<>();
    private ChooseCinameAdapter chooseAdapter;
    private List<ChooseCinemaBean.ResultBean> cinameList;
    private LinearLayoutManager linearLayoutManager;
    private boolean f = true;
    private boolean zan = false;
    private TextView bottom_tv_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_activty);
        upAndDownView = (UpAndDownView) findViewById(R.id.up_down_view);
        upAndDownView.setMaxHeight(Constant.POI_DEFAULT_SHOW_HEIGHT_MID);
        initView();
        showUpDownView();
        initData();
        //设置点击监听
        setOnClick();
    }

    private void setOnClick() {
        mMovieInfoIvBack.setOnClickListener(this);
    }

    public void showUpDownView() {
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) upAndDownView
                .getLayoutParams();
        upAndDownView.setDefaultShowHeight(Constant.POI_DEFAULT_SHOW_HEIGHT_BOTTOM);
        params.height = Utils.dp2Px(MovieInfoActivity.this, Constant.POI_DEFAULT_SHOW_HEIGHT_BOTTOM);
        upAndDownView.setLayoutParams(params);
        upAndDownView.setVisibility(View.VISIBLE);
        bottom_tv_title = upAndDownView.findViewById(R.id.bottom_tv_title);
    }

    @Override
    public void onSuccessful(MovieInfoBean movieInfoBean) {
        movieId = movieInfoBean.getResult().getId();
        Log.i("TAG", "onSuccessful: " + movieInfoBean.getMessage());
        dataList.add(new MutilEnty_MoviewInfo_Bean(MutilEnty_MoviewInfo_Bean.TYPE_ONE, movieInfoBean, null));
        dataList.add(new MutilEnty_MoviewInfo_Bean(MutilEnty_MoviewInfo_Bean.TYPE_TWO, movieInfoBean, null));
        dataList.add(new MutilEnty_MoviewInfo_Bean(MutilEnty_MoviewInfo_Bean.TYPE_THREE, movieInfoBean, null));
        dataList.add(new MutilEnty_MoviewInfo_Bean(MutilEnty_MoviewInfo_Bean.TYPE_FOUR, movieInfoBean, null));
        dataList.add(new MutilEnty_MoviewInfo_Bean(MutilEnty_MoviewInfo_Bean.TYPE_FIVE, movieInfoBean, null));
        adapter.notifyDataSetChanged();
        pingLun_presenter.setData();

    }

    @Override
    public void onFaild(String errorMsg) {
        Log.i("TAG", "onFaild: " + errorMsg);
    }

    @Override
    public void onSuccessfully_zhui(ZhuiPingListBean zhuiPingListBean) {

    }

    @Override
    public CanShuBean setCan() {
        return new CanShuBean(mid, 0, 0);
    }


    @Override
    public ZhuiPingLunCanShuBean setPingLunCan() {
        return new ZhuiPingLunCanShuBean(movieId, 1, 1, 1, Api.MOVIE_PING_LUN_LIST);
    }

    @Override
    public void onSuccessfully(MoviePingLunListBean pingLunList) {
        /**---------------分割线----------------------------分割线--------------------------------------分割线--------------------------------------------**/
        list = pingLunList;
        Log.i("TAG", "onSuccessfully: " + pingLunList.getResult().size());
        dataList.add(new MutilEnty_MoviewInfo_Bean(MutilEnty_MoviewInfo_Bean.TYPE_SIX, null, pingLunList));
        if (zan){
            dataList.remove(5);
            adapter.notifyItemChanged(MutilEnty_MoviewInfo_Bean.TYPE_SIX);
        }
        adapter.notifyDataSetChanged();
    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void Success(Result_pinglunBean result_pinglunBean) {
        ToastUtils.showToast(MovieInfoActivity.this, result_pinglunBean.getMessage());
        adapter.notifyItemChanged(MutilEnty_MoviewInfo_Bean.TYPE_SIX);
        if (result_pinglunBean.getMessage().equals("点赞成功")){
            zan = true;
            pingLun_presenter.setData();
            /**---------------分割线----------------------------分割线--------------------------------------分割线--------------------------------------------**/
        }
    }

    @Override
    public void Error(String e) {
        Log.i("TAG3", "Error: " + e);
    }

    @Override
    public ResultBean_Can setResultBeanCan() {
        return new ResultBean_Can(Integer.valueOf(userId), contentId, movieId, sessionId, null, null);
    }

    public void initData() {
        //获取sp
        sp = getSharedPreferences("user_info", MODE_PRIVATE);
        //获取电影id
        getMovieId();
        //持有presenter
        sendMoviePingLun_presenter = new SendMoviePingLun_Presenter(this);
        presenter = new MovieInfo_Presenter(this);
        mMovieInfoRecycler.addItemDecoration(new SpaceItemDecoration(25));
        adapter = new MovieInfoAdapter(dataList);
        mMovieInfoRecycler.setAdapter(adapter);
        //请求数据
        presenter.setData();
        //评论presenter
        pingLun_presenter = new PingLun_Presenter(this);
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

                if (view.getId() == R.id.movie_info_btn_share) {
                    ToastUtils.showToast(MovieInfoActivity.this, "点击了share");
                } else if (view.getId() == R.id.movie_info_btn_guanzhu) {
                    if (sp.getAll().isEmpty()) {
                        ToastUtils.showToast(MovieInfoActivity.this, "你还没登入呢！赶紧登入吧");
                    } else {
                        userId = sp.getInt("userId", 0);
                        sessionId = sp.getString("sessionId", "");
                        Log.i("TAGxxxx", "onItemChildClick: " + userId + sessionId);
                        sendMoviePingLun_presenter.setData_gzhu();
                    }
                } else if (view.getId() == R.id.usrecomment_dianzan) {
                    if (sp.getAll().isEmpty()) {
                        ToastUtils.showToast(MovieInfoActivity.this, "请登入后点赞");
                    } else {
                        userId = sp.getInt("userId", 0);
                        sessionId = sp.getString("sessionId", "");
                        Log.i("TAGxxxx", "onItemChildClick: " + userId + sessionId);
                        contentId = list.getResult().get(0).getCommentId();
                        Log.i("TAG", "onItemChildClick: " + userId + "---" + sessionId + "---" + contentId);
                        sendMoviePingLun_presenter.setData_dzan();
                    }
                }

            }
        });
        /**
         * 选择影院
         */
        final TextView tv_title = upAndDownView.findViewById(R.id.bottom_tv_title);
        tv_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (f){
                    upAndDownView.showView();
                    bottom_tv_title.setText("影院列表");
                    f=false;
                }else {
                    upAndDownView.hideView();
                    bottom_tv_title.setText("选择列表");
                    f=true;
                }
            }
        });
        chooseCinema_presenter = new ChooseCinema_Presenter(this);
        chooseAdapter = new ChooseCinameAdapter(R.layout.item_bottom_recycler_layout, chooseCinema);
        bottm_recycler.setAdapter(chooseAdapter);
        chooseCinema_presenter.loadData();
        chooseAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                EventBus.getDefault().postSticky(new EventBean(0, cinameList.get(position).getId(), 0, 0));
                startActivity(new Intent(MovieInfoActivity.this, CinemaAndMovieActivity.class));
            }
        });


        mMovieInfoRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                //在此做处理
                if (null != linearLayoutManager) {
                    //当前条目索引
                    int position = linearLayoutManager.findFirstVisibleItemPosition();
                    /*方法1：根据索引来做显示隐藏判断****//*

                    //根据当前条目索引做判断处理。例如：如果在索引是0，
                    *//*隐藏显示某个布局，索引大于0显示出来*//*
                    if (position > 1) {
                        //做隐藏布局操作
                        hideUpDownView();
                    } else {
                        //做显示布局操作
                        showUpDownView();
                    }*/
                    //根据索引来获取对应的itemView
                    View firstVisiableChildView = linearLayoutManager
                            .findViewByPosition(position);
                    //获取当前显示条目的高度
                    int itemHeight = firstVisiableChildView.getHeight();
                    //获取当前Recyclerview 偏移量
                    int flag = (position) * itemHeight - firstVisiableChildView.getTop();
                    if (flag >= itemHeight) {
                        //做隐藏布局操作
                        hideUpDownView();
                    } else {
                        //做显示布局操作
                        showUpDownView();
                    }
                }
            }
        });
    }

    private void getMovieId() {
        Intent intent = getIntent();
        mid = intent.getStringExtra("mid");
        Log.i("TAG", "onCreate: " + mid);
    }

    private void initView() {
        mLin = (LinearLayout) findViewById(R.id.lin);
        mMovieInfoIvBack = (ImageView) findViewById(R.id.movie_info_iv_back);
        mMovieInfoRecycler = (RecyclerView) findViewById(R.id.movie_info_recycler);
        mUpDownView = (UpAndDownView) findViewById(R.id.up_down_view);
        linearLayoutManager = new LinearLayoutManager(this);
        mMovieInfoRecycler.setLayoutManager(linearLayoutManager);
        bottm_recycler = upAndDownView.findViewById(R.id.bottom_recycler);
        bottm_recycler.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onSuccessfully(List<ChooseCinemaBean.ResultBean> list) {
        cinameList = list;
        chooseCinema.clear();
        chooseCinema.addAll(list);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onFail(String e) {
        Log.i("TAG5", "onFail: " + e);
    }
    public void hideUpDownView() {
        upAndDownView.setVisibility(View.GONE);
        bottom_tv_title.setText("选择影院");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.movie_info_iv_back:
                finish();
                break;

        }
    }
}


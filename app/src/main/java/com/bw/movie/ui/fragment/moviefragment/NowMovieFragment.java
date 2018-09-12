package com.bw.movie.ui.fragment.moviefragment;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bw.movie.R;
import com.bw.movie.adapter.GalleryAdapter;
import com.bw.movie.adapter.MyRecyclerMovieListAdapter;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.bean.EventBusBean_1;
import com.bw.movie.bean.MovieBean;
import com.bw.movie.mvp.contract.MovieContract;
import com.bw.movie.mvp.presenter.MoviePresenter;
import com.bw.movie.ui.activity.MovieInfoActivity;
import com.bw.movie.utils.BitMap;
import com.bw.movie.utils.BlurBitmapUtils;
import com.bw.movie.utils.ViewSwitchUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.view.jameson.library.CardScaleHelper;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class NowMovieFragment extends BaseFragment implements MovieContract.View {
    Unbinder unbinder1;
    @BindView(R.id.now_sign_blurView)
    ImageView nowSignBlurView;
    @BindView(R.id.now_grv_remen)
    RecyclerView nowGrvRemen;
    private Unbinder unbinder;
    @SuppressWarnings({"FieldCanBeLocal", "unused"})
    private MovieContract.Presenter presenter;
    @SuppressWarnings("FieldCanBeLocal")
    private String page = "1";
    @SuppressWarnings("FieldCanBeLocal")
    private String count = "12";
    private GalleryAdapter adapter;
    private List<MovieBean.ResultBean> list;
    private List<Bitmap> bitmaps;
    private CardScaleHelper mCardScaleHelper;
    private int mLastPos = -1;
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            notifyBackgroundChange();
        }
    };
    private Runnable mBlurRunnable;
    private List<MovieBean.ResultBean> hotMovieList;
    private Bitmap bitmap;
    private MyRecyclerMovieListAdapter adapter1;

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_now_movie;
    }

    @Override
    public Unbinder unbinder() {
        return unbinder;
    }

    @Override
    protected void initData() {
        EventBus.getDefault().register(this);
        bitmaps = new ArrayList<>();
        list = new ArrayList<>();
        presenter = new MoviePresenter(this);
        nowGrvRemen.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        /**---------------分割线----------------------------分割线--------------------------------------分割线--------------------------------------------**/
        adapter = new GalleryAdapter(getActivity(), list);
        adapter1 = new MyRecyclerMovieListAdapter(R.layout.item_recycler_movie_list, list);
        mCardScaleHelper = new CardScaleHelper();
        presenter.loadNowMoview();
        adapter1.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Log.i("TAG6", "onItemClick: "+"sssssssss");
                Intent intent = new Intent(getActivity(), MovieInfoActivity.class);
                intent.putExtra("mid",list.get(position).getId()+"");
                startActivity(intent);
            }
        });
    }
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void getEventBusBean(EventBusBean_1 eventBusBean_1){
        Log.i("TAG", "getEventBusBean: "+eventBusBean_1.isFlag());
        if (eventBusBean_1.isFlag()){
            nowGrvRemen.setLayoutManager(new LinearLayoutManager(getActivity()));
            nowGrvRemen.setAdapter(adapter1);
        }else {
            nowGrvRemen.setAdapter(adapter);
        }
    }
    @Override
    protected void initView(View view) {
        unbinder = ButterKnife.bind(this, view);
    }


    @Override
    public String getPage() {
        return page;
    }

    @Override
    public String getCount() {
        return count;
    }

    @Override
    public void onSuccess2HotMovie(List<MovieBean.ResultBean> list) {
        hotMovieList = list;
        this.list.addAll(list);
        nowGrvRemen.setAdapter(new GalleryAdapter(getActivity(), this.list));
        mCardScaleHelper.attachToRecyclerView(nowGrvRemen);
        initBlurBackground();
    }

    @Override
    public void onFaild(String e) {
        Log.i("TAG", "onFaild: " + e);
    }


    private void initBlurBackground() {
        nowGrvRemen.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    // notifyBackgroundChange();
                }
            }
        });

        //notifyBackgroundChange();
    }

    private void notifyBackgroundChange() {
        if (mLastPos == mCardScaleHelper.getCurrentItemPos()) return;
        mLastPos = mCardScaleHelper.getCurrentItemPos();
        new Thread(new Runnable() {
            @Override
            public void run() {

                bitmap = BitMap.getInstance().returnBitMap(hotMovieList.get(mLastPos).getImageUrl());
            }
        }).start();
        mBlurRunnable = new Runnable() {
            @Override
            public void run() {
                ViewSwitchUtils.startSwitchBackgroundAnim(nowSignBlurView, BlurBitmapUtils.getBlurBitmap(nowSignBlurView.getContext(), bitmap, 15));
            }
        };
        nowSignBlurView.postDelayed(mBlurRunnable, 500);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
        unbinder1.unbind();
    }

}

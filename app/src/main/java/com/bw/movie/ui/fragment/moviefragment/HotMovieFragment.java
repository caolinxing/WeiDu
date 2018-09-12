package com.bw.movie.ui.fragment.moviefragment;


import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bw.movie.R;
import com.bw.movie.adapter.GalleryAdapter;
import com.bw.movie.adapter.MyRecyclerMovieListAdapter;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.bean.EventBusBean_1;
import com.bw.movie.bean.MovieBean;
import com.bw.movie.mvp.contract.MovieContract;
import com.bw.movie.mvp.presenter.MoviePresenter;
import com.bw.movie.utils.BitMap;
import com.bw.movie.utils.BlurBitmapUtils;
import com.bw.movie.utils.ViewSwitchUtils;
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
public class HotMovieFragment extends BaseFragment implements MovieContract.View {
    @BindView(R.id.grv_remen)
    RecyclerView gryRemen;
    @BindView(R.id.ll_remen)
    RelativeLayout ll_remen;
    @BindView(R.id.sign_blurView)
    ImageView mBlurView;
    Unbinder unbinder1;
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
        return R.layout.fragment_hot_movie;
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
        gryRemen.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        adapter = new GalleryAdapter(getActivity(), list);
        adapter1 = new MyRecyclerMovieListAdapter(R.layout.item_recycler_movie_list, list);
        mCardScaleHelper = new CardScaleHelper();
        presenter.loadHotMovie();

    }
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void getEventBusBean(EventBusBean_1 eventBusBean_1){
        Log.i("TAG", "getEventBusBean: "+eventBusBean_1.isFlag());
        if (eventBusBean_1.isFlag()){
            gryRemen.setLayoutManager(new LinearLayoutManager(getActivity()));
            gryRemen.setAdapter(adapter1);
        }else {
            gryRemen.setAdapter(adapter);
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
        gryRemen.setAdapter(new GalleryAdapter(getActivity(), this.list));
        mCardScaleHelper.attachToRecyclerView(gryRemen);
        initBlurBackground();
    }

    @Override
    public void onFaild(String e) {
        Log.i("TAG", "onFaild: "+e);
    }


    private void initBlurBackground() {
        gryRemen.addOnScrollListener(new RecyclerView.OnScrollListener() {
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
                ViewSwitchUtils.startSwitchBackgroundAnim(mBlurView, BlurBitmapUtils.getBlurBitmap(mBlurView.getContext(), bitmap, 15));
            }
        };
        mBlurView.postDelayed(mBlurRunnable, 500);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
        unbinder1.unbind();
    }
}

package com.bw.movie.ui.activity;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.util.Log;
import android.view.MenuItem;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.bean.MessageBean;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import cn.jzvd.Jzvd;
import cn.jzvd.JzvdStd;

public class VideoActivity extends BaseActivity {


    @BindView(R.id.videoplayer)
    JzvdStd videoplayer;
    private MessageBean dataBean;
    private SensorManager mSensorManager;
    private Jzvd.JZAutoFullscreenListener mSensorEventListener;

    @Override
    public int bindLayout() {
        return R.layout.activity_video;
    }

    @Override
    public void initData() {
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mSensorEventListener = new Jzvd.JZAutoFullscreenListener();
        EventBus.getDefault().register(this);
        Log.i("TAG", "initData: "+dataBean.toString());
        videoplayer.setUp(dataBean.getCan3(), "预告片精彩来袭……",videoplayer.SCREEN_WINDOW_NORMAL );
        Glide.with(VideoActivity.this).load(dataBean.getCan3()).into(videoplayer.thumbImageView);
    }

    @Override
    public BasePresenter providePresenter() {
        return null;
    }
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void getMessage(MessageBean messageBean){
        dataBean = messageBean;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
    @Override
    protected void onResume() {
        super.onResume();
        videoplayer.releaseAllVideos();

    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(mSensorEventListener);
        JzvdStd.clearSavedProgress(this, null);
        //home back
        JzvdStd.goOnPlayOnPause();
    }

    @Override
    public void onBackPressed() {
        if (videoplayer.backPress()){
            return;
        }
        super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}

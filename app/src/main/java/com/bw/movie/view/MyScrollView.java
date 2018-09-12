package com.bw.movie.view;

import android.widget.ScrollView;

import android.content.Context;
import android.util.AttributeSet;


/**
 * Created by .
 */

public class MyScrollView extends ScrollView {

    TranslucentListener listener;

    public void setTranslucentListener(TranslucentListener listener) {
        this.listener = listener;
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public interface TranslucentListener {
        /**
         * 透明度的监听
         *
         * @param alpha 0~1透明度
         */
        void onTranlucent(float alpha);
    }
    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (listener != null) {
            int scrollY = getScrollY();
            int screen_height = getContext().getResources().getDisplayMetrics().heightPixels;
            if (scrollY <= screen_height / 5f) {//0~1f,而透明度应该是1~0f
                listener.onTranlucent(0 + scrollY / (screen_height / 5f));//alpha=滑出去的高度/(screen_height/3f)
            }
        }
    }
}

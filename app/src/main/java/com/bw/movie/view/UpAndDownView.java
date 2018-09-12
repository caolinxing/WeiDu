package com.bw.movie.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import com.bw.movie.R;

/**
 * Created by dingyasong on 2017/8/31.
 */

public class UpAndDownView extends CustomUpAndDownLayout {
    private Context mContext;

    public UpAndDownView(Context context) {
        super(context);
        initView(context);
    }

    public UpAndDownView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public void initView(final Context mContext) {
        this.mContext = mContext;
        View view = LayoutInflater.from(mContext).inflate(R.layout.up_down_layout, null, false);
        addView(view);
        InnerScrollView innerScrollView = (InnerScrollView) view.findViewById(R.id.scrollView);
        if (innerScrollView != null) {
            innerScrollView.parentView = UpAndDownView.this;
        }
    }

    public void setUpDownListener(UpAndDownListener1 listener) {
        setListener(listener);
    }

    public void setIsSlide(boolean isSlide) {
        isSlide(isSlide);
    }

    public boolean isSlide() {
        return isCanSlide();
    }

}

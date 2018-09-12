package com.bw.movie.utils;

import android.app.Application;
import android.content.res.Configuration;
import android.graphics.Point;
import android.view.WindowManager;

import com.facebook.fresco.helper.Phoenix;

public class MyApp extends Application {
    private float DESIGN_WIDTH = 380;

    @Override
    public void onCreate() {
        super.onCreate();
        new DensityHelper(this, DESIGN_WIDTH).activate();
        //DESIGN_WIDTH为设计图宽度，同样不要忘记清单文件配置Application，另 布局中使用pt
        Phoenix.init(this);
    }

}

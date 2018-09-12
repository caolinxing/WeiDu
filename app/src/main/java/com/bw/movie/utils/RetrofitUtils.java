package com.bw.movie.utils;


import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtils {
    private static final int READ_TIME = 60;//读取超时时间,单位  秒
    private static final int CONNECT_TIME = 60;//连接超时时间,单位  秒
    private static RetrofitUtils instance;
    @SuppressWarnings("FieldCanBeLocal")
    private Retrofit build;
    private String url;

    private RetrofitUtils(String url){
        this.url = url;
    }
    public static RetrofitUtils getInstance(String url) {
        if (instance == null) {
            synchronized (RetrofitUtils.class) {
                if (instance == null) {
                    instance = new RetrofitUtils(url);
                }
            }
        }
        return instance;

    }

    public Retrofit getRetrofit(){
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient =  new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .connectTimeout(CONNECT_TIME, TimeUnit.SECONDS)
                .readTimeout(READ_TIME,TimeUnit.SECONDS)
                .build();
        build = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(okHttpClient)
                .build();
        return build;
    }



}

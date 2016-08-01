package com.liguoxi.androiddemo.main;

import android.app.Application;

import com.baidu.mapapi.SDKInitializer;

/**
 * Created by Li Guoxi on 2016/7/29.
 */
public class MainApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        SDKInitializer.initialize(getApplicationContext());
    }
}

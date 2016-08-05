package com.liguoxi.androiddemo.main;

import android.app.Application;
import android.content.Context;

import com.baidu.mapapi.SDKInitializer;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.UsingFreqLimitedMemoryCache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.utils.StorageUtils;

import java.io.File;

/**
 * Created by Li Guoxi on 2016/7/29.
 */
public class MainApplication extends Application {
    public static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        SDKInitializer.initialize(getApplicationContext());
        initUniversalImageLoader();
    }

    private void initUniversalImageLoader() {
        ImageLoader.getInstance().init(getImageLoaderConfig());
    }

    private ImageLoaderConfiguration getImageLoaderConfig() {
        File cacheDir = StorageUtils.getOwnCacheDirectory(mContext, "AndroidDemo/images");
        return new ImageLoaderConfiguration.Builder(mContext)
                .memoryCacheExtraOptions(480, 800)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .threadPoolSize(3)
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .diskCache(new UnlimitedDiskCache(cacheDir))
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.FIFO)
                .memoryCache(new UsingFreqLimitedMemoryCache(2 * 1024 * 1024))
                .memoryCacheSize((int) (Runtime.getRuntime().maxMemory()/8))
                .imageDownloader(new BaseImageDownloader(mContext,5*1000,30*1000))
                .writeDebugLogs()
                .build();
    }
}

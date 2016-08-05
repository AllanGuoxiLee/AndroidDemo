package com.liguoxi.androiddemo.util;

import android.graphics.Bitmap;

import com.liguoxi.androiddemo.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

/**
 * Created by Li Guoxi on 2016/8/5.
 */
public class ImageDisplayOptions {
    private static ImageDisplayOptions instance;

    public ImageDisplayOptions() {
    }

    public static ImageDisplayOptions getInstance() {
        instance = new ImageDisplayOptions();
        return instance;
    }

    public final DisplayImageOptions defaulOption(){
        return new DisplayImageOptions.Builder()
                .showImageOnLoading(R.mipmap.ic_perm_media_grey)
                .imageScaleType(ImageScaleType.IN_SAMPLE_INT)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();
    }
    

}

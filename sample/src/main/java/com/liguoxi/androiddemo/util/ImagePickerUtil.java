package com.liguoxi.androiddemo.util;

import android.app.Activity;
import android.content.Context;
import android.util.TypedValue;
import android.widget.ImageView;

import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.view.CropImageView;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * Created by Li Guoxi on 2016/8/5.
 */
public class ImagePickerUtil {
    private static ImagePickerUtil instance;

    private int mWidth;
    private int mHeight;


    public static ImagePickerUtil getInstance(Context context) {
        instance = new ImagePickerUtil(context);
        return instance;
    }

    public ImagePickerUtil(Context context) {
        mWidth = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 200, context.getResources().getDisplayMetrics());
        mHeight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 200, context.getResources().getDisplayMetrics());
    }

    public ImagePicker initMultiPicker(int limit){
        return initMultiPicker(limit,false);
    }

    public ImagePicker initMultiPicker(int limit,boolean showCamera) {
        ImagePicker multiPicker;
        multiPicker = ImagePicker.getInstance();
        multiPicker.setMultiMode(true);
        multiPicker.setCrop(false);
        multiPicker.setFocusWidth(mWidth);
        multiPicker.setFocusHeight(mHeight);
        multiPicker.setSelectLimit(limit);
        multiPicker.setShowCamera(showCamera);
        multiPicker.setImageLoader(new com.lzy.imagepicker.loader.ImageLoader() {
            @Override
            public void displayImage(Activity activity, String path, ImageView imageView, int width, int height) {
                ImageLoader.getInstance().displayImage("file://"+path,imageView,ImageDisplayOptions.getInstance().defaulOption());
            }

            @Override
            public void clearMemoryCache() {

            }
        });
        return multiPicker;
    }

    public ImagePicker initAvatarPicker() {
        ImagePicker avatarPicker;

        avatarPicker = ImagePicker.getInstance();
        avatarPicker.setCrop(true);
        avatarPicker.setMultiMode(false);
        avatarPicker.setShowCamera(true);

        avatarPicker.setFocusWidth(mWidth);
        avatarPicker.setFocusHeight(mHeight);
        avatarPicker.setStyle(CropImageView.Style.RECTANGLE);
        avatarPicker.setImageLoader(new com.lzy.imagepicker.loader.ImageLoader() {
            @Override
            public void displayImage(Activity activity, String path, ImageView imageView, int width, int height) {
                ImageLoader.getInstance().displayImage("file://" + path, imageView, ImageDisplayOptions.getInstance().defaulOption());
            }

            @Override
            public void clearMemoryCache() {

            }
        });

        return avatarPicker;
    }
}

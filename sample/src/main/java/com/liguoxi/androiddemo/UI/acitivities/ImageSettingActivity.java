package com.liguoxi.androiddemo.UI.acitivities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.liguoxi.androiddemo.R;
import com.liguoxi.androiddemo.main.ToolBarCompatActivity;
import com.liguoxi.androiddemo.util.ImageDisplayOptions;
import com.liguoxi.androiddemo.util.ImagePickerUtil;
import com.liguoxi.androiddemo.widget.CircleImageView;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageCropActivity;
import com.lzy.imagepicker.ui.ImageGridBottomSheet;
import com.lzy.imagepicker.ui.ImageGridBottomSheetFragment;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.File;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ImageSettingActivity extends ToolBarCompatActivity implements View.OnClickListener {

    @Bind(R.id.avatar_iv)
    CircleImageView avatarIv;
    @Bind(R.id.multi_select)
    Button multiSelect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_setting);
        ButterKnife.bind(this);
        initViews();
    }

    private void initViews() {
        avatarIv.setOnClickListener(this);
    }

    @Override
    protected void onCreateCustomToolBar(Toolbar toolbar) {
        super.onCreateCustomToolBar(toolbar);
        toolbar.setTitle("设置图片");
        toolbar.setBackgroundResource(R.color.colorPrimary);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        imageGridBottomSheetFragment.dismiss();
        if (data != null) {
            if (resultCode == ImagePicker.RESULT_CODE_BACK) {

            } else {
                if (data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS) != null) {
                    ArrayList<ImageItem> items = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                    ImageItem item = items.get(0);
                    String url = "file://" + item.path;
                    ImageLoader.getInstance().displayImage(url, avatarIv, ImageDisplayOptions.getInstance().defaulOption());
                }
            }
        } else {
            ImagePicker imagePicker = ImagePicker.getInstance();
            File imageFile = imagePicker.getTakeImageFile();
            if (imageFile==null){
                return;
            }
            ImagePicker.galleryAddPic(this, imageFile);
            ImageItem imageItem = new ImageItem();
            imageItem.path = imagePicker.getTakeImageFile().getAbsolutePath();
            imagePicker.clearSelectedImages();
            imagePicker.addSelectedImageItem(0, imageItem, true);
            if (imagePicker.isCrop()) {
                Intent intent = new Intent(this, ImageCropActivity.class);
                startActivityForResult(intent, imagePicker.REQUEST_CODE_CROP);
            }else {
                ArrayList<ImageItem> items = imagePicker.getSelectedImages();
                ImageItem item = items.get(0);
                String url = "file://" + item.path;
                ImageLoader.getInstance().displayImage(url, avatarIv, ImageDisplayOptions.getInstance().defaulOption());
            }
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.avatar_iv:
                setAvatar();
                break;
        }
    }

    ImageGridBottomSheetFragment imageGridBottomSheetFragment;

    private void setAvatar() {
        ImagePickerUtil.getInstance(this).initAvatarPicker();
        if (imageGridBottomSheetFragment==null){
            imageGridBottomSheetFragment = ImageGridBottomSheetFragment.getInstance(new ImageGridBottomSheet.ImageBottomSheetListener() {
                @Override
                public void getImages(ArrayList<ImageItem> imageItems) {
                    if (imageItems != null && imageItems.size() > 0) {
                        ImageItem item = imageItems.get(0);
                        String uri = "file://" + item.path;
                        ImageLoader.getInstance().displayImage(uri, avatarIv, ImageDisplayOptions.getInstance().defaulOption());
                    }
                }
            });
        }
        imageGridBottomSheetFragment.show(getSupportFragmentManager(), "ImageGridBottomSheetFragment");

    }
}

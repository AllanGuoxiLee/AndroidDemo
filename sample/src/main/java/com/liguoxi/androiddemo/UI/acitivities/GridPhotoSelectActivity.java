package com.liguoxi.androiddemo.UI.acitivities;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.liguoxi.androiddemo.R;
import com.liguoxi.androiddemo.entity.GalleryItemBean;
import com.liguoxi.androiddemo.main.ToolBarCompatActivity;
import com.liguoxi.androiddemo.util.ImageDisplayOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class GridPhotoSelectActivity extends ToolBarCompatActivity {

    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;

    private boolean isEditing = false;

    private final int mSize = 6;

    private final GalleryItemBean addItem = new GalleryItemBean();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_photo_select);
        ButterKnife.bind(this);
        addItem.setAdd(true);
    }

    @Override
    protected void onCreateCustomToolBar(Toolbar toolbar) {
        super.onCreateCustomToolBar(toolbar);
        toolbar.setTitle("多选图片");
        toolbar.setBackgroundResource(R.color.colorPrimary);
    }

    private class GalleryAdapter extends BaseQuickAdapter<GalleryItemBean> {

        public GalleryAdapter(List<GalleryItemBean> data) {
            super(R.layout.gallery_item_layout, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, GalleryItemBean item) {
            getData().remove(addItem);
            if (isEditing) {
                if (!item.isAdd()) {
                    helper.getView(R.id.edit_cover).setVisibility(View.VISIBLE);
                }
            } else {
                helper.getView(R.id.edit_cover).setVisibility(View.GONE);
            }

            if (item.isSelected()) {
                helper.setImageResource(R.id.select_icon, R.mipmap.ic_assignment_turned_in_black);
            } else {
                helper.setImageResource(R.id.select_icon, R.mipmap.ic_assignment_turned_in_white);
            }

            if (item.isAdd()) {
                helper.getView(R.id.add_iv).setVisibility(View.VISIBLE);
            } else {
                helper.getView(R.id.add_iv).setVisibility(View.GONE);
            }

            ImageView imageView = helper.getView(R.id.photo_container);
            if (!TextUtils.isEmpty(item.getPath())) {
                ImageLoader.getInstance().displayImage(item.getPath(), imageView, ImageDisplayOptions.getInstance().defaulOption());
            }

            if (getData().size() < mSize) {
                getData().add(addItem);
            }

        }
    }

}

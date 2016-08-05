package com.liguoxi.androiddemo.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.liguoxi.androiddemo.R;
import com.liguoxi.androiddemo.UI.acitivities.ImageSettingActivity;
import com.liguoxi.androiddemo.UI.acitivities.MapBottomSheetActivity;
import com.liguoxi.androiddemo.entity.IndexEntity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    private RecyclerView mRecyclerView;

    private BaseQuickAdapter<IndexEntity> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        initAdapter();
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
        initData();
    }

    private void initData() {
        List<IndexEntity> indexList = new ArrayList<>();
        indexList.add(new IndexEntity("Map Bottom Sheet Activity", MapBottomSheetActivity.class));
        indexList.add(new IndexEntity("Image Setting Activity", ImageSettingActivity.class));
        mAdapter.setNewData(indexList);

    }

    private void initAdapter() {
        mAdapter = new BaseQuickAdapter<IndexEntity>(R.layout.index_list_item, new ArrayList<IndexEntity>()) {
            @Override
            protected void convert(BaseViewHolder helper, final IndexEntity item) {
                helper.setText(R.id.title_tv, item.getTitle());
                if (item.getSubTitle() != null && item.getSubTitle().length() > 0) {
                    helper.setText(R.id.subTitle_tv, item.getSubTitle());
                } else {
                    helper.getView(R.id.subTitle_tv).setVisibility(View.GONE);
                }
                helper.getConvertView().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(mContext, item.getCls()));
                    }
                });
            }
        };
    }


}

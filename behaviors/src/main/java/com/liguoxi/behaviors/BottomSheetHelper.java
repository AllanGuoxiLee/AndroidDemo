package com.liguoxi.behaviors;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

/**
 * Created by Li Guoxi on 2016/8/1.
 */
public class BottomSheetHelper {
    private Context mContext;
    private LayoutInflater inflater;

    public View userView;

    public View rootView;
    public FrameLayout mContainerLayout;
    public NestedScrollView mBottomSheetLayout;
    public AppBarLayout mExpandedAppBarLayout;
    public Toolbar mExpandedToolbar;
    public Toolbar mToolbar;

    public BottomSheetHelper(Context context, int layoutId) {
        this.mContext = context;
        this.inflater = LayoutInflater.from(context);
        initRootView();
        initWidget();
        initUserView(layoutId);
    }

    private void initRootView() {
        rootView = inflater.inflate(R.layout.activity_bottom_sheet,null);
        ViewGroup.LayoutParams params = new AppBarLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        rootView.setLayoutParams(params);
    }

    private void initUserView(int layoutId) {
        userView = inflater.inflate(layoutId, null);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mContainerLayout.addView(userView,params);
    }

    private void initWidget() {
        mExpandedAppBarLayout = (AppBarLayout) rootView.findViewById(R.id.expanded_appbarLayout);
        mExpandedToolbar = (Toolbar) rootView.findViewById(R.id.expanded_toolbar);
        mContainerLayout = (FrameLayout) rootView.findViewById(R.id.container_layout);
        mBottomSheetLayout = (NestedScrollView) rootView.findViewById(R.id.bottomSheet_layout);
        mToolbar = (Toolbar) rootView.findViewById(R.id.toolbar);
    }

    public View getRootView(){
        return rootView;
    }
}

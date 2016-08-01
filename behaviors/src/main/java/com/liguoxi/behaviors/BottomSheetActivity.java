package com.liguoxi.behaviors;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.StringRes;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.liguoxi.behaviors.behavior.BottomSheetBehaviorGoogleMapsLike;

public class BottomSheetActivity extends BaseActivity {

    protected FrameLayout mContainerLayout;
    protected BottomSheetBehaviorGoogleMapsLike mBottomSheetBehavior;
    protected NestedScrollView mBottomSheetLayout;
    protected AppBarLayout mExpandedAppBarLayout;
    protected Toolbar mExpandedToolbar;
    protected Toolbar mToolbar;
    protected FrameLayout bottomSheetHeader,BottomSheetContainer,BottomSheetFooter;

    private BottomSheetHelper mBottomSheetHelper;

    private String bottomTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_bottom_sheet);
//        mToolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(mToolbar);
//        initViews();
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        mBottomSheetHelper = new BottomSheetHelper(mContext,layoutResID);
        setContentView(mBottomSheetHelper.getRootView());
        mToolbar = mBottomSheetHelper.mToolbar;
        initViews();
    }

    private void initViews() {
        mExpandedAppBarLayout = mBottomSheetHelper.mExpandedAppBarLayout;
        mExpandedToolbar = mBottomSheetHelper.mExpandedToolbar;
        mContainerLayout = mBottomSheetHelper.mContainerLayout;
        mBottomSheetLayout = mBottomSheetHelper.mBottomSheetLayout;
        LinearLayout bottomLayout = (LinearLayout) mBottomSheetLayout.findViewById(R.id.bottomSheet_linearLayout);
        bottomSheetHeader = (FrameLayout) bottomLayout.findViewById(R.id.bottomSheet_header);
        BottomSheetContainer = (FrameLayout) bottomLayout.findViewById(R.id.bottomSheet_container);
        BottomSheetFooter = (FrameLayout) bottomLayout.findViewById(R.id.bottomSheet_footer);
        mBottomSheetBehavior = BottomSheetBehaviorGoogleMapsLike.from(mBottomSheetLayout);
        mBottomSheetBehavior.setHideable(true);
        mBottomSheetBehavior.setState(BottomSheetBehaviorGoogleMapsLike.STATE_HIDDEN);
    }

    public BottomSheetBehaviorGoogleMapsLike getBottomSheetBehavior() {
        return mBottomSheetBehavior;
    }


    public String getBottomTitle() {
        return bottomTitle;
    }

    public String setBottomTitle(@StringRes int titleId) {
        String title = getResources().getString(titleId);
        return title;
    }

    public void setBottomTitle(String bottomTitle) {
        this.bottomTitle = bottomTitle;
    }

    public void setBottomSheetView(@LayoutRes int layoutID){
        View view = getLayoutInflater().inflate(layoutID,mBottomSheetLayout);
        mBottomSheetLayout.addView(view);
    }

    public void setBottomFragment(Fragment fragment){
        mBottomSheetLayout.removeAllViews();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.bottomSheet_layout,fragment).commit();
        transaction.show(fragment);
    }

}

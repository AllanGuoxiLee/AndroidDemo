package com.liguoxi.androiddemo.main;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.widget.TextView;

import com.liguoxi.androiddemo.R;


/**
 * Created by Li Guoxi on 2016/5/30.
 */
public class ToolBarCompatActivity extends BaseActivity {
    protected ToolBarUtil toolBarUtil;
    protected Toolbar toolbar;
    protected TextView title_tv;
    protected Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mContext = this;
    }

    @Override
    public void setContentView(int layoutResID) {
        toolBarUtil = new ToolBarUtil(this, layoutResID);
        toolbar = toolBarUtil.getToolbar();
        toolbar.setTitle("");
        title_tv = toolBarUtil.getTitle_tv();
        setContentView(toolBarUtil.getContentView());
        setSupportActionBar(toolbar);
        onCreateCustomToolBar(toolbar);
    }

    protected void onCreateCustomToolBar(Toolbar toolbar) {
        toolbar.setContentInsetsRelative(0, 0);
    }

    protected void setToolBarTitle(@StringRes int stringId) {
        title_tv.setText(stringId);
    }

    protected void setToolBarTitle(String title) {
        title_tv.setText(title);
    }

    public void finishAct() {
        finish();
        overridePendingTransition(0, R.anim.push_right_out);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finishAct();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finishAct();
        }
        return false;
    }

//    private AlertDialog progressDialog;
//
//    protected void showProgressDialog(String message) {
//        dismissProgressDialog();
//        progressDialog = RollProgressDialog.showNetDialog(this, true, message);
//    }
//
//    protected void dismissProgressDialog() {
//        if (progressDialog != null && progressDialog.isShowing()) {
//            progressDialog.dismiss();
//        }
//    }
}

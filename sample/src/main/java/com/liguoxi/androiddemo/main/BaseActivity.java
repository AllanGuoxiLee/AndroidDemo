package com.liguoxi.androiddemo.main;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Li Guoxi on 2016/8/1.
 */
public class BaseActivity extends AppCompatActivity {
    protected Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mContext = this;
    }

    private AlertDialog progressDialog;

    protected void showProgressDialog(String message) {
        dismissProgressDialog();
        progressDialog = RollProgressDialog.showNetDialog(this, true, message);
    }

    protected void dismissProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }
}

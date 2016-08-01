package com.liguoxi.androiddemo.main;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.liguoxi.androiddemo.R;

import java.util.HashMap;
import java.util.Map;

/**
 * 加载滚动进度对话框建立、销毁类
 *
 * Created by Li Guoxi on 2015/11/11.
 */
public class RollProgressDialog {
    public static final String TAG = RollProgressDialog.class.getSimpleName();
    static Map<String, AlertDialog> mDialogMap = new HashMap<>();
    private static final int SHOW = 0;
    private static final int DISMISS = -1;
    static Handler handler;


    public static AlertDialog showNetDialog(Context context, boolean canelable, String message) {
        initHandler(context);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        if (Build.VERSION.SDK_INT > 11) {
            builder = new AlertDialog.Builder(context,AlertDialog.THEME_TRADITIONAL);
        }
        View view = LayoutInflater.from(context).inflate(R.layout.progress_dialog,null);
        TextView tv = (TextView) view.findViewById(R.id.dlg_message_tv);
        tv.setText(message);

        AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(canelable);
        alertDialog.dismiss();
        alertDialog.show();
        Window wd = alertDialog.getWindow();
        wd.setContentView(view);
        wd.setWindowAnimations(0);
        mDialogMap.put(context.getClass().getName(),alertDialog);

        return  alertDialog;
    }

    public static AlertDialog showNetDialog(Context context,String message){
        return showNetDialog(context,true,message);
    }

    public static AlertDialog showNetDialog(Context context,boolean cancelable){
        return showNetDialog(context,cancelable,"");
    }

    /**
     * 销毁正在转动的进度条对话框
     *
     * @param context
     *            需要销毁Dialog的Activity对象
     **/
    public static void dismissNetDialog(Context context){
        initHandler(context);
        if (mDialogMap.containsKey(context.getClass().getName())){
            Dialog dialog = mDialogMap.get(context.getClass().getName());
            if(dialog!=null){
                handler.obtainMessage(DISMISS,context.getClass().getName()).sendToTarget();
            }
        }
    }

    public static void initHandler(Context context) {
        if(handler!=null){
            return;
        }
        handler = new Handler(context.getMainLooper()){
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what){
                    case  SHOW:
                        Log.d(TAG,"显示对话框");
                        break;

                    case DISMISS:
                        try {
                            String context = (String) msg.obj;
                            Dialog dialog = mDialogMap.get(context);
                            dialog.dismiss();
                            mDialogMap.remove(context);
                        }catch (Exception e){

                        }
                        break;

                    default:
                        break;
                }
            }
        };
    }

    public static class NCYAlertDialog extends AlertDialog{

        protected NCYAlertDialog(Context context) {
            super(context);
        }
    }


}

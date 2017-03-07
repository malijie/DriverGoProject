package com.driver.go.widget.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.driver.go.R;
import com.driver.go.utils.Util;

/**
 * Created by malijie on 2017/3/1.
 */

public class CustomDialog{
    private Dialog mDialog = null;
    private DialogButtonListener mListener;
    private View v = null;

    public CustomDialog(Context context,int layoutId,int buttonId1,int buttonId2,DialogButtonListener listener){
        v = Util.getView(layoutId);

        Button confirmButton = (Button) v.findViewById(buttonId1);
        Button cancelButton = (Button) v.findViewById(buttonId2);

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onConfirm();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onCancel();
            }
        });


        mDialog = new AlertDialog.Builder(context, R.style.dialog)
                .setView(v)
                .create();

        Window dialogWindow = mDialog.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.alpha = 1f; // 透明度
        dialogWindow.setAttributes(lp);

        mListener = listener;
    }

    public void show(){
        if(mDialog != null){
            mDialog.show();
        }
    }

    public interface DialogButtonListener{
        void onConfirm();
        void onCancel();
    }

}


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
import android.widget.TextView;

import com.driver.go.R;
import com.driver.go.utils.Util;

/**
 * Created by malijie on 2017/3/1.
 */

public class CustomDialog{
    private Dialog mDialog = null;
    private DialogButtonListener mListener;
    private View v = null;

    public CustomDialog(Context context,String title){
        v = Util.getView(R.layout.dailg_layout);

        mDialog = new AlertDialog.Builder(context, R.style.dialog)
                .setView(v)
                .setCancelable(false)
                .create();
        TextView textTitle = (TextView) v.findViewById(R.id.id_dialog_text_title);
        textTitle.setText(title);

    }

    public void show(){
        if(mDialog != null){
            mDialog.show();
        }
    }

    public void dissmiss(){
        if(mDialog != null){
            mDialog.dismiss();
        }
    }

    public void setButtonClickListener(DialogButtonListener listener){
        mListener = listener;

        Button confirmButton = (Button) v.findViewById(R.id.id_dialog_button_confirm);
        Button cancelButton = (Button) v.findViewById(R.id.id_dialog_button_cancel);

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
    }

    public interface DialogButtonListener{
        void onConfirm();
        void onCancel();
    }

}


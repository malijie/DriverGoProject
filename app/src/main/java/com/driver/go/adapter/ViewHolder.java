package com.driver.go.adapter;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by malijie on 2016/12/17.
 */

public class ViewHolder {
    private SparseArray<View> mViews = null;
    private View mConvertView = null;
    private int mPosition;

    private ViewHolder(Context context, int resId, ViewGroup parent, int position){
        mViews = new SparseArray<>();
        mConvertView = LayoutInflater.from(context).inflate(resId,parent,false);
        mConvertView.setTag(this);
        mPosition = position;
    }


    public static ViewHolder get(Context context, View convertView, int resId, ViewGroup parent, int position){
        if(convertView == null){
            return new ViewHolder(context,resId,parent,position);
        }
        return (ViewHolder) convertView.getTag();
    }

    public <T extends View> T getView(int viewId){
        View view = mViews.get(viewId);
        if(view == null){
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId,view);
        }

        return (T) view;
    }

    public View getConvertView(){
        return mConvertView;
    }


    public void setText(int resId,String text){
        TextView tv = this.getView(resId);
        tv.setText(text);
    }

}

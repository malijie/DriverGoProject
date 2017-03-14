package com.driver.go.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by malijie on 2016/12/17.
 */

public abstract class CommonAdapter<T> extends BaseAdapter {
    private Context mContext;
    private List<T> mDataBeans;
    private int mLayoutId;
    public CommonAdapter(Context context, int layoutId, List<T> dataBeans){
        mContext = context;
        mDataBeans = dataBeans;
        mLayoutId = layoutId;
    }


    @Override
    public int getCount() {
        return mDataBeans.size();
    }

    @Override
    public Object getItem(int position) {
        return mDataBeans.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder holder = ViewHolder.get(mContext,convertView, mLayoutId,viewGroup,position);
        convert(holder,mDataBeans.get(position));
        return holder.getConvertView();
    }

    public abstract void convert(ViewHolder holder,T item);
}

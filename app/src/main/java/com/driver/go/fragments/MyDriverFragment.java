package com.driver.go.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.driver.go.activity.R;

@SuppressLint("ValidFragment")
public class MyDriverFragment extends Fragment
{

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		View view = inflater.inflate(R.layout.frag, container, false);
		TextView tv = (TextView) view.findViewById(R.id.id_tv);
		return view;
	}
}

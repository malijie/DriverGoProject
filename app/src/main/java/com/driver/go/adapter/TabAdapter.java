package com.driver.go.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

public class TabAdapter extends FragmentPagerAdapter
{

	public static String[] TITLES = new String[]
	{ "科目一", "科目四", "我的"};
	public List<Fragment> mFragments;

	public TabAdapter(FragmentManager fm, List<Fragment> fragments)
	{
		super(fm);
		mFragments = fragments;
	}

	@Override
	public Fragment getItem(int arg0)
	{
		return mFragments.get(arg0);
	}

	@Override
	public int getCount()
	{
		return mFragments.size();
	}

	@Override
	public CharSequence getPageTitle(int position)
	{
		return TITLES[position];
	}

}

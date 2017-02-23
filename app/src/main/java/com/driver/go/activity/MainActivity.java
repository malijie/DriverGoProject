package com.driver.go.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.Window;

import com.driver.go.R;
import com.driver.go.adapter.TabAdapter;
import com.driver.go.fragments.MyDriverFragment;
import com.driver.go.fragments.SubjectFourFragment;
import com.driver.go.fragments.SubjectOneFragment;
import com.viewpagerindicator.TabPageIndicator;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends DriverBaseActivity{
    private ViewPager mViewPager;
    private TabPageIndicator mTabPageIndicator;
    private TabAdapter mAdapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        initView();
    }

    @Override
    public void initView()
    {
        List<Fragment> fragmentList = new ArrayList<>();
        mViewPager = (ViewPager) findViewById(R.id.id_viewpager);
        mTabPageIndicator = (TabPageIndicator) findViewById(R.id.id_indicator);
        fragmentList.add(new SubjectOneFragment());
        fragmentList.add(new SubjectFourFragment());
        fragmentList.add(new MyDriverFragment());
        mAdapter = new TabAdapter(getSupportFragmentManager(),fragmentList);
        mViewPager.setAdapter(mAdapter);

        mTabPageIndicator.setViewPager(mViewPager, 0);
    }

    @Override
    public void initData() {

    }

}

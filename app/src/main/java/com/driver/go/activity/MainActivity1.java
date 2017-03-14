package com.driver.go.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.TextView;

import com.driver.go.R;
import com.driver.go.adapter.TabAdapter;
import com.driver.go.entity.QuestionItem;
import com.driver.go.fragments.SubjectFourFragment;
import com.driver.go.fragments.SubjectOneFragment;
import com.driver.go.http.RetrofitHttpRequest;
import com.driver.go.http.SubscriberOnNextListener;
import com.driver.go.utils.ToastManager;
import com.driver.go.utils.Util;
import com.driver.go.widget.ViewPagerIndicator;
import com.viewpagerindicator.TabPageIndicator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity1 extends DriverBaseActivity{

    private ViewPager mViewpager;
    private ViewPagerIndicator mViewPagerIndicator;
    private List<String> mTitles = Arrays.asList("科目一", "科目四");
    private List<android.support.v4.app.Fragment> mContents = new ArrayList<android.support.v4.app.Fragment>();// 装载ViewPager数据的List
    private FragmentPagerAdapter mAdapter;// ViewPager适配器
    private FragmentManager mSupportFragmentManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
        initData();
        initView();
    }

    @Override
    public void initView() {
        SubjectOneFragment subjectOneFragment = new SubjectOneFragment();
        SubjectFourFragment subjectFourFragment = new SubjectFourFragment();
        mContents.add(subjectOneFragment);
        mContents.add(subjectFourFragment);

        mAdapter = new FragmentPagerAdapter(mSupportFragmentManager) {

            @Override
            public int getCount() {
                return mContents.size();
            }

            @Override
            public android.support.v4.app.Fragment getItem(int position) {
                return mContents.get(position);
            }

        };

        mViewpager = (ViewPager) findViewById(R.id.id_viewpager);
        mViewPagerIndicator = (ViewPagerIndicator) findViewById(R.id.id_indicator);

        //动态设置tab
        mViewPagerIndicator.setVisibleTabCount(2);
        mViewPagerIndicator.setTabItemTitles(mTitles);

        mViewpager.setAdapter(mAdapter);
        mViewPagerIndicator.setViewPager(mViewpager, 0);

        mViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void initData() {
        this.mSupportFragmentManager = getSupportFragmentManager();

    }
}

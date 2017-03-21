package com.driver.go.activity;

import android.app.Activity;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.driver.go.R;
import com.driver.go.activity.base.DriverBaseActivity;
import com.driver.go.control.IntentManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/21.
 */

public class GuideActivity extends DriverBaseActivity{
    private PagerAdapter adapter = null;
    private ViewPager mViewPager = null;
    private List<ImageView> mList = new ArrayList<>();
    private int mImageRes[] ={R.mipmap.guide_1,R.mipmap.guide_2,R.mipmap.guide_4};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guide_layout);
        mViewPager = (ViewPager) findViewById(R.id.id_viewpager);

        for(int i=0;i<3;i++){
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(mImageRes[i]);
            mList.add(imageView);
        }

        adapter = new PagerAdapter() {

            @Override
            public int getCount() {
                return mImageRes.length;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView(mList.get(position));
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                container.addView(mList.get(position));
                return mList.get(position);
            }
        };
        mViewPager.setAdapter(adapter);
        mList.get(2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentManager.startActivity(MainActivity.class);
                finishActivity(GuideActivity.this);
            }
        });
    }
}

package com.driver.go.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.driver.go.R;
import com.driver.go.activity.base.DriverBaseActivity;
import com.driver.go.entity.QuestionItem;
import com.driver.go.fragments.SubjectFourFragment;
import com.driver.go.fragments.SubjectOneFragment;
import com.driver.go.http.SubscriberOnNextListener;
import com.driver.go.utils.ToastManager;
import com.driver.go.utils.Util;
import com.driver.go.widget.ViewPagerIndicator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends DriverBaseActivity {
    private TextView mTextTitle;
    private ImageButton mButtonBack;

    private ViewPager mViewpager;
    private ViewPagerIndicator mViewPagerIndicator;
    private List<String> mTitles = Arrays.asList("科目一", "科目四");
    private List<android.support.v4.app.Fragment> mContents = new ArrayList<android.support.v4.app.Fragment>();// 装载ViewPager数据的List
    private FragmentPagerAdapter mAdapter;// ViewPager适配器
    private FragmentManager mSupportFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();
    }

    public void initView() {
        mTextTitle = (TextView) findViewById(R.id.id_title_bar_text_title);
        mButtonBack = (ImageButton) findViewById(R.id.id_title_bar_button_back);
        mButtonBack.setVisibility(View.GONE);
        mTextTitle.setText(Util.getResString(R.string.main_title));
        mViewpager = (ViewPager) findViewById(R.id.id_viewpager);
        mViewPagerIndicator = (ViewPagerIndicator) findViewById(R.id.id_indicator);

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

    public void initData() {

        this.mSupportFragmentManager = getSupportFragmentManager();
        if(hasInternet()){
            if(!isOrderTableExist()){
                fetchOrderQuestionData2DB();
            }
        }else{
            ToastManager.showNoNetworkMsg();
        }

//        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
//        registerReceiver(receiver,filter);

    }

    /**
     * 抓取科目一，科目四顺序练习题目并插入数据库
     */
    private void fetchOrderQuestionData2DB(){
        mRetrofitHttpRequest.getC1Subject1OrderQuestions(new SubscriberOnNextListener<List<QuestionItem>>(){
            @Override
            public void onNext(final List<QuestionItem> questionItems) {
                new Thread( new Runnable() {
                    @Override
                    public void run() {
                        for(QuestionItem item:questionItems){
                            addSubject1OrderQuestionItem(item);
                        }
                    }
                }).start();

            }
        });

        mRetrofitHttpRequest.getC1Subject4OrderQuestions(new SubscriberOnNextListener<List<QuestionItem>>(){
            @Override
            public void onNext(final List<QuestionItem> questionItems) {
                new Thread( new Runnable() {
                    @Override
                    public void run() {
                        for(QuestionItem item:questionItems){
                            addSubject4OrderQuestionItem(item);
                        }
                    }
                }).start();

            }
        });
    }



//    public BroadcastReceiver receiver = new BroadcastReceiver() {
//
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            Logger.d("broadcast into ...");
//            if (intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
//                if(mSQLiteManager.isOrderTableHasData()){
//                    return;
//                }
//                if (Util.hasInternet()) {
//                    Logger.d("broadcast...");
//                    RetrofitHttpRequest.getInstance().getC1Subject1OrderQuestions(new SubscriberOnNextListener<List<QuestionItem>>(){
//                        @Override
//                        public void onNext(final List<QuestionItem> questionItems) {
//                            new Thread( new Runnable() {
//                                @Override
//                                public void run() {
//                                    for(QuestionItem item:questionItems){
//                                        addSubject1OrderQuestionItem(item);
//                                    }
//                                }
//                            }).start();
//
//                        }
//                    });
//                }
//            }
//        }
//    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        unregisterReceiver(receiver);
    }
}

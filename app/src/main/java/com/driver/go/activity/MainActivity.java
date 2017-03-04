package com.driver.go.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.Window;

import com.driver.go.R;
import com.driver.go.adapter.TabAdapter;
import com.driver.go.entity.QuestionItem;
import com.driver.go.fragments.MyDriverFragment;
import com.driver.go.fragments.SubjectFourFragment;
import com.driver.go.fragments.SubjectOneFragment;
import com.driver.go.http.RetrofitHttpRequest;
import com.driver.go.http.SubscriberOnNextListener;
import com.driver.go.utils.ToastManager;
import com.driver.go.utils.Util;
import com.viewpagerindicator.TabPageIndicator;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends DriverBaseActivity{
    private RetrofitHttpRequest mHttpRequest;
    private ViewPager mViewPager;
    private TabPageIndicator mTabPageIndicator;
    private TabAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        initView();
        initData();
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
        mHttpRequest = RetrofitHttpRequest.getInstance();
        if(hasInternet()){
            if(!isOrderTableExist()){
                fetchOrderQuestionData2DB();
            }
            fetchRandomQuestionData2DB();
        }else{
            ToastManager.showNoNetworkMsg();
        }

    }

    /**
     * 抓取C1顺序练习题目并插入数据库
     */
    private void fetchOrderQuestionData2DB(){
        mHttpRequest.getC1Subject1OrderQuestions(new SubscriberOnNextListener<List<QuestionItem>>(){
            @Override
            public void onNext(final List<QuestionItem> questionItems) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for(QuestionItem item:questionItems){
                            addOrderQuestionItem(item);
                        }
                    }
                }).start();

            }
        });
    }

    /**
     * 抓取C1随机练习题目并插入数据库
     */
    private void fetchRandomQuestionData2DB(){
        mHttpRequest.getC1Subject1RandomQuestions(new SubscriberOnNextListener<List<QuestionItem>>(){
            @Override
            public void onNext(final List<QuestionItem> questionItems) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for(int i=0;i<10;i++){
                            QuestionItem item = questionItems.get(i);
                            addRandomQuestionItem(item);
                        }
                    }
                }).start();
            }
        });
    }

}

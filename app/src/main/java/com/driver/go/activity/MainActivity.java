package com.driver.go.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.driver.go.R;
import com.driver.go.activity.base.DriverBaseActivity;
import com.driver.go.db.DBConstants;
import com.driver.go.entity.QuestionItem;
import com.driver.go.fragments.SubjectFourFragment;
import com.driver.go.fragments.SubjectOneFragment;
import com.driver.go.http.SubscriberOnNextListener;
import com.driver.go.utils.Logger;
import com.driver.go.utils.ToastManager;
import com.driver.go.utils.Util;
import com.driver.go.utils.permission.PermissionController;
import com.driver.go.wap.WapManager;
import com.driver.go.widget.ViewPagerIndicator;
import com.wanpu.pay.PayConnect;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.driver.go.utils.permission.PermissionController.RESULT_CODE;

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
        initPermission();
//        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
//        registerReceiver(receiver,filter);

    }

    final private int REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS = 124;
    private void initPermission() {
        List<String> permissionsNeeded = new ArrayList<String>();
        final List<String> permissionsList = new ArrayList<String>();
        if (!addPermission(permissionsList, Manifest.permission.ACCESS_FINE_LOCATION))
            permissionsNeeded.add("GPS");
        if (!addPermission(permissionsList, Manifest.permission.READ_PHONE_STATE))
            permissionsNeeded.add("Read Phone");
        if (!addPermission(permissionsList, Manifest.permission.WRITE_EXTERNAL_STORAGE))
            permissionsNeeded.add("Write Storage");

        if (permissionsList.size() > 0) {
            if (permissionsNeeded.size() > 0) {
                // Need Rationale
                String message = "You need to grant access to " + permissionsNeeded.get(0);
                for (int i = 1; i < permissionsNeeded.size(); i++)
                    message = message + ", " + permissionsNeeded.get(i);
                ActivityCompat.requestPermissions(this,permissionsList.toArray(new String[permissionsList.size()]),
                        RESULT_CODE);
                return;
            }
            ActivityCompat.requestPermissions(this,permissionsList.toArray(new String[permissionsList.size()]),
                    RESULT_CODE);
            return;
        }

    }



    private boolean addPermission(List<String> permissionsList, String permission) {
        if (ContextCompat.checkSelfPermission(this,permission) != PackageManager.PERMISSION_GRANTED) {
            permissionsList.add(permission);
            // Check for Rationale Option
            if (!ActivityCompat.shouldShowRequestPermissionRationale(this,permission))
                return false;
        }
        return true;
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
        Logger.mlj("======onDestroy====");
        super.onDestroy();
        mWapManager.getPayConnect().close();
//        unregisterReceiver(receiver);
    }

    private long waitTime = 2000;
    private long touchTime = 0;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(event.getAction() == KeyEvent.ACTION_DOWN && KeyEvent.KEYCODE_BACK == keyCode) {
            long currentTime = System.currentTimeMillis();
            if((currentTime-touchTime)>=waitTime) {
                ToastManager.showExitTipMsg();
                touchTime = currentTime;
            }else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}

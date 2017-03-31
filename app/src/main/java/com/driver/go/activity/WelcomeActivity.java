package com.driver.go.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.driver.go.R;
import com.driver.go.control.IntentManager;
import com.driver.go.utils.SharePreferenceUtil;
import com.driver.go.utils.Util;

/**
 * Created by Administrator on 2017/3/21.
 */

public class WelcomeActivity extends FragmentActivity{
    private ImageView mImageWelcome = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//ȫ����ʾ
        super.setContentView(R.layout.welcome_layout);

        initData();
        initViews();
    }

    public void initViews(){
        mImageWelcome = (ImageView) findViewById(R.id.id_welcome_image);
        AlphaAnimation alpha = new AlphaAnimation(0.1f, 1.0f);
        alpha.setDuration(3000);
        mImageWelcome.setAnimation(alpha);

        alpha.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if(SharePreferenceUtil.loadIsFirstLoaded()){
                    IntentManager.startActivity(GuideActivity.class);
                    SharePreferenceUtil.saveIsFirstLoaded(false);

                }else{
                    IntentManager.startActivity(MainActivity.class);
                }

                WelcomeActivity.this.finish();
            }
        });
        mImageWelcome.startAnimation(alpha);
    }

    public void initData(){
        if(SharePreferenceUtil.loadIsFirstLoaded()){
            Util.copyDB2Phone();
        }
    }

    /**
     * 抓取科目一，科目四顺序练习题目并插入数据库
     */
//    private void fetchOrderQuestionData2DB(){
//        mRetrofitHttpRequest.getC1Subject1OrderQuestions(new SubscriberOnNextListener<List<QuestionItem>>(){
//            @Override
//            public void onNext(final List<QuestionItem> questionItems) {
//                new Thread( new Runnable() {
//                    @Override
//                    public void run() {
//                        for(QuestionItem item:questionItems){
//                            saveQuestionItem2DB(DBConstants.SUBJECT1_ORDER_PRACTISE_TABLE,item);
//                        }
//
//                    }
//                }).start();
//
//            }
//        });
//
//        mRetrofitHttpRequest.getC1Subject4OrderQuestions(new SubscriberOnNextListener<List<QuestionItem>>(){
//            @Override
//            public void onNext(final List<QuestionItem> questionItems) {
//                new Thread( new Runnable() {
//                    @Override
//                    public void run() {
//                        for(QuestionItem item:questionItems){
//                            saveQuestionItem2DB(DBConstants.SUBJECT4_ORDER_EXAM_TABLE,item);
//                        }
//                    }
//                }).start();
//
//            }
//        });
//    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}

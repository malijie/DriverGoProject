package com.driver.go.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.driver.go.activity.base.DriverBaseActivity;
import com.driver.go.R;
import com.driver.go.db.DBConstants;
import com.driver.go.entity.QuestionItem;
import com.driver.go.http.SubscriberOnNextListener;
import com.driver.go.utils.SharePreferenceUtil;

import java.util.List;

/**
 * Created by Administrator on 2017/3/21.
 */

public class WelcomeActivity extends DriverBaseActivity{
    private ImageView mImageWelcome = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//ȫ����ʾ
        super.setContentView(R.layout.welcome_layout);

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
                    Intent itMain = new Intent(WelcomeActivity.this,GuideActivity.class);
                    startActivity(itMain);
                    WelcomeActivity.this.finish();
                    SharePreferenceUtil.saveIsFirstLoaded(false);

                }else{
                    Intent itMain = new Intent(WelcomeActivity.this,MainActivity.class);
                    startActivity(itMain);
                    WelcomeActivity.this.finish();
                }
            }
        });

        fetchOrderQuestionData2DB();
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
                            saveQuestionItem2DB(DBConstants.SUBJECT1_ORDER_PRACTISE_TABLE,item);
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
                            saveQuestionItem2DB(DBConstants.SUBJECT4_ORDER_EXAM_TABLE,item);
                        }
                    }
                }).start();

            }
        });
    }
}

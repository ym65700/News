package com.ming.news;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;


/**
 * @author Ming
 *         <p>
 *         启动界面
 */


public class WelcomeActivity extends AppCompatActivity {
    // 延迟时间
    private static final int TIME = 3000;
    //标记是否第一次
    private boolean isFirst = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 隐藏顶部的状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.welcome_activity);
        init();
    }

    private void init() {
        SharedPreferences sp = getSharedPreferences("welcome", MODE_PRIVATE);
        //判断是否第一次进入
        isFirst = sp.getBoolean("isFirst", true);
        if (!isFirst) {
            goHome();
        } else {
            SharedPreferences.Editor editor = sp.edit();
            editor.putBoolean("isFirst", false);
            editor.commit();
            goGuide();
        }
    }

    // 跳转首页
    private void goHome() {
        // 延迟两秒跳转
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {

                Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                startActivity(intent);
                WelcomeActivity.this.finish();
            }
        }, TIME);
    }

    // 跳转引导页
    private void goGuide() {
        // 延迟两秒跳转
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                Intent intent = new Intent(WelcomeActivity.this, GuideActivity.class);
                startActivity(intent);
                WelcomeActivity.this.finish();
            }
        }, TIME);

    }


}

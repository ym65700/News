package com.ming.news;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.Toast;


import com.flyco.tablayout.CommonTabLayout;

import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.ming.entity.TabEntity;

import com.ming.fragment.GameFragment;
import com.ming.fragment.NewsFragment;
import com.ming.fragment.RecommendFragment;
import com.ming.fragment.SetFragment;


import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Administrator on 2016/7/7.
 */
public class MainActivity extends AppCompatActivity {
    private Context mContext = this;
    //底部title
    private String[] mTitles = {"新闻", "推荐", "视频", "我"};
    //底部title图片
    private int[] mIconUnselectIds = {
            R.mipmap.tab_home_unselect, R.mipmap.tab_speech_unselect,
            R.mipmap.tab_contact_unselect, R.mipmap.tab_more_unselect};
    private int[] mIconSelectIds = {
            R.mipmap.tab_home_select, R.mipmap.tab_speech_select,
            R.mipmap.tab_contact_select, R.mipmap.tab_more_select};

    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private ArrayList<Fragment> fragments = new ArrayList<>();
    private CommonTabLayout mTabLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        init();
    }


    private void init() {
        //循环获取title
        for (int i = 0; i < mTitles.length; i++) {
            //实例化
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }
        NewsFragment newsFragment = new NewsFragment();
        RecommendFragment recommendFragment = new RecommendFragment();
        //------------
        GameFragment videoFragment = new GameFragment();
        SetFragment setFragment = new SetFragment();
        fragments.add(newsFragment);
        fragments.add(recommendFragment);
        fragments.add(videoFragment);
        fragments.add(setFragment);

        //获取tablayout设置title和内容fragments
        mTabLayout = (CommonTabLayout) findViewById(R.id.botton_tablayout);
        mTabLayout.setTabData(mTabEntities, this, R.id.content_framelayout, fragments);
        mTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                mTabLayout.setCurrentTab(position);

            }

            @Override
            public void onTabReselect(int position) {

            }
        });
        //设置当前页
        mTabLayout.setCurrentTab(0);
    }

    /**
     * 菜单、返回键响应
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exitBy2Click();
        }
        return false;
    }

    /**
     * 双击退出函数
     */
    private static Boolean isExit = false;

    private void exitBy2Click() {
        Timer timer = null;
        if (isExit == false) {
            isExit = true;
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_LONG).show();
            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    isExit = false;
                }
            }, 2000);
        } else {
            finish();
            System.exit(0);
        }
    }


}

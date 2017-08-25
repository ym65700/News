package com.ming.news;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.Button;

import com.ming.fragment.TranslateFragment;
import com.ming.util.TranslatePageTransformer;

/**
 * 引导页
 */
public class GuideActivity extends FragmentActivity {
    private ViewPager vp;
    private Button button;
    private int[] layoutId = {R.layout.guide1, R.layout.guide2, R.layout.guide3};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guide_activity);
        vp = (ViewPager) findViewById(R.id.vp_guide);
        MyAdapter adapter = new MyAdapter(getSupportFragmentManager());

        // 页面滑动监听器
        vp.setPageTransformer(true, new TranslatePageTransformer());
        vp.setAdapter(adapter);


    }


    //viewPaget适配器
    class MyAdapter extends FragmentStatePagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }
        @Override
        public Fragment getItem(int position) {

            Fragment fragment = new TranslateFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("layoutId", layoutId[position]);
            fragment.setArguments(bundle);
            return fragment;
        }
        @Override
        public int getCount() {
            return layoutId.length;
        }


    }
}



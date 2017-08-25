package com.ming.fragment;


import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.ming.news.R;

import java.util.ArrayList;


/**
 * Created by Administrator on 2016/9/16.
 */
public class NewsFragment extends Fragment {
    private ViewPager vp;
    private SlidingTabLayout tabLayout;
    private String[] mTitles;
    private ArrayList<Fragment> mFragments = new ArrayList<Fragment>();
    private MyPagerAdapter myPagerAdapter;
    private String[] type;

    public NewsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        mTitles = getResources().getStringArray(R.array.NewsTitle);
        type = getResources().getStringArray(R.array.NewsTitlePinyin);
        //for循环 实例化填fragments
        for (int i = 0; i < mTitles.length; i++) {
            mFragments.add(ContentFragment.getInstance(mTitles[i], type[i]));
        }
        vp = (ViewPager) view.findViewById(R.id.vp);
        myPagerAdapter = new MyPagerAdapter(getFragmentManager());

        //viewpager设置适配器
        vp.setAdapter(myPagerAdapter);
        //myPagerAdapter.notifyDataSetChanged();
        //获取tabLayout
        tabLayout = (SlidingTabLayout) view.findViewById(R.id.tb_layout);
        //绑定viewpager
        tabLayout.setViewPager(vp);
        //设置当前下标
        vp.setCurrentItem(0);
        //tabLayout Title点击事件
        tabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {

            }

            @Override
            public void onTabReselect(int position) {
                //Toast.makeText(getActivity(), "position--->" + position, Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    //FragmentStatePagerAdapter
    private class MyPagerAdapter extends FragmentStatePagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getItemPosition(Object object) {
            return super.getItemPosition(object);

        }

    }

}

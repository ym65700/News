package com.ming.fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.ming.news.R;

/**
 * Created by Administrator on 2016/10/14.
 */
public class RecommendFragment extends Fragment {
    private FrameLayout fl;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recommend, container, false);
      //  fl = (FrameLayout) view.findViewById(R.id.fl_recommend);
        ContentFragment contentFragment = ContentFragment.getInstance("头条", "top");
        getFragmentManager().beginTransaction().add(R.id.fl_recommend, contentFragment).commit();
        return view;
    }
}

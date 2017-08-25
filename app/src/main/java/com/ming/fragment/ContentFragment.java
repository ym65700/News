package com.ming.fragment;


import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.markmao.pulltorefresh.widget.XListView;
import com.ming.adapter.ContentAdapter;
import com.ming.jsonBean.NewsInfoBean;
import com.ming.jsonBean.NewsInfoBean.ResultBean.DataBean;
import com.ming.news.ItemActivity;
import com.ming.news.R;
import com.ming.server.ApplicationController;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by Administrator on 2016/9/14.
 */
public class ContentFragment extends Fragment implements XListView.IXListViewListener {
    private String mTitle;
    //下拉列表
    private XListView mListView;
    private View view;
    private ContentAdapter contentAdapter;
    private Handler mHandler;
    private String requestTag = "";
    private Gson gson;
    private ContentFragment context = this;
    private NewsInfoBean newsInfoBean;
    private final int TIME = 2000;
    private List<DataBean> dataList;
    private String mtype;
    private Intent intent;


    public static ContentFragment getInstance(String title, String type) {
        ContentFragment sf = new ContentFragment();
        sf.mTitle = title;
        sf.mtype = type;
        return sf;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_content, null);
        init();
        getNewsContent();
        return view;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void init() {
        gson = new Gson();
        mHandler = new Handler();
        mListView = (XListView) view.findViewById(R.id.pulltorefresh_list);
        mListView.setPullRefreshEnable(true);
        mListView.setPullLoadEnable(true);
        //    mListView.setAutoLoadEnable(true);
        mListView.setXListViewListener(this);
        mListView.setRefreshTime(getTime());


        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0) {
                    position = position - 1;
                }
                intent = new Intent(getActivity(), ItemActivity.class);
                ArrayList<String> data = new ArrayList<String>();
                data.add(dataList.get(position).getUrl());
                Bundle bundle = new Bundle();
                bundle.putStringArrayList("data", data);
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });
    }


    //获取网络内容
    private void getNewsContent() {

        String urlTitle = "http://v.juhe.cn/toutiao/index?type=" + mtype + "&key=00dff034f7af636320acfee793573bc8";
        Log.i("URL",urlTitle.toString());
        JsonObjectRequest requestTitle = new JsonObjectRequest(urlTitle, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                NewsInfoBean newsInforBean = gson.fromJson(response.toString(), NewsInfoBean.class);
                if (newsInforBean != null) {
                    dataList = newsInforBean.getResult().getData();
                    for (int i = 0; i < dataList.size(); i++) {
                        String s = dataList.get(i).getTitle();
                        if (s.contains("中国") || s.contains("主席") || s.contains("萨德")) {
                            dataList.remove(dataList.get(i));

                        }
                    }
                    contentAdapter = new ContentAdapter(getContext(), dataList);
                    mListView.setAdapter(contentAdapter);
                } else {

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                VolleyLog.e("Title_Error: ", volleyError.getMessage());
            }
        }) {

        };
        requestTitle.setRetryPolicy(new DefaultRetryPolicy(3 * 1000, 1, 1.0f));
        requestTag = "volley_content_get";
        requestTitle.setTag(requestTag);
        ApplicationController.getsInstance().addToRequestQueue(requestTitle);
    }

    //------------------------------------------------------------------

    @Override
    public void onRefresh() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                getNewsContent();
                onLoad();
            }
        }, TIME);
    }

    @Override
    public void onLoadMore() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                onLoad();
            }
        }, TIME);
    }

    private void onLoad() {
        mListView.stopRefresh();
        mListView.stopLoadMore();
        mListView.setRefreshTime(getTime());
    }

    private String getTime() {
        return new SimpleDateFormat("MM-dd HH:mm", Locale.CHINA).format(new Date());
    }

    @Override
    public void onResume() {
        super.onResume();

    }

}

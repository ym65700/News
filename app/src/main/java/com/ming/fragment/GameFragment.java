package com.ming.fragment;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.android.volley.Cache;
import com.markmao.pulltorefresh.widget.XListView;
import com.ming.adapter.VideoAdapter;
import com.ming.jsonBean.BaisiBean;
import com.ming.news.R;
import com.ming.util.CacheUtils;
import com.show.api.ShowApiRequest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static com.ming.util.Constans.BAIDU_URL;


/**
 * Created by Administrator on 2016/10/14.
 */
public class GameFragment extends Fragment implements XListView.IXListViewListener {
    private ViewGroup container;
    private View view;
    private Context context;
    private XListView mListView;
    private final int TIME = 2000;
    protected Handler mHandler = new Handler();
    private BaisiBean baisiBean;
    private int currentPage = 1;
    List<BaisiBean.ShowapiResBodyBean.PagebeanBean.ContentlistBean> datas;
    private Boolean isMoreData = false;

    public GameFragment() {
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        context = getActivity();
        view = inflater.inflate(R.layout.fragment_game, container, false);
        init();
        //把之前缓存的数据提取出来
        String saveJson = CacheUtils.getString(context, "redianxinwen");
        if (!TextUtils.isEmpty(saveJson)) {
            //解析显示数据
            setData(saveJson);
        }
        getDataFromNet();
        return view;
    }


    private void getDataFromNet() {
        new Thread() {
            //在新线程中发送网络请求
            public void run() {
                //默认
                if (!isMoreData) {
                    currentPage = 1;
                    String appid = "42348";//要替换成自己的
                    String secret = "26c2b07de6a6474c9b2591bfdbb61271";//要替换成自己的
                    final String res = new ShowApiRequest(BAIDU_URL, appid, secret)
                            .addTextPara("type", "41")
                            .addTextPara("title", "")
                            .addTextPara("page", currentPage + "")
                            .post();
                    //把返回内容通过handler对象更新到界面
                    mHandler.post(new Thread() {
                        public void run() {
                            Log.e("xxxxxx", res);
                            if (!TextUtils.isEmpty(res)) {
                                CacheUtils.putString(context, "videoResult", res);
                                setData(res);
                            }


                        }
                    });
                } else {
                    //加载更多.
                    String appid = "42348";//要替换成自己的
                    String secret = "26c2b07de6a6474c9b2591bfdbb61271";//要替换成自己的
                    final String res = new ShowApiRequest(BAIDU_URL, appid, secret)
                            .addTextPara("type", "41")
                            .addTextPara("title", "")
                            .addTextPara("page", currentPage + "")
                            .post();
                    //把返回内容通过handler对象更新到界面
                    mHandler.post(new Thread() {
                        public void run() {
                            CacheUtils.putString(context, "videoResult", res);
                            setData(res);
                            Log.e("xxxxxx", res);
                        }
                    });

                }

            }
        }.start();
    }

    public void init() {
        mListView = (XListView) view.findViewById(R.id.pulltorefresh_list);
        mListView.setPullRefreshEnable(true);
        mListView.setPullLoadEnable(true);
        //    mListView.setAutoLoadEnable(true);
        mListView.setXListViewListener(this);
        mListView.setRefreshTime(getTime());
    }

    private void setData(String res) {
        baisiBean = JSON.parseObject(res, BaisiBean.class);
        if (baisiBean.getShowapi_res_body().getRet_code() == 0) {
            datas = baisiBean.getShowapi_res_body().getPagebean().getContentlist();
            mListView.setAdapter(new VideoAdapter(context,datas));
        }

    }

    @Override
    public void onRefresh() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                isMoreData = false;
                getDataFromNet();
                onLoad();
            }
        }, TIME);
    }

    @Override
    public void onLoadMore() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                isMoreData = true;
                int tempPage = baisiBean.getShowapi_res_body().getPagebean().getCurrentPage();
                if (tempPage == currentPage && currentPage < baisiBean.getShowapi_res_body().getPagebean().getAllPages()) {
                    currentPage = currentPage + 1;
                    getDataFromNet();

                    //添加到原来的集合中
                    datas.addAll(baisiBean.getShowapi_res_body().getPagebean().getContentlist());
                    //刷新适配器
                   // mListView.notifyDataSetChanged();
//                    mListView.notifyAll();
                }
                if (currentPage == baisiBean.getShowapi_res_body().getPagebean().getAllPages()) {
                    //没有更多数据
                    Toast.makeText(context, "没有更多数据", Toast.LENGTH_SHORT).show();
                }
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

    public void onDestroy() {
        Log.e("TAG", "onDestroy()");
        //方式一：
        // mHandler.removeMessages(1);
        //方式二：
        mHandler.removeCallbacksAndMessages(null);
        super.onDestroy();
    }
}


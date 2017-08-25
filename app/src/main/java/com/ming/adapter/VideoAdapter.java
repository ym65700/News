package com.ming.adapter;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;

import com.ming.jsonBean.BaisiBean;
import com.ming.news.R;

import java.util.List;
import java.util.zip.Inflater;

/**
 * 作者：Administrator on 2017/7/14 21:50
 * 作用：
 */
public class VideoAdapter extends BaseAdapter {
    private List<BaisiBean.ShowapiResBodyBean.PagebeanBean.ContentlistBean> contentlistBeans;
    private Context context;

    public VideoAdapter(Context context, List<BaisiBean.ShowapiResBodyBean.PagebeanBean.ContentlistBean> contentlistBeans) {
        this.contentlistBeans = contentlistBeans;
        this.context = context;
    }

    @Override
    public int getCount() {
        return contentlistBeans == null ? 0 : contentlistBeans.size();

    }

    @Override
    public Object getItem(int position) {
        return contentlistBeans.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_video,null);

        return null;
    }
}

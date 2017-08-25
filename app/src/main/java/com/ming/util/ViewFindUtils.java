package com.ming.util;

import android.view.View;

/**
 * Created by Administrator on 2016/9/14.
 */
public class ViewFindUtils {


    /**
     * ViewHolder简洁写法,避免适配器中重复定义ViewHolder,减少代码量 用法:
     *
     * <pre>
     * if (convertView == null)
     * {
     * 	convertView = View.inflate(context, R.layout.ad_demo, null);
     * }
     * TextView tv_demo = ViewHolderUtils.get(convertView, R.id.tv_demo);
     * ImageView iv_demo = ViewHolderUtils.get(convertView, R.id.iv_demo);
     * </pre>s
     */
    /**
     * 替代findviewbyid方法
     */
    public static <T extends View> T find(View view, int id) {
        return (T) view.findViewById(id);
    }
}

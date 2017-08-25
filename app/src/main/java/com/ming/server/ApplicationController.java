package com.ming.server;

import android.app.Application;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import android.text.TextUtils;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;


/**
 * Created by Administrator on 2016/9/29.
 */
public class ApplicationController extends Application {
    //请求标签
    public static final String TAG = "VolleyPatterns";
    //全局请求列队
    private RequestQueue mRequestQueue;
    //应用程序单利实例访问其他地方
    private static ApplicationController sInstance;
    private ImageLoader imageLoader;

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化单利
        sInstance = this;
    }

    //获取实例
    public static synchronized ApplicationController getsInstance() {
        return sInstance;
    }

    //实例化请求列队
    public RequestQueue getmRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }
        return mRequestQueue;
    }

    /**
     * 添加全局请求列队 设置标签
     *
     * @param req
     * @param tag
     * @param <T>
     */
    public <T> void addToRequestQueue(Request<T> req, String tag) {

        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        VolleyLog.d("Adding request to queue: %s", req.getUrl());
        getmRequestQueue().add(req);
    }

    /**
     * 添加全局请求列队 设置默认标签
     *
     * @param req
     * @param <T>
     */
    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getmRequestQueue().add(req);

    }

    public ImageLoader getImageLoader() {
        imageLoader = new ImageLoader(getmRequestQueue(), new LruImageCache());
        return imageLoader;
    }

    class LruImageCache implements ImageLoader.ImageCache {
        //缓存容器
        private LruCache<String, Bitmap> cache;

        public LruImageCache() {
            //计算缓存最值
            int maxSize = (int) (Runtime.getRuntime().maxMemory() / 8);
            //创建缓存对象的实例
            cache = new LruCache<String, Bitmap>(maxSize) {
                protected int sizeOf(String key, Bitmap value) {
                    return value.getRowBytes() * value.getHeight();
                }

            };

        }

        // 从缓存中取图片对象
        @Override
        public Bitmap getBitmap(String url) {
            return cache.get(url);
        }

        // 将图片对象保存到缓存容器中
        @Override
        public void putBitmap(String url, Bitmap bitmap) {
            cache.put(url, bitmap);
        }

    }

    /**
     * 取消请求指定的标记
     *
     * @param tag
     */
    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }


}

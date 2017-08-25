package com.ming.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 作者：Administrator on 2017/7/14 22:10
 * 作用：
 */

public class CacheUtils {
    /**
     * 获取缓存的文本信息
     *
     * @param context
     * @param key
     * @return
     */
    public static String getString(Context context, String key) {
        String result="";
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            try {
                File file = new File(Environment.getExternalStorageDirectory() + "/redianxinwen/files", key);
                if (file.exists()) {
                    FileInputStream fis = new FileInputStream(file);
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    byte[] buffer = new byte[1024];
                    int length;
                    while ((length = fis.read(buffer)) != -1) {
                        stream.write(buffer, 0, length);
                    }
                    fis.close();
                    stream.close();
                    result = stream.toString();
                }

            } catch (IOException e) {
                e.printStackTrace();
                Log.e("CacheUtils", "文本huoqu失败");
            }
        }else {
            SharedPreferences sp = context.getSharedPreferences("xinwen", Context.MODE_PRIVATE);
            result = sp.getString(key, "");
        }
        return result;

    }

    /**
     * 缓存的文本信息
     *
     * @param context
     * @param key
     * @return
     */
    public static void putString(Context context, String key, String value) {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {

            try {
                File file = new File(Environment.getExternalStorageDirectory() + "/redianxinwen/files", key);
                File fileParent = file.getParentFile();
                if (!fileParent.exists()) {
                    fileParent.mkdirs();
                }

                if (!file.exists()) {
                    file.createNewFile();
                }
                // //保存文本数据
                FileOutputStream fos = new FileOutputStream(file);
                fos.write(value.getBytes());
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
                Log.e("CacheUtils", "文本缓存失败");
            }

        } else {
            SharedPreferences sp = context.getSharedPreferences("redianxinwen", Context.MODE_PRIVATE);
            sp.edit().putString("key", "value").commit();

        }
    }

}

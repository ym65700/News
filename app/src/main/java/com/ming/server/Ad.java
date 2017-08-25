/*
package com.ming.server;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.provider.Settings;
import android.view.ViewGroup;
import android.widget.TextView;


import com.baidu.appx.BDAppWallAd;
import com.baidu.appx.BDBannerAd;
import com.baidu.appx.BDInterstitialAd;
import com.baidu.appx.BDNativeAd;
import com.baidu.appx.BDSplashAd;

import java.util.ArrayList;

*/
/**
 * Created by Administrator on 2016/10/19.
 *//*

public class Ad {
    private String SDK_APP_KEY = "i1gBBKCdpcaqORMrNbqUNCy6tVOO4BLv";
    private String SDK_BANNER_AD_ID = "IA6GoOUvF20AowXoqm7WcoHr";
    private String SDK_SPLASH_AD_ID = "FExCWuQgND7Kjhunt0yOv3uI";
    // private String SDK_INTERSTITIAL_AD_ID = "ntuHx5sTGGniFdR0eubEH76c";
    private String SDK_APPWALL_AD_ID = "MNVbs5Z9yFKRIIj7rLzEBuGt";
    private String SDK_NATIVE_AD_ID = "AM0G8OwdpVzOitb2jmAEdnfD";

    //开屏广告
    public static BDSplashAd SplashAd(Context context, BDSplashAd splashAd) {

        if (null == splashAd) {
            //创建开屏广告
            splashAd = new BDSplashAd((Activity) context,
                    "SDK_APP_KEY", "SDK_SPLASH_AD_ID");
            //设置监听回调
            splashAd.setAdListener(new AdListener("bdSplashAd"));
            //如果本地无广告可用，需要下载广告，待下次启动使用
            if (!splashAd.isLoaded()) {
                splashAd.loadAd();
            } else {
                //展示开屏广告
                splashAd.showAd();
            }
        }
        return splashAd;

    }

    //banner
    public static BDBannerAd bannerAd(Context context, BDBannerAd bannerAd, ViewGroup container) {
        if (null == bannerAd) {
            //创建并展示广告
            bannerAd = new BDBannerAd((Activity) context, "SDK_APP_KEY", "SDK_BANNER_AD_ID");
            //选择模式
            bannerAd.setAdSize(BDBannerAd.SIZE_FLEXIBLE);
            // 设置监听回调
            bannerAd.setAdListener(new AdListener("bannerAd"));
        }
        container.addView(bannerAd);
        return bannerAd;
    }

    //广告墙
    public static BDAppWallAd appwallAd(Context context, BDAppWallAd appwallAd) {
        if (null == appwallAd) {
            //创建广告强
            appwallAd = new BDAppWallAd((Activity) context, "SDK_APP_KEY", "SDK_APPWALL_AD_ID");
            //如果本地无广告可用，需要下载广告，待下次启动使用
            if (!appwallAd.isLoaded()) {
                appwallAd.loadAd();
            } else {
                //展示开屏广告
                appwallAd.doShowAppWall();
            }


        }
        return appwallAd;
    }

    //原生广告
    public static void nativeAd(Context context, BDNativeAd nativeAd) {
        if (null == nativeAd) {
            //创建广告强
            nativeAd = new BDNativeAd((Activity) context, "SDK_APP_KEY", "SDK_NATIVE_AD_ID");
            //下载广告
            nativeAd.loadAd();
            //判断下载结果，并获取广告数据
            if (nativeAd.isLoaded()) {
                ArrayList<BDNativeAd.AdInfo> adArray = nativeAd.getAdInfos();
                //... 自定义展示UI，其中BDNativeAd.AdInfo里的
                // didShow() 和 didClick()
                // 需要相应的UI响应逻辑中触发调用。 }


            }

        }
    }

    private static class AdListener implements BDBannerAd.BannerAdListener, BDInterstitialAd.InterstitialAdListener, BDSplashAd.SplashAdListener {
        private String stringTag;

        public AdListener(String tag) {
            this.stringTag = tag;
        }

        @Override
        public void onAdvertisementDataDidLoadFailure() {
            System.out.println(stringTag + "    ad did load failure");
        }


        @Override
        public void onAdvertisementDataDidLoadSuccess() {
            System.out.println(stringTag + "    ad did load success");
        }

        @Override
        public void onAdvertisementViewDidClick() {
            System.out.println(stringTag + "    ad view did click");
        }

        @Override
        public void onAdvertisementViewDidShow() {
            System.out.println(stringTag + "    ad view did show");
        }

        @Override
        public void onAdvertisementViewWillStartNewIntent() {
            System.out.println(stringTag + "    ad view will new intent");
        }

        @Override
        public void onAdvertisementViewDidHide() {
            System.out.println(stringTag + "    ad view did hide");
        }

    }

}

*/

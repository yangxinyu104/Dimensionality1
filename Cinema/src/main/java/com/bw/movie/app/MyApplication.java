package com.bw.movie.app;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;

import com.bw.movie.bean.BeonBean;
import com.bw.movie.bean.ParticularsBean;
import com.bw.movie.bean.PopularMovieBean;
import com.bw.movie.bean.ReviewBean;
import com.bw.movie.bean.ShowingBean;
import com.facebook.cache.disk.DiskCacheConfig;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author：Y
 * @E-mail： 2447892835@qq.com
 * @Date：2019.5.11 10:50
 * @Description：YangXinYu
 */
public class MyApplication extends Application {
    //影院ID
    public static  int cinemaId;
    //影片ID
    public static  int movieId;
    //影片名字
    public static String FilmName;
    //UserId
    public static int  UserId ;
    //SessionId
    public static String  SessionId ;
    //标识
    public static int flag;
    //关注标识
    public static int Zanflag;
    //点击的电影详情
    public static ParticularsBean.ResultBean resultBean;
    //电影评论
    public static List<ReviewBean.ResultBean> reviewBean;
    @Override
    public void onCreate() {
        super.onCreate();

        Fresco.initialize(this,ImagePipelineConfig.newBuilder(this).setMainDiskCacheConfig(DiskCacheConfig.newBuilder(this)
                .setBaseDirectoryName("fresco")
                .setBaseDirectoryPath(new File(Environment.getExternalStorageDirectory()+"f"))
                .setMaxCacheSize(100)
                .setMaxCacheSizeOnLowDiskSpace(60)
                .setMaxCacheSizeOnVeryLowDiskSpace(40)
                .build()).build());
        UMConfigure.init(this,"5a12384aa40fa3551f0001d1"
                ,"umeng",UMConfigure.DEVICE_TYPE_PHONE,"");//58edcfeb310c93091c000be2 5965ee00734be40b580001a0
        PlatformConfig.setWeixin("wxdc1e388c3822c80b", "3baf1193c85774b3fd9d18447d76cab0");

    }

    public static  boolean isNetworkConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }
        }
        return false;
    }
}

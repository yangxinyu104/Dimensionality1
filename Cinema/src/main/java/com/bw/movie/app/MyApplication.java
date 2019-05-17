package com.bw.movie.app;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.text.SpannableString;

import com.baidu.location.LocationClient;
import com.bw.movie.bean.ParticularsBean;
import com.bw.movie.bean.ReviewBean;
import com.facebook.cache.disk.DiskCacheConfig;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * @Author：Y
 * @E-mail： 2447892835@qq.com
 * @Date：2019.5.11 10:50
 * @Description：YangXinYu
 */
public class MyApplication extends Application {
    //性别
    public static int  Sex;
    //用户名字
    public static String  UserName;
    //头像
    public static String HeadPic;
    //手机号
    public static String phones;
    //生日
    public static long birthday;
    //用户最后登陆时间
    public static long lastLoginTime;
    //微信或支付宝
    public static int type;
    //订单号
    public static String orderId;
    //总价
    public static SpannableString sum;
    //单价
    public static double price;
    //排期表
    public static  int scheduleId;
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
    //定位
    public static String City;
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

    }

    //判断网络
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

    /**
     * 32位MD5加密
     * @param content -- 待加密内容
     * @return
     */
    public static  String md5Decode(String content) {
        byte[] hash;
        try {
            hash = MessageDigest.getInstance("MD5").digest(content.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("NoSuchAlgorithmException",e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("UnsupportedEncodingException", e);
        }
        //对生成的16字节数组进行补零操作
        StringBuilder hex = new StringBuilder(hash.length * 2);
        for (byte b : hash) {
            if ((b & 0xFF) < 0x10){
                hex.append("0");
            }
            hex.append(Integer.toHexString(b & 0xFF));
        }
        return hex.toString();
    }

    /**
     * 16位MD5加密
     * 实际是截取的32位加密结果的中间部分(8-24位)
     * @param content
     * @return
     */
    public static  String md5Decode16(String content) {
        return md5Decode(content).substring(8, 24);
    }
}

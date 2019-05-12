package com.bw.movie.util;

import android.content.Context;

import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

/**
 * @Author：Y
 * @E-mail： 2447892835@qq.com
 * @Date：2019.5.11 15:45
 * @Description：YangXinYu
 */
public class WeiXinUtil {

    // APP_ID 替换为你的应用从官方网站申请到的合法appID
    public static  String APP_ID = "wxb3852e6a6b7d9516";

    // IWXAPI 是第三方app和微信通信的openApi接口
    private WeiXinUtil() {

    }
    public  static  boolean success(Context context){
        //判断是否安装过微信
        if (WeiXinUtil.reg(context).isWXAppInstalled()) {
            return  true;
        }else {
            return false;
        }
    }
    public static IWXAPI reg(Context context){
        if (context!=null) {
            //AppConst.WEIXIN.APP_ID是指你应用在微信开放平台上的AppID，记得替换。
            IWXAPI wxapi = WXAPIFactory.createWXAPI(context, APP_ID, true);
            //注册到微信
            wxapi.registerApp(APP_ID);
            return wxapi;
        }else {
            return  null;
        }
    }
   /* //支付
    public static void  weiXinPay(Bean_OrderSuccessBean com.bw.movie.bean){
        IWXAPI wxapi = WXAPIFactory.createWXAPI(App.context, APP_ID, true);
        //注册到微信
        wxapi.registerApp(APP_ID);

        PayReq payReq = new PayReq();
        payReq.appId=com.bw.movie.bean.getAppId();
        payReq.prepayId=com.bw.movie.bean.getPrepayId();
        payReq.partnerId=com.bw.movie.bean.getPartnerId();
        payReq.nonceStr=com.bw.movie.bean.getNonceStr();
        payReq.timeStamp=com.bw.movie.bean.getTimeStamp();
        payReq.sign=com.bw.movie.bean.getSign();
        payReq.packageValue=com.bw.movie.bean.getPackageValue();
        Log.d("",payReq.toString()+"111111");
        wxapi.sendReq(payReq);
    }*/

}

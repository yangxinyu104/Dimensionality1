package com.bw.movie.receiver;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.activity.InternetActivity;
import com.bw.movie.activity.LoginActivity;
import com.bw.movie.app.MyApplication;

/**
 * @Author：Y
 * @E-mail： 2447892835@qq.com
 * @Date：2019.5.19 20:56
 * @Description：YangXinYu
 */
public class NetWorkChangReceiver extends BroadcastReceiver {
    private  PopupWindow popupWindow;
    private String getConnectionType(int type) {
        String connType = "";
        if (type == ConnectivityManager.TYPE_MOBILE) {
            connType = "3G网络数据";
        } else if (type == ConnectivityManager.TYPE_WIFI) {
            connType = "WIFI网络";
        }
        return connType;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (WifiManager.WIFI_STATE_CHANGED_ACTION.equals(intent.getAction())) {// 监听wifi的打开与关闭，与wifi的连接无关
            int wifiState = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE, 0);
            Log.e("TAG", "wifiState:" + wifiState);
            switch (wifiState) {
                case WifiManager.WIFI_STATE_DISABLED:
                    break;
                case WifiManager.WIFI_STATE_DISABLING:
                    break;
            }
        }
        // 监听网络连接，包括wifi和移动数据的打开和关闭,以及连接上可用的连接都会接到监听
        if (ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())) {
            //获取联网状态的NetworkInfo对象
            NetworkInfo info = intent.getParcelableExtra(ConnectivityManager.EXTRA_NETWORK_INFO);
            if (info != null) {
                //如果当前的网络连接成功并且网络连接可用
                if (NetworkInfo.State.CONNECTED == info.getState() && info.isAvailable()) {
                    if (info.getType() == ConnectivityManager.TYPE_WIFI || info.getType() == ConnectivityManager.TYPE_MOBILE) {
                        if (InternetActivity.internetActivity!=null){
                            InternetActivity.internetActivity.finish();
                        }
                    }
                } else {
                    Intent intent1 = new Intent(context,InternetActivity.class);
                    context.startActivity(intent1);
                }
            }
        }
    }


   /* public interface  SetFinish{
        void back();
    }
    public  SetFinish  setFinish;
    public void Setaa(SetFinish  setFinish){
        this.setFinish = setFinish;

    }*/


    private void StaerPopupWindow() {
        View view = View.inflate(MyApplication.GetContext(), R.layout.network, null);
        popupWindow = new PopupWindow(view,LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
        popupWindow.setAnimationStyle(R.style.popwin_anim_style);
        popupWindow.showAtLocation(view,Gravity.CENTER_HORIZONTAL,0,100);
    }
}

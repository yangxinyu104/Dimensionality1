package com.bw.movie.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.app.MyApplication;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.receiver.NetWorkChangReceiver;

public class InternetActivity extends BaseActivity  {
    public static  InternetActivity internetActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internet);
        internetActivity = this;
    }
    //屏蔽返回键
    @Override
    public boolean onKeyDown(int keyCode,KeyEvent event){
        if(keyCode==KeyEvent.KEYCODE_BACK)
            return true;
        return super.onKeyDown(keyCode, event);
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (!MyApplication.isNetworkConnected(this)){
            //Toast.makeText(this, "糟糕,网络不给力呀", Toast.LENGTH_SHORT).show();
            Dialog(this);

        }
        return true;
    }

    private void Dialog(final Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("提示");
        builder.setMessage("网络中断,您可以去设置中打开网络!");
        builder.setCancelable(false);
        builder.setPositiveButton("设置", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(Settings.ACTION_WIRELESS_SETTINGS));;
            }
        });
        builder.setNegativeButton("确实", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }


}

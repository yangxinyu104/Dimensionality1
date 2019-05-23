package com.bw.movie.activity;

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
            Toast.makeText(this, "糟糕,网络不给力呀", Toast.LENGTH_SHORT).show();
        }
        return true;
    }


}

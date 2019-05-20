package com.bw.movie.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.app.MyApplication;
import com.bw.movie.base.BaseActivity;

public class InternetActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internet);
        Log.e("tag","111111");
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (MyApplication.isNetworkConnected(this)){
            finish();
        }else{
            Toast.makeText(this, "糟糕,网络不给力呀", Toast.LENGTH_SHORT).show();
        }
        return super.onTouchEvent(event);
    }
}

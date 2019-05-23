package com.bw.movie.throwable;

import android.content.Context;
import android.content.Intent;
import android.os.Looper;
import android.widget.Toast;

import com.bw.movie.activity.LoginActivity;

/**
 * @Author：Y
 * @E-mail： 2447892835@qq.com
 * @Date：2019.5.21 18:41
 * @Description：YangXinYu
 */
public class ThrowDispose implements Thread.UncaughtExceptionHandler{
    Context context;
    private static ThrowDispose aThrow;
    private ThrowDispose(){
    }
    public  static ThrowDispose GetInstance(){
        if (aThrow==null){
            aThrow = new ThrowDispose();
        }
        return aThrow;
    }
    public void Init(Context context){
        this.context = context;
        Thread.setDefaultUncaughtExceptionHandler(this);
    }
    @Override
    public void uncaughtException(Thread t, Throwable e) {

        new Thread(){
            @Override
            public void run() {
                super.run();

                Looper.prepare();

               Toast.makeText(context, "遇到了异常", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(context,LoginActivity.class);
                 context.startActivity(intent);

                Looper.loop();

            }
        }.start();

    }


}

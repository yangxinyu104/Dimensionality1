package com.example.pattern;

import android.app.Application;
import android.content.Context;

/**
 * @Author：Y
 * @E-mail： 2447892835@qq.com
 * @Date：2019.6.3 18:35
 * @Description：YangXinYu
 */
public class App extends Application {

    private static Context applicationContext;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationContext = getApplicationContext();
    }

    public static  Context GetContext(){
        return applicationContext;
    }
}

package app;

import android.app.Application;
import android.os.Environment;

import com.facebook.cache.disk.DiskCacheConfig;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;

import java.io.File;

/**
 * @Author：Y
 * @E-mail： 2447892835@qq.com
 * @Date：2019.5.11 10:50
 * @Description：YangXinYu
 */
public class MyApplication extends Application {

    public static int  UserId ;
    public static String  SessionId ;

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
}

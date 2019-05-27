package com.example.upfile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

public class MainActivity extends AppCompatActivity implements UploadTaskListener{

    UploadManager uploadManager;

    private static String URL_Test_ID = "url_test";

    private String url_test = "http://product.xmjunfeng.com:38080/pes2/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button upfile = findViewById(R.id.upfile);
        upfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*JSONObject  jsonObject = new JSONObject();
                int   chunk = Integer.valueOf(jsonObject.get("off").toString());
                UploadTask task = new UploadTask.Builder()
                        .setId(URL_Test_ID)
                        .setUrl(url_test)
                        .setChunck(chunk)
                        .setFileName("5.mp4")
                        .setListener(this).build();
                uploadManager.addUploadTask(task);*/
            }
        });

    }

    @Override
    public void onUploading(UploadTask uploadTask, String percent, int position) {
        Log.e("tag","onUploading");
    }

    @Override
    public void onUploadSuccess(UploadTask uploadTask, File file) {
        Log.e("tag","onUploadSuccess");
    }

    @Override
    public void onError(UploadTask uploadTask, int errorCode, int position) {
        Log.e("tag","onError");
    }

    @Override
    public void onPause(UploadTask uploadTask) {
        Log.e("tag","onPause");
    }
}

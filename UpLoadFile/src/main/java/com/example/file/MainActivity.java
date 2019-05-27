package com.example.file;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.mainaer.wjoklib.okhttp.upload.UploadManager;
import com.mainaer.wjoklib.okhttp.upload.UploadTask;
import com.mainaer.wjoklib.okhttp.upload.UploadTaskListener;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, UploadTaskListener {
    private ProgressBar mProgressBar;
    private Button mButton;
    private Button mButtonPause;
    private Button mButtonCancel;
    private Button mButtonResume;
    private TextView mTvStatus;


    UploadManager uploadManager;

    private static String URL_Test_ID = "url_test";

    private String url_test = "http://product.xmjunfeng.com:38080/pes2/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        //----------第一组下载----------------
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        mButton = (Button) findViewById(R.id.button);
        mButton.setOnClickListener(this);

        mButtonPause = (Button) findViewById(R.id.buttonpause);
        mButtonPause.setOnClickListener(this);

        mButtonCancel = (Button) findViewById(R.id.buttoncancel);
        mButtonCancel.setOnClickListener(this);

        mButtonResume = (Button) findViewById(R.id.buttonresume);
        mButtonResume.setOnClickListener(this);

        mTvStatus = (TextView) findViewById(R.id.tv_status);

    }
    @Override
    public void onClick(View v) {
        if (mButton == v) {
            download360();
            Log.e("tag","点击");
        }

        else if (mButtonPause == v) {
            uploadManager.pause(URL_Test_ID);
            Log.e("tag","点击1");
        }
        else if (mButtonResume == v) {
            uploadManager.resume(URL_Test_ID,this);
            Log.e("tag","点击2");
        }

    }
    private void download360() {
        Log.e("tag","上传");
        Map<String, String> params = new HashMap<String, String>();
        params.put("filename", "5.mp4");
        OkHttpRequestUtils.getInstance(this).OkHttpGetStringRequest(url_test + "ckeckFileServlet", params, new StringCallback() {

            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                Log.i("ckeckFileServlet", response);
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(response);
                    final int chunk = Integer.valueOf(jsonObject.get("off").toString());
                    UploadTask task = new UploadTask.Builder().setId(URL_Test_ID).setUrl(url_test).setChunck(chunk).setFileName("5.mp4").setListener(MainActivity.this).build();
                    uploadManager.addUploadTask(task);


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }


    public static void go(Context context) {
        Intent intent = new Intent(context, UpLoadActivity.class);
        context.startActivity(intent);
    }


    @Override
    public void onUploading(UploadTask uploadTask, String percent, int position) {
        if (uploadTask.getId().equals(URL_Test_ID)) {
            mProgressBar.setProgress(Integer.parseInt(percent));
            mTvStatus.setText("正在下载..." + percent + "%");
        }
        else {
            mProgressBar.setProgress(Integer.parseInt(percent));
            mTvStatus.setText("正在上传..." + percent + "%");
        }
    }

    @Override
    public void onUploadSuccess(UploadTask uploadTask, File file) {
        if (uploadTask.getId().equals(URL_Test_ID)) {
            mTvStatus.setText("上传完成 path：" + file.getAbsolutePath());
        }
        else {
            mTvStatus.setText("上传完成 path：" + file.getAbsolutePath());
        }
    }

    @Override
    public void onError(UploadTask uploadTask, int errorCode, int position) {
        if (uploadTask.getId().equals(URL_Test_ID)) {
            mTvStatus.setText("上传失败=" + errorCode);
        }else{
            mTvStatus.setText("上传失败errorCode=" + errorCode);
        }
    }

    @Override
    public void onPause(UploadTask uploadTask) {
        if (uploadTask.getId().equals(URL_Test_ID)) {
            mTvStatus.setText("上传暂停！");
        }else{
            mTvStatus.setText("上传暂停！");
        }
    }




}

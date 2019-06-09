package com.example.mvp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.base.BaseActivity;

public class Main2Activity extends BaseActivity {


    private TextView id;
    private String stringExtra;

    @Override
    protected int getLayout() {
        return R.layout.activity_main2;
    }

    @Override
    protected void findView() {
        id = findViewById(R.id.eeeeeeeeeeeeeeee);

    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        stringExtra = intent.getStringExtra("111");
        id.setOnClickListener(this);

    }

    @Override
    protected void setListener() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.eeeeeeeeeeeeeeee:
                id.setText(stringExtra);
                break;

        }

    }
}

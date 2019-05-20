package com.bw.movie.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bw.movie.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AccomplishActivity extends AppCompatActivity {

    @BindView(R.id.accomplish_finish)
    ImageView accomplishFinish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accomplish);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.accomplish_finish)
    public void onViewClicked() {
        finish();
    }
}

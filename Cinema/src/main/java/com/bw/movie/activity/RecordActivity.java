package com.bw.movie.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;

import com.bw.movie.R;
import com.bw.movie.adapter.RecordAdapter;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.bean.RecordBean;
import com.bw.movie.contract.ContractInterFace;
import com.bw.movie.presenter.MyPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RecordActivity extends BaseActivity implements ContractInterFace.IRecord {

    @BindView(R.id.record_film)
    RadioButton recordFilm;
    @BindView(R.id.record_cinema)
    RadioButton recordCinema;
    @BindView(R.id.record_RecyclerView)
    RecyclerView recordRecyclerView;
    @BindView(R.id.record_finish)
    ImageView recordFinish;
    private ContractInterFace.IPresenter iPresenter;
    List<RecordBean.ResultBean> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        ButterKnife.bind(this);

        iPresenter = new MyPresenter<>(this);
        iPresenter.record(1, 10, 1);
    }



    @OnClick({R.id.record_film, R.id.record_cinema, R.id.record_finish})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.record_film:
                recordFilm.setTextColor(Color.WHITE);
                recordCinema.setTextColor(Color.BLACK);
                iPresenter.record(1, 10, 1);
                recordRecyclerView.setLayoutManager(new LinearLayoutManager(this));
                RecordAdapter adapter = new RecordAdapter(list,this);
                recordRecyclerView.setAdapter(adapter);
                break;
            case R.id.record_cinema:
                recordFilm.setTextColor(Color.BLACK);
                recordCinema.setTextColor(Color.WHITE);
                iPresenter.record(1, 10, 2);
                break;
            case R.id.record_finish:
                finish();
                break;
        }
    }

    @Override
    public void record(RecordBean wechatLoginBean) {
        list.clear();
        List<RecordBean.ResultBean> result = wechatLoginBean.getResult();
        list.addAll(result);
        recordRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecordAdapter adapter = new RecordAdapter(list,this);
        recordRecyclerView.setAdapter(adapter);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        iPresenter.Desetory();
        iPresenter =null;
    }
}

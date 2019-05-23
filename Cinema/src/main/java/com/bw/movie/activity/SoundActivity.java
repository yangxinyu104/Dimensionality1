package com.bw.movie.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.adapter.SoundAdapter;
import com.bw.movie.app.MyApplication;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.bean.ChangerBean;
import com.bw.movie.bean.SoundBean;
import com.bw.movie.bean.SumBean;
import com.bw.movie.contract.ContractInterFace;
import com.bw.movie.presenter.MyPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SoundActivity extends BaseActivity implements ContractInterFace.ISound {

    @BindView(R.id.sound_NoRead)
    TextView soundNoRead;
    @BindView(R.id.sound_RecyclerView)
    RecyclerView soundRecyclerView;
    @BindView(R.id.sound_finish)
    ImageView soundFinish;
    int nums = 0;
    List<SoundBean.ResultBean> list = new ArrayList<>();
    private SoundAdapter adapter;
    private ContractInterFace.IPresenter iPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sound);
        ButterKnife.bind(this);
        soundRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new SoundAdapter(list,this);
        soundRecyclerView.setAdapter(adapter);

        iPresenter = new MyPresenter<>(this);
        iPresenter.sound(1, 150);
        iPresenter.sum();
        adapter.setOnItemClick(new SoundAdapter.OnItemClick() {
            @Override
            public void onItemClick(int position) {
                iPresenter.changer(list.get(position).getId());
            }
        });
    }

    @Override
    public void sound(SoundBean wechatLoginBean) {
        list.clear();
        List<SoundBean.ResultBean> result = wechatLoginBean.getResult();
        list.addAll(result);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void changer(ChangerBean iSound) {
        iPresenter.sound(1, 150);
        iPresenter.sum();
    }

    @Override
    public void sum(SumBean wechatLoginBean) {
        soundNoRead.setText("("+wechatLoginBean.getCount()+"条未读)");
    }
    @OnClick(R.id.sound_finish)
    public void onViewClicked() {
        finish();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        iPresenter.Desetory();
        iPresenter =null;
    }
}

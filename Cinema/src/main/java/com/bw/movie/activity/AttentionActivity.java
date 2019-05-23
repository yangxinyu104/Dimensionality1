package com.bw.movie.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.bw.movie.R;
import com.bw.movie.adapter.AttentionCinemaAdapter;
import com.bw.movie.adapter.AttentionFilmAdapter;
import com.bw.movie.bean.AttentionCinemaBean;
import com.bw.movie.bean.AttentionFilmBean;
import com.bw.movie.contract.ContractInterFace;
import com.bw.movie.presenter.MyPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AttentionActivity extends AppCompatActivity implements ContractInterFace.IAttentionAll {

    @BindView(R.id.attention_film)
    RadioButton attentionFilm;
    @BindView(R.id.attention_cinema)
    RadioButton attentionCinema;
    @BindView(R.id.search_RadioGroup)
    RadioGroup searchRadioGroup;
    @BindView(R.id.attention_RecyclerView)
    RecyclerView attentionRecyclerView;
    @BindView(R.id.attention_finish)
    ImageView attentionFinish;
    private ContractInterFace.IPresenter iPresenter;
    List<AttentionFilmBean.ResultBean> FilmList = new ArrayList<>();
    List<AttentionCinemaBean.ResultBean> CinemaList = new ArrayList<>();
    private AttentionFilmAdapter attentionFilmAdapter;
    private AttentionCinemaAdapter attentionCinemaAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attention);
        ButterKnife.bind(this);
        iPresenter = new MyPresenter<>(this);
        iPresenter.attentionFilm(1,10);
    }

    @OnClick({R.id.attention_film, R.id.attention_cinema, R.id.attention_finish})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.attention_film:
                attentionFilm.setTextColor(Color.WHITE);
                attentionCinema.setTextColor(Color.BLACK);
                iPresenter.attentionFilm(1,10);
                attentionRecyclerView.setLayoutManager(new LinearLayoutManager(this));
                attentionFilmAdapter = new AttentionFilmAdapter(FilmList,this);
                attentionRecyclerView.setAdapter(attentionFilmAdapter);
                break;
            case R.id.attention_cinema:
                attentionCinema.setTextColor(Color.WHITE);
                attentionFilm.setTextColor(Color.BLACK);
                iPresenter.attentionCinema(1,10);
                attentionRecyclerView.setLayoutManager(new LinearLayoutManager(this));
                attentionCinemaAdapter = new AttentionCinemaAdapter(CinemaList,this);
                attentionRecyclerView.setAdapter(attentionCinemaAdapter);
                break;
            case R.id.attention_finish:
                finish();
                break;
        }
    }

    @Override
    public void attentionFilm(AttentionFilmBean wechatLoginBean) {
        CinemaList.clear();
        Log.e("tag","AttentionFilmBean   : " +  wechatLoginBean.getResult().size());
        List<AttentionFilmBean.ResultBean> result = wechatLoginBean.getResult();
        FilmList.addAll(result);
        attentionRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        attentionFilmAdapter = new AttentionFilmAdapter(FilmList,this);
        attentionRecyclerView.setAdapter(attentionFilmAdapter);
       // attentionCinemaAdapter.notifyDataSetChanged();
    }

    @Override
    public void attentionCinema(AttentionCinemaBean wechatLoginBean) {
        FilmList.clear();
        Log.e("tag","AttentionCinemaBean   : " +  wechatLoginBean.getResult().size());
        List<AttentionCinemaBean.ResultBean> result = wechatLoginBean.getResult();
        CinemaList.addAll(result);
        attentionFilmAdapter.notifyDataSetChanged();
        attentionCinemaAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        iPresenter.Desetory();
        iPresenter =null;
    }
}

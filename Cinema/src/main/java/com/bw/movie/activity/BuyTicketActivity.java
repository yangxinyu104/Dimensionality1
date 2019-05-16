package com.bw.movie.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.adapter.CinemaRecyclerAdapter;
import com.bw.movie.bean.CinemaBannerBean;
import com.bw.movie.bean.CinemafjBean;
import com.bw.movie.bean.CinematjBean;
import com.bw.movie.bean.CinemaxqBean;
import com.bw.movie.ghb.CinemaPresenter;
import com.bw.movie.ghb.IMainView;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;
import recycler.coverflow.RecyclerCoverFlow;

/**
 * Project name：Dimensionality1
 * Time: 2019/5/15 15:38
 * Author: 高海波
 */

public class BuyTicketActivity extends AppCompatActivity implements IMainView {

    @BindView(R.id.cinema_cinemalogo)
    SimpleDraweeView cinemaCinemalogo;
    @BindView(R.id.cinema_cinemaname)
    TextView cinemaCinemaname;
    @BindView(R.id.cinema_cinemaaddress)
    TextView cinemaCinemaaddress;
    @BindView(R.id.xin_id)
    LinearLayout xinId;
    @BindView(R.id.Layout_of_cinema)
    LinearLayout LayoutOfCinema;
    @BindView(R.id.cinema_addres)
    ImageView cinemaAddres;
    @BindView(R.id.cinema_movie_coverflow)
    RecyclerCoverFlow cinemaMovieCoverflow;
    @BindView(R.id.proBars)
    SeekBar proBars;
    @BindView(R.id.ssc_id)
    LinearLayout sscId;
    @BindView(R.id.recy_hot_movie)
    RecyclerView recyHotMovie;
    @BindView(R.id.movieByCinema_finish)
    ImageView movieByCinemaFinish;
    private CinemaPresenter cinemaPresenter;
    private String id;
    private CinemaRecyclerAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cinema__banner_ticket);
        ButterKnife.bind(this);

        id = getIntent().getStringExtra("id" );

        Toast.makeText(this, id, Toast.LENGTH_LONG).show();
        cinemaCinemalogo.setImageURI(getIntent().getStringExtra("logo"));
        cinemaCinemaname.setText(getIntent().getStringExtra("name"));
        cinemaCinemaaddress.setText(getIntent().getStringExtra("address"));

        cinemaPresenter = new CinemaPresenter();
        cinemaPresenter.setView(this);
        cinemaPresenter.loadBanner(id);
    }

    @Override
    public void onSuccess(CinematjBean cinematjBean) {

    }

    @Override
    public void onError(String errMessage) {

    }

    @Override
    public void onSuccess(CinemafjBean cinemafjBean) {

    }

    @Override
    public void onErrorfj(String errMessage) {

    }

    @Override
    public void onBanner(CinemaBannerBean cinemaBanner) {
        mAdapter = new CinemaRecyclerAdapter(this, cinemaBanner.getResult());
        cinemaMovieCoverflow.setAdapter(mAdapter);
        //让轮播图显示中间的图片
        cinemaMovieCoverflow.smoothScrollToPosition(2);
    }

    @Override
    public void onErrorbanner(String errMessage) {

    }

    @Override
    public void onCinemaxq(CinemaxqBean cinemaxqBean) {

    }

    @Override
    public void onErrorxq(String errMessage) {

    }


}

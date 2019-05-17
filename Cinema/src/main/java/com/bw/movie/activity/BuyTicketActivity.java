package com.bw.movie.activity;

import android.annotation.SuppressLint;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.adapter.CinemaPingAdapter;
import com.bw.movie.adapter.CinemaRecyclerAdapter;
import com.bw.movie.bean.CinemaBannerBean;
import com.bw.movie.bean.CinemafjBean;
import com.bw.movie.bean.CinemaplBean;
import com.bw.movie.bean.CinematjBean;
import com.bw.movie.bean.CinemaxqBean;

import com.bw.movie.ghb.CinemaPresenter;
import com.bw.movie.ghb.IMainView;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
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
    private CinemaRecyclerAdapter mAdapter;
    private TextView details_name;
    private TextView details_phone;
    private TextView details_metro;
    private RecyclerView evaluate_recycler;
    private PopupWindow popupWindow;
    private int id;
    private TextView ditie;
    private CinemaxqBean cinemaxqBean1;

    @SuppressLint("CommitTransaction")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cinema__banner_ticket);
        ButterKnife.bind(this);

        id = getIntent().getExtras().getInt("id");

        cinemaCinemalogo.setImageURI(getIntent().getStringExtra("logo"));
        cinemaCinemaname.setText(getIntent().getStringExtra("name"));
        cinemaCinemaaddress.setText(getIntent().getStringExtra("address"));

        cinemaPresenter = new CinemaPresenter();
        cinemaPresenter.setView(this);
        cinemaPresenter.loadBanner(id+"");
        cinemaPresenter.loadNexDataxq(id);
        cinemaAddres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopwindow();
                details_name.setText(cinemaxqBean1.getResult().getName());
                details_phone.setText(cinemaxqBean1.getResult().getPhone());
                details_metro.setText(cinemaxqBean1.getResult().getVehicleRoute());

            }
        });
    }

    @OnClick(R.id.movieByCinema_finish)
    public void onViewClicked() {
        finish();
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

        if (cinemaBanner== null) {

            Toast.makeText(this, "该影院没有电影", Toast.LENGTH_LONG).show();
        } else {
            mAdapter = new CinemaRecyclerAdapter(this, cinemaBanner.getResult());
            cinemaMovieCoverflow.setAdapter(mAdapter);
            //让轮播图显示中间的图片
            cinemaMovieCoverflow.smoothScrollToPosition(2);
        }
    }

    @Override
    public void onErrorbanner(String errMessage) {

    }

    @Override
    public void onCinemaxq(CinemaxqBean cinemaxqBean) {
        cinemaxqBean1 = cinemaxqBean;


    }

    @Override
    public void onErrorxq(String errMessage) {

    }

    @Override
    public void onCinemapl(CinemaplBean cinemaplBean) {

        List<CinemaplBean.ResultBean> result = cinemaplBean.getResult();
        CinemaPingAdapter cinemaPing = new CinemaPingAdapter(result, BuyTicketActivity.this);
        evaluate_recycler.setLayoutManager(new LinearLayoutManager(this));
        evaluate_recycler.setAdapter(cinemaPing);
    }

    @Override
    public void onErrorpl(String errMessage) {

    }

    private void showPopwindow() {
        View v = View.inflate(this, R.layout.cinema_details_popupwindow, null);
        details_name = v.findViewById(R.id.details_name);
        details_phone = v.findViewById(R.id.details_phone);
        ditie = v.findViewById(R.id.ditie);
        details_metro = v.findViewById(R.id.details_metro);
        ditie.setText("公交路线:");
        TextView evaluate = v.findViewById(R.id.evaluatess);//评价按钮
        TextView details = v.findViewById(R.id.detailsss);//详情按钮
        final View details_view = v.findViewById(R.id.details_view);//详情view线
        final View evaluate_view = v.findViewById(R.id.evaluate_view);//评价view线
      final RelativeLayout details_details = v.findViewById(R.id.details_details);//布局
        //布局
        evaluate_recycler = v.findViewById(R.id.evaluate_recycler);


        //评价点击事件
        evaluate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                details_view.setVisibility(View.GONE);
                evaluate_view.setVisibility(View.VISIBLE);
                details_details.setVisibility(View.GONE);
                evaluate_recycler.setVisibility(View.VISIBLE);
                //p层
                cinemaPresenter.loadNexDatapl(id,1,10);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(BuyTicketActivity.this);
                linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
                evaluate_recycler.setLayoutManager(linearLayoutManager);

            }
        });
        //详情点击事件
        details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                details_view.setVisibility(View.VISIBLE);
                evaluate_view.setVisibility(View.GONE);
                details_details.setVisibility(View.VISIBLE);
                evaluate_recycler.setVisibility(View.GONE);


            }
        });
        v.findViewById(R.id.request).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });


        popupWindow = new PopupWindow(v, ViewGroup.LayoutParams.MATCH_PARENT, 777, true);

        popupWindow.setFocusable(true);
        popupWindow.setTouchable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.showAtLocation(v, Gravity.BOTTOM, 0, 0);
    }
}

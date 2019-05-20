package com.bw.movie.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
import com.bw.movie.adapter.TicketAdapter;
import com.bw.movie.adapter.TicketAdapters;
import com.bw.movie.app.MyApplication;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.bean.CinemaBannerBean;
import com.bw.movie.bean.CinemafjBean;
import com.bw.movie.bean.CinemaplBean;
import com.bw.movie.bean.CinematjBean;
import com.bw.movie.bean.CinemaxqBean;

import com.bw.movie.bean.ScheduleBean;
import com.bw.movie.contract.ContractInterFace;
import com.bw.movie.ghb.CinemaPresenter;
import com.bw.movie.ghb.IMainView;
import com.bw.movie.presenter.MyPresenter;
import com.facebook.common.activitylistener.BaseActivityListener;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
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

public class BuyTicketActivity extends BaseActivity implements ContractInterFace.IBuyTicket,ContractInterFace.ITicket {

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


    private ContractInterFace.IPresenter cinemaPresenter;
    private CinemaRecyclerAdapter mAdapter;
    private TextView details_name;
    private TextView details_phone;
    private TextView details_metro;
    private int id;
    private PopupWindow popupWindow1;
    private RelativeLayout details_relativeLayout;
    private RelativeLayout details_details;
    private RecyclerView evaluate_recycler1;
    private TicketAdapters ticketAdapters;
    private String stringExtra;
    private String address;
    List<CinemaBannerBean.ResultBean> beanList = new ArrayList<>();
    @SuppressLint("CommitTransaction")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cinema__banner_ticket);
        ButterKnife.bind(this);
        id = getIntent().getIntExtra("id",0);
        stringExtra = getIntent().getStringExtra("name");
        cinemaCinemalogo.setImageURI(getIntent().getStringExtra("logo"));
        cinemaCinemaname.setText(stringExtra);
        address = getIntent().getStringExtra("address");
        cinemaCinemaaddress.setText(address);
        cinemaPresenter = new MyPresenter<>(this);
        cinemaPresenter.banners(id);

        if (beanList== null) {
            Toast.makeText(this, "该影院没有电影", Toast.LENGTH_LONG).show();
        } else {
            mAdapter = new CinemaRecyclerAdapter(this, beanList);
            cinemaMovieCoverflow.setAdapter(mAdapter);
            //让轮播图显示中间的图片
            cinemaMovieCoverflow.smoothScrollToPosition(1);
            mAdapter.setOnItemClick(new CinemaRecyclerAdapter.OnItemClick() {
                @Override
                public void onItemClick(int position) {
                    cinemaMovieCoverflow.smoothScrollToPosition(position);
                    cinemaPresenter.infos(beanList.get(position).getId());
                    cinemaPresenter.schedule(id,beanList.get(position).getId());
                    MyApplication.FilmNames = beanList.get(position).getName();

                }
            });
        }

    }

    @OnClick(R.id.movieByCinema_finish)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void banners(final CinemaBannerBean wechatLoginBean) {
        List<CinemaBannerBean.ResultBean> result = wechatLoginBean.getResult();
        beanList.addAll(result);
        mAdapter.notifyDataSetChanged();
        cinemaPresenter.schedule(id,beanList.get(1).getId());
        MyApplication.FilmNames = wechatLoginBean.getResult().get(1).getName();
    }

    @Override
    public void infos(CinemaxqBean wechatLoginBean) {
        View  view_xsq = View.inflate(this, R.layout.cinema_details_popupwindow, null);
        popupWindow1 = new PopupWindow(view_xsq,LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        details_name = view_xsq.findViewById(R.id.details_name);
        details_phone = view_xsq.findViewById(R.id.details_phone);
        ImageView request  = view_xsq.findViewById(R.id.request);
        details_metro = view_xsq.findViewById(R.id.details_metro);
        details_relativeLayout = view_xsq.findViewById(R.id.details_RelativeLayout);
        //布局
        details_details = view_xsq.findViewById(R.id.details_details);
        evaluate_recycler1 = view_xsq.findViewById(R.id.evaluate_recycler);
        TextView evaluatess = view_xsq.findViewById(R.id.evaluatess);
        TextView detailsss = view_xsq.findViewById(R.id.detailsss);
        detailsss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                details_relativeLayout.setVisibility(View.VISIBLE);
                evaluate_recycler1.setVisibility(View.GONE);
            }
        });
        evaluatess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cinemaPresenter.ping(id,1,10);
                details_relativeLayout.setVisibility(View.GONE);
                evaluate_recycler1.setVisibility(View.VISIBLE);


            }
        });
        request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow1.dismiss();
            }
        });
        details_metro.setText(wechatLoginBean.getResult().getVehicleRoute());
        details_name.setText(wechatLoginBean.getResult().getName());
        details_phone.setText(wechatLoginBean.getResult().getPhone());
        popupWindow1.setBackgroundDrawable(new BitmapDrawable());
        popupWindow1.setOutsideTouchable(true);
        popupWindow1.setAnimationStyle(R.style.popwin_anim_style);
        popupWindow1.showAtLocation(view_xsq,Gravity.CENTER_HORIZONTAL,0,100);

    }

    @Override
    public void ping(CinemaplBean wechatLoginBean) {
        List<CinemaplBean.ResultBean> result = wechatLoginBean.getResult();
        CinemaPingAdapter cinemaPing = new CinemaPingAdapter(result, BuyTicketActivity.this);
        evaluate_recycler1.setLayoutManager(new LinearLayoutManager(this));
        evaluate_recycler1.setAdapter(cinemaPing);
    }

    @Override
    public void schedule(final ScheduleBean scheduleBean) {
       ;
        recyHotMovie.setLayoutManager(new LinearLayoutManager(this));
        ticketAdapters = new TicketAdapters(scheduleBean.getResult(),this);
        recyHotMovie.setAdapter(ticketAdapters);
        ticketAdapters.setOnItemClick(new TicketAdapters.OnItemClick() {
            @Override
            public void onItemClick(int position) {
                Intent intent1 = new Intent(BuyTicketActivity.this,SeatActivity.class);
                intent1.putExtra("cinemaName",stringExtra);
                intent1.putExtra("cinemaAddress",address);
                intent1.putExtra("filmName", MyApplication.FilmNames);
                intent1.putExtra("startTime",scheduleBean.getResult().get(position).getBeginTime());
                intent1.putExtra("endTime",scheduleBean.getResult().get(position).getEndTime());
                intent1.putExtra("TicketName",scheduleBean.getResult().get(position).getScreeningHall());
                intent1.putExtra("seatsTotal", scheduleBean.getResult().get(position).getSeatsTotal());
                MyApplication.price = scheduleBean.getResult().get(position).getPrice();
                MyApplication.scheduleId = scheduleBean.getResult().get(position).getStatus();
                startActivity(intent1);

            }
        });

    }
}

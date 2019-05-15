package com.bw.movie.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.adapter.TicketAdapter;
import com.bw.movie.app.MyApplication;
import com.bw.movie.bean.ScheduleBean;
import com.bw.movie.contract.ContractInterFace;
import com.bw.movie.presenter.MyPresenter;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TicketActivity extends AppCompatActivity implements ContractInterFace.ITicket {

    @BindView(R.id.ticket_name)
    TextView ticketName;
    @BindView(R.id.ticket_address)
    TextView ticketAddress;
    @BindView(R.id.film_cinema_finish)
    ImageView filmCinemaFinish;
    @BindView(R.id.ticket_SimpleDraweeView)
    SimpleDraweeView ticketSimpleDraweeView;
    @BindView(R.id.ticket_filmName)
    TextView ticketFilmName;
    @BindView(R.id.ticket_lx)
    TextView ticketLx;
    @BindView(R.id.ticket_dy)
    TextView ticketDy;
    @BindView(R.id.ticket_sc)
    TextView ticketSc;
    @BindView(R.id.ticket_cd)
    TextView ticketCd;
    @BindView(R.id.ticket_RecyclerView)
    RecyclerView ticketRecyclerView;
    List<ScheduleBean.ResultBean> list  = new ArrayList<>();
    private TicketAdapter adapter;
    private String name;
    private String address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket);
        ButterKnife.bind(this);
        final Intent intent = getIntent();
        name = intent.getStringExtra("name");
        address = intent.getStringExtra("address");
        ticketName.setText(name);
        ticketAddress.setText(address);
        ticketSimpleDraweeView.setImageURI(MyApplication.resultBean.getImageUrl());
        ticketFilmName.setText(MyApplication.resultBean.getName());
        ticketLx.setText(MyApplication.resultBean.getMovieTypes());
        ticketDy.setText(MyApplication.resultBean.getDirector());
        ticketSc.setText(MyApplication.resultBean.getDuration());
        ticketCd.setText(MyApplication.resultBean.getPlaceOrigin());
        ContractInterFace.IPresenter iPresenter = new MyPresenter<>(this);
        iPresenter.schedule(MyApplication.cinemaId,MyApplication.movieId);

        ticketRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new TicketAdapter(list,this);
        ticketRecyclerView.setAdapter(adapter);
        adapter.setOnItemClick(new TicketAdapter.OnItemClick() {
            @Override
            public void onItemClick(int position) {
                Intent intent1 = new Intent(TicketActivity.this,SeatActivity.class);
                intent1.putExtra("cinemaName",name);
                intent1.putExtra("cinemaAddress",address);
                intent1.putExtra("filmName",MyApplication.resultBean.getName());
                intent1.putExtra("startTime",list.get(position).getBeginTime());
                intent1.putExtra("endTime",list.get(position).getEndTime());
                intent1.putExtra("TicketName",list.get(position).getScreeningHall());
                intent1.putExtra("seatsTotal",list.get(position).getSeatsTotal());
                intent1.putExtra("seatsUseCount",list.get(position).getSeatsUseCount());
                startActivity(intent1);

            }
        });
    }

    @OnClick(R.id.film_cinema_finish)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void schedule(ScheduleBean scheduleBean) {
        List<ScheduleBean.ResultBean> result = scheduleBean.getResult();
        list.addAll(result);
        adapter.notifyDataSetChanged();
    }
}

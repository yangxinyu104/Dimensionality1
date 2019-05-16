package com.bw.movie.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.adapter.FilmCinemaAdapter;
import com.bw.movie.app.MyApplication;
import com.bw.movie.bean.FilmCinemaBean;
import com.bw.movie.bean.GreatBean;
import com.bw.movie.contract.ContractInterFace;
import com.bw.movie.presenter.MyPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FilmCinemaActivity extends AppCompatActivity implements ContractInterFace.IFilmCinema {

    @BindView(R.id.film_cinema_name)
    TextView filmCinemaName;
    @BindView(R.id.film_cinema_RecyclerView)
    RecyclerView filmCinemaRecyclerView;
    @BindView(R.id.film_cinema_finish)
    ImageView filmCinemaFinish;
    List<FilmCinemaBean.ResultBean> list = new ArrayList<>();
    private FilmCinemaAdapter adapter;
    public ContractInterFace.IPresenter iPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_cinema);
        ButterKnife.bind(this);
        filmCinemaName.setText(MyApplication.FilmName);
        iPresenter = new MyPresenter<>(this);
        iPresenter.filmcinema(MyApplication.movieId);
        filmCinemaRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new FilmCinemaAdapter(list,this);
        filmCinemaRecyclerView.setAdapter(adapter);
        adapter.setOnItemClick(new FilmCinemaAdapter.OnItemClick() {
            @Override
            public void onItemClick(int position) {
                MyApplication.cinemaId = list.get(position).getId();
                Intent intent = new Intent(FilmCinemaActivity.this,TicketActivity.class);
                intent.putExtra("name",list.get(position).getName());
                intent.putExtra("address",list.get(position).getAddress());
                startActivity(intent);
            }
        });


    }

    @OnClick(R.id.film_cinema_finish)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void filmcinema(FilmCinemaBean filmCinemaBean) {
        list.clear();
        List<FilmCinemaBean.ResultBean> result = filmCinemaBean.getResult();
       // Toast.makeText(this, "filmCinemaBean.getResult().size():" + filmCinemaBean.getResult().size(), Toast.LENGTH_SHORT).show();
        list.addAll(result);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void followCinema(GreatBean greatBean) {
        Toast.makeText(this, greatBean.getMessage(), Toast.LENGTH_SHORT).show();
        //iPresenter.filmcinema(MyApplication.movieId);
    }

    @Override
    public void noFollowCinema(GreatBean greatBean) {
        Toast.makeText(this, greatBean.getMessage(), Toast.LENGTH_SHORT).show();
       // iPresenter.filmcinema(MyApplication.movieId);
    }
}

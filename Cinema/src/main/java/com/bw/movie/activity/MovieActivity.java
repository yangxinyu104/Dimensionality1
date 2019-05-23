package com.bw.movie.activity;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bw.movie.R;
import com.bw.movie.adapter.ViewPagerAdapter;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.fragment.CinemaFragment;
import com.bw.movie.fragment.FilmFragment;
import com.bw.movie.fragment.MyFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MovieActivity extends AppCompatActivity {

    @BindView(R.id.movie_ViewPager)
    ViewPager movieViewPager;
    @BindView(R.id.movie_film)
    ImageView movieFilm;
    @BindView(R.id.movie_cinema)
    ImageView movieCinema;
    @BindView(R.id.movie_my)
    ImageView movieMy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        ButterKnife.bind(this);
        List<Fragment> list = initList();
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), list);
        movieViewPager.setAdapter(adapter);

    }

    private List<Fragment> initList() {
        List<Fragment> list = new ArrayList<>();
        list.add(new FilmFragment());
        list.add(new CinemaFragment());
        list.add(new MyFragment());
        return list;
    }

    @OnClick({R.id.movie_film, R.id.movie_cinema, R.id.movie_my})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.movie_film:
                movieViewPager.setCurrentItem(0);
                movieFilm.setImageResource(R.mipmap.com_icon_film_selected);
                movieCinema.setImageResource(R.mipmap.com_icon_cinema_default);
                movieMy.setImageResource(R.mipmap.com_icon_my_default);
                break;
            case R.id.movie_cinema:
                movieViewPager.setCurrentItem(1);
                movieFilm.setImageResource(R.mipmap.com_icon_film_fault);
                movieCinema.setImageResource(R.mipmap.com_icon_cinema_selected);
                movieMy.setImageResource(R.mipmap.com_icon_my_default);
                break;
            case R.id.movie_my:
                movieViewPager.setCurrentItem(2);
                movieFilm.setImageResource(R.mipmap.com_icon_film_fault);
                movieCinema.setImageResource(R.mipmap.com_icon_cinema_default);
                movieMy.setImageResource(R.mipmap.com_icon_my_selected);
                break;
        }
    }
}

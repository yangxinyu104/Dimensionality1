package com.bw.movie.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.adapter.SearchBeonAdapter;
import com.bw.movie.adapter.SearchHotAdapter;
import com.bw.movie.adapter.SearchShowingAdapter;
import com.bw.movie.app.MyApplication;
import com.bw.movie.app.MyViews;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.bean.AttentionBean;
import com.bw.movie.bean.BeonBean;
import com.bw.movie.bean.PopularMovieBean;
import com.bw.movie.bean.ShowingBean;
import com.bw.movie.contract.ContractInterFace;
import com.bw.movie.presenter.MyPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchActivity extends BaseActivity implements ContractInterFace.ISearchFilm, ContractInterFace.IFilmHome {
    @BindView(R.id.search_hot)
    RadioButton searchHot;
    @BindView(R.id.search_showing)
    RadioButton searchShowing;
    @BindView(R.id.search_beon)
    RadioButton searchBeon;
    @BindView(R.id.search_RadioGroup)
    RadioGroup searchRadioGroup;
    @BindView(R.id.search_RecyclerView)
    RecyclerView searchRecyclerView;
    @BindView(R.id.search_finish)
    ImageView searchFinish;
    public ContractInterFace.IPresenter iPresenter;
    List<BeonBean.ResultBean> Beonlist = new ArrayList<>();
    List<ShowingBean.ResultBean> Showinglist = new ArrayList<>();
    List<PopularMovieBean.ResultBean> Hotlist = new ArrayList<>();
    @BindView(R.id.search_MyViews)
    MyViews searchMyViews;
    private SearchHotAdapter hotadapter;
    private SearchShowingAdapter showingadapter;
    private SearchBeonAdapter beonadapters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        iPresenter = new MyPresenter<>(this);
        searchHot.setTextColor(Color.WHITE);
        iPresenter.popularMovie();
        HotList();
        searchMyViews.locations.setText(MyApplication.City);
    }

    private void HotList() {
        searchRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        hotadapter = new SearchHotAdapter(Hotlist, this);
        searchRecyclerView.setAdapter(hotadapter);
        hotadapter.setOnItemClick(new SearchHotAdapter.OnItemClick() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(SearchActivity.this, DetailsActivity.class);
                MyApplication.flag = Hotlist.get(position).getFollowMovie();
                intent.putExtra("aid", Hotlist.get(position).getId());
                startActivity(intent);
            }
        });
    }

    @OnClick({R.id.search_hot, R.id.search_showing, R.id.search_beon})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.search_hot:
                iPresenter.popularMovie();
                searchHot.setTextColor(Color.WHITE);
                searchShowing.setTextColor(Color.BLACK);
                searchBeon.setTextColor(Color.BLACK);
                HotList();
                break;
            case R.id.search_showing:
                iPresenter.showing();
                searchShowing.setTextColor(Color.WHITE);
                searchBeon.setTextColor(Color.BLACK);
                searchHot.setTextColor(Color.BLACK);
                showingadapter = new SearchShowingAdapter(Showinglist, this);
                searchRecyclerView.setAdapter(showingadapter);
                showingadapter.setOnItemClick(new SearchShowingAdapter.OnItemClick() {
                    @Override
                    public void onItemClick(int position) {
                        Intent intent = new Intent(SearchActivity.this, DetailsActivity.class);
                        MyApplication.flag = Showinglist.get(position).getFollowMovie();
                        intent.putExtra("aid", Showinglist.get(position).getId());
                        startActivity(intent);
                    }
                });
                break;
            case R.id.search_beon:
                iPresenter.beon();
                searchShowing.setTextColor(Color.BLACK);
                searchHot.setTextColor(Color.BLACK);
                searchBeon.setTextColor(Color.WHITE);
                beonadapters = new SearchBeonAdapter(Beonlist, this);
                searchRecyclerView.setAdapter(beonadapters);
                beonadapters.setOnItemClick(new SearchBeonAdapter.OnItemClick() {
                    @Override
                    public void onItemClick(int position) {
                        Intent intent = new Intent(SearchActivity.this, DetailsActivity.class);
                        MyApplication.flag = Beonlist.get(position).getFollowMovie();
                        intent.putExtra("aid", Beonlist.get(position).getId());
                        startActivity(intent);
                    }
                });
                break;
        }
    }

    @OnClick(R.id.search_finish)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void attention(AttentionBean attentionBean) {
        Toast.makeText(this, attentionBean.getMessage(), Toast.LENGTH_SHORT).show();
        if (MyApplication.Zanflag == 1) {
            iPresenter.popularMovie();
        } else if (MyApplication.Zanflag == 2) {
            iPresenter.showing();
        } else if (MyApplication.Zanflag == 3) {
            iPresenter.beon();
        }
    }

    @Override
    public void noattention(AttentionBean attentionBean) {
        Toast.makeText(this, attentionBean.getMessage(), Toast.LENGTH_SHORT).show();
        if (MyApplication.Zanflag == 1) {
            iPresenter.popularMovie();
        } else if (MyApplication.Zanflag == 2) {
            iPresenter.showing();
        } else if (MyApplication.Zanflag == 3) {
            iPresenter.beon();
        }
    }

    @Override
    public void popularMovie(PopularMovieBean popularMovieBean) {
        Hotlist.clear();
        List<PopularMovieBean.ResultBean> result = popularMovieBean.getResult();
        Hotlist.addAll(result);
        hotadapter.notifyDataSetChanged();

    }

    @Override
    public void showing(ShowingBean showingBean) {
        Showinglist.clear();
        List<ShowingBean.ResultBean> result = showingBean.getResult();
        Showinglist.addAll(result);
        showingadapter.notifyDataSetChanged();
    }

    @Override
    public void beon(BeonBean beonBean) {
        Beonlist.clear();
        List<BeonBean.ResultBean> result = beonBean.getResult();
        Beonlist.addAll(result);
        beonadapters.notifyDataSetChanged();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        iPresenter.Desetory();
        iPresenter =null;
    }

}

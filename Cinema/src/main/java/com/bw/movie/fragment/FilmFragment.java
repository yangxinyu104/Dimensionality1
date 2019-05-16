package com.bw.movie.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.bw.movie.R;
import com.bw.movie.activity.SearchActivity;
import com.bw.movie.adapter.BeonRecyclerViewAdapter;
import com.bw.movie.adapter.FilmRecyclerAdapter;
import com.bw.movie.adapter.HotRecyclerViewAdapter;
import com.bw.movie.adapter.ShowingRecyclerViewAdapter;
import com.bw.movie.app.MyViews;
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
import butterknife.Unbinder;
import recycler.coverflow.RecyclerCoverFlow;

/**
 * @Author：Y
 * @E-mail： 2447892835@qq.com
 * @Date：2019.5.11 17:26
 * @Description：YangXinYu
 */
public class FilmFragment extends Fragment implements ContractInterFace.IFilmHome {

    Unbinder unbinder;
    @BindView(R.id.film_RecyclerCoverFlow)
    RecyclerCoverFlow filmRecyclerCoverFlow;
    @BindView(R.id.view_crnema)
    MyViews viewCrnema;
    List<BeonBean.ResultBean> Beonlist = new ArrayList<>();
    List<ShowingBean.ResultBean> Showinglist = new ArrayList<>();
    List<PopularMovieBean.ResultBean> Hotlist = new ArrayList<>();
    @BindView(R.id.film_hot_RelativeLayout)
    RelativeLayout filmHotRelativeLayout;
    @BindView(R.id.film_hot_RecyclerView)
    RecyclerView filmHotRecyclerView;
    @BindView(R.id.film_showing_RelativeLayout)
    RelativeLayout filmShowingRelativeLayout;
    @BindView(R.id.film_showing_RecyclerView)
    RecyclerView filmShowingRecyclerView;
    @BindView(R.id.film_beon_RelativeLayout)
    RelativeLayout filmBeonRelativeLayout;
    @BindView(R.id.film_beon_RecyclerView)
    RecyclerView filmBeonRecyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_film, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ContractInterFace.IPresenter iPresenter = new MyPresenter<>(this);
        iPresenter.popularMovie();
        iPresenter.showing();
        iPresenter.beon();
        viewCrnema.bringToFront();
    }

    private void BeonFilm(List<BeonBean.ResultBean> result) {
        RecyclerView.LayoutManager BeonLayoutManager = new LinearLayoutManager(getContext());
        ((LinearLayoutManager) BeonLayoutManager).setOrientation(OrientationHelper.HORIZONTAL);
        filmBeonRecyclerView.setLayoutManager(BeonLayoutManager);
        BeonRecyclerViewAdapter adapter = new BeonRecyclerViewAdapter(result, getContext());
        filmBeonRecyclerView.setAdapter(adapter);
        adapter.setOnItemClick(new BeonRecyclerViewAdapter.OnItemClick() {
            @Override
            public void onItemClick(int position) {
                StartActivity();
            }
        });
    }

    private void ShowingFilm(List<ShowingBean.ResultBean> result) {
        RecyclerView.LayoutManager ShowingLayoutManager = new LinearLayoutManager(getContext());
        ((LinearLayoutManager) ShowingLayoutManager).setOrientation(OrientationHelper.HORIZONTAL);
        filmShowingRecyclerView.setLayoutManager(ShowingLayoutManager);
        ShowingRecyclerViewAdapter adapter = new ShowingRecyclerViewAdapter(result, getContext());
        filmShowingRecyclerView.setAdapter(adapter);
        adapter.setOnItemClick(new ShowingRecyclerViewAdapter.OnItemClick() {
            @Override
            public void onItemClick(int position) {
                StartActivity();
            }
        });
    }

    private void HotFilm(List<PopularMovieBean.ResultBean> result) {
        RecyclerView.LayoutManager HotLayoutManager = new LinearLayoutManager(getContext());
        ((LinearLayoutManager) HotLayoutManager).setOrientation(OrientationHelper.HORIZONTAL);
        filmHotRecyclerView.setLayoutManager(HotLayoutManager);
        HotRecyclerViewAdapter adapter = new HotRecyclerViewAdapter(result, getContext());
        filmHotRecyclerView.setAdapter(adapter);
        adapter.setOnItemClick(new HotRecyclerViewAdapter.OnItemClick() {
            @Override
            public void onItemClick(int position) {
                StartActivity();
            }
        });
    }

    private void Image(List<BeonBean.ResultBean> result) {
        FilmRecyclerAdapter adapter = new FilmRecyclerAdapter(this, result);
        filmRecyclerCoverFlow.setAdapter(adapter);
        //让轮播图显示中间的图片
        filmRecyclerCoverFlow.smoothScrollToPosition(2);
        //自定义接口回调，点击图片使它展示到中间
        adapter.setOnItemClick(new FilmRecyclerAdapter.OnItemClick() {
            @Override
            public void onItemClick(int position) {
                filmRecyclerCoverFlow.smoothScrollToPosition(position);
                StartActivity();
            }
        });
    }

    private void StartActivity() {
        Intent intent = new Intent(getActivity(),SearchActivity.class);
        getActivity().startActivity(intent);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void popularMovie(PopularMovieBean popularMovieBean) {
        Hotlist.clear();
        List<PopularMovieBean.ResultBean> result = popularMovieBean.getResult();
        Hotlist.addAll(result);
        HotFilm(popularMovieBean.getResult());
    }

    @Override
    public void showing(ShowingBean showingBean) {
        Showinglist.clear();
        List<ShowingBean.ResultBean> result = showingBean.getResult();
        Showinglist.addAll(result);
        ShowingFilm(showingBean.getResult());
    }

    @Override
    public void beon(BeonBean beonBean) {
        Beonlist.clear();
        List<BeonBean.ResultBean> result = beonBean.getResult();
        Beonlist.addAll(result);
        Image(beonBean.getResult());
        BeonFilm(beonBean.getResult());
    }

    @OnClick({R.id.film_hot_RelativeLayout, R.id.film_showing_RelativeLayout, R.id.film_beon_RelativeLayout})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.film_hot_RelativeLayout:
                StartActivity();
                break;
            case R.id.film_showing_RelativeLayout:
                StartActivity();
                break;
            case R.id.film_beon_RelativeLayout:
                StartActivity();
                break;
        }
    }
    }

package com.bw.movie.fragment.cinema;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bw.movie.R;
import com.bw.movie.adapter.CinemaAdapterfj;
import com.bw.movie.bean.CinemaBannerBean;
import com.bw.movie.bean.CinemafjBean;
import com.bw.movie.bean.CinematjBean;
import com.bw.movie.bean.CinemaxqBean;
import com.bw.movie.ghb.CinemaPresenter;
import com.bw.movie.ghb.IMainView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

/**
 * Project name：Dimensionality1
 * Time: 2019/5/14 11:39
 * Author: 高海波
 */
public class CinemaFJFragment extends Fragment implements IMainView {

    private int page=1;
    private int count=10;
    private CinemaPresenter cinemaPresenter;
    private XRecyclerView xRecyclerViewfj;
    private LinearLayoutManager managerfj;
    private CinemaAdapterfj adapterfj;
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getContext(), R.layout.cinema_fragfj, null);

        cinemaPresenter = new CinemaPresenter();
        cinemaPresenter.setView(this);
        cinemaPresenter.loadNetDatafj(page,count);

        initView();
        initData();

        return view;
    }

    private void initView() {
        xRecyclerViewfj = view.findViewById(R.id.X_RecyclerViewfj);
    }

    private void initData() {

        managerfj = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        xRecyclerViewfj.setLayoutManager(managerfj);

        //创建适配器
        adapterfj = new CinemaAdapterfj(getActivity());

        //设置适配器
        xRecyclerViewfj.setAdapter(adapterfj);

        xRecyclerViewfj.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        cinemaPresenter.loadNetDatafj(page,count);
                        xRecyclerViewfj.refreshComplete();
                    }
                },1000);
            }

            @Override
            public void onLoadMore() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        cinemaPresenter.loadNetDatafj(page,count);
                        xRecyclerViewfj.loadMoreComplete();
                    }
                },1000);
            }
        });

    }

    @Override
    public void onSuccess(CinemafjBean cinemafjBean) {
        //打印
        Log.e("fjMessage",cinemafjBean.toString());
        adapterfj.setArr(cinemafjBean.getResult());
    }

    Handler handler = new Handler();

    @Override
    public void onErrorfj(String errMessage) {

    }

    @Override
    public void onBanner(CinemaBannerBean cinemaBanner) {

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

    @Override
    public void onSuccess(CinematjBean cinematjBean) {

    }

    @Override
    public void onError(String errMessage) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        cinemaPresenter.detachView();
    }
}

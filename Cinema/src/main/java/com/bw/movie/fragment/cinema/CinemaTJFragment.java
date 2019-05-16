package com.bw.movie.fragment.cinema;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bw.movie.R;
import com.bw.movie.activity.BuyTicketActivity;
import com.bw.movie.adapter.CinemaAdaptertj;
import com.bw.movie.bean.CinemaBannerBean;
import com.bw.movie.bean.CinemafjBean;
import com.bw.movie.bean.CinematjBean;
import com.bw.movie.bean.CinemaxqBean;
import com.bw.movie.ghb.CinemaPresenter;
import com.bw.movie.ghb.IMainView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

/**
 * Project name：Dimensionality1
 * Time: 2019/5/14 11:15
 * Author: 高海波
 */
public class CinemaTJFragment extends Fragment implements IMainView {

    private CinemaPresenter cinemaPresenter;
    private int page=1;
    private int count=10;
    private CinemaAdaptertj adaptertj;
    private XRecyclerView mRecyclerView;
    private LinearLayoutManager manager;
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getContext(), R.layout.cinema_fragtj, null);

        cinemaPresenter = new CinemaPresenter();
        cinemaPresenter.setView(this);
        cinemaPresenter.loadNetData(page,count);

        //上下拉
        initView();
        initData();

        return view;
    }

    private void initView() {
        mRecyclerView = view.findViewById(R.id.X_RecyclerView);
    }

    private void initData() {

        //创建适配器
        adaptertj = new CinemaAdaptertj(getActivity());
        manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(OrientationHelper.VERTICAL);
        mRecyclerView.setLayoutManager(manager);

        //设置适配器
        mRecyclerView.setAdapter(adaptertj);

        //设置上下拉
        mRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        cinemaPresenter.loadNetData(page,count);
                        mRecyclerView.refreshComplete();
                    }
                },1000);
            }

            @Override
            public void onLoadMore() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        cinemaPresenter.loadNetData(page,count);
                        mRecyclerView.loadMoreComplete();
                    }
                },1000);
            }

        });

        adaptertj.setOnItemClickListener(new CinemaAdaptertj.OnItemClickListener() {
            @Override
                public void onItemClick(String position, String image, String name, String address) {
                Intent intent = new Intent(getActivity(),BuyTicketActivity.class);
                intent.putExtra("id",position);
                intent.putExtra("logo",image);
                intent.putExtra("name",name);
                intent.putExtra("address",address);

                startActivity(intent);
            }
        });
    }


    @Override
    public void onSuccess(CinematjBean cinematjBean) {
        //打印
        Log.e("myMessage",cinematjBean.toString());
        adaptertj.setArr(cinematjBean.getResult());
    }

    Handler handler = new Handler();

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
    public void onDestroy() {
        super.onDestroy();
        cinemaPresenter.detachView();
    }
}

package com.bw.movie.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bw.movie.R;
import com.bw.movie.app.MyApplication;
import com.bw.movie.app.MyViews;
import com.bw.movie.bean.CinematjBean;
import com.bw.movie.contract.ContractInterFace;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * @Author：Y
 * @E-mail： 2447892835@qq.com
 * @Date：2019.5.11 17:26
 * @Description：YangXinYu
 */
public class CinemaFragment extends Fragment implements ContractInterFace.IRecommendCinema {

    @BindView(R.id.view_crnema)
    MyViews viewCrnema;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_cinema, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewCrnema.locations.setText(MyApplication.City);

    }

    @Override
    public void recommendCinema(CinematjBean cinematjBean) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}

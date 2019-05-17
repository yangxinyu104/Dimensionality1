package com.bw.movie.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.bw.movie.bean.CinemaxqBean;
import com.bw.movie.contract.ContractInterFace;
import com.bw.movie.presenter.MyPresenter;

/**
 * Project name：Dimensionality1
 * Time: 2019/5/15 19:29
 * Author: 高海波
 */

public class CinemaxqActivity extends AppCompatActivity implements ContractInterFace.IMessage {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ContractInterFace.IPresenter iPresenter = new MyPresenter<>(this);
        iPresenter.message(11);

    }

    @Override
    public void message(CinemaxqBean cinemaxqBean) {

    }
}

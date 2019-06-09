package com.example.mvp;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.example.OneFragment;
import com.example.ViewPagerAdapter;
import com.example.base.BaseActivity;
import com.example.bean.Login;
import com.example.contract.IContract;
import com.example.presenter.MyPresenter;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends  BaseActivity<IContract.LoginView, MyPresenter>
        implements IContract.LoginView {


    private ViewPager ccc;

    @Override
    protected  int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected  void findView() {
        ccc = findViewById(R.id.cccc);

    }

    @Override
    protected  void initData() {
        List<Fragment> list = new ArrayList<>();
        list.add(new OneFragment());
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(),list);
        ccc.setAdapter(adapter);
    }

    @Override
    protected  void setListener() {

    }

    @Override
    protected MyPresenter createPresenter() {
        return new MyPresenter();
    }

    @Override
    protected void getData() {

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){

        }

    }

    @Override
    public void onLoginSuccess(Login login) {

    }

    @Override
    public void onLoginFail(String errorInfo) {

    }
}

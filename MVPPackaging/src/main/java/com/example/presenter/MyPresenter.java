package com.example.presenter;

import com.example.base.BasePresenter;
import com.example.bean.Login;
import com.example.contract.IContract;
import com.example.model.MyModel;

/**
 * @Author：Y
 * @E-mail： 2447892835@qq.com
 * @Date：2019.6.9 8:28
 * @Description：YangXinYu
 */
public class MyPresenter extends BasePresenter<IContract.LoginView> {
    private MyModel model;

    public MyPresenter() {
        model = new MyModel();
    }


    /**
     * 登录
     *
     * @param name
     * @param password
     */
    public void login(String name, String password) {
        model.login(name, password, new IContract.LoginCallBack() {
            @Override
            public void onSuccess(Login login) {
                getView().onLoginSuccess(login);
            }

            @Override
            public void onFail(String errorInfo) {
                getView().onLoginFail(errorInfo);
            }
        });
    }



}

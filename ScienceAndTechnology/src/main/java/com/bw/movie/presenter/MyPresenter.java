package com.bw.movie.presenter;

import com.bw.movie.bean.LoginBean;
import com.bw.movie.bean.RegisterBean;
import com.bw.movie.contract.ContractInterFace;
import com.bw.movie.model.MyModel;

/**
 * @Author：Y
 * @E-mail： 2447892835@qq.com
 * @Date：2019.5.10 8:51
 * @Description：YangXinYu
 */
public class MyPresenter<T> implements ContractInterFace.IPresenter {


    T t;
    private final ContractInterFace.IModel iModel;

    public MyPresenter(T t) {
        this.t = t;
        iModel = new MyModel();
    }

    @Override
    public void login(String phone, String pwd) {
        iModel.login(phone, pwd, new MyModel.SetLogin() {
            @Override
            public void Succeed(LoginBean loginBean) {
                ContractInterFace.LoginIView loginIView = (ContractInterFace.LoginIView) t;
                loginIView.login(loginBean);
            }
        });
    }

    @Override
    public void register(String phone, String nickName, String pwd) {
        iModel.register(phone, nickName, pwd, new MyModel.SetRegister() {
            @Override
            public void Succeed(RegisterBean registerBean) {
                ContractInterFace.RegisterIView registerIView  = (ContractInterFace.RegisterIView) t;
                registerIView.register(registerBean);
            }
        });
    }
}

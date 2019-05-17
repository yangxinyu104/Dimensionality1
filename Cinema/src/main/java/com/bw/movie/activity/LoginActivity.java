package com.bw.movie.activity;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;
import com.tencent.mm.opensdk.modelmsg.SendAuth;

import com.bw.movie.app.MyApplication;
import com.bw.movie.bean.LoginBean;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.bw.movie.contract.ContractInterFace;
import com.bw.movie.presenter.MyPresenter;
import com.bw.movie.util.EncryptUtil;
import com.bw.movie.util.WeiXinUtil;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

public class LoginActivity extends AppCompatActivity implements ContractInterFace.ILogin {

    @BindView(R.id.phone_id)
    EditText phoneId;
    @BindView(R.id.pwd_id)
    EditText pwdId;
    @BindView(R.id.jzpwd_id)
    CheckBox jzpwdId;
    @BindView(R.id.quickly_id)
    TextView quicklyId;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.wxLogin)
    ImageView wxLogin;
    @BindView(R.id.login_Nointernet)
    LinearLayout loginNointernet;
    @BindView(R.id.login_Internet)
    LinearLayout loginInternet;
    private ContractInterFace.IPresenter iPresenter;
    private SharedPreferences sp;
    // APP_ID 替换为你的应用从官方网站申请到的合法appID
    private static final String APP_ID = "wxb3852e6a6b7d9516";

    // IWXAPI 是第三方app和微信通信的openApi接口
    private IWXAPI api;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        regToWx();

        if (MyApplication.isNetworkConnected(this)) {
            loginNointernet.setVisibility(View.GONE);
            loginInternet.setVisibility(View.VISIBLE);
        }else{
            loginNointernet.setVisibility(View.VISIBLE);
            loginInternet.setVisibility(View.GONE);
        }

        iPresenter = new MyPresenter<>(this);
        sp = getPreferences(MODE_PRIVATE);
        if (sp.getBoolean("flag", true) == true) {
            phoneId.setText(sp.getString("name", ""));
            pwdId.setText(sp.getString("pwds", ""));
            jzpwdId.setChecked(sp.getBoolean("flag", true));
        } else {
            phoneId.setText("");
            pwdId.setText("");
            jzpwdId.setChecked(false);
        }



    }
    private void regToWx() {
        // 通过WXAPIFactory工厂，获取IWXAPI的实例
        api = WXAPIFactory.createWXAPI(this, APP_ID, true);
        // 将应用的appId注册到微信
        api.registerApp(APP_ID);
    }

    @Override
    public void login(LoginBean loginBean) {
        if (loginBean.getMessage().equals("登陆成功")) {
            Toast.makeText(this, loginBean.getMessage(), Toast.LENGTH_LONG).show();
            MyApplication.UserId = loginBean.getResult().getUserId();
            MyApplication.SessionId = loginBean.getResult().getSessionId();
            MyApplication.HeadPic = loginBean.getResult().getUserInfo().getHeadPic();
            MyApplication.UserName = loginBean.getResult().getUserInfo().getNickName();
            MyApplication.phones = loginBean.getResult().getUserInfo().getPhone();
            MyApplication.birthday = loginBean.getResult().getUserInfo().getBirthday();
            MyApplication.Sex =  loginBean.getResult().getUserInfo().getSex();
            MyApplication.lastLoginTime =  loginBean.getResult().getUserInfo().getLastLoginTime();

            Log.e("tag", " SessionId :  " + MyApplication.SessionId);
            Log.e("tag", " UserId :  " + MyApplication.UserId);
            if (jzpwdId.isChecked()) {
                SharedPreferences.Editor edit = sp.edit();
                edit.putString("name", phoneId.getText().toString());
                edit.putString("pwds", pwdId.getText().toString());
                edit.putBoolean("flag", true);
                edit.commit();
            } else {
                SharedPreferences.Editor edit = sp.edit();
                edit.putString("name", "");
                edit.putString("pwds", "");
                edit.putBoolean("flag", false);
                edit.commit();
            }
            Intent intent = new Intent(LoginActivity.this, MovieActivity.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, loginBean.getMessage(), Toast.LENGTH_LONG).show();
        }

    }


    @OnClick({R.id.quickly_id, R.id.btn_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.quickly_id:
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_login:
                Log.e("tag", "view");
                //Toast.makeText(this, "111", Toast.LENGTH_LONG).show();
                String pwd = EncryptUtil.encrypt(pwdId.getText().toString());
                iPresenter.login(phoneId.getText().toString(), pwd);
                break;
        }
    }


    @OnClick(R.id.wxLogin)
    public void onViewClicked() {
        Log.e("tag","111111");
        SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "wechat_sdk_demo_test";
        api.sendReq(req);
        finish();


    }
}

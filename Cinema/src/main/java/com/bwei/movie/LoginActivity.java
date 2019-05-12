package com.bwei.movie;

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

import com.tencent.mm.opensdk.modelmsg.SendAuth;

import app.MyApplication;
import bean.LoginBean;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import contract.ContractInterFace;
import presenter.MyPresenter;
import util.EncryptUtil;
import util.WeiXinUtil;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

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

        if (Build.VERSION.SDK_INT >= 23) {
            String[] mPermissionList = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CALL_PHONE, Manifest.permission.READ_LOGS, Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.SET_DEBUG_APP, Manifest.permission.SYSTEM_ALERT_WINDOW, Manifest.permission.GET_ACCOUNTS, Manifest.permission.WRITE_APN_SETTINGS};
            ActivityCompat.requestPermissions(this, mPermissionList, 123);
        }
    }

    @Override
    public void login(LoginBean loginBean) {
        if (loginBean.getMessage().equals("登陆成功")) {
            Toast.makeText(this, loginBean.getMessage(), Toast.LENGTH_LONG).show();
            MyApplication.UserId = loginBean.getResult().getUserId();
            MyApplication.SessionId = loginBean.getResult().getSessionId();
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
        // 微信登录
        if (!WeiXinUtil.success(this)) {
            Toast.makeText(this, "请先安装应用", Toast.LENGTH_SHORT).show();
        } else {
            //  验证
            SendAuth.Req req = new SendAuth.Req();
            req.scope = "snsapi_userinfo";
            req.state = "wechat_sdk_demo_test";
            WeiXinUtil.reg(LoginActivity.this).sendReq(req);
        }
       /* UMShareAPI.get(LoginActivity.this).getPlatformInfo(LoginActivity.this, SHARE_MEDIA.WEIXIN, new UMAuthListener() {
            @Override
            public void onStart(SHARE_MEDIA share_media) {
                Toast.makeText(LoginActivity.this, "onStart", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
                Toast.makeText(LoginActivity.this, "onComplete", Toast.LENGTH_SHORT).show();

                for (String key : map.keySet()) {
                    Log.e("tag", map.get(key));
                }

            }

            @Override
            public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {

            }

            @Override
            public void onCancel(SHARE_MEDIA share_media, int i) {

            }
        });*/
    }
}

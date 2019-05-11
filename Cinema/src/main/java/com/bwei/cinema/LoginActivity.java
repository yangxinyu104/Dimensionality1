package com.bwei.cinema;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import app.MyApplication;
import bean.LoginBean;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import contract.ContractInterFace;
import presenter.MyPresenter;
import util.EncryptUtil;

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
    private ContractInterFace.IPresenter iPresenter;
    private SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
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

    @Override
    public void login(LoginBean loginBean) {
        if (loginBean.getMessage().equals("登陆成功")) {
            Toast.makeText(this, loginBean.getMessage(), Toast.LENGTH_LONG).show();
            MyApplication.UserId = loginBean.getResult().getUserId();
            MyApplication.SessionId = loginBean.getResult().getSessionId();

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
            finish();
        }else{
            Toast.makeText(this, loginBean.getMessage(), Toast.LENGTH_LONG).show();
        }

    }


    @OnClick({R.id.quickly_id, R.id.btn_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.quickly_id:
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class) ;
                startActivity(intent);
                break;
            case R.id.btn_login:
                Log.e("tag","view");
                //Toast.makeText(this, "111", Toast.LENGTH_LONG).show();
                String pwd = EncryptUtil.encrypt(pwdId.getText().toString());
                iPresenter.login(phoneId.getText().toString(),pwd);
                break;
        }
    }
}

package com.bwei.cinema;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import contract.ContractInterFace;
import presenter.MyPresenter;
import util.EncryptUtil;

public class RegisterActivity extends AppCompatActivity implements ContractInterFace.IRegister {

    @BindView(R.id.register_name)
    EditText registerName;
    @BindView(R.id.register_sex)
    EditText registerSex;
    @BindView(R.id.register_data)
    EditText registerData;
    @BindView(R.id.register_phone)
    EditText registerPhone;
    @BindView(R.id.register_emil)
    EditText registerEmil;
    @BindView(R.id.register_pwd)
    EditText registerPwd;
    @BindView(R.id.text_login)
    TextView textLogin;
    @BindView(R.id.btn_register)
    Button btnRegister;
    @BindView(R.id.register_pwd2)
    EditText registerPwd2;
    int Man = 1;
    int WoMan = 2;
    private ContractInterFace.IPresenter iPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        iPresenter = new MyPresenter<>(this);

    }

    @OnClick({R.id.text_login, R.id.btn_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.text_login:
                finish();
                break;
            case R.id.btn_register:
                String pwd = EncryptUtil.encrypt(registerPwd.getText().toString());
                String pwd2 = EncryptUtil.encrypt(registerPwd2.getText().toString());

                if (registerSex.getText().toString().equals("男")){
                    Log.e("tag","name : " + registerName.getText().toString()  + "  "+ Man);
                    iPresenter.register(registerName.getText().toString(),Man,registerData.getText().toString(),registerPhone.getText().toString(),registerEmil.getText().toString(),pwd,pwd2);
                }else if (registerSex.getText().toString().equals("女")){
                    Log.e("tag","name : " + registerName.getText().toString()  + "  "+ WoMan);
                    iPresenter.register(registerName.getText().toString(),WoMan,registerData.getText().toString(),registerPhone.getText().toString(),registerEmil.getText().toString(),pwd,pwd2);
                }else{
                    Toast.makeText(this, "性别输入错误", Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }

    @Override
    public void register(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        if (message.equals("注册成功")){
            finish();
        }
    }
}

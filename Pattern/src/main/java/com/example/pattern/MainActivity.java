package com.example.pattern;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowInsets;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.zackratos.ultimatebar.UltimateBar;

public class MainActivity extends AppCompatActivity {
    //private TextView tv;
    private Button pattern;
    // 默认是日间模式
    private int theme = R.style.AppTheme;
    private EditText nikeName;
    private EditText phone;
    private EditText pwd;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 判断是否有主题存储
        if (savedInstanceState != null) {
            theme = savedInstanceState.getInt("theme");
            setTheme(theme);
        }
        setContentView(R.layout.activity_main);
        pattern = findViewById(R.id.pattern);

        nikeName = findViewById(R.id.nikeName);
        phone = findViewById(R.id.phone);
        pwd = findViewById(R.id.pwd);
        EditText phonelogin  = findViewById(R.id.phonelogin);
        EditText pwdlogin  = findViewById(R.id.pwdlogin);
        Button register = findViewById(R.id.register);
        Button login = findViewById(R.id.login);
        UltimateBar ultimateBar = new UltimateBar(this);
        ultimateBar.setTransparentBar(Color.GRAY, 20);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this,SecondActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_in,R.anim.anim_exit2);

            }
        });

        register.setOnClickListener(new View.OnClickListener() {


            private String s;

            @Override
            public void onClick(View v) {
                try {
                    s = RsaCoder.encryptByPublicKey(pwd.getText().toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                RetrofitUtil.GetInstance().doFiledPost("techApi/user/v1/register",phone.getText().toString() ,nikeName.getText().toString(),s,new RetrofitUtil.HttpListener() {
                    @Override
                    public void Succeed(String s) {
                        Log.e("tag", "Succeed   :"+s  );

                    }

                    @Override
                    public void Error(String s) {
                        Log.e("tag", "Error   :"+s  );
                    }
                });
            }
        });









        pattern.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                theme = (theme == R.style.AppTheme) ? R.style.NightAppTheme : R.style.AppTheme;
                MainActivity.this.recreate();
            }
        });


    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("theme", theme);
    }


    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        theme = savedInstanceState.getInt("theme");
    }

}

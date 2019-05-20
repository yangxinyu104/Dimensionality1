package com.bw.movie.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.bean.OpinionBean;
import com.bw.movie.contract.ContractInterFace;
import com.bw.movie.presenter.MyPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OpinionActivity extends BaseActivity implements ContractInterFace.IOpinion {

    @BindView(R.id.opinion_EditText)
    EditText opinionEditText;
    @BindView(R.id.opinion_Button)
    Button opinionButton;
    @BindView(R.id.opinion_finish)
    ImageView opinionFinish;
    private ContractInterFace.IPresenter iPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opinion);
        ButterKnife.bind(this);
        iPresenter = new MyPresenter<>(this);

    }

    @OnClick({R.id.opinion_Button, R.id.opinion_finish})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.opinion_Button:
                iPresenter.opinion(opinionEditText.getText().toString());
                break;
            case R.id.opinion_finish:
                finish();
                break;
        }
    }

    @Override
    public void opinion(OpinionBean wechatLoginBean) {
        Toast.makeText(this, wechatLoginBean.getMessage(), Toast.LENGTH_SHORT).show();
        if (wechatLoginBean.getMessage().equals("反馈成功")){
            finish();
            Intent intent = new Intent(this,AccomplishActivity.class);
            startActivity(intent);
        }
    }
}

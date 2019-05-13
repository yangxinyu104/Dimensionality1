package com.bw.movie.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.app.MyApplication;
import com.bw.movie.bean.DetailsBean;
import com.bw.movie.bean.ParticularsBean;
import com.bw.movie.contract.ContractInterFace;
import com.bw.movie.presenter.MyPresenter;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailsActivity extends AppCompatActivity implements ContractInterFace.IDetailsFilm {


    @BindView(R.id.details_CheckBox)
    ImageView detailsCheckBox;
    @BindView(R.id.details_name)
    TextView detailsName;
    @BindView(R.id.details_SimpleDraweeView)
    SimpleDraweeView detailsSimpleDraweeView;
    @BindView(R.id.detailss_xq)
    TextView detailssXq;
    @BindView(R.id.detailss_yg)
    TextView detailssYg;
    @BindView(R.id.detailss_jz)
    TextView detailssJz;
    @BindView(R.id.detailss_yp)
    TextView detailssYp;
    @BindView(R.id.linear_id)
    LinearLayout linearId;
    @BindView(R.id.detailss_finish)
    ImageView detailssFinish;
    @BindView(R.id.detailss_gm)
    Button detailssGm;
    @BindView(R.id.details_RelativeLayout)
    RelativeLayout detailsRelativeLayout;
    @BindView(R.id.details_SimpleDraweeView_alpha)
    SimpleDraweeView detailsSimpleDraweeViewAlpha;
    @BindView(R.id.details_popwName)
    TextView detailsPopwName;
    private ContractInterFace.IPresenter iPresenter;
    private int id;
    private SimpleDraweeView simpleDraweeView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);
        Log.e("tag", "flag : " + MyApplication.flag);
        if (MyApplication.flag) {
            detailsCheckBox.setImageResource(R.mipmap.collection_selected);
        } else {
            detailsCheckBox.setImageResource(R.mipmap.collection);
        }

        Intent intent = getIntent();
        id = intent.getIntExtra("aid", 0);
        iPresenter = new MyPresenter<>(this);
        iPresenter.details(id);


    }

    @OnClick({R.id.detailss_xq, R.id.detailss_yg, R.id.detailss_jz, R.id.detailss_yp, R.id.detailss_finish, R.id.detailss_gm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.detailss_xq:
                iPresenter.particulars(id);
                View  view1 = View.inflate(this, R.layout.popw_particulars, null);
                PopupWindow popupWindow = new PopupWindow(view1,LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
                simpleDraweeView = view1.findViewById(R.id.popw_particulars_SimpleDraweeView);
                simpleDraweeView.bringToFront();
                popupWindow.setBackgroundDrawable(new BitmapDrawable());
                popupWindow.setOutsideTouchable(true);
                popupWindow.setAnimationStyle(R.style.popwin_anim_style);
                popupWindow.showAtLocation(view1,Gravity.CENTER_HORIZONTAL,0,100);
                break;
            case R.id.detailss_yg:
                break;
            case R.id.detailss_jz:
                break;
            case R.id.detailss_yp:
                break;
            case R.id.detailss_finish:
                finish();
                break;
            case R.id.detailss_gm:
                break;
        }
    }

    @Override
    public void details(DetailsBean detailsBean) {
        detailsSimpleDraweeViewAlpha.setImageURI(detailsBean.getResult().getImageUrl());
        detailsName.setText(detailsBean.getResult().getName());
        detailsSimpleDraweeView.setImageURI(detailsBean.getResult().getImageUrl());
    }

    @Override
    public void particulars(ParticularsBean particularsBean) {
        ParticularsBean.ResultBean result = particularsBean.getResult();
        Log.e("tag", " result : " + result.getImageUrl());
        simpleDraweeView.setImageURI(result.getImageUrl());
    }
}

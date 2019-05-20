package com.bw.movie.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.activity.AttentionActivity;
import com.bw.movie.activity.LoginActivity;
import com.bw.movie.activity.MineMessageActivity;
import com.bw.movie.activity.OpinionActivity;
import com.bw.movie.app.MyApplication;
import com.bw.movie.bean.SignBean;
import com.bw.movie.bean.VersionBean;
import com.bw.movie.contract.ContractInterFace;
import com.bw.movie.presenter.MyPresenter;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @Author：Y
 * @E-mail： 2447892835@qq.com
 * @Date：2019.5.11 17:26
 * @Description：YangXinYu
 */
public class MyFragment extends Fragment implements ContractInterFace.IMy {

    @BindView(R.id.mine_sound)
    ImageView mineSound;
    @BindView(R.id.mine_head)
    SimpleDraweeView mineHead;
    @BindView(R.id.mine_name)
    TextView mineName;
    @BindView(R.id.qiandao)
    Button qiandao;
    @BindView(R.id.mine_myinfo)
    ImageView mineMyinfo;
    @BindView(R.id.mine_like)
    ImageView mineLike;
    @BindView(R.id.mine_bypiao)
    ImageView mineBypiao;
    @BindView(R.id.mine_yijian)
    ImageView mineYijian;
    @BindView(R.id.mine_update)
    ImageView mineUpdate;
    @BindView(R.id.back_login)
    ImageView backLogin;
    Unbinder unbinder;
    private ContractInterFace.IPresenter iPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_mine, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mineHead.setImageURI(MyApplication.HeadPic);
        mineName.setText(MyApplication.UserName);
        iPresenter = new MyPresenter<>(this);
        qiandao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iPresenter.signin();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.mine_myinfo, R.id.mine_like, R.id.mine_bypiao, R.id.mine_yijian, R.id.mine_update, R.id.back_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mine_myinfo:
                Intent intent = new Intent(getActivity(),MineMessageActivity.class);
                startActivity(intent);
                break;
            case R.id.mine_like:
                Intent intent3 = new Intent(getActivity(),AttentionActivity.class);
                startActivity(intent3);
                break;
            case R.id.mine_bypiao:
                break;
            case R.id.mine_yijian:
                Intent intent1 = new Intent(getActivity(),OpinionActivity.class);
                startActivity(intent1);
                break;
            case R.id.mine_update:
                long appVersionCode = MyApplication.getAppVersionCode(getContext());
                Log.e("tag","versionCode  :"  + appVersionCode);
                iPresenter.version(appVersionCode+"");
                break;
            case R.id.back_login:
                getActivity().finish();
                MyApplication.UserId = 0;
                MyApplication.SessionId = null;
                Intent intent2 = new Intent(getActivity(),LoginActivity.class);
                startActivity(intent2);
                break;
        }
    }
    @Override
    public void onResume() {
        super.onResume();
        mineHead.setImageURI(MyApplication.HeadPic);
        mineName.setText(MyApplication.UserName);
    }

    @Override
    public void signin(SignBean wechatLoginBean) {
        Toast.makeText(getActivity(), wechatLoginBean.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void version(VersionBean wechatLoginBean) {
        Toast.makeText(getContext(), wechatLoginBean.getFlag()+"", Toast.LENGTH_SHORT).show();
        if (wechatLoginBean.getFlag()==2){
            Toast.makeText(getContext(), "当前版本为  ：  "+MyApplication.getAppVersionName(getContext())+"   已是最新版本", Toast.LENGTH_SHORT).show();
        }else if (wechatLoginBean.getFlag()==1){
            AlertDialog.Builder builder =  new AlertDialog.Builder(getContext());
            builder.setTitle("提示:");
            builder.setMessage("当前不是最新版本,是否更新当前的版本");
            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            builder.show();

        }
    }
}

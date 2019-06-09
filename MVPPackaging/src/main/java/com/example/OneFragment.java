package com.example;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.base.BaseFragment;
import com.example.mvp.Main2Activity;
import com.example.mvp.R;

/**
 * @Author：Y
 * @E-mail： 2447892835@qq.com
 * @Date：2019.6.8 19:29
 * @Description：YangXinYu
 */
public class OneFragment extends BaseFragment {

    private TextView viewById;


    @Override
    protected int getLayout() {
        return R.layout.fragment_one;
    }

    @Override
    protected void initView(View view) {
        viewById = view.findViewById(R.id.aaaa);
        viewById.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.aaaa:
                viewById.setText("00000000000000000");
                Bundle bundle = new Bundle();
                bundle.putString("111","11111");
                toClass(getContext(),Main2Activity.class,bundle);
                break;

        }

    }
}

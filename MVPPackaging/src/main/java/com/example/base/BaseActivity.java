package com.example.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * @Author：Y
 * @E-mail： 2447892835@qq.com
 * @Date：2019.6.8 19:20
 * @Description：YangXinYu
 */
public abstract class BaseActivity<V, T extends BasePresenter<V>> extends AppCompatActivity implements View.OnClickListener {
    public String TAG = getClass().getSimpleName() + "";

    protected T mPresenter;

    public Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());

        findView();

        initData();

        setListener();

        mContext = BaseActivity.this;

        //创建presenter
        mPresenter = createPresenter();

    }
    //获取布局的id
    protected  abstract int getLayout();
    //查找页面上的组件
    protected  abstract void findView();
    //初始化数据
    protected  abstract void initData();
    //设置监听
    protected  abstract void  setListener();

    /**
     * 创建Presenter 对象
     *
     * @return
     */
    protected abstract T createPresenter();

    protected abstract void getData();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != mPresenter) {
            mPresenter.detachView();
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        if (null != mPresenter) {
            mPresenter.attachView((V) this);
        }
    }



    /**
     * Intent跳转
     * @param context
     * @param clazz
     */
    protected void toClass(Context context, Class<? extends BaseActivity> clazz){
        toClass(context,clazz,null);
    }

    /**
     * Intent带值跳转
     * @param context
     * @param clazz
     * @param bundle
     */
    protected void toClass(Context context, Class<? extends BaseActivity> clazz, Bundle bundle){
        Intent intent = new Intent(context,clazz);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    /**
     * 带返回值的跳转
     * @param context
     * @param clazz
     * @param bundle
     * @param reuqestCode
     */
    protected void toClass(Context context,Class<? extends BaseActivity> clazz,Bundle bundle,int reuqestCode){
        Intent intent = new Intent(context,clazz);
        intent.putExtras(bundle);
        startActivityForResult(intent,reuqestCode);
    }



}

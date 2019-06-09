package com.example.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @Author：Y
 * @E-mail： 2447892835@qq.com
 * @Date：2019.6.8 19:26
 * @Description：YangXinYu
 */
public abstract class BaseFragment<V, T extends BasePresenter<V>> extends Fragment implements View.OnClickListener {

    protected Context mContext;
    private View view;
    protected T mPresenter;
    /**
     * 当fragment与activity发生关联时调用
     * @param context  与之相关联的activity
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
        mPresenter = createPresenter();//创建presenter
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(getLayout(),null);
        return view;
    }

    /**
     * 绑定布局
     * @return
     */
    protected abstract int getLayout();

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    /**
     * 初始化组件
     * @param view
     */
    protected abstract void initView(View view);

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    /**
     * 设置数据等逻辑代码
     */
    protected abstract void initData();


    /**
     * 创建Presenter对象
     */
    protected abstract T createPresenter();

    @Override
    public void onResume() {
        super.onResume();
        if (null != mPresenter) {
            mPresenter.attachView((V) this);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (null != mPresenter) {
            mPresenter.detachView();
        }
    }




    /**
     * intent跳转
     * @param context
     * @param clazz
     */
    protected void toClass(Context context,Class<? extends BaseActivity> clazz){
        toClass(context,clazz,null);
    }

    /**
     * intent带值跳转
     * @param context
     * @param clazz
     * @param bundle
     */
    protected void toClass(Context context,Class<? extends BaseActivity> clazz,Bundle bundle){
        Intent intent = new Intent(context,clazz);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    /**
     * 带返回值的跳转
     * @param context
     * @param clazz
     * @param bundle
     * @param requestCode
     */
    protected void toClass(Context context, Class<? extends BaseActivity> clazz, Bundle bundle, int requestCode){
        Intent intent = new Intent(context,clazz);
        intent.putExtras(bundle);
        getActivity().startActivityForResult(intent,requestCode);
    }

}

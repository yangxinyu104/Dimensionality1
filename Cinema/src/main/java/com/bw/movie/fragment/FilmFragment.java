package com.bw.movie.fragment;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.bw.movie.R;
import com.bw.movie.activity.SearchActivity;
import com.bw.movie.adapter.BeonRecyclerViewAdapter;
import com.bw.movie.adapter.FilmRecyclerAdapter;
import com.bw.movie.adapter.HotRecyclerViewAdapter;
import com.bw.movie.adapter.ShowingRecyclerViewAdapter;
import com.bw.movie.app.MyApplication;
import com.bw.movie.app.MyViews;
import com.bw.movie.bean.BeonBean;
import com.bw.movie.bean.PopularMovieBean;
import com.bw.movie.bean.ShowingBean;
import com.bw.movie.contract.ContractInterFace;
import com.bw.movie.listener.MyLocationListener;
import com.bw.movie.presenter.MyPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import me.weyye.hipermission.HiPermission;
import me.weyye.hipermission.PermissionCallback;
import me.weyye.hipermission.PermissionItem;
import recycler.coverflow.RecyclerCoverFlow;

/**
 * @Author：Y
 * @E-mail： 2447892835@qq.com
 * @Date：2019.5.11 17:26
 * @Description：YangXinYu
 */
public class FilmFragment extends Fragment implements ContractInterFace.IFilmHome {

    Unbinder unbinder;
    @BindView(R.id.film_RecyclerCoverFlow)
    RecyclerCoverFlow filmRecyclerCoverFlow;
    @BindView(R.id.view_crnema)
    MyViews viewCrnema;
    List<BeonBean.ResultBean> Beonlist = new ArrayList<>();
    List<ShowingBean.ResultBean> Showinglist = new ArrayList<>();
    List<PopularMovieBean.ResultBean> Hotlist = new ArrayList<>();
    @BindView(R.id.film_hot_RelativeLayout)
    RelativeLayout filmHotRelativeLayout;
    @BindView(R.id.film_hot_RecyclerView)
    RecyclerView filmHotRecyclerView;
    @BindView(R.id.film_showing_RelativeLayout)
    RelativeLayout filmShowingRelativeLayout;
    @BindView(R.id.film_showing_RecyclerView)
    RecyclerView filmShowingRecyclerView;
    @BindView(R.id.film_beon_RelativeLayout)
    RelativeLayout filmBeonRelativeLayout;
    @BindView(R.id.film_beon_RecyclerView)
    RecyclerView filmBeonRecyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_film, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        requestRunPermissions();
        ContractInterFace.IPresenter iPresenter = new MyPresenter<>(this);
        iPresenter.popularMovie();
        iPresenter.showing();
        iPresenter.beon();
        viewCrnema.bringToFront();
        initLocationOption();
        initLocationOptions();
        viewCrnema.locations.setText(MyApplication.City);

    }

    private void initLocationOptions() {
//定位服务的客户端。宿主程序在客户端声明此类，并调用，目前只支持在主线程中启动
        LocationClient locationClient = new LocationClient(getContext());
//声明LocationClient类实例并配置定位参数
        LocationClientOption locationOption = new LocationClientOption();
        MyLocationListener myLocationListener = new MyLocationListener();
//注册监听函数
        locationClient.registerLocationListener(myLocationListener);
//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        locationOption.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
//可选，默认gcj02，设置返回的定位结果坐标系，如果配合百度地图使用，建议设置为bd09ll;
        locationOption.setCoorType("gcj02");
//可选，默认0，即仅定位一次，设置发起连续定位请求的间隔需要大于等于1000ms才是有效的
        locationOption.setScanSpan(1000);
//可选，设置是否需要地址信息，默认不需要
        locationOption.setIsNeedAddress(true);
//可选，设置是否需要地址描述
        locationOption.setIsNeedLocationDescribe(true);
//可选，设置是否需要设备方向结果
        locationOption.setNeedDeviceDirect(false);
//可选，默认false，设置是否当gps有效时按照1S1次频率输出GPS结果
        locationOption.setLocationNotify(true);
//可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        locationOption.setIgnoreKillProcess(true);
//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        locationOption.setIsNeedLocationDescribe(true);
//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        locationOption.setIsNeedLocationPoiList(true);
//可选，默认false，设置是否收集CRASH信息，默认收集
        locationOption.SetIgnoreCacheException(false);
//可选，默认false，设置是否开启Gps定位
        locationOption.setOpenGps(true);
//可选，默认false，设置定位时是否需要海拔信息，默认不需要，除基础定位版本都可用
        locationOption.setIsNeedAltitude(false);
//设置打开自动回调位置模式，该开关打开后，期间只要定位SDK检测到位置变化就会主动回调给开发者，该模式下开发者无需再关心定位间隔是多少，定位SDK本身发现位置变化就会及时回调给开发者
        locationOption.setOpenAutoNotifyMode();
//设置打开自动回调位置模式，该开关打开后，期间只要定位SDK检测到位置变化就会主动回调给开发者
        locationOption.setOpenAutoNotifyMode(3000,1, LocationClientOption.LOC_SENSITIVITY_HIGHT);
//需将配置好的LocationClientOption对象，通过setLocOption方法传递给LocationClient对象使用
        locationClient.setLocOption(locationOption);
//开始定位
        locationClient.start();
        Log.e("tag","lainxi");
    }

    private void initLocationOption() {
//定位服务的客户端。宿主程序在客户端声明此类，并调用，目前只支持在主线程中启动
        LocationClient locationClient = new LocationClient(getContext());
//声明LocationClient类实例并配置定位参数
        LocationClientOption locationOption = new LocationClientOption();
        MyLocationListener myLocationListener = new MyLocationListener();
//注册监听函数
        locationClient.registerLocationListener(myLocationListener);
//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        locationOption.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
//可选，默认gcj02，设置返回的定位结果坐标系，如果配合百度地图使用，建议设置为bd09ll;
        locationOption.setCoorType("gcj02");
//可选，默认0，即仅定位一次，设置发起连续定位请求的间隔需要大于等于1000ms才是有效的
        locationOption.setScanSpan(1000);
//可选，设置是否需要地址信息，默认不需要
        locationOption.setIsNeedAddress(true);
//可选，设置是否需要地址描述
        locationOption.setIsNeedLocationDescribe(true);
//可选，设置是否需要设备方向结果
        locationOption.setNeedDeviceDirect(false);
//可选，默认false，设置是否当gps有效时按照1S1次频率输出GPS结果
        locationOption.setLocationNotify(true);
//可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        locationOption.setIgnoreKillProcess(true);
//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        locationOption.setIsNeedLocationDescribe(true);
//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        locationOption.setIsNeedLocationPoiList(true);
//可选，默认false，设置是否收集CRASH信息，默认收集
        locationOption.SetIgnoreCacheException(false);
//可选，默认false，设置是否开启Gps定位
        locationOption.setOpenGps(true);
//可选，默认false，设置定位时是否需要海拔信息，默认不需要，除基础定位版本都可用
        locationOption.setIsNeedAltitude(false);
//设置打开自动回调位置模式，该开关打开后，期间只要定位SDK检测到位置变化就会主动回调给开发者，该模式下开发者无需再关心定位间隔是多少，定位SDK本身发现位置变化就会及时回调给开发者
        locationOption.setOpenAutoNotifyMode();
//设置打开自动回调位置模式，该开关打开后，期间只要定位SDK检测到位置变化就会主动回调给开发者
        locationOption.setOpenAutoNotifyMode(3000,1, LocationClientOption.LOC_SENSITIVITY_HIGHT);
//需将配置好的LocationClientOption对象，通过setLocOption方法传递给LocationClient对象使用
        locationClient.setLocOption(locationOption);
//开始定位
        locationClient.start();
        Log.e("tag","ding");
    }

    private void requestRunPermissions() {
        List<PermissionItem> permisson = new ArrayList<>();
        permisson.add(new PermissionItem(Manifest.permission.READ_PHONE_STATE, "电话状态", R.drawable.permission_ic_phone));
        permisson.add(new PermissionItem(Manifest.permission.ACCESS_COARSE_LOCATION, "地理位置", R.drawable.permission_ic_location));
        permisson.add(new PermissionItem(Manifest.permission.ACCESS_FINE_LOCATION, "地理位置", R.drawable.permission_ic_location));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            permisson.add(new PermissionItem(Manifest.permission.READ_EXTERNAL_STORAGE, "读取权限", R.drawable.permission_ic_storage));
            permisson.add(new PermissionItem(Manifest.permission.WRITE_EXTERNAL_STORAGE, "写入权限", R.drawable.permission_ic_storage));
        }
        HiPermission.create(getActivity())
                .title("开启地图权限")
                .permissions(permisson)
                .msg("我们需要获得以下权限才能为您提供服务")
                .checkMutiPermission(new PermissionCallback() {
                    @Override
                    public void onClose() {

                    }

                    @Override
                    public void onFinish() {
//                        showToastShort("所有权限申请完成");
                    }

                    @Override
                    public void onDeny(String permission, int position) {

                    }

                    @Override
                    public void onGuarantee(String permission, int position) {

                    }
                });
    }


    private void BeonFilm(List<BeonBean.ResultBean> result) {
        RecyclerView.LayoutManager BeonLayoutManager = new LinearLayoutManager(getContext());
        ((LinearLayoutManager) BeonLayoutManager).setOrientation(OrientationHelper.HORIZONTAL);
        filmBeonRecyclerView.setLayoutManager(BeonLayoutManager);
        BeonRecyclerViewAdapter adapter = new BeonRecyclerViewAdapter(result, getContext());
        filmBeonRecyclerView.setAdapter(adapter);
        adapter.setOnItemClick(new BeonRecyclerViewAdapter.OnItemClick() {
            @Override
            public void onItemClick(int position) {
                StartActivity();
            }
        });
    }

    private void ShowingFilm(List<ShowingBean.ResultBean> result) {
        RecyclerView.LayoutManager ShowingLayoutManager = new LinearLayoutManager(getContext());
        ((LinearLayoutManager) ShowingLayoutManager).setOrientation(OrientationHelper.HORIZONTAL);
        filmShowingRecyclerView.setLayoutManager(ShowingLayoutManager);
        ShowingRecyclerViewAdapter adapter = new ShowingRecyclerViewAdapter(result, getContext());
        filmShowingRecyclerView.setAdapter(adapter);
        adapter.setOnItemClick(new ShowingRecyclerViewAdapter.OnItemClick() {
            @Override
            public void onItemClick(int position) {
                StartActivity();
            }
        });
    }

    private void HotFilm(List<PopularMovieBean.ResultBean> result) {
        RecyclerView.LayoutManager HotLayoutManager = new LinearLayoutManager(getContext());
        ((LinearLayoutManager) HotLayoutManager).setOrientation(OrientationHelper.HORIZONTAL);
        filmHotRecyclerView.setLayoutManager(HotLayoutManager);
        HotRecyclerViewAdapter adapter = new HotRecyclerViewAdapter(result, getContext());
        filmHotRecyclerView.setAdapter(adapter);
        adapter.setOnItemClick(new HotRecyclerViewAdapter.OnItemClick() {
            @Override
            public void onItemClick(int position) {
                StartActivity();
            }
        });
    }

    private void Image(List<BeonBean.ResultBean> result) {
        FilmRecyclerAdapter adapter = new FilmRecyclerAdapter(this, result);
        filmRecyclerCoverFlow.setAdapter(adapter);
        //让轮播图显示中间的图片
        filmRecyclerCoverFlow.smoothScrollToPosition(2);
        //自定义接口回调，点击图片使它展示到中间
        adapter.setOnItemClick(new FilmRecyclerAdapter.OnItemClick() {
            @Override
            public void onItemClick(int position) {
                filmRecyclerCoverFlow.smoothScrollToPosition(position);
                StartActivity();
            }
        });
    }

    private void StartActivity() {
        Intent intent = new Intent(getActivity(),SearchActivity.class);
        getActivity().startActivity(intent);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void popularMovie(PopularMovieBean popularMovieBean) {
        Hotlist.clear();
        List<PopularMovieBean.ResultBean> result = popularMovieBean.getResult();
        Hotlist.addAll(result);
        HotFilm(popularMovieBean.getResult());
    }

    @Override
    public void showing(ShowingBean showingBean) {
        Showinglist.clear();
        List<ShowingBean.ResultBean> result = showingBean.getResult();
        Showinglist.addAll(result);
        ShowingFilm(showingBean.getResult());
    }

    @Override
    public void beon(BeonBean beonBean) {
        Beonlist.clear();
        List<BeonBean.ResultBean> result = beonBean.getResult();
        Beonlist.addAll(result);
        Image(beonBean.getResult());
        BeonFilm(beonBean.getResult());
    }

    @OnClick({R.id.film_hot_RelativeLayout, R.id.film_showing_RelativeLayout, R.id.film_beon_RelativeLayout})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.film_hot_RelativeLayout:
                StartActivity();
                break;
            case R.id.film_showing_RelativeLayout:
                StartActivity();
                break;
            case R.id.film_beon_RelativeLayout:
                StartActivity();
                break;
        }
    }
    }

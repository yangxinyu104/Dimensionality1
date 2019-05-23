package com.bw.movie.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import com.bw.movie.R;
import com.bw.movie.activity.BuyTicketActivity;
import com.bw.movie.adapter.CinemaAdapterfj;
import com.bw.movie.app.MyApplication;
import com.bw.movie.app.MyViews;
import com.bw.movie.bean.CinemaBannerBean;
import com.bw.movie.bean.CinemafjBean;
import com.bw.movie.bean.CinemaplBean;
import com.bw.movie.bean.CinematjBean;
import com.bw.movie.bean.CinemaxqBean;
import com.bw.movie.contract.ContractInterFace;
import com.bw.movie.ghb.CinemaPresenter;
import com.bw.movie.ghb.IMainView;
import com.bw.movie.presenter.MyPresenter;

import java.util.ArrayList;
import java.util.List;

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
public class CinemaFragment extends Fragment  implements ContractInterFace.IFilm {
    private LinearLayoutManager managerfj;
    private int page=1;
    private int count=10;
    private ContractInterFace.IPresenter cinemaPresenter;
    private CinemaAdapterfj adapterfj;
    @BindView(R.id.view_crnema)
    MyViews viewCrnema;
    Unbinder unbinder;
    @BindView(R.id.movie_recommended)
    RadioButton movieRecommended;
    @BindView(R.id.movie_nearby)
    RadioButton movieNearby;
    @BindView(R.id.RecyclerViews)
    RecyclerView RecyclerViews;
    List<CinemafjBean.ResultBean> fjlist = new ArrayList<>();
    List<CinematjBean.ResultBean> tjlist = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_cinema, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        viewCrnema.locations.setText(MyApplication.City);
        cinemaPresenter = new MyPresenter<>(this);
        cinemaPresenter.recommend(page,count);

        managerfj = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        RecyclerViews.setLayoutManager(managerfj);

        //创建适配器
        adapterfj = new CinemaAdapterfj(fjlist,getContext());

        //设置适配器
        RecyclerViews.setAdapter(adapterfj);

        adapterfj.setOnItemClickListener(new CinemaAdapterfj.OnItemClickListener() {
            @Override
            public void onItemClick(int id, String image, String name, String address) {
                Intent intent = new Intent(getActivity(),BuyTicketActivity.class);
                intent.putExtra("id",id);
                intent.putExtra("logo",image);
                intent.putExtra("name",name);
                intent.putExtra("address",address);
                startActivity(intent);
            }
        });
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.movie_recommended, R.id.movie_nearby})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.movie_recommended:
                cinemaPresenter.recommend(page,count);
                break;
            case R.id.movie_nearby:
                cinemaPresenter.nearby(page,count);
                break;
        }
    }


        @Override
    public void recommend(CinemafjBean wechatLoginBean) {
        fjlist.clear();
        List<CinemafjBean.ResultBean> result = wechatLoginBean.getResult();
        fjlist.addAll(result);
        adapterfj.notifyDataSetChanged();


    }

    @Override
    public void nearby(CinemafjBean wechatLoginBean) {
        fjlist.clear();
        List<CinemafjBean.ResultBean> result = wechatLoginBean.getResult();
        fjlist.addAll(result);
        adapterfj.notifyDataSetChanged();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        cinemaPresenter.Desetory();
        cinemaPresenter =null;
    }
}

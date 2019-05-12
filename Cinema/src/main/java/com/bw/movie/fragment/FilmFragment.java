package com.bw.movie.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.bw.movie.R;
import com.bw.movie.adapter.FilmRecyclerAdapter;
import com.bw.movie.bean.PopularMovieBean;
import com.bw.movie.contract.ContractInterFace;
import com.bw.movie.presenter.MyPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import recycler.coverflow.RecyclerCoverFlow;

/**
 * @Author：Y
 * @E-mail： 2447892835@qq.com
 * @Date：2019.5.11 17:26
 * @Description：YangXinYu
 */
public class FilmFragment extends Fragment implements ContractInterFace.IFilmHome {

    @BindView(R.id.film_LinearLayout)
    RelativeLayout filmLinearLayout;
    Unbinder unbinder;
    @BindView(R.id.film_RecyclerCoverFlow)
    RecyclerCoverFlow filmRecyclerCoverFlow;

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
        filmLinearLayout.bringToFront();
        ContractInterFace.IPresenter iPresenter = new MyPresenter<>(this);
        iPresenter.popularMovie();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void popularMovie(PopularMovieBean popularMovieBean) {
        Log.e("tag", "popularMovieBean" + popularMovieBean.getResult().size() + "");

        FilmRecyclerAdapter adapter = new FilmRecyclerAdapter(this, popularMovieBean.getResult());
        filmRecyclerCoverFlow.setAdapter(adapter);
        //让轮播图显示中间的图片
        filmRecyclerCoverFlow.smoothScrollToPosition(popularMovieBean.getResult().size()/2);
        //自定义接口回调，点击图片使它展示到中间
        adapter.setOnItemClick(new FilmRecyclerAdapter.OnItemClick() {
            @Override
            public void onItemClick( int position) {
                filmRecyclerCoverFlow.smoothScrollToPosition(position);
            }
        });


    }


}
